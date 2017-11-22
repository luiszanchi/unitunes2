
package br.com.unitunes.session;

import java.io.Serializable;

public class Config  implements Serializable{
    
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

