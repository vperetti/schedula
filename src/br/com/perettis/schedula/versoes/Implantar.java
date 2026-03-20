package br.com.perettis.schedula.versoes;

/**
 *
 * @author Vinicius Peretti
 */
public class Implantar {
    public Implantacao[] getImplantacoes(){
        Implantacao[] implantacoes = new Implantacao[1];
        implantacoes[0] = new Implantacao1();
        return implantacoes;
    }
}
