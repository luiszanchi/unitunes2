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
@Table(name = "COMPRA")
public class Compra {
    @Id
    @GeneratedValue
    @Column(name = "COD_COMPRA")
    private Long codCompra;
    
    //@Column(name = )
}
