
package br.com.unitunes.dao;

import br.com.unitunes.entity.Configuracao;
import java.io.Serializable;

public class ConfigDao extends Dao<Configuracao, Serializable>{
    
    public ConfigDao() {
        super(Configuracao.class);
    }
}
   