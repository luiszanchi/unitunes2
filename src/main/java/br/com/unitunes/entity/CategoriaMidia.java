/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LuisFernandoTorriani
 */
@Entity
@Table(name = "CATEGORIA_MIDIA")
public class CategoriaMidia {
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
