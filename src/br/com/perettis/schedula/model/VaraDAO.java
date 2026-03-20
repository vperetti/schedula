/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.Comarca;
import br.com.perettis.schedula.entity.Vara;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class VaraDAO
	extends GenericHibernateDAO<Vara, Integer> {

	public Vara buscaPorNome(String nome) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("nome", nome));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (Vara) result.get(0);
		}
	}
    public Vara buscaPorNomeComarca(String nome, Comarca comarca) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("nome", nome));
        criteria.add(Expression.eq("comarca", comarca));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (Vara) result.get(0);
		}
	}
}
