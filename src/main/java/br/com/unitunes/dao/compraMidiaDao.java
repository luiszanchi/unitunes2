
package br.com.unitunes.dao;

import br.com.unitunes.entity.Compra;
import java.io.Serializable;

public class compraMidiaDao extends Dao<Compra, Serializable> implements Serializable{
    
    public compraMidiaDao() {
        super(Compra.class);
    }
}
    