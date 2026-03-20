package br.com.perettis.schedula.entity;

import java.util.Date;


public class PessoaFisica extends Pessoa {

    private String cpf;

    private EstadoCivil estadoCivil;

    private String profissao;

    private Date dataNascimento;

    private boolean sexo;

    private String rg;

    private String rgOrgaoExpeditor;

    private String carteiraTrabalho;

    private String carteiraTrabalhoSerie;

    private String nomeMae;

    private String nomePai;

    private String nacionalidade;

    private String naturalidade;

    public PessoaFisica () {
    }

    public String getCarteiraTrabalho () {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho (String val) {
        this.carteiraTrabalho = val;
    }

    public String getCarteiraTrabalhoSerie () {
        return carteiraTrabalhoSerie;
    }

    public void setCarteiraTrabalhoSerie (String val) {
        this.carteiraTrabalhoSerie = val;
    }

    public String getCpf () {
        return cpf;
    }

    public void setCpf (String val) {
        this.cpf = val;
    }

    public Date getDataNascimento () {
        return dataNascimento;
    }

    public void setDataNascimento (Date val) {
        this.dataNascimento = val;
    }

    public EstadoCivil getEstadoCivil () {
        return estadoCivil;
    }

    public void setEstadoCivil (EstadoCivil val) {
        this.estadoCivil = val;
    }

    public String getNacionalidade () {
        return nacionalidade;
    }

    public void setNacionalidade (String val) {
        this.nacionalidade = val;
    }

    public String getNaturalidade () {
        return naturalidade;
    }

    public void setNaturalidade (String val) {
        this.naturalidade = val;
    }

    public String getNomeMae () {
        return nomeMae;
    }

    public void setNomeMae (String val) {
        this.nomeMae = val;
    }

    public String getNomePai () {
        return nomePai;
    }

    public void setNomePai (String val) {
        this.nomePai = val;
    }

    public String getProfissao () {
        return profissao;
    }

    public void setProfissao (String val) {
        this.profissao = val;
    }

    public String getRg () {
        return rg;
    }

    public void setRg (String val) {
        this.rg = val;
    }

    public String getRgOrgaoExpeditor () {
        return rgOrgaoExpeditor;
    }

    public void setRgOrgaoExpeditor (String val) {
        this.rgOrgaoExpeditor = val;
    }

    public boolean getSexo () {
        return sexo;
    }

    public void setSexo (boolean val) {
        this.sexo = val;
    }

}

