/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula.versoes;

/**
 *
 * @author vinicius
 */
public abstract class ImplantacaoAbstract implements Implantacao {
    void estagio(int estagio, int versao, String descricao) {
        System.out.println("Estagio " + estagio + " implantação " + versao + " \n "+descricao);
    }
}
