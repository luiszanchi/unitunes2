/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.bean;

import br.com.unitunes.dao.UsuarioDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.unitunes.entity.Usuario;
import br.com.unitunes.service.GerenciarUsuariosService;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author LuisFernandoTorriani
 */
@ManagedBean(name = "usuariosGerenciar")
@ViewScoped
public class UsuariosBean implements Serializable{
    private List<Usuario> usuarios;
    @ManagedProperty(value = "#{usersBO}")
    private GerenciarUsuariosService gerenciarUsuariosService;

    public UsuariosBean() {
    }
    
    public UsuariosBean(List<Usuario> usuarios, GerenciarUsuariosService gerenciarUsuariosService) {
        this.usuarios = usuarios;
        this.gerenciarUsuariosService = gerenciarUsuariosService;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    @PostConstruct
    public void init(){
        if(this.gerenciarUsuariosService == null){
            this.gerenciarUsuariosService = new GerenciarUsuariosService();
        }
        this.usuarios = this.gerenciarUsuariosService.usuarios();
    }

    public GerenciarUsuariosService getGerenciarUsuariosService() {
        return gerenciarUsuariosService;
    }

    public void setGerenciarUsuariosService(GerenciarUsuariosService gerenciarUsuariosService) {
        this.gerenciarUsuariosService = gerenciarUsuariosService;
    }

    
}
