/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.Usuario;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class UsuarioDAO
	extends GenericHibernateDAO<Usuario, Long> {

	public Usuario buscaPorApelido(String apelido) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("apelido", apelido));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (Usuario) result.get(0);
		}
	}
}
