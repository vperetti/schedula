package br.com.perettis.schedula.entity;

import java.io.Serializable;

public class Vara implements Serializable {

	private Integer id;
	private String nome;
	private Comarca comarca;
	private boolean desabilitado = false;

	public Vara() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer val) {
		this.id = val;
	}

	@Override
	public String toString() {
		return "entidade: " + this.getClass().toString() + " id:" + String.valueOf(this.id);
	}

	@Override
	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == 0) {
			return false;
		}
		if (!(outro instanceof Vara)) {
			return false;
		}
		final Vara aquele = (Vara) outro;
		return (this.id == aquele.getId());
	}

	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	public Comarca getComarca() {
		return comarca;
	}

	public void setComarca(Comarca comarca) {
		this.comarca = comarca;
	}

	public boolean isDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(boolean desabilitado) {
		this.desabilitado = desabilitado;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

