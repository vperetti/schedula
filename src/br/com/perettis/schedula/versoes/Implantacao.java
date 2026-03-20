package br.com.perettis.schedula.versoes;

import br.com.perettis.schedula.model.SchedulaDAOFactory;

/**
 *
 * @author Vinicius Peretti
 */
public interface Implantacao {
    public boolean executa(SchedulaDAOFactory daoFactory) throws ImplantacaoException;
}
