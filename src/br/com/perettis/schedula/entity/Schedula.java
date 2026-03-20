package br.com.perettis.schedula.entity;

import java.io.Serializable;
import java.util.Date;

public class Schedula implements Serializable {

	private Integer id;
	private String nomeEmpresa;
	private Integer versao;
	private Date dataInstalacao;

	public Schedula() {
	}

	public Date getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(Date val) {
		this.dataInstalacao = val;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String val) {
		this.nomeEmpresa = val;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer val) {
		this.versao = val;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		if (!(outro instanceof Schedula)) {
			return false;
		}
		final Schedula aquele = (Schedula) outro;
		return (this.id == aquele.getId());
	}

	public int hashCode() {
		return id == 0 ? System.identityHashCode(this) : id;
	}
}

