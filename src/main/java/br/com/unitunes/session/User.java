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
public class User {
    private String codUsuario;
    
    private String nomeUsuario;
    
    private String emailUsuario;
    
    private String tipoUsuario;

    public User() {
    }

    public User(String codUsuario, String nomeUsuario, String emailUsuario, String tipoUsuario) {
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
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

    public String getTipoUsuario() {
        if (tipoUsuario.equalsIgnoreCase("D")){
            return "Administrador";
        }
        if (tipoUsuario.equalsIgnoreCase("U")){
            return "Autor";
        }
        if (tipoUsuario.equalsIgnoreCase("A")){
            return "Academico";
        }

        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
    
}
