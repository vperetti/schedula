package br.com.perettis.schedula.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Acompanhamento implements Serializable {

	private Long id;
	private Long version;
	private String historico;
	private Date dataCadastro;
	private Date dataProximaProvidencia;
	private boolean baixa;
	private Date dataBaixa;
	private Double parcelaValor;
	private String parcela;
	private String observacao;
	private Processo processo;
	private AcompanhamentoTipo acompanhamentoTipo;
	private AudienciaTipo audienciaTipo;
    private Set<AcordoParcela> acordoParcelas;

	public Acompanhamento() {
	}

	public AcompanhamentoTipo getAcompanhamentoTipo() {
		return acompanhamentoTipo;
	}

	public void setAcompanhamentoTipo(AcompanhamentoTipo val) {
		this.acompanhamentoTipo = val;
	}

	public AudienciaTipo getAudienciaTipo() {
		return audienciaTipo;
	}

	public void setAudienciaTipo(AudienciaTipo val) {
		this.audienciaTipo = val;
	}

	public boolean getBaixa() {
		return baixa;
	}

	public void setBaixa(boolean val) {
		this.baixa = val;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date val) {
		this.dataBaixa = val;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date val) {
		this.dataCadastro = val;
	}

	public Date getDataProximaProvidencia() {
		return dataProximaProvidencia;
	}

	public void setDataProximaProvidencia(Date val) {
		this.dataProximaProvidencia = val;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String val) {
		this.historico = val;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long val) {
		this.id = val;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String val) {
		this.observacao = val;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String val) {
		this.parcela = val;
	}

	public Double getParcelaValor() {
		return parcelaValor;
	}

	public void setParcelaValor(Double val) {
		this.parcelaValor = val;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo val) {
		this.processo = val;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

    @Override
	public String toString() {
		return String.valueOf(this.id);
	}

    @Override
	public boolean equals(Object outro) {
		if (this == outro) {
			return true;
		}
		if (id == null) {
			return false;
		}
		if (!(outro instanceof Acompanhamento)) {
			return false;
		}
		final Acompanhamento aquele = (Acompanhamento) outro;
		return this.id.equals(aquele.getId());
	}

    @Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}
    
    public Set<AcordoParcela> getAcordoParcelas() {
       return acordoParcelas;
    }

    public void setAcordoParcelas(Set<AcordoParcela> acordoParcelas) {
        if (this.acordoParcelas == null) {
            this.acordoParcelas = acordoParcelas;
        } else {
            this.acordoParcelas.clear();
            this.acordoParcelas.addAll(acordoParcelas);
        }
    }
}

