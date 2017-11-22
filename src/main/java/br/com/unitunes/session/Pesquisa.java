
package br.com.unitunes.session;

import java.io.Serializable;

public class Pesquisa implements Serializable{
    private String pesquisa;

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    
}
