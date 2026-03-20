/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula;


import br.com.perettis.schedula.entity.Usuario;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import br.com.perettis.schedula.versoes.Implantacao;
import br.com.perettis.schedula.versoes.Implantacao1;
import javax.swing.JOptionPane;

/**
 *
 * @author vinicius
 */
public class SchedulaInstalacao {
    
    public boolean executar(SchedulaDAOFactory daoFactory){
        boolean ret = false;    
        try{
            ret = (new Implantacao1()).executa(daoFactory);
            if (ret){
                JOptionPane.showMessageDialog(null, "É preciso criar uma senha para o usuário admin (administrador)");
                TrocaSenha trocaSenha = new TrocaSenha(daoFactory);
                trocaSenha.setModal(true);
                Usuario usuario = new Usuario();
                usuario.setAdministrador(true);
                usuario.setApelido("admin");
                usuario.setNome("Administrador do Sistema");
                trocaSenha.btnCancelar.setEnabled(false);
                trocaSenha.txtSenhaAtual.setEnabled(false);
                trocaSenha.setUsuario(usuario);
                trocaSenha.setVisible(true);
                
            }
            
        }catch (Exception e){
            ret = false;
        }
        return ret;
    }
}
