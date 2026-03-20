package br.com.perettis.schedula.entity;

import java.io.Serializable;

public class AcompanhamentoTipo implements Serializable {

	private int id;
	private String descricao;

	public AcompanhamentoTipo() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String val) {
		this.descricao = val;
	}

	public int getId() {
		return id;
	}

	public void setId(int val) {
		this.id = val;
	}

	public String toString() {
		return getDescricao();
	}

	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == 0) {
			return false;
		}
		if (!(outro instanceof AcompanhamentoTipo)) {
			return false;
		}
		final AcompanhamentoTipo aquele = (AcompanhamentoTipo) outro;
		return (this.id == aquele.getId());
	}

	public int hashCode() {
		return id == 0 ? System.identityHashCode(this) : id;
	}
}

