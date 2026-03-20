/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula.model;

import br.com.perettis.gets.security.CryptoMD5;
import br.com.perettis.schedula.entity.SchedulaRotina;
import br.com.perettis.schedula.entity.Usuario;
import br.com.perettis.schedula.entity.UsuarioDireito;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.Transaction;

/**
 *
 * @author vinicius
 */
public class Seguranca {
    private static Usuario usuarioAutenticado;
    private static SchedulaDAOFactory daoFactory = null;
    
    public final static String  KEYPASS = "S";
    
    public static final String CriptografaSenha(String senha){
        try {
            return CryptoMD5.crypto(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Seguranca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static final boolean TemDireito(Usuario usuario, SchedulaRotina rotina){
    
        if (usuario.isAdministrador()){
            return true;
        }
        Set<UsuarioDireito> direitos = usuario.getDireitos();
        for (UsuarioDireito usuarioDireito : direitos) {
            if (usuarioDireito.getRotina().equals(rotina)){
                return true;
            }
        }
        return false;
    }

    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public static void setUsuarioAutenticado(Usuario usarioAutenticado) {
        Seguranca.usuarioAutenticado = usarioAutenticado;
    }
    
    public static boolean Permitido(String nomeRotina) {
        if (usuarioAutenticado.isAdministrador()) return true;
        SchedulaRotinaDAO schedulaRotinaDAO = getDaoFactory().getSchedulaRotinaDAO();
        UsuarioDAO usuarioDAO = getDaoFactory().getUsuarioDAO();
        Transaction t = schedulaRotinaDAO.getSession().getTransaction();
        Transaction tu = usuarioDAO.getSession().getTransaction();
        t.begin();
        tu.begin();
                usuarioAutenticado = usuarioDAO.findById(usuarioAutenticado.getId(), false);
        SchedulaRotina schedulaRotina = schedulaRotinaDAO.buscaPorNome(nomeRotina);
        if (!TemDireito(usuarioAutenticado, schedulaRotina)) {
            //JOptionPane.showMessageDialog(null, "Sem permissão para rotina: "+schedulaRotina.toString());
            return false;
        }
        return true;
    }

    public static SchedulaDAOFactory getDaoFactory() {
        return daoFactory;
    }

    public static void setDaoFactory(SchedulaDAOFactory aDaoFactory) {
        daoFactory = aDaoFactory;
    }
}
