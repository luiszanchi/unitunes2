
package br.com.unitunes.dao;

import br.com.unitunes.entity.Configuracao;
import java.io.Serializable;

public class ConfigDao extends Dao<Configuracao, Serializable> implements Serializable{
    
    public ConfigDao() {
        super(Configuracao.class);
    }
}
   