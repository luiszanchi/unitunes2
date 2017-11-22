
package br.com.unitunes.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "COMPRA")
public class Compra implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "COD_COMPRA")
    private Long codCompra;

    @Column(name = "COD_MIDIA")
    private Long codMidia;
    
    @Column(name = "COD_USUARIO")
    private Long codUsuario;

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    public Long getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(Long codCompra) {
        this.codCompra = codCompra;
    }

    public Long getCodMidia() {
        return codMidia;
    }

    public void setCodMidia(Long codMidia) {
        this.codMidia = codMidia;
    }
    
}
