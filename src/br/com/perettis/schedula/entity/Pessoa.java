package br.com.perettis.schedula.entity;

import br.com.perettis.gets.text.Filters;
import java.io.Serializable;

public class Pessoa implements Serializable {

	private Long id;
	private Long version;
	private String nome;
	private String endereco;
	private String bairro;
	private String cep;
	private String cidade;
	private Uf uf;
	private String fone1;
	private String fone2;
	private String fone3;
	private String observacao;
	private String contato1;
	private String contato2;
	private String contato3;
	private String email;
    private Long codRequerente;
    private Long codRequerido;
    private Boolean pagaTaxa;
    private Double valorTaxa;

	public Pessoa() {
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String val) {
		this.bairro = val;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String val) {
		this.cep = Filters.onlyAlphaNumerics(val);
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String val) {
		this.cidade = val;
	}

	public String getContato1() {
		return contato1;
	}

	public void setContato1(String val) {
		this.contato1 = val;
	}

	public String getContato2() {
		return contato2;
	}

	public void setContato2(String val) {
		this.contato2 = val;
	}

	public String getContato3() {
		return contato3;
	}

	public void setContato3(String val) {
		this.contato3 = val;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String val) {
		this.email = val;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String val) {
		this.endereco = val;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String val) {
		this.fone1 = val;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String val) {
		this.fone2 = val;
	}

	public String getFone3() {
		return fone3;
	}

	public void setFone3(String val) {
		this.fone3 = val;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String val) {
		this.observacao = val;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf val) {
		this.uf = val;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
		if (!(outro instanceof Pessoa)) {
			return false;
		}
		final Pessoa aquele = (Pessoa) outro;
		return this.id.equals(aquele.getId());
	}

	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

    public Long getCodRequerente() {
        return codRequerente;
    }

    public void setCodRequerente(Long codRequerente) {
        this.codRequerente = codRequerente;
    }

    public Long getCodRequerido() {
        return codRequerido;
    }

    public void setCodRequerido(Long codRequerido) {
        this.codRequerido = codRequerido;
    }

    public Boolean getPagaTaxa() {
        return pagaTaxa;
    }

    public void setPagaTaxa(Boolean pagaTaxa) {
        this.pagaTaxa = pagaTaxa;
    }

    public Double getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(Double valorTaxa) {
        this.valorTaxa = valorTaxa;
    }
}

