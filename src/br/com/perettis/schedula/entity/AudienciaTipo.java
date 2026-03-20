package br.com.perettis.schedula.entity;

import java.io.Serializable;

public class AudienciaTipo implements Serializable {

	private Integer id;
	private String descricao;
	private boolean desabilitado = false;

	public AudienciaTipo() {
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

    @Override
	public String toString() {
		return getDescricao();
	}

    @Override
	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == 0) {
			return false;
		}
		if (!(outro instanceof AudienciaTipo)) {
			return false;
		}
		final AudienciaTipo aquele = (AudienciaTipo) outro;
		return (this.id == aquele.getId());
	}

    @Override
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

