/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula;

import br.com.perettis.gets.dao.DAOConstants;
import br.com.perettis.gets.dao.DAOFactoryAbstract;
import br.com.perettis.gets.swing.FormSearch;
import br.com.perettis.gets.swing.FormSearchColumn;
import br.com.perettis.schedula.entity.PessoaJuridica;
import br.com.perettis.schedula.model.PessoaJuridicaDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.SwingConstants;

/**
 *
 * @author vinicius
 */
public class PesquisaPessoaJuridica extends FormSearch<Long, PessoaJuridica, PessoaJuridicaDAO> {

    public PesquisaPessoaJuridica(SchedulaDAOFactory daoFactory) {
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

        FormSearchColumn fscCnpj = new FormSearchColumn();
        fscCnpj.setProperty("cnpj");
        fscCnpj.setPropertyClass(String.class);
        fscCnpj.setLabel("CNPJ");
        fscCnpj.setWidth(100);
        fscCnpj.setAlign(SwingConstants.RIGHT);
        fscCnpj.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscCnpj);

        FormSearchColumn fscIe = new FormSearchColumn();
        fscIe.setProperty("inscEstadual");
        fscIe.setPropertyClass(String.class);
        fscIe.setLabel("IE");
        fscIe.setWidth(200);
        fscIe.setAlign(SwingConstants.LEFT);
        fscIe.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscIe);
       
        FormSearchColumn fscIm = new FormSearchColumn();
        fscIm.setProperty("inscMunicipal");
        fscIm.setPropertyClass(String.class);
        fscIm.setLabel("IM");
        fscIm.setWidth(200);
        fscIm.setAlign(SwingConstants.LEFT);
        fscIm.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscIm);


        
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
        super.setEntityClass(PessoaJuridica.class);
        super.setDaoClass(PessoaJuridicaDAO.class);
        super.setDaoFactory((DAOFactoryAbstract) daoFactory);
        super.makeForm();
        super.getRbAny().setSelected(true);
    }
}
