/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.service;

import br.com.unitunes.dao.UsuarioDao;
import br.com.unitunes.entity.Usuario;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author LuisFernandoTorriani
 */
@ManagedBean(name = "carService")
@ApplicationScoped
public class GerenciarUsuariosService {
    private UsuarioDao ud;
    public List<Usuario> usuarios(){
        if (ud == null){
            ud = new UsuarioDao();
        }
        return ud.getList();
    }
}
