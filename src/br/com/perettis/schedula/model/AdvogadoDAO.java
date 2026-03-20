/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.gets.validators.DocumentsBR;
import br.com.perettis.schedula.entity.Advogado;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class AdvogadoDAO
	extends GenericHibernateDAO<Advogado, Long> {

    //TODO EXTENDER PESSOAFISICADAO
    
    public Advogado buscaPorCpf(String cpf) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("cpf", cpf));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (Advogado) result.get(0);
		}
	}
    public Advogado buscaPorCodAdvogado(Long cod) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("codAdvogado", cod));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (Advogado) result.get(0);
		}
	}
}
