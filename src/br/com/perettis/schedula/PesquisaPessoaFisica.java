/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula;

import br.com.perettis.gets.dao.DAOConstants;
import br.com.perettis.gets.dao.DAOFactoryAbstract;
import br.com.perettis.gets.swing.FormSearch;
import br.com.perettis.gets.swing.FormSearchColumn;
import br.com.perettis.gets.text.BooleanFormat;
import br.com.perettis.schedula.entity.PessoaFisica;
import br.com.perettis.schedula.model.PessoaFisicaDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.SwingConstants;

/**
 *
 * @author vinicius
 */
public class PesquisaPessoaFisica extends FormSearch<Long, PessoaFisica, PessoaFisicaDAO> {

    public PesquisaPessoaFisica(SchedulaDAOFactory daoFactory) {
        super();
        Set<FormSearchColumn> colunas = new LinkedHashSet<FormSearchColumn>();
        
        
        FormSearchColumn fscNome = new FormSearchColumn();
        fscNome.setProperty("nome");
        fscNome.setPropertyClass(String.class);
        fscNome.setLabel("Nome");
        fscNome.setWidth(200);
        fscNome.setAlign(SwingConstants.LEFT);
        fscNome.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscNome);

        FormSearchColumn fscRg = new FormSearchColumn();
        fscRg.setProperty("rg");
        fscRg.setPropertyClass(String.class);
        fscRg.setLabel("RG");
        fscRg.setWidth(200);
        fscRg.setAlign(SwingConstants.LEFT);
        fscRg.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscRg);

        FormSearchColumn fsccpf = new FormSearchColumn();
        fsccpf.setProperty("cpf");
        fsccpf.setPropertyClass(String.class);
        fsccpf.setLabel("CPF");
        fsccpf.setWidth(100);
        fsccpf.setAlign(SwingConstants.RIGHT);
        fsccpf.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fsccpf);

        FormSearchColumn fscId = new FormSearchColumn();
        fscId.setProperty("id");
        fscId.setPropertyClass(Long.class);
        fscId.setLabel("ID");
        fscId.setWidth(100);
        fscId.setAlign(SwingConstants.RIGHT);
        fscId.setTypeSearch(DAOConstants.ABOVE_EQUAL);
        colunas.add(fscId);



        super.setIdProperty("id");
        super.setIdClass(Long.class);
        super.setColumns(colunas);
        super.setDefaultProperty("nome");
        super.setEntityClass(PessoaFisica.class);
        super.setDaoClass(PessoaFisicaDAO.class);
        super.setDaoFactory((DAOFactoryAbstract) daoFactory);
        super.makeForm();
        super.getRbAny().setSelected(true);
    }
}
