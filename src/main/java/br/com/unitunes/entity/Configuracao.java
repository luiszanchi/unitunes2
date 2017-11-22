
package br.com.unitunes.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name = "configuracao")
public class Configuracao implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "COD_CONFIG", nullable = false)
    private Long codConfig;

    @Column(name = "CAMINHO_BASE", nullable = false, length = 1)
    private String caminhoBase;
 
    @Column(name = "CAMINHO_FOTO", nullable = false, length = 1)
    private String caminhoFoto;

    public Long getCodConfig() {
        return codConfig;
    }

    public void setCodConfig(Long codConfig) {
        this.codConfig = codConfig;
    }

    public String getCaminhoBase() {
        return caminhoBase;
    }

    public void setCaminhoBase(String caminhoBase) {
        this.caminhoBase = caminhoBase;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
    
}