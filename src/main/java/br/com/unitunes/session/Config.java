/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.session;

/**
 *
 * @author Tito-Casa
 */
public class Config {
    
private String caminhoFoto;
private String caminhoBase;

    public Config (String caminhoFoto, String caminhoBase){
        this.caminhoFoto = caminhoFoto;
        this.caminhoBase = caminhoBase;
    }
    

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getCaminoBase() {
        return caminhoBase;
    }

    public void setCaminoBase(String caminoBase) {
        this.caminhoBase = caminoBase;
    }

}

