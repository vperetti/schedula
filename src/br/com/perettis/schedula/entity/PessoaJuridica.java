package br.com.perettis.schedula.entity;


public class PessoaJuridica extends Pessoa {

    private String cnpj;

    private String inscEstadual;

    private String inscMunicipal;

    private String socios;

    private String codigoEmpresa;

    public PessoaJuridica () {
    }

    public String getSocios () {
        return socios;
    }

    public void setSocios (String val) {
        this.socios = val;
    }

    public String getCnpj () {
        return cnpj;
    }

    public void setCnpj (String val) {
        this.cnpj = val;
    }

    public String getCodigoEmpresa () {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa (String val) {
        this.codigoEmpresa = val;
    }

    public String getInscEstadual () {
        return inscEstadual;
    }

    public void setInscEstadual (String val) {
        this.inscEstadual = val;
    }

    public String getInscMunicipal () {
        return inscMunicipal;
    }

    public void setInscMunicipal (String val) {
        this.inscMunicipal = val;
    }

}

