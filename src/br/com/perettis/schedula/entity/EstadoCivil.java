package br.com.perettis.schedula.entity;

import java.io.Serializable;

public class EstadoCivil implements Serializable {

	private Integer id;
    private String nome;
	private String descricao;
	private boolean desabilitado = false;

	public EstadoCivil() {
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
		//return "entidade: " + this.getClass().toString() + " id:" + String.valueOf(this.id);
        return getNome();
	}

	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == null) {
			return false;
		}
		if (!(outro instanceof EstadoCivil)) {
			return false;
		}
		final EstadoCivil aquele = (EstadoCivil) outro;
		return this.id.equals(aquele.getId());
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

