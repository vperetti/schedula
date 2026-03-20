package br.com.perettis.schedula.versoes;

import br.com.perettis.gets.dao.DAOFactory;
import br.com.perettis.gets.text.Filters;
import br.com.perettis.schedula.entity.PessoaJuridica;
import br.com.perettis.schedula.entity.Schedula;
import br.com.perettis.schedula.model.AdvogadoDAO;
import br.com.perettis.schedula.model.PessoaJuridicaDAO;
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
public class doAdtb_Requeridos {

    private SchedulaDAOFactory daoFactory;
    /**
     * Variavel guarda conexão
     */
    private static Connection conn;
    public PessoaJuridicaDAO pessoaJuridicaDAO;
    public UfDAO ufDAO;
    private long conta = 0;

    public doAdtb_Requeridos(SchedulaDAOFactory daoFactory) {
        setDaoFactory(daoFactory);
    }

    public void executa() {
        try {
            // Define classe de conexao do jdbc
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            // Cria conexão
            // Conexão com banco Firebird legado (ATDB). Ajuste o caminho, usuário e senha para seu ambiente:
            conn = DriverManager.getConnection("jdbc:firebirdsql:<hostexemplo>:c:/atdb/dados/atdb.gdb", "<userexemplo>", "<senhaexemplo>");
        } catch (Exception e) {
            // Se der erro emite
            e.printStackTrace();
            return;
        }
        Transaction t;
        pessoaJuridicaDAO = getDaoFactory().getPessoaJuridicaDAO();
        ufDAO = getDaoFactory().getUfDAO();

        String sql = "" +
                "Select * " +
                "from " +
                "RECLAMADOS";

        t = pessoaJuridicaDAO.getSession().beginTransaction();

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // executa
            ResultSet rs = pstmt.executeQuery();
            PessoaJuridica reclamado;
            String observacaoConversao;

            // se retornar algo
            while (rs.next()) {
                // Dimencao Tempo

                //data = rs.getDate("DATAVENDA");
                //if ((dataAtual == null)||(!dataAtual.equals(data))){
                //    dataAtual = data;
                //    tempo = new Tempo(data);
                //    dao.cria(tempo);
                //}

                reclamado = new PessoaJuridica();
                observacaoConversao = "";
                reclamado.setCodRequerido(rs.getLong("ID"));
                reclamado.setNome(rs.getString("NOME"));



                if (Filters.onlyNumbers(rs.getString("CNPJ")).length() > 0) {
                    if (pessoaJuridicaDAO.buscaPorCnpj(Filters.onlyNumbers(rs.getString("CNPJ"))) == null) {
                        reclamado.setCnpj(Filters.onlyNumbers(rs.getString("CNPJ")));
                    } else {
                        observacaoConversao += "\nCNPJ duplicado:"+rs.getString("CNPJ");
                    }
                }

                reclamado.setInscEstadual(rs.getString("IE"));


                reclamado.setEndereco(rs.getString("ENDERECO"));
                reclamado.setBairro(rs.getString("BAIRRO"));
                reclamado.setCep(Filters.onlyNumbers(rs.getString("CEP")));
                reclamado.setCidade(rs.getString("CIDADE"));
                reclamado.setUf(ufDAO.buscaPorSigla(rs.getString("UF")));
                reclamado.setFone1(rs.getString("FONE1"));
                reclamado.setFone2(rs.getString("FONE2"));
                
                if (rs.getString("OBS") != null){
                    observacaoConversao = rs.getString("OBS")+ observacaoConversao;
                }
                if (observacaoConversao.equalsIgnoreCase("")) {
                    observacaoConversao = null;
                }
                reclamado.setObservacao(observacaoConversao);
                
                
                reclamado.setSocios(rs.getString("SOCIOS"));
                reclamado.setCodigoEmpresa(rs.getString("CODEMPRESA"));
                pessoaJuridicaDAO.makePersistent(reclamado);
                setConta(getConta() + 1);
            }
            t.commit();
        } catch (Exception e) {
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
