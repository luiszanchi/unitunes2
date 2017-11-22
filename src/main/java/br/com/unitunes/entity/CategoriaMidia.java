
package br.com.unitunes.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CATEGORIA_MIDIA")
public class CategoriaMidia implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "COD_CATEGORIA", nullable = false)
    private Long codCategoria;
    
    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    public CategoriaMidia() {
    }
    
    public CategoriaMidia(Long codCategoria, String descricao) {
        this.codCategoria = codCategoria;
        this.descricao = descricao;
    }
    
    public Long getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(Long codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
