/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author LuisFernandoTorriani
 */
@Entity
@Table(name = "MIDIA_AUTOR")
public class MidiaAutor {
    @Id
    @OneToOne(targetEntity = Midia.class)
    @JoinColumn(name = "COD_MIDIA_ID", unique=true)
    //@Column(name="COD_MIDIA")
    private Midia codMidia;
    @Id
    @OneToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "COD_USUARIO_ID", unique=true)
    //@Column(name="COD_USUARIO")
    private Usuario codUsuario;

    public MidiaAutor() {
    }

    public MidiaAutor(Midia codMidia, Usuario codUsuario) {
        this.codMidia = codMidia;
        this.codUsuario = codUsuario;
    }

    public Midia getCodMidia() {
        return codMidia;
    }

    public void setCodMidia(Midia codMidia) {
        this.codMidia = codMidia;
    }

    public Usuario getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Usuario codUsuario) {
        this.codUsuario = codUsuario;
    }
    
}
