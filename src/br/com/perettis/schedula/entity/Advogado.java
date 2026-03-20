package br.com.perettis.schedula.entity;

public class Advogado extends PessoaFisica {

	private String oab;
	private Uf oabUf;
	private Boolean desabilitado = false;
    private Long codAdvogado;

	public Advogado() {
	}

	public String getOab() {
		return oab;
	}

	public void setOab(String val) {
		this.oab = val;
	}

	public Uf getOabUf() {
		return oabUf;
	}

	public void setOabUf(Uf val) {
		this.oabUf = val;
	}

	public Boolean getDesabilitado() {
		return desabilitado;
	}
    
    public Boolean isDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(Boolean desabilitado) {
		this.desabilitado = desabilitado;
	}

    public Long getCodAdvogado() {
        return codAdvogado;
    }

    public void setCodAdvogado(Long codAdvogado) {
        this.codAdvogado = codAdvogado;
    }
}

