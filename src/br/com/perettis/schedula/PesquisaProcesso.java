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
import br.com.perettis.schedula.entity.Processo;
import br.com.perettis.schedula.model.ProcessoDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.SwingConstants;

/**
 *
 * @author vinicius
 */
public class PesquisaProcesso extends FormSearch<Long, Processo, ProcessoDAO> {

    public PesquisaProcesso(SchedulaDAOFactory daoFactory) {
        super();
        Set<FormSearchColumn> colunas = new LinkedHashSet<FormSearchColumn>();
        
        
        FormSearchColumn fscNumero = new FormSearchColumn();
        fscNumero.setProperty("numeroProcesso");
        fscNumero.setPropertyClass(String.class);
        fscNumero.setLabel("Número");
        fscNumero.setWidth(100);
        fscNumero.setAlign(SwingConstants.LEFT);
        fscNumero.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscNumero);

        FormSearchColumn fscVara = new FormSearchColumn();
        fscVara.setProperty("vara.nome");
        fscVara.setPropertyClass(String.class);
        fscVara.setLabel("Vara");
        fscVara.setWidth(100);
        fscVara.setAlign(SwingConstants.LEFT);
        fscVara.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscVara);
        
        FormSearchColumn fscAdvogado = new FormSearchColumn();
        fscAdvogado.setProperty("advogado.nome");
        fscAdvogado.setPropertyClass(String.class);
        fscAdvogado.setLabel("Advogado");
        fscAdvogado.setWidth(200);
        fscAdvogado.setAlign(SwingConstants.LEFT);
        fscAdvogado.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscAdvogado);

        // Cria formatador da coluna aposicao
        BooleanFormat desFormat = new BooleanFormat();
        desFormat.setTrueString("autor");
        desFormat.setFalseString("réu");

        FormSearchColumn fscPosicao = new FormSearchColumn();
        fscPosicao.setProperty("autor");
        fscPosicao.setPropertyClass(Boolean.class);
        fscPosicao.setLabel("Posição");
        fscPosicao.setWidth(10);
        fscPosicao.setAlign(SwingConstants.CENTER);
        fscPosicao.setTypeSearch(DAOConstants.EQUAL);
        fscPosicao.setFormatter(desFormat);
        colunas.add(fscPosicao);
        
        FormSearchColumn fscRequerido = new FormSearchColumn();
        fscRequerido.setProperty("reclamado.nome");
        fscRequerido.setPropertyClass(String.class);
        fscRequerido.setLabel("Requerido");
        fscRequerido.setWidth(200);
        fscRequerido.setAlign(SwingConstants.LEFT);
        fscRequerido.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscRequerido);

        FormSearchColumn fscRequerente = new FormSearchColumn();
        fscRequerente.setProperty("reclamante.nome");
        fscRequerente.setPropertyClass(String.class);
        fscRequerente.setLabel("Requerente");
        fscRequerente.setWidth(200);
        fscRequerente.setAlign(SwingConstants.LEFT);
        fscRequerente.setTypeSearch(DAOConstants.LIKE);
        colunas.add(fscRequerente);

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
        super.setDefaultProperty("numeroProcesso");
        super.setEntityClass(Processo.class);
        super.setDaoClass(ProcessoDAO.class);
        super.setDaoFactory((DAOFactoryAbstract) daoFactory);
        super.makeForm();
        super.getRbAny().setSelected(true);
    }
}
