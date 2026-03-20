package br.com.perettis.schedula.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Usuario implements Serializable {

	private Long id;
	private Long version;
	private String apelido;
	private String nome;
	private String senha;
	private String email;
	private boolean administrador;
	private boolean desabilitado = false;
    private Set<UsuarioDireito> direitos;

	public Usuario() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String val) {
		this.email = val;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String val) {
		this.senha = val;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public String toString() {
		return "entidade: " + this.getClass().toString() + " id:" + String.valueOf(this.id);
	}

	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == null) {
			return false;
		}
		if (!(outro instanceof Usuario)) {
			return false;
		}
		final Usuario aquele = (Usuario) outro;
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

    public Set<UsuarioDireito> getDireitos() {
        return direitos;
    }

    public void setDireitos(Set<UsuarioDireito> direitos) {
        if (this.direitos == null) {
            this.direitos = direitos;
        } else {
            this.direitos.clear();
            this.direitos.addAll(direitos);
        }
    }
}

