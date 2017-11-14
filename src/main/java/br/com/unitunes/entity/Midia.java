/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author LuisFernandoTorriani
 */
@Entity
@Table(name="MIDIA")
public class Midia {
    @Id
    @GeneratedValue
    @Column(name = "COD_MIDIA", nullable = false)
    private Long codMidia;
    
    @Column(name = "TIPO_MIDIA", nullable = false, length = 1)
    private String tipoMidia;
    
    @Column(name = "NOME_MIDIA", nullable = false, length = 255)
    private String nomeMidia;
    
    @Column(name = "DESCRICAO", length = 255)
    private String descricao;
    
    @Column(name = "IMAGEM")
    private byte[] imagem;
    
    @Column(name = "VALOR_MIDIA", precision = 2, nullable = false)
    private Double valorMidia;
    
    @OneToOne(targetEntity = MidiaAutor.class)
    private MidiaAutor codAutor;
    
    @Column(name = "CONTEUDO_MIDIA", nullable = false)
    private byte[] conteudoMidia;
    
    @Column(name = "DATA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
       
    @Column(name = "CATEGORIA", length = 255)
    private String categoria;
    
    @Column(name = "DURACAO", nullable = false)
    private Double duracao;
    
    public Midia() {
    }

    public Midia(Long codMidia, String tipoMidia, String nomeMidia, String descricao, byte[] imagem, Double valorMidia, byte[] conteudoMidia, Date dataCriacao, String categoria, Double duracao) {
        this.codMidia = codMidia;
        this.tipoMidia = tipoMidia;
        this.nomeMidia = nomeMidia;
        this.descricao = descricao;
        this.imagem = imagem;
        this.valorMidia = valorMidia;
        this.conteudoMidia = conteudoMidia;
        this.dataCriacao = dataCriacao;
        this.categoria = categoria;
        this.duracao = duracao;
    }

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
            this.categoria = categoria;
    }
    

    public Long getCodMidia() {
        return codMidia;
    }

    public void setCodMidia(Long codMidia) {
        this.codMidia = codMidia;
    }

    public String getTipoMidia() {
        return tipoMidia;
    }

    public void setTipoMidia(String tipoMidia) {
        this.tipoMidia = tipoMidia;
    }

    public String getNomeMidia() {
        return nomeMidia;
    }

    public void setNomeMidia(String nomeMidia) {
        this.nomeMidia = nomeMidia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Double getValorMidia() {
        return valorMidia;
    }

    public void setValorMidia(Double valorMidia) {
        this.valorMidia = valorMidia;
    }

    public MidiaAutor getCodAutor() {
        return codAutor;
    }

    public void setCodAutor(MidiaAutor codAutor) {
        this.codAutor = codAutor;
    }

    public byte[] getConteudoMidia() {
        return conteudoMidia;
    }

    public void setConteudoMidia(byte[] conteudoMidia) {
        this.conteudoMidia = conteudoMidia;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
}

