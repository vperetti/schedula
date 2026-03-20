package br.com.perettis.schedula.entity;

import java.io.Serializable;

public class TipoDeAcompanhamento implements Serializable {


	/*
	A = Audiência,
	
	P = Providência,
	
	C = Acordo;
	 */
	private Integer id;
	private String descricao;
	private boolean desabilitado = false;

	TipoDeAcompanhamento() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String val) {
		this.descricao = val;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer val) {
		this.id = val;
	}

	public String toString() {
		return "entidade: " + this.getClass().toString() + " id:" + String.valueOf(this.id);
	}

	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == 0) {
			return false;
		}
		if (!(outro instanceof TipoDeAcompanhamento)) {
			return false;
		}
		final TipoDeAcompanhamento aquele = (TipoDeAcompanhamento) outro;
		return (this.id == aquele.getId());
	}

	public int hashCode() {
		return id == 0 ? System.identityHashCode(this) : id;
	}

	public boolean isDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(boolean desabilitado) {
		this.desabilitado = desabilitado;
	}
}

