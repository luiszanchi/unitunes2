
package br.com.unitunes.dto;

import br.com.unitunes.entity.Midia;
import java.io.Serializable;

public class midiasDTO implements Serializable{
    Midia mid;
    Boolean isCompra;

    public Midia getMid() {
        return mid;
    }

    public void setMid(Midia mid) {
        this.mid = mid;
    }

    public Boolean getIsCompra() {
        return isCompra;
    }

    public void setIsCompra(Boolean isCompra) {
        this.isCompra = isCompra;
    }
    
    
}
