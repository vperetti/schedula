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
import br.com.perettis.schedula.entity.Advogado;
import br.com.perettis.schedula.model.AdvogadoDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.SwingConstants;

/**
 *
 * @author vinicius
 */
public class PesquisaAdvogado extends FormSearch<Long, Advogado, AdvogadoDAO> {

    public PesquisaAdvogado(SchedulaDAOFactory daoFactory) {
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

        FormSearchColumn fscOab = new FormSearchColumn();
        fscOab.setProperty("oab");
        fscOab.setPropertyClass(String.class);
        fscOab.setLabel("OAB");
        fscOab.setWidth(200);
        fscOab.setAlign(SwingConstants.LEFT);
        fscOab.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscOab);

        FormSearchColumn fscOabUf = new FormSearchColumn();
        fscOabUf.setProperty("oabUf.sigla");
        fscOabUf.setPropertyClass(String.class);
        fscOabUf.setLabel("OABUF");
        fscOabUf.setWidth(100);
        fscOabUf.setAlign(SwingConstants.LEFT);
        fscOabUf.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscOabUf);

        FormSearchColumn fscId = new FormSearchColumn();
        fscId.setProperty("id");
        fscId.setPropertyClass(Long.class);
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
        super.setIdClass(Long.class);
        super.setColumns(colunas);
        super.setDefaultProperty("id");
        super.setEntityClass(Advogado.class);
        super.setDaoClass(AdvogadoDAO.class);
        super.setDaoFactory((DAOFactoryAbstract) daoFactory);
        super.makeForm();
        super.getRbAny().setSelected(true);
    }
}
