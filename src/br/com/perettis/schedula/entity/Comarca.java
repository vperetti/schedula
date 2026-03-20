package br.com.perettis.schedula.entity;

import java.io.Serializable;

public class Comarca implements Serializable {

	private Integer id;
	private String nome;
	private boolean desabilitado = false;

	public Comarca() {
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
		if (!(outro instanceof Comarca)) {
			return false;
		}
		final Comarca aquele = (Comarca) outro;
		return (this.id == aquele.getId());
	}

	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
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

