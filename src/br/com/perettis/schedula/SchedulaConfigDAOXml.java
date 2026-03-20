package br.com.perettis.schedula;

import br.com.perettis.gets.daoxml.DAOXml;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vinicius Peretti
 */
public class SchedulaConfigDAOXml extends DAOXml<SchedulaConfig, Integer> {
    static String CHAVE = "id";
    static Class TIPOCHAVE = Integer.class;
            
    private Map<String, Class> mapeamento = new HashMap();
    
    public SchedulaConfigDAOXml(){
        super.setFilePath(SchedulaApp.getApplication().getContext().getLocalStorage().getDirectory().getAbsolutePath()+File.separator+"SchedulaConfig.xml");
        super.setNameRootElement("SchedulaConfig");
        super.setNameRowElement("Config");
        super.setIdProperty(CHAVE);
        super.setIdPropertyType(TIPOCHAVE);

        // Define o mapeamento necessário
        mapeamento.put("databaseConection", String.class);
        mapeamento.put("databaseDialect", String.class);
        mapeamento.put("databaseDriver", String.class);
        mapeamento.put("databaseUsername", String.class);
        mapeamento.put("databasePassword", String.class);

        super.setMapProperties(mapeamento);
        
        super.setEntityClass(SchedulaConfig.class);
    }
}
