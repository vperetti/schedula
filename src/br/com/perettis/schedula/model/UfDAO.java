/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.Uf;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class UfDAO
	extends GenericHibernateDAO<Uf, Long> {

	public Uf buscaPorSigla(String sigla) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("sigla", sigla));
		List<Uf> result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}
}
