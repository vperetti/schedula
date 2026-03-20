/*
 * SchedulaApp.java
 */
package br.com.perettis.schedula;


import br.com.perettis.gets.dao.DAOFactoryAbstract;
import br.com.perettis.schedula.entity.Schedula;
import br.com.perettis.schedula.model.HibernateDAOFactory;
import br.com.perettis.schedula.model.SchedulaDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import br.com.perettis.schedula.model.Seguranca;
import javax.swing.JOptionPane;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class SchedulaApp extends SingleFrameApplication {

    /**
     * Versão atual do sistema
     */
    public static final Integer VERSAO = 1;
    /**
     * Guarda daoFactory
     */
    SchedulaDAOFactory daoFactory = null;
    /**
     * Guarda configurações do Sistema
     */
    SchedulaConfig schedulaConfig;
    


    
    /**
     * Busca configuração do arquivo de confiuração SchedulaConfig.xml
     */
    public void initConfig(){
        SchedulaConfigDAOXml dao;
        try {
            dao = new SchedulaConfigDAOXml();
            schedulaConfig = dao.findByKey(new Integer(1));
        } catch (NullPointerException e) {
            schedulaConfig = null;
        }
        if (schedulaConfig == null){
            configuraSchedula();
        }
    }
    
    /**
     * Abre formulario de configuração
     */
    public void configuraSchedula() {
        SchedulaConfigForm schedulaConfigForm = new SchedulaConfigForm();
        schedulaConfigForm.setModal(true);
        schedulaConfigForm.setVisible(true);
    }
    
    /**
     * Inicia Fabrica de daos, testando todas configurações de banco de dados
     */
    public void initFactories() {
        try {
            daoFactory = (SchedulaDAOFactory) DAOFactoryAbstract.instance(HibernateDAOFactory.class);
            daoFactory.setSchedulaConfig(schedulaConfig);
        } catch (Exception e) {
            System.out.println("Erro:" + e.toString());
            System.exit(1);
        }
    }

	/**
	 * At startup create and show the main frame of the application.
	 */
	@Override
	protected void startup() {
        // Tentativas de conexão
        boolean tente = true;
        
        // Busca configuração de conexão
        initConfig();

        // Tenta conexão
        while (tente) {
            try {
                // Inicia fabricas
                initFactories();
                // Testa fabricação
                daoFactory.getUsuarioDAO();
                break;
            //Qualquer erro
            } catch (Exception e) {
                // Mostra
                e.printStackTrace();
                // Pergunta se quer alterar configuração
                int res = JOptionPane.showConfirmDialog(null, "Erro ao tentar conectar no banco de dados\nConfigurar dados de Conexão?");
                switch (res){
                    case 0: 
                        // Manda confiurar
                        configuraSchedula();
                        // Recarrega configuração
                        initConfig();
                        // Continua pra tentar novamente
                        continue;
                    // Senão fecha o sistema
                    case 1: 
                        System.exit(1);
                        break;
                    case 2: 
                        System.exit(1);
                        break;
                }
                break;
            }
        }
        
        // Inicia configurações do banco de dados, com verificações de atualizações
        initSchedula();
        
        Autenticacao autenticacao = new Autenticacao(daoFactory);
        autenticacao.setVisible(true);
        if (autenticacao.getUsuario() == null ) System.exit(1);
        
        Seguranca.setUsuarioAutenticado(autenticacao.getUsuario());
        Seguranca.setDaoFactory(daoFactory);
        
        SchedulaView schedulaView = new SchedulaView(this);
        schedulaView.setUsuarioAutenticado(autenticacao.getUsuario());
        schedulaView.setDaoFactory(daoFactory);
		show(schedulaView);
	}

	/**
	 * This method is to initialize the specified window by injecting resources.
	 * Windows shown in our application come fully initialized from the GUI
	 * builder, so this additional configuration is not needed.
	 */
	@Override
	protected void configureWindow(java.awt.Window root) {
	}

	/**
	 * A convenient static getter for the application instance.
	 * @return the instance of SchedulaApp
	 */
	public static SchedulaApp getApplication() {
		return Application.getInstance(SchedulaApp.class);
	}

	/**
	 * Main method launching the application.
	 */
	public static void main(String[] args) {
		launch(SchedulaApp.class, args);
	}

    private void initSchedula() {
        SchedulaDAO schedulaDAO = daoFactory.getSchedulaDAO();
        
        try {
            Transaction t = schedulaDAO.getSession().getTransaction();
            t.begin();
            Schedula schedula = schedulaDAO.busca();
            if (schedula.getVersao() < VERSAO){
                JOptionPane.showConfirmDialog(null, "Sistema não está atualizado no banco de dados, confirma atualização?");
            }
        } catch (SQLGrammarException e) {
            
            int res = JOptionPane.showConfirmDialog(null, "Sistema não está instalado no banco de dados, confirma instalação?");
                switch (res){
                    case 0: 
                        if (!(new SchedulaInstalacao().executar(daoFactory))){
                            JOptionPane.showMessageDialog(null, "Erro na instalação, contactar suporte!");
                            System.exit(4);
                        }
                        initConfig();
                        break;
                    case 1: 
                        System.exit(1);
                        break;
                    case 2: 
                        System.exit(1);
                        break;
                }
        }
        
        
        
    }
}
