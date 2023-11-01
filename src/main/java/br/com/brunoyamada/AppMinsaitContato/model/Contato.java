package br.com.brunoyamada.AppMinsaitContato.model;

import java.util.Objects;

public class Contato {

	private Long id;
	private Integer tipoContato;
	private String contato;
	private Pessoa pessoaId;
	
	public Contato() {
	}

	public Contato(Long id, Integer tipoContato, String contato, Pessoa pessoaId) {
		this.id = id;
		this.tipoContato = tipoContato;
		this.contato = contato;
		this.pessoaId = pessoaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(Integer tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Pessoa getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Pessoa pessoaId) {
		this.pessoaId = pessoaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(id, other.id);
	}

	
}
