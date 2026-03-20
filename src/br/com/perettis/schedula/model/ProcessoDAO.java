/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.Processo;
import br.com.perettis.schedula.entity.Vara;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class ProcessoDAO
	extends GenericHibernateDAO<Processo, Long> {

	public Processo buscaPorNumeroProcessoVara(String numeroProcesso, Vara vara) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("numeroProcesso", numeroProcesso));
        criteria.add(Expression.eq("vara", vara));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (Processo) result.get(0);
		}
	}
}
