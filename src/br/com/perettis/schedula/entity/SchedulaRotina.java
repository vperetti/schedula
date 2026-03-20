package br.com.perettis.schedula.entity;

import java.io.Serializable;

public class SchedulaRotina implements Serializable {

	private Long id;
	private String nome;
	private String descricao;

	public SchedulaRotina() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String val) {
		this.descricao = val;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long val) {
		this.id = val;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String val) {
		this.nome = val;
	}

    @Override
	public String toString() {
		return "["+getNome()+"] "+getDescricao();
	}

    @Override
	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == null) {
			return false;
		}
		if (!(outro instanceof SchedulaRotina)) {
			return false;
		}
		final SchedulaRotina aquele = (SchedulaRotina) outro;
		return this.id.equals(aquele.getId());
	}

    @Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}
}

