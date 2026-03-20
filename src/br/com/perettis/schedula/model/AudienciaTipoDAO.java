/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.AudienciaTipo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class AudienciaTipoDAO
	extends GenericHibernateDAO<AudienciaTipo, Integer> {

	public AudienciaTipo buscaPorDescricao(String descricao) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("descricao", descricao));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (AudienciaTipo) result.get(0);
		}
	}
    
    public AudienciaTipo findByLetra(String string) {

        if (string == null){
            return null;    
        }
        if (string.equalsIgnoreCase("C")){
            return findById(1, false);
        }
        if (string.equalsIgnoreCase("E")){
            return findById(2, false);
        }
        if (string.equalsIgnoreCase("I")){
            return findById(3, false);
        }
        if (string.equalsIgnoreCase("J")){
            return findById(4, false);
        }                
        if (string.equalsIgnoreCase("T")){
            return findById(5, false);
        }
        return null;
    }
}
