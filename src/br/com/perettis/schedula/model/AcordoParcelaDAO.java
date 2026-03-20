package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.AcordoParcela;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author Vinicius Peretti
 */
public class AcordoParcelaDAO
	extends GenericHibernateDAO<AcordoParcela, Long> {
    
    public List<AcordoParcela> listByVencimento(Date dataVencimentoIni, Date dataVencimentoFim){
        dataVencimentoFim.setDate(dataVencimentoFim.getDate() + 1);
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Expression.ge("vencimento", dataVencimentoIni));
        criteria.add(Expression.lt("vencimento", dataVencimentoFim));
        return criteria.list();
    }
}
