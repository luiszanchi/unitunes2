/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.bean;

import br.com.unitunes.session.SessionContext;
import br.com.unitunes.session.UserLogin;
import br.com.unitunes.session.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author LuisFernandoTorriani
 */
@ManagedBean(name = "usuarioLogadoMB")
@ViewScoped
public class LoginBean implements Serializable{

    @ManagedProperty(value = "#{userBO}")
    private UserLogin userLogin;

    private String email;
    private String login;
    private String senha;
    

    public LoginBean() {
        userLogin = new UserLogin();
        email = new String();
        login = new String();
        senha = new String();
    }

    public LoginBean(UserLogin userLogin, String email, String login, String senha) {
        this.userLogin = userLogin;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    
    

    
    public User getUser() {
        return (User) SessionContext.getInstance().getAttribute("user");
    }

   

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    

    public String doLogout() {
        //logger.info("Fazendo logout com usuário "
        //   + SessionContext.getInstance().getUsuarioLogado().getLogin());
        SessionContext.getInstance().encerrarSessao();
        //ddInfoMessage("Logout realizado com sucesso !");
        return "/login.xhtml?faces-redirect=true";
    }
     public String doLogin() {
        try {
            if(userLogin==null){
                userLogin = new UserLogin();
            }
            //logger.info("Tentando logar com usuário " + login);
            User user = userLogin.isUsuarioReadyToLogin(login, senha);

            if (user == null) {
                //addErrorMessage("Login ou Senha errado, tente novamente !");
                FacesContext.getCurrentInstance().validationFailed();
                return "erro";
            }

            //User usuario = (User) getUserBO().findByNamedQuery(Usuario.FIND_BY_ID,
            // new NamedParams("id", user.getId())).get(0);
            // logger.info("Login efetuado com sucesso");
            SessionContext.getInstance().setAttribute("user", user);
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(login+" - "+senha);
            //addErrorMessage(e.getMessage());
            FacesContext.getCurrentInstance().validationFailed();
            e.printStackTrace();
            return "erro";
        }

    }
}
