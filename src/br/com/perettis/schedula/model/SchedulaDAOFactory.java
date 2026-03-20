package br.com.perettis.schedula.model;

import br.com.perettis.schedula.SchedulaConfig;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 *
 * @author vinicius
 * 
 * Interface para fabrica de daos da aplicação Schedula
 */
public interface SchedulaDAOFactory {
    
    void setSchedulaConfig(SchedulaConfig schedulaConfig);
    
    SchedulaConfig getSchedulaConfig();

    EstadoCivilDAO getEstadoCivilDAO();
    
    AudienciaTipoDAO getAudienciaTipoDAO();
    
    UsuarioDAO getUsuarioDAO();
    
    SchedulaDAO getSchedulaDAO();
    
    UfDAO getUfDAO();
    
    AcompanhamentoDAO getAcompanhamentoDAO();  
    AcompanhamentoTipoDAO getAcompanhamentoTipoDAO();
    AcordoParcelaDAO getAcordoParcelaDAO();  
    AdvogadoDAO getAdvogadoDAO();
    ComarcaDAO getComarcaDAO();    
    PessoaDAO getPessoaDAO();
    PessoaFisicaDAO getPessoaFisicaDAO();
    PessoaJuridicaDAO getPessoaJuridicaDAO();
    ProcessoDAO getProcessoDAO();
    SchedulaRotinaDAO getSchedulaRotinaDAO();
    //TipoDeAcompanhamentoDAO getTipoDeAcompanhamentoDAO();
    UsuarioDireitoDAO getUsuarioDireitoDAO();
    VaraDAO getVaraDAO();
    
    Configuration getConfiguration();
    
    SessionFactory getSessionFactory();
}
