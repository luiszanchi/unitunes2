/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.bean;

import br.com.unitunes.entity.Configuracao;
import br.com.unitunes.service.GerenciaConfigService;
import br.com.unitunes.session.Config;
import br.com.unitunes.session.Pesquisa;
import br.com.unitunes.session.SessionContext;
import br.com.unitunes.session.UserLogin;
import br.com.unitunes.session.User;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean(name = "usuarioLogadoMB")
@SessionScoped
public class LoginBean implements Serializable{

    @ManagedProperty(value = "#{userBO}")
    private UserLogin userLogin;

    private String email;
    private String login;
    private String senha;
    private User usuario;
    private String pesquisa;    
    private String codUsuario = "";
    private String nomeUsuario = "";
    private String emailUsuario = "";
    private String tipoUsuario = "";

    public String pesquisa() {
        try {
            //TODO init() gerenciarMidias
            Pesquisa psq = new Pesquisa();
            if (this.pesquisa != null && this.pesquisa.length() <= 2){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de tamanho", "No minimo 3 Caracteres"));
                return null;
            }
            
            psq.setPesquisa(this.pesquisa);
            SessionContext.getInstance().setAttribute("pesquisa", psq);
            FacesContext.getCurrentInstance().getExternalContext().redirect("GerenciarMidias.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "login.xhtml?faces-redirect=true";
    }

    

    public LoginBean() {
        userLogin = new UserLogin();
        email = new String();
        login = new String();
        senha = new String();
        usuario = new User();
    }

    public LoginBean(UserLogin userLogin, String email, String login, String senha) {
        this.userLogin = userLogin;
        this.email = email;
        this.login = login;
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
            User user = userLogin.isUsuarioReadyToLogin(login, senha);

            codUsuario = user.getCodUsuario();
            nomeUsuario = user.getNomeUsuario();
            emailUsuario = user.getEmailUsuario();
            tipoUsuario = user.getTipoUsuario();
            
            if (user == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login ou Senha invalidos"));
                return null;
            }

            GerenciaConfigService configaService = new GerenciaConfigService();
            try {
                List<Configuracao> listaConfiguracao = configaService.buscarConfiguracao();
                if ( listaConfiguracao != null && listaConfiguracao.get(0) != null && listaConfiguracao.get(0).getCaminhoBase() != null && listaConfiguracao.get(0).getCaminhoFoto() != null){
                    SessionContext.getInstance().setAttribute("config", new Config(listaConfiguracao.get(0).getCaminhoFoto(), listaConfiguracao.get(0).getCaminhoBase()));
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Adicionar Configurações"));
                    return null;
                }
            } catch (Exception e) {
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Adicionar Configurações"));
                  return null;
            }
            
            SessionContext.getInstance().setAttribute("user", user);
            usuario = user;
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(login+" - "+senha);
            //addErrorMessage(e.getMessage());
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login ou Senha invalidos"));
            e.printStackTrace();
            return "null";
        }

    }
     
         public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
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
    
        public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String getPesquisa() {
        return pesquisa;
    }
    
}
