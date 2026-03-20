package br.com.perettis.schedula.entity;

import java.io.Serializable;
import java.util.Date;

public class AcordoParcela implements Serializable {

	private Long id;
	private Acompanhamento acompanhamento;
    private Double parcelaValor;
    private Date vencimento;
    private String parcela;
    
            
	public AcordoParcela() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long val) {
		this.id = val;
	}



    @Override
	public String toString() {
		return getParcela() ;
	}

    @Override
	public boolean equals(Object outro) {
//		if (this == outro) {
//			return true;
//		}
//		if (id == null) {
//			return false;
//		}
		if (!(outro instanceof AcordoParcela)) {
			return false;
		}
		final AcordoParcela aquele = (AcordoParcela) outro;
		return this.getVencimento().equals(aquele.getVencimento());
	}

    @Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Double getParcelaValor() {
        return parcelaValor;
    }

    public void setParcelaValor(Double parcelaValor) {
        this.parcelaValor = parcelaValor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }
}

