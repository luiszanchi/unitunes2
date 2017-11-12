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
    public void cadastrarUsuario(Usuario u){
        if(ud == null){
            ud = new UsuarioDao();
        }
        Boolean a = ud.adicionar(u);
        if(a)
            System.out.println("Usuario Cadastrado Com Sucesso");
    }
    public Usuario buscaUsuario(Long codUsuario){
        if(ud == null){
            ud = new UsuarioDao();
        }
        return ud.encontrar(codUsuario);
    }
    public void editarUsuario(Usuario u){
        if(ud == null){
            ud = new UsuarioDao();
        }
        Boolean a = ud.atualizar(u);
        if(a)
            System.out.println("Usuario Editado Com Sucesso");
        else 
            System.out.println("Usuário não alterado");
    }
    public void excluirUsuario(Usuario u){
                if(ud == null){
            ud = new UsuarioDao();
        }
        Boolean a = ud.excluit(u);
        if(a)
            System.out.println("Usuario Excluido Com Sucesso");
        else 
            System.out.println("Usuário não excluido");
>>>>>>> origin/master
    }
}
