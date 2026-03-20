
package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.Schedula;
import org.hibernate.Transaction;

/**
 *
 * @author vinicius
 */
public class SchedulaDAO
	extends GenericHibernateDAO<Schedula, Integer> {

	public Schedula busca() {
		return findById(new Integer(1), false);
	}
    public String getNomeEmpresa(){
        //Transaction t = getSession().beginTransaction();
        String nome = findById(new Integer(1), false).getNomeEmpresa();
        //t.commit();
        return nome;
    }
}
