/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.DAOFactoryAbstract;
import br.com.perettis.gets.dao.GenericDAO;
import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.gets.security.CryptoDES;
import br.com.perettis.schedula.SchedulaConfig;
import br.com.perettis.schedula.entity.Uf;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Vinicius Peretti
 */
public class HibernateDAOFactory extends DAOFactoryAbstract implements SchedulaDAOFactory {

    private SchedulaConfig schedulaConfig;
    private SessionFactory sessionFactory;
    Configuration cfg = new Configuration();

    public ProcessoDAO getProcessoDAO() {
        return (ProcessoDAO) instantiateDAO(ProcessoDAO.class);
    }

    public AcompanhamentoDAO getAcompanhamentoDAO() {
        return (AcompanhamentoDAO) instantiateDAO(AcompanhamentoDAO.class);
    }

    public AcompanhamentoTipoDAO getAcompanhamentoTipoDAO() {
        return (AcompanhamentoTipoDAO) instantiateDAO(AcompanhamentoTipoDAO.class);
    }

    public AcordoParcelaDAO getAcordoParcelaDAO() {
        return (AcordoParcelaDAO) instantiateDAO(AcordoParcelaDAO.class);
    }
    
    public EstadoCivilDAO getEstadoCivilDAO() {
        return (EstadoCivilDAO)instantiateDAO(EstadoCivilDAO.class);
    }

    public PessoaDAO getPessoaDAO() {
        return (PessoaDAO) instantiateDAO(PessoaDAO.class);
    }

    public PessoaFisicaDAO getPessoaFisicaDAO() {
        return (PessoaFisicaDAO)instantiateDAO(PessoaFisicaDAO.class);
    }

    public PessoaJuridicaDAO getPessoaJuridicaDAO() {
        return (PessoaJuridicaDAO)instantiateDAO(PessoaJuridicaDAO.class);
    }
    public AdvogadoDAO getAdvogadoDAO() {
        return (AdvogadoDAO)instantiateDAO(AdvogadoDAO.class);
    }

    public ComarcaDAO getComarcaDAO() {
        return (ComarcaDAO) instantiateDAO(ComarcaDAO.class);
    }
    
    public AudienciaTipoDAO getAudienciaTipoDAO() {
        return (AudienciaTipoDAO) instantiateDAO(AudienciaTipoDAO.class);
    }
    
    public VaraDAO getVaraDAO() {
        return (VaraDAO) instantiateDAO(VaraDAO.class);
    }
        
    public UsuarioDAO getUsuarioDAO() {
        return (UsuarioDAO) instantiateDAO(UsuarioDAO.class);
    }

    public UsuarioDireitoDAO getUsuarioDireitoDAO() {
        return (UsuarioDireitoDAO) instantiateDAO(UsuarioDireitoDAO.class);
    }
    
    public UfDAO getUfDAO() {
        return (UfDAO) instantiateDAO(UfDAO.class);
    }

    public SchedulaRotinaDAO getSchedulaRotinaDAO() {
        return (SchedulaRotinaDAO) instantiateDAO(SchedulaRotinaDAO.class);
    }
      
    public SchedulaDAO getSchedulaDAO() {
        return (SchedulaDAO) instantiateDAO(SchedulaDAO.class);
    }
    
    public GenericDAO instantiateDAO(Class daoClass) {
        try {
            GenericHibernateDAO dao = (GenericHibernateDAO)daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }

    // You could override this if you don't want HibernateUtil for lookup
    protected Session getCurrentSession() {
        Session session = null;
        if (getSchedulaConfig() != null) {
            if (getSessionFactory() == null) {
                
                cfg.setProperty("hibernate.dialect", getSchedulaConfig().getDatabaseDialect());
                cfg.setProperty("hibernate.connection.driver_class", getSchedulaConfig().getDatabaseDriver());
                cfg.setProperty("hibernate.connection.url", getSchedulaConfig().getDatabaseConection());
                cfg.setProperty("hibernate.connection.username", getSchedulaConfig().getDatabaseUsername());
                cfg.setProperty("hibernate.connection.password", CryptoDES.decrypt(getSchedulaConfig().getDatabasePassword(), Seguranca.KEYPASS));
                cfg.setProperty("hibernate.current_session_context_class", "thread");
                
                cfg.setProperty("show_sql", "true");
                cfg.setProperty("hibernate.generate_statistics", "true");
                cfg.setProperty("hibernate.use_sql_comments", "true");
                
                cfg.addResource("br/com/perettis/schedula/entity/Acompanhamento.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/AcompanhamentoTipo.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/AudienciaTipo.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/EstadoCivil.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/Pessoa.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/Advogado.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/Processo.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/Schedula.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/SchedulaRotina.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/TipoDeAcompanhamento.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/Uf.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/UsuarioDireito.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/Usuario.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/Comarca.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/Vara.hbm.xml");
                cfg.addResource("br/com/perettis/schedula/entity/AcordoParcela.hbm.xml");
                setSessionFactory(cfg.buildSessionFactory());
            }
            session = getSessionFactory().getCurrentSession();
            //session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        /*
        else{
                    session = HibernateUtil.getSessionFactory().getCurrentSession();
        }*/

        return session;
    }

    // Inline concrete DAO implementations with no business-related data access methods.
    // If we use public static nested classes, we can centralize all of them in one source file.

 /*   public static class UfDAO
            extends GenericHibernateDAO<Uf, Integer> {

        public List<Uf> findByPropertyLike(String property, Object value, int type) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
*/
    public Object getDAObyClass(Class dao) {
        return instantiateDAO(dao);
    }

    public

    SchedulaConfig getSchedulaConfig() {
        return schedulaConfig;
    }

    public void setSchedulaConfig(SchedulaConfig schedulaConfig) {
        this.schedulaConfig = schedulaConfig;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Configuration getConfiguration() {
        return cfg;
    }


/*    public static class ShipmentDAOHibernate
            extends GenericHibernateDAO<Shipment, Long>
            implements ShipmentDAO {}
*/
}

