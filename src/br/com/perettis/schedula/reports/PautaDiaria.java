/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.perettis.schedula.reports;

import br.com.perettis.gets.dao.DAOFactory;
import br.com.perettis.schedula.entity.Acompanhamento;
import br.com.perettis.schedula.entity.AcordoParcela;
import br.com.perettis.schedula.model.AcompanhamentoDAO;
import br.com.perettis.schedula.model.AcordoParcelaDAO;
import br.com.perettis.schedula.model.SchedulaDAOFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author vinicius
 */
public class PautaDiaria {
    private SchedulaDAOFactory daoFactory;
    Set lista = new LinkedHashSet();
    AcompanhamentoDAO acompanhamentoDAO;
    AcordoParcelaDAO acordoParcelaDAO;
    public Set generate(Date dateIni, Date dateEnd){
        
        acompanhamentoDAO = daoFactory.getAcompanhamentoDAO();
        List<Acompanhamento> listaAcompanhamento = acompanhamentoDAO.listByDataProvidencia(dateIni, dateEnd);
        Iterator<Acompanhamento> itAcompanhamento = listaAcompanhamento.iterator();
        while (itAcompanhamento.hasNext()) {
            Acompanhamento acompanhamento = itAcompanhamento.next();
            lista.add(new PautaDiariaBean(acompanhamento));
        }

        acordoParcelaDAO = daoFactory.getAcordoParcelaDAO();
        List<AcordoParcela> listaAcordoParcela = acordoParcelaDAO.listByVencimento(dateIni, dateEnd);
        Iterator<AcordoParcela> itAcordoParcela = listaAcordoParcela.iterator();
        while (itAcordoParcela.hasNext()) {
            AcordoParcela acordoParcela = itAcordoParcela.next();
            lista.add(new PautaDiariaBean(acordoParcela));
        }
        
        
        return lista;
    }

    public SchedulaDAOFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(SchedulaDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
