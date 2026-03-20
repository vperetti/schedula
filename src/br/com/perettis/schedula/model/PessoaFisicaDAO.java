/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.gets.validators.DocumentsBR;
import br.com.perettis.schedula.entity.PessoaFisica;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class PessoaFisicaDAO
	extends GenericHibernateDAO<PessoaFisica, Long> {

    
    public PessoaFisica buscaPorCpf(String cpf) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("cpf", cpf));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (PessoaFisica) result.get(0);
		}
	}
    public PessoaFisica buscaPorCodRequerente(Long cod) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("codRequerente", cod));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (PessoaFisica) result.get(0);
		}
	}
    public PessoaFisica buscaPorRg(String rg, String oe) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("rg", rg));
        criteria.add(Expression.eq("rgOrgaoExpeditor", oe));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (PessoaFisica) result.get(0);
		}
	}
    public PessoaFisica buscaPorCt(String ct, String cts) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("carteiraTrabalho", ct));
        criteria.add(Expression.eq("carteiraTrabalhoSerie", cts));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (PessoaFisica) result.get(0);
		}
	}
}
