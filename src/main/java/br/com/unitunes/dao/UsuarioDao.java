/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.dao;

import br.com.unitunes.entity.Usuario;
import java.io.Serializable;

/**
 *
 * @author LuisFernandoTorriani
 */
public class UsuarioDao extends Dao<Usuario, Serializable>{
    
    public UsuarioDao() {
        super(Usuario.class);
    }
    
}
