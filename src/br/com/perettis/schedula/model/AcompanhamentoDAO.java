package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.DAOConstants;
import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.Acompanhamento;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author Vinicius Peretti
 */
public class AcompanhamentoDAO
	extends GenericHibernateDAO<Acompanhamento, Long> {
    
    public List<Acompanhamento> listByDataProvidencia(Date dataProvidenciaIni, Date dataProvidenciaFim){
        dataProvidenciaFim.setDate(dataProvidenciaFim.getDate() + 1);
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Expression.ge("dataProximaProvidencia", dataProvidenciaIni));
        criteria.add(Expression.lt("dataProximaProvidencia", dataProvidenciaFim));
        return criteria.list();
    }
}
