package br.com.perettis.schedula.versoes;

import br.com.perettis.gets.dao.DAOFactory;
import br.com.perettis.gets.text.Filters;
import br.com.perettis.schedula.entity.Acompanhamento;
import br.com.perettis.schedula.entity.AcordoParcela;
import br.com.perettis.schedula.entity.PessoaFisica;
import br.com.perettis.schedula.entity.PessoaJuridica;
import br.com.perettis.schedula.entity.Processo;
import br.com.perettis.schedula.entity.Schedula;
import br.com.perettis.schedula.entity.Vara;
import br.com.perettis.schedula.model.AcompanhamentoDAO;
import br.com.perettis.schedula.model.AcompanhamentoTipoDAO;
import br.com.perettis.schedula.model.AcordoParcelaDAO;
import br.com.perettis.schedula.model.AdvogadoDAO;
import br.com.perettis.schedula.model.AudienciaTipoDAO;
import br.com.perettis.schedula.model.EstadoCivilDAO;
import br.com.perettis.schedula.model.PessoaFisicaDAO;
import br.com.perettis.schedula.model.PessoaJuridicaDAO;
import br.com.perettis.schedula.model.ProcessoDAO;
import br.com.perettis.schedula.model.VaraDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import br.com.perettis.schedula.model.UfDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.hibernate.Transaction;

/**
 *
 * @author Vinicius Peretti
 */
public class doAdtb_Processos {
    private SchedulaDAOFactory daoFactory;
    /**
     * Variavel guarda conexão
     */
    private static Connection conn;
    public PessoaFisicaDAO pessoaFisicaDAO;
    public PessoaJuridicaDAO pessoaJuridicaDAO;
    public ProcessoDAO processoDAO;
    public AdvogadoDAO advogadoDAO;
    public AcompanhamentoDAO acompanhamentoDAO;
    public AcompanhamentoTipoDAO acompanhamentoTipoDAO;
    public AudienciaTipoDAO audienciaTipoDAO;
    public AcordoParcelaDAO acordoParcelaDAO;
    public UfDAO ufDAO;
    public VaraDAO varaDAO;
    public EstadoCivilDAO estadoCivilDAO;
    private long conta = 0;
    
    public doAdtb_Processos(SchedulaDAOFactory daoFactory){
        setDaoFactory(daoFactory);
    } 
    public void executa(){
       try {
            // Define classe de conexao do jdbc
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            // Cria conexão
            // Conexão com banco Firebird legado (ATDB). Ajuste o caminho, usuário e senha para seu ambiente:
            conn = DriverManager.getConnection("jdbc:firebirdsql:<hostexemplo>:c:/atdb/dados/atdb.gdb","<userexemplo>","<senhaexemplo>");
        } catch (Exception e) {
            // Se der erro emite
            e.printStackTrace();
            return;
        }
       Transaction t;
       pessoaFisicaDAO = getDaoFactory().getPessoaFisicaDAO();
       pessoaJuridicaDAO = getDaoFactory().getPessoaJuridicaDAO();
       advogadoDAO = getDaoFactory().getAdvogadoDAO();
       processoDAO = getDaoFactory().getProcessoDAO();
       ufDAO = getDaoFactory().getUfDAO();
       acompanhamentoDAO = getDaoFactory().getAcompanhamentoDAO();
       acompanhamentoTipoDAO = getDaoFactory().getAcompanhamentoTipoDAO();
       audienciaTipoDAO = getDaoFactory().getAudienciaTipoDAO();
       varaDAO = getDaoFactory().getVaraDAO();
       estadoCivilDAO = getDaoFactory().getEstadoCivilDAO();
       acordoParcelaDAO = getDaoFactory().getAcordoParcelaDAO();
       
       String sql = "" +
               "Select * " +
               "from " +
               "PROCESSOS";
       
       t = processoDAO.getSession().beginTransaction();

       try{
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // executa
            ResultSet rs = pstmt.executeQuery();
            Processo processo;
            String observacaoConversaoProcesso;
            Vara vara;
            
            // se retornar algo
            while (rs.next()) { 

                observacaoConversaoProcesso = "";
                processo = new Processo();
                if(rs.getInt("JUNTA") == 0){
                    System.out.println("Vara zerada Processo:"+rs.getString("CODIGO")+ "Vara:0 ---- redefinindo para vara número 10");
                    observacaoConversaoProcesso += "\nVara convertida de 0 para 10";
                    vara = varaDAO.findById(10,false);    
                }else{
                    vara = varaDAO.findById(Integer.valueOf(rs.getInt("JUNTA")),false);    
                }
                
                
                if (processoDAO.buscaPorNumeroProcessoVara(rs.getString("CODIGO"), vara)!=null) {
                    System.out.println("Processo Duplicado:"+rs.getString("CODIGO")+ "Vara:" +vara.getId());
                    observacaoConversaoProcesso += "\nProcesso Duplicado:"+rs.getString("CODIGO")+ "Vara:" +vara.getId();
                    processo.setNumeroProcesso(rs.getString("CODIGO")+"DUPLICADO");
                }else{
                    processo.setNumeroProcesso(rs.getString("CODIGO"));
                }
                
                processo.setVara(vara);
                processo.setAutor(new Boolean(true));
                processo.setReclamante(pessoaFisicaDAO.buscaPorCodRequerente(Long.valueOf(rs.getLong("IDRECLAMANTE"))));
                if (processo.getReclamante().getPagaTaxa()){
                    processo.setPagaTaxaAbertura(processo.getReclamante().getPagaTaxa());
                    processo.setValorTaxaAbertura(processo.getReclamante().getValorTaxa());
                }
                processo.setReclamado(pessoaJuridicaDAO.buscaPorCodRequerido(Long.valueOf(rs.getLong("IDRECLAMADO"))));
                processo.setAdvogado(advogadoDAO.buscaPorCodAdvogado(Long.valueOf(rs.getLong("IDADVOGADO"))));
                processo.setArmario(rs.getString("NARMARIO"));
                processo.setGaveta(rs.getString("NGAVETA"));
                if (rs.getString("GEEI") == null){
                    processo.setGeei(false);
                }else {
                    processo.setGeei(rs.getString("GEEI").equalsIgnoreCase("S"));
                }

                if (rs.getString("TRT") == null){
                    processo.setTrt(false);
                }else {
                    processo.setTrt(rs.getString("TRT").equalsIgnoreCase("S"));
                }
                
                if (rs.getString("NRECURSO").equalsIgnoreCase("    /  ")){
                    processo.setRecurso("");
                }else{
                    processo.setRecurso(rs.getString("NRECURSO"));
                }
                
                if (rs.getString("OBS") != null){
                    observacaoConversaoProcesso = rs.getString("OBS")+ observacaoConversaoProcesso;
                }
                if (observacaoConversaoProcesso.equalsIgnoreCase("")) {
                    observacaoConversaoProcesso = null;
                }
                processo.setObservacao(observacaoConversaoProcesso);
                
                
                
                
                processoDAO.makePersistent(processo);
                setConta(getConta() + 1);
                
                //TODO ACOMPANHAMENTOS

                String sqlAcompanhamentos = "" +
                    "Select * " +
                    "from " +
                    "ANDAMENTO " +
                    "where IDPROCESSO = "+rs.getInt("ID") ;
                

                
                PreparedStatement pstmtA = conn.prepareStatement(sqlAcompanhamentos);
            
                // executa
                ResultSet rsA = pstmtA.executeQuery();
                Acompanhamento acompanhamento;
                String observacaoConversaoAcompanhamento;
            
                // se retornar algo
                while (rsA.next()) { 


                    observacaoConversaoAcompanhamento = "";
                    acompanhamento = new Acompanhamento();
                    acompanhamento.setProcesso(processo);
                    acompanhamento.setHistorico(rsA.getString("HISTORICO"));
                    acompanhamento.setDataCadastro(rsA.getDate("DTCADASTRO"));

                    if (rsA.getDate("DT_PROX_PROV")!=null){
                    Calendar dataProv = Calendar.getInstance();
                    dataProv.setTime(rsA.getDate("DT_PROX_PROV"));                    
                    if (Filters.onlyNumbers(rsA.getString("HR_PROX_PROV")).length() == 4) {
                        try {
                            String shora = Filters.onlyNumbers(rsA.getString("HR_PROX_PROV"));
                            dataProv.add(Calendar.HOUR, Integer.parseInt(shora.substring(0, 2)));
                            dataProv.add(Calendar.MINUTE, Integer.parseInt(shora.substring(2, 4)));
                        } catch (Exception e){
                            String mens = "Hora providencia nao pode ser cocnvertida: "+rsA.getString("HR_PROX_PROV");
                            System.out.println("Processo: "+processo.getNumeroProcesso()+" Vara:"+vara.getId()+" "+mens);
                            observacaoConversaoAcompanhamento += "\n"+mens;
                            e.printStackTrace();
                            System.exit(1);
                        }
                    }
                    acompanhamento.setDataProximaProvidencia(dataProv.getTime());       
                    }else{
                            String mens = "Data e Hora providencia nulo: ";
                            System.out.println("Processo: "+processo.getNumeroProcesso()+" Vara:"+vara.getId()+" "+mens);
                            observacaoConversaoAcompanhamento += "\n"+mens;
                        
                    }
                            
                    
                    if ((rsA.getString("BAIXA")!=null)&&(rsA.getString("BAIXA").equalsIgnoreCase("S"))){
                        acompanhamento.setBaixa(true);
                    }else{
                        acompanhamento.setBaixa(false);
                    }
                   
                    
                    if (rsA.getDate("DTBAIXA") != null) {
                        Calendar dataBaixa = Calendar.getInstance();
                        dataBaixa.setTime(rsA.getDate("DTBAIXA"));
                        if ((acompanhamento.getDataBaixa() != null) && (Filters.onlyNumbers(rsA.getString("HRBAIXA")).length() == 4)) {
                            try {
                                String hora = Filters.onlyNumbers(rsA.getString("HRBAIXA"));
                                dataBaixa.add(Calendar.HOUR, Integer.parseInt(hora.substring(0, 2)));
                                dataBaixa.add(Calendar.MINUTE, Integer.parseInt(hora.substring(2, 4)));
                            } catch (IllegalArgumentException e) {
                                String mens = "Hora baixa nao pode ser cocnvertida: " + rsA.getString("HR_PROX_PROV");
                                System.out.println("Processo: " + processo.getNumeroProcesso() + " Vara:" + vara.getId() + " " + mens);
                                observacaoConversaoAcompanhamento += "\n" + mens;
                            //e.printStackTrace();
                            }
                        }
                        acompanhamento.setDataBaixa(dataBaixa.getTime());
                    }
                    
                    acompanhamento.setAcompanhamentoTipo(acompanhamentoTipoDAO.findByLetra(rsA.getString("TIPO")));
                    acompanhamento.setAudienciaTipo(audienciaTipoDAO.findByLetra(rsA.getString("TIPO_AUDIENCIA")));
                    acompanhamento.setObservacao(rsA.getString("OBS")+observacaoConversaoAcompanhamento);

                    acompanhamento.setParcelaValor(rsA.getDouble("VL_PARCELA"));
                    acompanhamento.setParcela(rsA.getString("PARCELA"));
                    acompanhamentoDAO.makePersistent(acompanhamento);
                    if (acompanhamento.getAcompanhamentoTipo().getId() == 3){ //SE FOR ACORDO ENTRA COM PARCELA
                        AcordoParcela acordoParcela = new AcordoParcela();
                        acordoParcela.setAcompanhamento(acompanhamento);
                        acordoParcela.setParcela(acompanhamento.getParcela());
                        acordoParcela.setParcelaValor(acompanhamento.getParcelaValor());
                        acordoParcela.setVencimento(acompanhamento.getDataProximaProvidencia());                        
                        acordoParcelaDAO.makePersistent(acordoParcela);
                    }

                }
                
                
            }
            t.commit();
        } catch (Exception e){
                System.out.println(sql);
            e.printStackTrace();
            t.rollback();
        }
            
    }

    public SchedulaDAOFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(SchedulaDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public long getConta() {
        return conta;
    }

    public void setConta(long conta) {
        this.conta = conta;
    }
}
