
package br.com.unitunes.dao;

import br.com.unitunes.entity.Usuario;
import java.io.Serializable;


public class UsuarioDao extends Dao<Usuario, Serializable> implements Serializable{
    
    public UsuarioDao() {
        super(Usuario.class);
    }
    
}
