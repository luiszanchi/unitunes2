/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.session;

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
    
    public User isUsuarioReadyToLogin(String l, String s){
        User user = new User();
        if(l.equalsIgnoreCase("admin@unitunes.com") && s.equals("admin")){
            user.setCodUsuario("1");
            user.setNomeUsuario(l);
            user.setTipoUsuario("D");
        }else if(l.equalsIgnoreCase("autor@unitunes.com") && s.equals("autor")){
            user.setCodUsuario("2");
            user.setNomeUsuario(l);
            user.setTipoUsuario("U");
        }else if(l.equalsIgnoreCase("academico@unitunes.com") && s.equals("academico")){
            user.setCodUsuario("3");
            user.setNomeUsuario(l);
            user.setTipoUsuario("A");
        }else{
            return null;
        }
        
        return user;
    }
}
