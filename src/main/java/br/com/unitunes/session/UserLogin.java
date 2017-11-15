/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.session;

import br.com.unitunes.dao.UsuarioDao;
import br.com.unitunes.entity.Usuario;

/**
 *
 * @author LuisFernandoTorriani
 */
public class UserLogin {

    private String user;

    private String passwd;

    public UserLogin() {
    }

    public UserLogin(String user, String passwd) {
        this.user = user;
        this.passwd = passwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public User isUsuarioReadyToLogin(String l, String s) {
        User user = new User();
        Usuario u;
        UsuarioDao ud = new UsuarioDao();
        if (l.equalsIgnoreCase("admin@unitunes.com")) { // && s.equals("admin")
            u = ud.getQueryList("from Usuario u where u.email = '" + l + "' and u.senha = '" + s + "'").get(0);
            if (u == null) {
                u = new Usuario();
                u.setEmail("admin@unitunes.com");
                u.setSenha("admin");
                u.setSnAtivo("S");
                u.setTipoUsuario("D");
                u.setNomeUsuario("Administrador do Sistema");
                ud.adicionar(u);
                u = ud.getQueryList("from Usuario u where u.email = '" + l + "' and u.senha = '" + s + "'").get(0);
                user.setCodUsuario(u.getCodUsuario().toString());
                user.setEmailUsuario(u.getEmail());
                user.setNomeUsuario(u.getNomeUsuario());
                user.setTipoUsuario(u.getTipoUsuario());
            }
        } else {
            u = ud.getQueryList("from Usuario u where u.email = '" + l + "' and u.senha = '" + s + "'").get(0);
            if (u == null) {
                return null;
            } else {
                user.setCodUsuario(u.getCodUsuario().toString());
                user.setEmailUsuario(u.getEmail());
                user.setNomeUsuario(u.getNomeUsuario());
                user.setTipoUsuario(u.getTipoUsuario());
            }
        }

        return user;
    }
}
