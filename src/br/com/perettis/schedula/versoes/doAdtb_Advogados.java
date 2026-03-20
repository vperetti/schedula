package br.com.perettis.schedula.versoes;

import br.com.perettis.gets.dao.DAOFactory;
import br.com.perettis.schedula.entity.Advogado;
import br.com.perettis.schedula.entity.EstadoCivil;
import br.com.perettis.schedula.entity.Schedula;
import br.com.perettis.schedula.model.AdvogadoDAO;
import br.com.perettis.schedula.model.EstadoCivilDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import br.com.perettis.schedula.model.UfDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.hibernate.Transaction;

/**
 *
 * @author Vinicius Peretti
 */
public class doAdtb_Advogados {
    private SchedulaDAOFactory daoFactory;
    /**
     * Variavel guarda conexão
     */
    private static Connection conn;
    public AdvogadoDAO advogadoDAO;
    public UfDAO ufDAO;
    public EstadoCivilDAO estadoCivilDAO;
    private long conta = 0;
    
    public doAdtb_Advogados(SchedulaDAOFactory daoFactory){
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
       advogadoDAO = getDaoFactory().getAdvogadoDAO();
       ufDAO = getDaoFactory().getUfDAO();
       estadoCivilDAO = getDaoFactory().getEstadoCivilDAO();
       
       String sql = "" +
               "Select " +
               "ID, " +
               "NOME, " +
               "ENDERECO, " +
               "BAIRRO, " +
               "CEP, " +
               "CIDADE, " +
               "UF, " +
               "FONE1, " +
               "FONE2, " +
               "FONE3, " +
               "OBS, " +
               "OAB, " +
               "UF_OAB, " +
               "EST_CIV, " +
               "PROF " +
               "from " +
               "ADVOGADOS";
       
       t = advogadoDAO.getSession().beginTransaction();

       try{
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // executa
            ResultSet rs = pstmt.executeQuery();
            Advogado advogado;
            
            // se retornar algo
            while (rs.next()) { 
                // Dimencao Tempo
                
                //data = rs.getDate("DATAVENDA");
                //if ((dataAtual == null)||(!dataAtual.equals(data))){
                //    dataAtual = data;
                //    tempo = new Tempo(data);
                //    dao.cria(tempo);
                //}
                
                advogado = new Advogado();
                advogado.setCodAdvogado(rs.getLong("ID"));
                advogado.setNome(rs.getString("NOME"));
                advogado.setEndereco(rs.getString("ENDERECO"));
                advogado.setBairro(rs.getString("BAIRRO"));
                advogado.setCep(rs.getString("CEP"));
                advogado.setCidade(rs.getString("CIDADE"));
                advogado.setUf(ufDAO.buscaPorSigla(rs.getString("UF")));
                advogado.setFone1(rs.getString("FONE1"));
                advogado.setFone2(rs.getString("FONE2"));
                advogado.setFone3(rs.getString("FONE3"));
                advogado.setObservacao(rs.getString("OBS"));
                advogado.setOab(rs.getString("OAB"));
                advogado.setOabUf(ufDAO.buscaPorSigla(rs.getString("UF_OAB")));
                advogado.setEstadoCivil(estadoCivilDAO.findByLetra(rs.getString("EST_CIV")));
                advogado.setProfissao(rs.getString("PROF"));
                advogado.setSexo(true);
                advogadoDAO.makePersistent(advogado);
                System.out.println("Advogado "+advogado.getId() +" inserido");
                setConta(getConta() + 1);
                
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
