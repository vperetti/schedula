/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula.reports;

import br.com.perettis.schedula.entity.Acompanhamento;
import br.com.perettis.schedula.entity.AcordoParcela;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vinicius
 */
public class PautaDiariaBean {
    private Acompanhamento acompanhamento;
    private AcordoParcela acordoParcela;
    private String acompanhamentoDescricao;
    private Date dataProximaProvidencia;
    private String data;
    private String numeroProcesso;
    private String reclamante;
    private String reclamado;
    private String historico;
    private String detalhamento;
    private String vara;
    private String audienciaTipo;
    private Double valor;
    private String parcela;
    private Long id;
    
    SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd");

    public PautaDiariaBean(Acompanhamento acompanhamento){
        setAcompanhamento(acompanhamento);
    }
    
    public PautaDiariaBean(AcordoParcela acordoParcela){
        setAcordoParcela(acordoParcela);
    }
    
    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
        if (acompanhamento != null) {
            setAcompanhamentoDescricao(acompanhamento.getAcompanhamentoTipo().getDescricao());
            setAcordoParcela(null);
            if (acompanhamento.getAudienciaTipo()!=null){
                setAudienciaTipo(acompanhamento.getAudienciaTipo().getDescricao());
            }else{
                setAudienciaTipo(null);
            }
            setData(formataData.format(acompanhamento.getDataProximaProvidencia()));
            setDataProximaProvidencia(acompanhamento.getDataProximaProvidencia());
            setDetalhamento(acompanhamento.getObservacao());
            setHistorico(acompanhamento.getHistorico());
            setId(acompanhamento.getProcesso().getId());
            setNumeroProcesso(acompanhamento.getProcesso().getNumeroProcesso());
            setParcela(null);
            setReclamado(acompanhamento.getProcesso().getReclamado().getNome());
            setReclamante(acompanhamento.getProcesso().getReclamante().getNome());
            setValor(null);
            setVara(acompanhamento.getProcesso().getVara().getNome());
        }
    }

    public AcordoParcela getAcordoParcela() {
        return acordoParcela;
    }

    public void setAcordoParcela(AcordoParcela acordoParcela) {
        this.acordoParcela = acordoParcela;
        if (acordoParcela != null) {
            setAcompanhamentoDescricao("Recebimento de Acordo");
            setAcompanhamento(null);
            setAudienciaTipo(null);
            setData(formataData.format(acordoParcela.getVencimento()));
            setDataProximaProvidencia(acordoParcela.getVencimento());
            setDetalhamento(acordoParcela.getAcompanhamento().getObservacao());
            setHistorico(acordoParcela.getAcompanhamento().getHistorico());
            setId(acordoParcela.getAcompanhamento().getProcesso().getId());
            setNumeroProcesso(acordoParcela.getAcompanhamento().getProcesso().getNumeroProcesso());
            setParcela(acordoParcela.getParcela());
            setReclamado(acordoParcela.getAcompanhamento().getProcesso().getReclamado().getNome());
            setReclamante(acordoParcela.getAcompanhamento().getProcesso().getReclamante().getNome());
            setValor(acordoParcela.getParcelaValor());
            setVara(acordoParcela.getAcompanhamento().getProcesso().getVara().getNome());
        }
    }

    public String getAcompanhamentoDescricao() {
        return acompanhamentoDescricao;
    }

    public void setAcompanhamentoDescricao(String acompanhamentoDescricao) {
        this.acompanhamentoDescricao = acompanhamentoDescricao;
    }

    public Date getDataProximaProvidencia() {
        return dataProximaProvidencia;
    }

    public void setDataProximaProvidencia(Date dataProximaProvidencia) {
        this.dataProximaProvidencia = dataProximaProvidencia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getReclamante() {
        return reclamante;
    }

    public void setReclamante(String reclamante) {
        this.reclamante = reclamante;
    }

    public String getReclamado() {
        return reclamado;
    }

    public void setReclamado(String reclamado) {
        this.reclamado = reclamado;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public String getAudienciaTipo() {
        return audienciaTipo;
    }

    public void setAudienciaTipo(String audienciaTipo) {
        this.audienciaTipo = audienciaTipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
