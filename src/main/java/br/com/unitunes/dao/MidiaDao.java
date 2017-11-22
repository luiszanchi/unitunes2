
package br.com.unitunes.dao;

import br.com.unitunes.entity.Midia;
import java.io.Serializable;


public class MidiaDao extends Dao<Midia, Serializable> implements Serializable{
    
    public MidiaDao() {
        super(Midia.class);
    }
    
}
