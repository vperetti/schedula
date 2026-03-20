/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.EstadoCivil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author vinicius
 */
public class EstadoCivilDAO
	extends GenericHibernateDAO<EstadoCivil, Integer> {

	public EstadoCivil buscaPorNome(String nome) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Expression.eq("nome", nome));
		List result = criteria.list();
		if (result.isEmpty()) {
			return null;
		} else {
			return (EstadoCivil) result.get(0);
		}
	}

    public EstadoCivil findByLetra(String string) {

        if (string == null){
            return null;    
        }
        if (string.equalsIgnoreCase("S")){
            //solteiro
            return findById(1, false);
        }
        if (string.equalsIgnoreCase("C")){
            //casado
            return findById(2, false);
        }
        if (string.equalsIgnoreCase("V")){
            //viuvo
            return findById(5, false);
        }
        if (string.equalsIgnoreCase("D")){
            //divorciado
            return findById(4, false);
        }                
        if (string.equalsIgnoreCase("O")){
            //uniao estavel
            return findById(6, false);
        }
        return null;
    }
}
