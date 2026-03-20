package br.com.perettis.schedula.entity;

import java.io.Serializable;


public class Uf implements Serializable {

    private Integer id;

    private String sigla;

    private String nome;

    public Uf () {
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer val) {
        this.id = val;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String val) {
        this.nome = val;
    }

    public String getSigla () {
        return sigla;
    }

    public void setSigla (String val) {
        this.sigla = val;
    }
	public String toString() {
		return getSigla()+" - "+getNome();
	}

	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == null) {
			return false;
		}
		if (!(outro instanceof Uf)) {
			return false;
		}
		final Uf aquele = (Uf) outro;
		return this.id.equals(aquele.getId());
	}

	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}
}

