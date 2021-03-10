package br.com.localhost.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Videoaulas")
@NamedQueries({
	@NamedQuery(name = "Videoaulas.findAll", query = "SELECT v FROM Videoaulas v"),
	@NamedQuery(name = "Videoaulas.findByCurso", query = "SELECT v FROM Videoaulas v WHERE v.curso.id = :idCurso"),
	@NamedQuery(name = "Videoaulas.findWithoutCurso", query = "SELECT new Videoaulas(v.id, v.titulo, v.descricao, v.numero) FROM Videoaulas v WHERE v.curso.id = :idCurso"),
	@NamedQuery(name = "Videoaulas.findByIdVideoaulasAndIdCurso", query = "SELECT v FROM Videoaulas v WHERE v.id = :idVideoaula AND v.curso.id = :idCurso")
})
public class Videoaulas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 60)
	private String titulo;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Integer numero;
	
	@ManyToOne
	@JoinColumn(name = "id_curso_fk")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties({ "videoaulas", "id" })
	private Cursos curso;

	public Videoaulas() {
		super();
	}

	public Videoaulas(Long id, String titulo, String descricao, Integer numero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.numero = numero;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Cursos getCurso() {
		return curso;
	}

	public void setCurso(Cursos curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videoaulas other = (Videoaulas) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Videoaula [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", numero=" + numero
				+ ", curso=" + curso + "]";
	}

}
