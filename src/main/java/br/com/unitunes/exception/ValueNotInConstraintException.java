/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.exception;

/**
 *
 * @author LuisFernandoTorriani
 */
public class ValueNotInConstraintException extends Exception{

    public ValueNotInConstraintException(Class name) {
        
        super("Valor inserido não contido na constraint da Classe: "+name.getName());
    }
    
}
