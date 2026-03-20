package br.com.perettis.schedula.model;

import br.com.perettis.gets.dao.GenericHibernateDAO;
import br.com.perettis.schedula.entity.AcompanhamentoTipo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

/**
 *
 * @author Vinicius Peretti
 */
public class AcompanhamentoTipoDAO
        extends GenericHibernateDAO<AcompanhamentoTipo, Integer> {

    public AcompanhamentoTipo buscaPorDescricao(String descricao) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Expression.like("descricao", descricao));
        List result = criteria.list();
        if (result.isEmpty()) {
            return null;
        } else {
            return (AcompanhamentoTipo) result.get(0);
        }
    }

    public AcompanhamentoTipo findByLetra(String string) {

        if (string == null) {
            return null;
        }
        if (string.equalsIgnoreCase("A")) {
            //solteiro
            return findById(1, false);
        }
        if (string.equalsIgnoreCase("P")) {
            //casado
            return findById(2, false);
        }
        if (string.equalsIgnoreCase("C")) {
            //viuvo
            return findById(3, false);
        }
        return null;
    }
}
