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
import br.com.perettis.schedula.entity.Comarca;
import br.com.perettis.schedula.model.ComarcaDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.SwingConstants;

/**
 *
 * @author vinicius
 */
public class PesquisaComarca extends FormSearch<Integer, Comarca, ComarcaDAO> {

    public PesquisaComarca(SchedulaDAOFactory daoFactory) {
        super();
        Set<FormSearchColumn> colunas = new LinkedHashSet<FormSearchColumn>();
        FormSearchColumn fscNome = new FormSearchColumn();
        fscNome.setProperty("nome");
        fscNome.setPropertyClass(String.class);
        fscNome.setLabel("Nome");
        fscNome.setWidth(400);
        fscNome.setAlign(SwingConstants.LEFT);
        fscNome.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscNome);


        FormSearchColumn fscId = new FormSearchColumn();
        fscId.setProperty("id");
        fscId.setPropertyClass(Integer.class);
        fscId.setLabel("ID");
        fscId.setWidth(100);
        fscId.setAlign(SwingConstants.RIGHT);
        fscId.setTypeSearch(DAOConstants.ABOVE_EQUAL);
        colunas.add(fscId);


        // Cria formatador da coluna desabilitado
        BooleanFormat desFormat = new BooleanFormat();
        desFormat.setTrueString("desabilitado");
        desFormat.setFalseString("habilitado");

        FormSearchColumn fscDesabilitado = new FormSearchColumn();
        fscDesabilitado.setProperty("desabilitado");
        fscDesabilitado.setPropertyClass(Boolean.class);
        fscDesabilitado.setLabel("Situação");
        fscDesabilitado.setWidth(10);
        fscDesabilitado.setAlign(SwingConstants.CENTER);
        fscDesabilitado.setTypeSearch(DAOConstants.EQUAL);
        fscDesabilitado.setFormatter(desFormat);
        colunas.add(fscDesabilitado);


        super.setIdProperty("id");
        super.setIdClass(Integer.class);
        super.setColumns(colunas);
        super.setDefaultProperty("id");
        super.setEntityClass(Comarca.class);
        super.setDaoClass(ComarcaDAO.class);
        super.setDaoFactory((DAOFactoryAbstract) daoFactory);
        super.makeForm();
        super.getRbAny().setSelected(true);
    }
}
