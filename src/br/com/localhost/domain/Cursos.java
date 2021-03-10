package br.com.localhost.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "cursos", indexes = {
		@Index(columnList = "titulo, data_inicio", 
				unique = true, 
				name = "unique_titulo_dataInicio") 
		})
@NamedQueries({ 
	@NamedQuery(name = "Cursos.findAll", query = "SELECT c FROM Cursos c"),
	@NamedQuery(name = "Cursos.findAllWithoutVideoaulas", query = "SELECT new Cursos(c.id, c.titulo, c.cargaHoraria, c.dataInicio) FROM Cursos c")
	})
public class Cursos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(name = "carga_horaria", nullable = false)
	@Enumerated(EnumType.STRING)
	private CargaHoraria cargaHoraria;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({"curso"})
	private List<Videoaulas> videoaulas;

	public Cursos() {
		super();
	}
	
	public Cursos(Long id, String titulo, CargaHoraria cargaHoraria, Date dataInicio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.cargaHoraria = cargaHoraria;
		this.dataInicio = dataInicio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public CargaHoraria getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(CargaHoraria cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public List<Videoaulas> getVideoaulas() {
		return videoaulas;
	}

	public void setVideoaulas(List<Videoaulas> videoaulas) {
		this.videoaulas = videoaulas;
	}
	
	public void addVideoaula(Videoaulas videoaula) {
		if(this.videoaulas == null) {
			this.videoaulas = new ArrayList<>();
		}
		videoaula.setCurso(this);
		this.videoaulas.add(videoaula);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Cursos curso = (Cursos) o;

		return id != null ? id.equals(curso.id) : curso.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Curso{" + "id=" + id + ", titulo='" + titulo + '\'' + '}';
	}
}
