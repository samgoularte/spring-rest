package br.com.localhost.resource.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.localhost.domain.Cursos;
import br.com.localhost.service.CursosService;

@RestController
@RequestMapping(value = "/cursos", 
				produces = MediaType.APPLICATION_JSON_VALUE, 
				consumes = MediaType.APPLICATION_JSON_VALUE)
public class CursosRestController {

	@Autowired
	private CursosService service;

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Cursos curso) {
		service.save(curso);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(curso.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cursos editar(@PathVariable("id") Long id, @RequestBody Cursos curso) {
		service.update(id, curso);
		return curso;
	}

	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cursos editarDataInicio(@PathVariable("id") Long id, @RequestBody Cursos curso) {
		service.updateDataInicial(id, curso.getDataInicio());
		return curso;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable("id") Long id) {
		service.delete(id);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cursos getCurso(@PathVariable("id") Long id) {
		return service.findById(id);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cursos> listar(@RequestParam(name = "fields", required = false, defaultValue = "") String fields) {
		return fields.equals("videoaulas") 
						? service.findAll() 
						: service.findAllWithoutVideoaulas();
	}
}
