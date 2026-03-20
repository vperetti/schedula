/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula.versoes;

import br.com.perettis.schedula.entity.*;
import br.com.perettis.schedula.model.*;
import java.util.Date;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2ddl.SchemaExport;
/**
 *
 * @author Vinicius Peretti
 */
public class Implantacao1 extends ImplantacaoAbstract {
    private SchedulaDAOFactory daoFactory;
    private SchedulaDAO schedulaDAO;
    private Schedula schedula;
    static Integer versaoImplantacao = new Integer(1);
    
    public boolean executa(SchedulaDAOFactory daoFactory) throws ImplantacaoException {
        //schedulaDAO = daoFactory.getSchedulaDAO();
        Transaction t;// = schedulaDAO.getSession().getTransaction();
        //t.begin();
        //schedula = schedulaDAO.busca();
        //if (schedula.getVersao() >= versaoImplantacao){
        //    throw new ImplantacaoException("Versao já foi instalada");
        //}
        
        // UPDATE SCHEMA, DDL
        SchemaExport schemaExport = new SchemaExport(daoFactory.getConfiguration());
        schemaExport.setFormat(true);
        schemaExport.create(true,true);

        
        /////////////////////////
        // INSTALA LISTA DE UF
        //

        UfDAO ufDAO = daoFactory.getUfDAO();
        estagio(1, versaoImplantacao, "Cadastrando UFs");
        
        String[][] aUfs = {
                {"Acre",                 "AC"},
                {"Alagoas",              "AL"},
                {"Amapá",                "AP"},
                {"Amazonas",             "AM"},
                {"Bahia",                "BA"},
                {"Ceará",                "CE"},
                {"Distrito Federal",     "DF"},
                {"Espírito Santo",       "ES"},
                {"Goiás",                "GO"},
                {"Maranhão",             "MA"},
                {"Mato Grosso",          "MT"},
                {"Mato Grosso do Sul",   "MS"},
                {"Minas Gerais",         "MG"},
                {"Paraná",               "PR"},
                {"Paraíba",              "PB"},
                {"Pará",                 "PA"},
                {"Pernambuco",           "PE"},
                {"Piauí",                "PI"},
                {"Rio de Janeiro",       "RJ"},
                {"Rio Grande do Norte",  "RN"},
                {"Rio Grande do Sul",    "RS"},
                {"Rondônia",             "RO"},
                {"Roraima",              "RR"},
                {"Santa Catarina",       "SC"},
                {"Sergipe",              "SE"},
                {"São Paulo",            "SP"},
                {"Tocantins",            "TO"}
        };
        t = ufDAO.getSession().getTransaction();
        t.begin();
        for (int i = 0; i < aUfs.length; i++) {
            String[] aUf = aUfs[i];
            Uf uf = new Uf();
            uf.setNome(aUf[0]);
            uf.setSigla(aUf[1]);
            ufDAO.makePersistent(uf);
        }
        t.commit();
        
        /////////////////////////////////////////////////////////
        // CADASTRA ROTINAS

        SchedulaRotinaDAO schedulaRotinaDAO = daoFactory.getSchedulaRotinaDAO();
        estagio(2, versaoImplantacao, "Cadastrando Rotinas");
        
        
        String[][] aRotinas = {
            {"TrocaSenha", "Permite ao usuário trocar a senha."},
            {"Processos-Dados", "Gestão de dados dos processos."},
            {"Processos-Dados-inclusao", "Gestão de dados dos processos. - inclusão"},
            {"Processos-Dados-alteracao", "Gestão de dados dos processos. - alteração"},
            {"Processos-Dados-exclusao", "Gestão de dados dos processos. - exclusão"},
            {"Processos-Acompanhamentos", "Gestão de Acompanhamentos."},
            {"Processos-Acompanhamentos-inclusao", "Gestão de Acompanhamentos. - inclusão"},
            {"Processos-Acompanhamentos-alteracao", "Gestão de Acompanhamentos. - alteração"},
            {"Processos-Acompanhamentos-exclusao", "Gestão de Acompanhamentos. - exclusão"},
            {"Cadastro-PessoaFisica", "Cadastro de Pessoas Físicas."},
            {"Cadastro-PessoaFisica-inclusao", "Cadastro de Pessoas Físicas. - inclusão"},
            {"Cadastro-PessoaFisica-alteracao", "Cadastro de Pessoas Físicas. - alteração"},
            {"Cadastro-PessoaFisica-exclusao", "Cadastro de Pessoas Físicas. - exclusão"},
            {"Cadastro-PessoaJuridica", "Cadastro de Pessoas Jurídicas."},
            {"Cadastro-PessoaJuridica-inclusao", "Cadastro de Pessoas Jurídicas. - inclusão"},
            {"Cadastro-PessoaJuridica-alteracao", "Cadastro de Pessoas Jurídicas. - alteração"},
            {"Cadastro-PessoaJuridica-exclusao", "Cadastro de Pessoas Jurídicas. - exclusão"},
            {"Cadastro-Advogado", "Cadastro de Advogados."},
            {"Cadastro-Advogado-inclusao", "Cadastro de Advogados. - inclusão"},
            {"Cadastro-Advogado-alteracao", "Cadastro de Advogados. - alteração"},
            {"Cadastro-Advogado-exclusao", "Cadastro de Advogados. - exclusão"},
            {"Parametros-CadastroVara", "Cadastro de Varas."},
            {"Parametros-CadastroVara-inclusao", "Cadastro de Varas. - inclusão"},
            {"Parametros-CadastroVara-alteracao", "Cadastro de Varas. - alteração"},
            {"Parametros-CadastroVara-exclusao", "Cadastro de Varas. - exclusão"},
            {"Parametros-CadastroComarca", "Cadastro de Comarcas."},
            {"Parametros-CadastroComarca-inclusao", "Cadastro de Comarcas. - inclusão"},
            {"Parametros-CadastroComarca-alteracao", "Cadastro de Comarcas. - alteração"},
            {"Parametros-CadastroComarca-exclusao", "Cadastro de Comarcas. - exclusão"},
            {"Parametros-CadastroEstadoCivil", "Cadastro de Estado Civil."},
            {"Parametros-CadastroEstadoCivil-inclusao", "Cadastro de Estado Civil. - inclusão"},
            {"Parametros-CadastroEstadoCivil-alteracao", "Cadastro de Estado Civil. - alteração"},
            {"Parametros-CadastroEstadoCivil-exclusao", "Cadastro de Estado Civil. - exclusão"},
            {"Parametros-CadastroTipoAudiencia", "Cadastro de Tipo de Audiências."},
            {"Parametros-CadastroTipoAudiencia-inclusao", "Cadastro de Tipo de Audiências. - inclusão"},
            {"Parametros-CadastroTipoAudiencia-alteracao", "Cadastro de Tipo de Audiências. - alteração"},
            {"Parametros-CadastroTipoAudiencia-exclusao", "Cadastro de Tipo de Audiências. - exclusão"},
            {"Suporte-CadastroUsuario", "Cadastro de Usuários."},
            {"Suporte-CadastroUsuario-inclusao", "Cadastro de Usuários. - inclusão"},
            {"Suporte-CadastroUsuario-alteracao", "Cadastro de Usuários. - alteração"},
            {"Suporte-CadastroUsuario-exclusao", "Cadastro de Usuários. - exclusão"},
            {"Parametros-OpcoesSchedula", "Opções do Sistema."},
            {"Relatorio-PautaDiaria", "Pauta Diária - Relatório de Acompanhamentos Diário."}
        };
        t = schedulaRotinaDAO.getSession().getTransaction();
        t.begin();
        for (int i = 0; i < aRotinas.length; i++) {
            String[] aRotina = aRotinas[i];
            SchedulaRotina rotina = new SchedulaRotina();
            rotina.setNome(aRotina[0]);
            rotina.setDescricao(aRotina[1]);
            schedulaRotinaDAO.makePersistent(rotina);
        }
        t.commit();
        

        
        
        /////////////////////////////////////////////////////////
        // CADASTRA estdos civis

        EstadoCivilDAO estadoCivilDAO = daoFactory.getEstadoCivilDAO();
        estagio(3, versaoImplantacao, "Cadastrando Estados Civis");
        
        
        String[][] aEstadosCivis = {
            {"solteiro(a)", "quem nunca se casou, o que teve o casamento anulado"},
            {"casado(a)", "quem contraiu matrimônio, independente do regime de bens adotado"},
            {"separado(a) judicialmente", "quem não vive mais com o cônjuge (vive em separação física dele), mas que ainda não obteve o divórcio"},
            {"divorciado(a)", "após a homologação do divórcio pela justiça"},
            {"viúvo(a)", "pessoa cujo cônjuge faleceu."},
            {"união estável", "condição de convivência considerada como entidade familiar não registrada (como o casamento), onde são exigidos o atendimento de quatro requisitos fundamentais: que a convivência seja duradoura, seja pública, seja contínua, e que a união tenha o objetivo de constituir família (Lei 9.278/96)."}
        };
        t = estadoCivilDAO.getSession().getTransaction();
        t.begin();
        for (int i = 0; i < aEstadosCivis.length; i++) {
            String[] aEstadoCivil = aEstadosCivis[i];
            EstadoCivil estadoCivil = new EstadoCivil();
            estadoCivil.setNome(aEstadoCivil[0]);
            estadoCivil.setDescricao(aEstadoCivil[1]);
            estadoCivilDAO.makePersistent(estadoCivil);
        }
        t.commit();


        /////////////////////////////////////////////////////////
        // CADASTRA aCOMPANHAMENTOS TIPOS

        AcompanhamentoTipoDAO acompanhamentoTipoDAO = daoFactory.getAcompanhamentoTipoDAO();
        estagio(4, versaoImplantacao, "Cadastrando Acompanhamento Tipo");
        
        
        String[][] aAcompanhamentoTipos = {
            {"Audiência"},
            {"Providência"},
            {"Acordo"}
        };
        t = acompanhamentoTipoDAO.getSession().getTransaction();
        t.begin();
        for (int i = 0; i < aAcompanhamentoTipos.length; i++) {
            String[] aAcompanhamentoTipo = aAcompanhamentoTipos[i];
            AcompanhamentoTipo acompanhamentoTipo = new AcompanhamentoTipo();
            acompanhamentoTipo.setDescricao(aAcompanhamentoTipo[0]);
            acompanhamentoTipoDAO.makePersistent(acompanhamentoTipo);
        }
        t.commit();

        /////////////////////////////////////////////////////////
        // CADASTRA AUDIENCIA TIPOS

        AudienciaTipoDAO audienciaTipoDAO = daoFactory.getAudienciaTipoDAO();
        estagio(5, versaoImplantacao, "Cadastrando Audiência Tipo");
        
        
        String[][] aAudienciaTipos = {
            {"Conciliação"},
            {"Encerramento"},
            {"Instrução"},
            {"Julgamento"},
            {"Tentativa de Acordo"}
        };
        t = audienciaTipoDAO.getSession().getTransaction();
        t.begin();
        for (int i = 0; i < aAudienciaTipos.length; i++) {
            String[] aAudienciaTipo = aAudienciaTipos[i];
            AudienciaTipo audienciaTipo = new AudienciaTipo();
            audienciaTipo.setDescricao(aAudienciaTipo[0]);
            audienciaTipoDAO.makePersistent(audienciaTipo);
        }
        t.commit();
        
        //////////////////////////////////////////////////////////////////
        // DEFINE DADOS DO PROGRAMA
        estagio(6, versaoImplantacao, "Definindo Dados do Programa");
        schedulaDAO = daoFactory.getSchedulaDAO();
        t = schedulaDAO.getSession().getTransaction();
        t.begin();
        schedula = new Schedula(); // NA PROXIMA VERSAO NAO CRIA NOVO, BUSCA DO BANCO O OUTRO E ATUALIZA
        schedula.setDataInstalacao(new Date());
        schedula.setVersao(versaoImplantacao);
        schedulaDAO.makePersistent(schedula);
        t.commit();
        
        
        return true;
    }
}
