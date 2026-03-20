/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.PessoaJuridica;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class PessoaJuridicaDAO
	extends GenericHibernateDAO<PessoaJuridica, Long> {

    public PessoaJuridica buscaPorCnpj(String cnpj) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("cnpj", cnpj));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (PessoaJuridica) result.get(0);
		}
	}
    public PessoaJuridica buscaPorIe(String ie) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("inscEstadual", ie));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (PessoaJuridica) result.get(0);
		}
	}
    
    public PessoaJuridica buscaPorCodRequerido(Long cod) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("codRequerido", cod));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (PessoaJuridica) result.get(0);
		}
	}
}
