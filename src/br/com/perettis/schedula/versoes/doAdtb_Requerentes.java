package br.com.perettis.schedula.versoes;

import br.com.perettis.gets.dao.DAOFactory;
import br.com.perettis.gets.text.Filters;
import br.com.perettis.schedula.entity.PessoaFisica;
import br.com.perettis.schedula.entity.EstadoCivil;
import br.com.perettis.schedula.entity.Schedula;
import br.com.perettis.schedula.model.AdvogadoDAO;
import br.com.perettis.schedula.model.EstadoCivilDAO;
import br.com.perettis.schedula.model.PessoaFisicaDAO;
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
public class doAdtb_Requerentes {

    private SchedulaDAOFactory daoFactory;
    /**
     * Variavel guarda conexão
     */
    private static Connection conn;
    public PessoaFisicaDAO pessoaFisicaDAO;
    public UfDAO ufDAO;
    public EstadoCivilDAO estadoCivilDAO;
    private long conta = 0;

    public doAdtb_Requerentes(SchedulaDAOFactory daoFactory) {
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
        pessoaFisicaDAO = getDaoFactory().getPessoaFisicaDAO();
        ufDAO = getDaoFactory().getUfDAO();
        estadoCivilDAO = getDaoFactory().getEstadoCivilDAO();

        String sql = "" +
                "Select * " +
                "from " +
                "RECLAMANTES";

        t = pessoaFisicaDAO.getSession().beginTransaction();

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // executa
            ResultSet rs = pstmt.executeQuery();
            PessoaFisica reclamante;
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

                reclamante = new PessoaFisica();
                observacaoConversao = "";
                reclamante.setCodRequerente(rs.getLong("ID"));
                reclamante.setNome(rs.getString("NOME"));
                reclamante.setDataNascimento(rs.getDate("DT_NASC"));

                if ((rs.getString("SEXO") == null) || (rs.getString("SEXO").equalsIgnoreCase("M"))) {
                    reclamante.setSexo(true);
                } else {
                    reclamante.setSexo(false);
                }
                reclamante.setEstadoCivil(estadoCivilDAO.findByLetra(rs.getString("EST_CIV")));

                reclamante.setProfissao(rs.getString("PROFISSAO"));


                if (Filters.onlyNumbers(rs.getString("CPF")).length() > 0) {
                    if (pessoaFisicaDAO.buscaPorCpf(Filters.onlyNumbers(rs.getString("CPF"))) == null) {
                        reclamante.setCpf(Filters.onlyNumbers(rs.getString("CPF")));
                    } else {
                        observacaoConversao += "\nCPF duplicado:"+rs.getString("CPF");
                    }
                }

                if (pessoaFisicaDAO.buscaPorRg(rs.getString("RG"), rs.getString("ORGAO")) == null) {
                    reclamante.setRg(rs.getString("RG"));
                    reclamante.setRgOrgaoExpeditor(rs.getString("ORGAO"));
                } else {
                    reclamante.setRg("DUPLICADO!" + rs.getString("RG"));
                    reclamante.setRgOrgaoExpeditor(rs.getString("ORGAO"));
                }

                if (pessoaFisicaDAO.buscaPorCt(rs.getString("CT_TRAB"), rs.getString("SERIE")) == null) {
                    reclamante.setCarteiraTrabalho(rs.getString("CT_TRAB"));
                    reclamante.setCarteiraTrabalhoSerie(rs.getString("SERIE"));
                } else {
                    reclamante.setCarteiraTrabalho("DUPLICADO!" + rs.getString("CT_TRAB"));
                    reclamante.setCarteiraTrabalhoSerie(rs.getString("SERIE"));
                }

                reclamante.setEndereco(rs.getString("ENDERECO"));
                reclamante.setBairro(rs.getString("BAIRRO"));
                reclamante.setCep(Filters.onlyNumbers(rs.getString("CEP")));
                reclamante.setCidade(rs.getString("CIDADE"));
                reclamante.setUf(ufDAO.buscaPorSigla(rs.getString("UF")));
                reclamante.setFone1(rs.getString("FONE1"));
                reclamante.setContato1(rs.getString("CONTATO1"));
                reclamante.setFone2(rs.getString("FONE2"));
                reclamante.setContato2(rs.getString("CONTATO2"));
                reclamante.setFone3(rs.getString("FONE3"));
                reclamante.setContato3(rs.getString("CONTATO3"));


                if (rs.getString("PAG_TAXA") == null) {
                    reclamante.setPagaTaxa(false);
                } else if (rs.getString("PAG_TAXA").equalsIgnoreCase("S")) {
                    reclamante.setPagaTaxa(true);
                } else {
                    reclamante.setPagaTaxa(false);
                }

                reclamante.setValorTaxa(rs.getDouble("VL_TAXA"));
                
                if (rs.getString("OBS") != null){
                    observacaoConversao = rs.getString("OBS")+ observacaoConversao;
                }
                if (observacaoConversao.equalsIgnoreCase("")) {
                    observacaoConversao = null;
                }
                reclamante.setObservacao(observacaoConversao);
                
                reclamante.setNomeMae(rs.getString("MAE"));
                reclamante.setNacionalidade(rs.getString("NACIONALIDADE"));
                reclamante.setNaturalidade(rs.getString("NATURALIDADE"));
                pessoaFisicaDAO.makePersistent(reclamante);
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
