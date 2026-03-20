/*
 * SchedulaView.java
 */
package br.com.perettis.schedula;

import br.com.perettis.schedula.entity.SchedulaRotina;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import br.com.perettis.schedula.entity.Usuario;
import br.com.perettis.schedula.model.SchedulaRotinaDAO;
import br.com.perettis.schedula.model.Seguranca;
import br.com.perettis.schedula.model.UsuarioDAO;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.hibernate.Transaction;

/**
 * The application's main frame.
 */
public class SchedulaView extends FrameView {

    private SchedulaDAOFactory daoFactory = null;
    private Usuario usuarioAutenticado;

    public SchedulaView(SingleFrameApplication app) {
        super(app);


        initComponents();
        getFrame().setIconImage(new Imagens().getIcone());
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });




    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = SchedulaApp.getApplication().getMainFrame();
            aboutBox = new SchedulaAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        SchedulaApp.getApplication().show(aboutBox);
    }

    public boolean Permitido(String nomeRotina) {
        if (usuarioAutenticado.isAdministrador()) {
            return true;
        }
        if (nomeRotina.equalsIgnoreCase("")) {
            return false;
        }
        SchedulaRotinaDAO schedulaRotinaDAO = getDaoFactory().getSchedulaRotinaDAO();
        UsuarioDAO usuarioDAO = getDaoFactory().getUsuarioDAO();
        Transaction t = schedulaRotinaDAO.getSession().getTransaction();
        Transaction tu = usuarioDAO.getSession().getTransaction();
        t.begin();
        tu.begin();
        usuarioAutenticado = usuarioDAO.findById(usuarioAutenticado.getId(), false);
        SchedulaRotina schedulaRotina = schedulaRotinaDAO.buscaPorNome(nomeRotina);
        if (!Seguranca.TemDireito(usuarioAutenticado, schedulaRotina)) {
            JOptionPane.showMessageDialog(null, "Sem permissão para rotina: " + schedulaRotina.toString());
            return false;
        }
        return true;
    }

    @Action
    public void doAtdb() {
        if (Permitido("")) {
            new doAdtb(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroAndamentoProcesso() {
        if (Permitido("Processos-Acompanhamentos")) {
            new CadastroAndamentoProcesso(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroProcesso() {
        if (Permitido("Processos-Dados")) {
            new CadastroProcesso(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroPessoaJuridica() {
        if (Permitido("Cadastro-PessoaJuridica")) {
            new CadastroPessoaJuridica(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroPessoaFisica() {
        if (Permitido("Cadastro-PessoaFisica")) {
            new CadastroPessoaFisica(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroAdvogados() {
        if (Permitido("Cadastro-Advogado")) {
            new CadastroAdvogados(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroEstadoCivil() {
        if (Permitido("Parametros-CadastroEstadoCivil")) {
            new CadastroEstadoCivil(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroAudienciaTipo() {
        if (Permitido("Parametros-CadastroTipoAudiencia")) {
            new CadastroAudienciaTipo(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroComarca() {
        if (Permitido("Parametros-CadastroComarca")) {
            new CadastroComarca(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroVara() {
        if (Permitido("Parametros-CadastroVara")) {
            new CadastroVara(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void cadastroUsuario() {
        if (Permitido("Suporte-CadastroUsuario")) {
            new CadastroUsuarioHabilitacoes(getDaoFactory()).setVisible(true);
        }
    }

    @Action
    public void schedulaConfig() {
        if (Permitido("Parametros-OpcoesSchedula")) {
            new SchedulaConfigForm().setVisible(true);
        }
    }

    @Action
    public void schedulaOpcoes() {
        if (Permitido("Parametros-OpcoesSchedula")) {
            new SchedulaConfigForm(daoFactory).setVisible(true);
        }
    }

    @Action
    public void trocaSenha() {
        if (Permitido("TrocaSenha")) {
            TrocaSenha trocaSenha = new TrocaSenha(daoFactory);
            trocaSenha.setUsuario(usuarioAutenticado);
            trocaSenha.setVisible(true);
        }
    }

    @Action
    public void relatorioPautaDiaria() {
        if (Permitido("Relatorio-PautaDiaria")) {
            new RelatorioPautaDiaria(getDaoFactory()).setVisible(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        informacoesUser = new javax.swing.JMenuItem();
        trocaSenhaItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        processosMenu = new javax.swing.JMenu();
        processosItem = new javax.swing.JMenuItem();
        acompanhamentosItem = new javax.swing.JMenuItem();
        javax.swing.JMenu CadastrosMenu = new javax.swing.JMenu();
        pessoaFisicaItem = new javax.swing.JMenuItem();
        pessoaJuridicaItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        advogadosItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        varaItem = new javax.swing.JMenuItem();
        comarcaItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        estadoCivilItem = new javax.swing.JMenuItem();
        tipoAudienciaItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        gestaoUsuariosMenu = new javax.swing.JMenu();
        usuariosItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jmiConfiguracoes = new javax.swing.JMenuItem();
        opcoesSchedulaItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        diarioItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(br.com.perettis.schedula.SchedulaApp.class).getContext().getResourceMap(SchedulaView.class);
        jLabel1.setIcon(resourceMap.getIcon("imagem.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("imagem.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setName("imagem"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(82, 82, 82))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        menuBar.setName("menuBar"); // NOI18N

        jMenu2.setMnemonic('r');
        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        informacoesUser.setMnemonic('i');
        informacoesUser.setText(resourceMap.getString("informacoesUser.text")); // NOI18N
        informacoesUser.setName("informacoesUser"); // NOI18N
        informacoesUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informacoesUserActionPerformed(evt);
            }
        });
        jMenu2.add(informacoesUser);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(br.com.perettis.schedula.SchedulaApp.class).getContext().getActionMap(SchedulaView.class, this);
        trocaSenhaItem.setAction(actionMap.get("trocaSenha")); // NOI18N
        trocaSenhaItem.setText(resourceMap.getString("trocaSenhaItem.text")); // NOI18N
        trocaSenhaItem.setName("trocaSenhaItem"); // NOI18N
        jMenu2.add(trocaSenhaItem);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jMenu2.add(jSeparator5);

        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N

        jMenuItem1.setAction(actionMap.get("doAtdb")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenu4.add(jMenuItem1);

        jMenu2.add(jMenu4);

        jSeparator6.setName("jSeparator6"); // NOI18N
        jMenu2.add(jSeparator6);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        jMenu2.add(exitMenuItem);

        menuBar.add(jMenu2);

        processosMenu.setMnemonic('o');
        processosMenu.setText(resourceMap.getString("processosMenu.text")); // NOI18N
        processosMenu.setName("processosMenu"); // NOI18N

        processosItem.setAction(actionMap.get("cadastroProcesso")); // NOI18N
        processosItem.setText(resourceMap.getString("processosItem.text")); // NOI18N
        processosItem.setName("processosItem"); // NOI18N
        processosMenu.add(processosItem);

        acompanhamentosItem.setAction(actionMap.get("cadastroAndamentoProcesso")); // NOI18N
        acompanhamentosItem.setName("acompanhamentosItem"); // NOI18N
        processosMenu.add(acompanhamentosItem);

        menuBar.add(processosMenu);

        CadastrosMenu.setMnemonic('c');
        CadastrosMenu.setText(resourceMap.getString("CadastrosMenu.text")); // NOI18N
        CadastrosMenu.setName("CadastrosMenu"); // NOI18N

        pessoaFisicaItem.setAction(actionMap.get("cadastroPessoaFisica")); // NOI18N
        pessoaFisicaItem.setText(resourceMap.getString("pessoaFisicaItem.text")); // NOI18N
        pessoaFisicaItem.setName("pessoaFisicaItem"); // NOI18N
        CadastrosMenu.add(pessoaFisicaItem);

        pessoaJuridicaItem.setAction(actionMap.get("cadastroPessoaJuridica")); // NOI18N
        pessoaJuridicaItem.setText(resourceMap.getString("pessoaJuridicaItem.text")); // NOI18N
        pessoaJuridicaItem.setName("pessoaJuridicaItem"); // NOI18N
        CadastrosMenu.add(pessoaJuridicaItem);

        jSeparator3.setName("jSeparator3"); // NOI18N
        CadastrosMenu.add(jSeparator3);

        advogadosItem.setAction(actionMap.get("cadastroAdvogados")); // NOI18N
        advogadosItem.setName("advogadosItem"); // NOI18N
        CadastrosMenu.add(advogadosItem);

        menuBar.add(CadastrosMenu);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        varaItem.setAction(actionMap.get("cadastroVara")); // NOI18N
        varaItem.setText(resourceMap.getString("varaItem.text")); // NOI18N
        varaItem.setName("varaItem"); // NOI18N
        jMenu1.add(varaItem);

        comarcaItem.setAction(actionMap.get("cadastroComarca")); // NOI18N
        comarcaItem.setText(resourceMap.getString("comarcaItem.text")); // NOI18N
        comarcaItem.setToolTipText(resourceMap.getString("comarcaItem.toolTipText")); // NOI18N
        comarcaItem.setName("comarcaItem"); // NOI18N
        jMenu1.add(comarcaItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu1.add(jSeparator2);

        estadoCivilItem.setAction(actionMap.get("cadastroEstadoCivil")); // NOI18N
        estadoCivilItem.setText(resourceMap.getString("estadoCivilItem.text")); // NOI18N
        estadoCivilItem.setName("estadoCivilItem"); // NOI18N
        jMenu1.add(estadoCivilItem);

        tipoAudienciaItem.setAction(actionMap.get("cadastroAudienciaTipo")); // NOI18N
        tipoAudienciaItem.setText(resourceMap.getString("tipoAudienciaItem.text")); // NOI18N
        tipoAudienciaItem.setName("tipoAudienciaItem"); // NOI18N
        jMenu1.add(tipoAudienciaItem);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jMenu1.add(jSeparator4);

        gestaoUsuariosMenu.setText(resourceMap.getString("gestaoUsuariosMenu.text")); // NOI18N
        gestaoUsuariosMenu.setName("gestaoUsuariosMenu"); // NOI18N

        usuariosItem.setAction(actionMap.get("cadastroUsuario")); // NOI18N
        usuariosItem.setText(resourceMap.getString("usuariosItem.text")); // NOI18N
        usuariosItem.setName("usuariosItem"); // NOI18N
        gestaoUsuariosMenu.add(usuariosItem);

        jMenu1.add(gestaoUsuariosMenu);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu1.add(jSeparator1);

        jmiConfiguracoes.setAction(actionMap.get("schedulaConfig")); // NOI18N
        jmiConfiguracoes.setName("jmiConfiguracoes"); // NOI18N
        jMenu1.add(jmiConfiguracoes);

        opcoesSchedulaItem.setAction(actionMap.get("schedulaOpcoes")); // NOI18N
        opcoesSchedulaItem.setText(resourceMap.getString("opcoesSchedulaItem.text")); // NOI18N
        opcoesSchedulaItem.setName("opcoesSchedulaItem"); // NOI18N
        jMenu1.add(opcoesSchedulaItem);

        menuBar.add(jMenu1);

        jMenu3.setMnemonic('r');
        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        diarioItem.setAction(actionMap.get("relatorioPautaDiaria")); // NOI18N
        diarioItem.setName("diarioItem"); // NOI18N
        jMenu3.add(diarioItem);

        menuBar.add(jMenu3);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

private void informacoesUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informacoesUserActionPerformed
    String mensagem = "Dados do Usuário: " + getUsuarioAutenticado().getApelido() + "\n" +
            "Nome: " + getUsuarioAutenticado().getNome() + "\n" +
            "Administrador: " + getUsuarioAutenticado().isAdministrador() + "\n" +
            "ID: " + getUsuarioAutenticado().getId();
    JOptionPane.showMessageDialog(null, mensagem);
}//GEN-LAST:event_informacoesUserActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem acompanhamentosItem;
    private javax.swing.JMenuItem advogadosItem;
    private javax.swing.JMenuItem comarcaItem;
    private javax.swing.JMenuItem diarioItem;
    private javax.swing.JMenuItem estadoCivilItem;
    private javax.swing.JMenu gestaoUsuariosMenu;
    private javax.swing.JMenuItem informacoesUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JMenuItem jmiConfiguracoes;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem opcoesSchedulaItem;
    private javax.swing.JMenuItem pessoaFisicaItem;
    private javax.swing.JMenuItem pessoaJuridicaItem;
    private javax.swing.JMenuItem processosItem;
    private javax.swing.JMenu processosMenu;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenuItem tipoAudienciaItem;
    private javax.swing.JMenuItem trocaSenhaItem;
    private javax.swing.JMenuItem usuariosItem;
    private javax.swing.JMenuItem varaItem;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;

    public SchedulaDAOFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(SchedulaDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }
}
