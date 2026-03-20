package br.com.perettis.schedula.entity;

import java.io.Serializable;
import java.util.Set;

public class Processo implements Serializable {

	private Long id;
	private Long version;
	private String numeroProcesso;
	private Vara vara;
	private String armario;
	private String gaveta;
	private Boolean geei;
	private Boolean trt;
    private Boolean autor;
	private String recurso;
	private String advogadoContraria;
	private String observacao;
	private Pessoa reclamante;
	private Pessoa reclamado;
	private Advogado advogado;
    private String detalhes;
    private Set<Acompanhamento> acompanhamentos;
    private Boolean pagaTaxaAbertura;
    private Double valorTaxaAbertura;

	public Processo() {
	}

	public Advogado getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Advogado val) {
		this.advogado = val;
	}

	public String getAdvogadoContraria() {
		return advogadoContraria;
	}

	public void setAdvogadoContraria(String val) {
		this.advogadoContraria = val;
	}

	public String getArmario() {
		return armario;
	}

	public void setArmario(String val) {
		this.armario = val;
	}

	public String getGaveta() {
		return gaveta;
	}

	public void setGaveta(String val) {
		this.gaveta = val;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long val) {
		this.id = val;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String val) {
		this.numeroProcesso = val;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String val) {
		this.observacao = val;
	}

	public Pessoa getReclamado() {
		return reclamado;
	}

	public void setReclamado(Pessoa val) {
		this.reclamado = val;
	}

	public Pessoa getReclamante() {
		return reclamante;
	}

	public void setReclamante(Pessoa val) {
		this.reclamante = val;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String val) {
		this.recurso = val;
	}

	public Vara getVara() {
		return vara;
	}

	public void setVara(Vara val) {
		this.vara = val;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
		if (id == null) {
			return false;
		}
		if (!(outro instanceof Processo)) {
			return false;
		}
		final Processo aquele = (Processo) outro;
		return this.id.equals(aquele.getId());
	}

    @Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Boolean getGeei() {
        return geei;
    }

    public void setGeei(Boolean geei) {
        this.geei = geei;
    }

    public Boolean getTrt() {
        return trt;
    }

    public void setTrt(Boolean trt) {
        this.trt = trt;
    }

    public Boolean getAutor() {
        return autor;
    }

    public void setAutor(Boolean autor) {
        this.autor = autor;
    }

    public Set<Acompanhamento> getAcompanhamentos() {
        return acompanhamentos;
    }

    public void setAcompanhamentos(Set<Acompanhamento> acompanhamentos) {
        if (this.acompanhamentos == null) {
            this.acompanhamentos = acompanhamentos;
        } else {
            this.acompanhamentos.clear();
            this.acompanhamentos.addAll(acompanhamentos);
        }
    }

    public Boolean getPagaTaxaAbertura() {
        return pagaTaxaAbertura;
    }

    public void setPagaTaxaAbertura(Boolean pagaTaxaAbertura) {
        this.pagaTaxaAbertura = pagaTaxaAbertura;
    }

    public Double getValorTaxaAbertura() {
        return valorTaxaAbertura;
    }

    public void setValorTaxaAbertura(Double valorTaxaAbertura) {
        this.valorTaxaAbertura = valorTaxaAbertura;
    }
}

