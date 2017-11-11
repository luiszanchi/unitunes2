/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.tools;

import br.com.unitunes.exception.ValueNotInConstraintException;

/**
 *
 * @author LuisFernandoTorriani
 */
public class Validator {
    public static void checkConstraintValues(String[] array, String value){
        int count = 0;
        for(String array_f : array){
            if(!array_f.equalsIgnoreCase(value)){
                count++;
            }
        }
        if(count == array.length){
            new ValueNotInConstraintException(Validator.class);
        }
    }
    
}
