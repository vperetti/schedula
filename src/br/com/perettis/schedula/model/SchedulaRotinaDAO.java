/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.SchedulaRotina;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

/**
 *
 * @author vinicius
 */
public class SchedulaRotinaDAO
	extends GenericHibernateDAO<SchedulaRotina, Long> {

	public SchedulaRotina buscaPorNome(String nome) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("nome", nome));
		List<SchedulaRotina> result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}
    
    public List<SchedulaRotina> buscaLista(){
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("nome"));
		return criteria.list();
    }
}
