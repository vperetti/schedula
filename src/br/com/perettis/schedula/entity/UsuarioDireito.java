package br.com.perettis.schedula.entity;

import java.io.Serializable;

public class UsuarioDireito implements Serializable {

	private Long id;
	private Usuario usuario;
	private SchedulaRotina rotina;

	public UsuarioDireito() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long val) {
		this.id = val;
	}

	public SchedulaRotina getRotina() {
		return rotina;
	}

	public void setRotina(SchedulaRotina val) {
		this.rotina = val;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario val) {
		this.usuario = val;
	}

    @Override
	public String toString() {
		return getRotina().toString();
	}

    @Override
	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == null) {
			return false;
		}
		if (!(outro instanceof UsuarioDireito)) {
			return false;
		}
		final UsuarioDireito aquele = (UsuarioDireito) outro;
		return this.id.equals(aquele.getId());
	}

    @Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}
}

