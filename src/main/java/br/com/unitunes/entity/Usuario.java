
package br.com.unitunes.entity;

import br.com.unitunes.tools.Validator;
import br.com.unitunes.values.ValoresPerfil;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="COD_USUARIO", nullable = false)
    private Long codUsuario;
    
    @Column(name="NOME_USUARIO", nullable = false, length = 50)
    private String nomeUsuario;
    
    @Column(name="TIPO_USUARIO", nullable = false, length = 1)
    private String tipoUsuario;
    
    @Column(name="SN_ATIVO", nullable = false, length = 1)
    private String snAtivo = "S";
    
    @Column(name="EMAIL", nullable = false, length = 100, unique = true)
    private String email;
    
    @Column(name="SENHA", nullable = false, length = 8)
    private String senha;
    
    @Transient
    private Boolean snAtivoParam = true;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if((!senha.isEmpty()) && (!senha.contains("**")))
            this.senha = senha;
    }

    
    
    public Boolean getSnAtivoParam() {
        return snAtivoParam;
    }

    public void setSnAtivoParam(Boolean snAtivoParam) {
        this.snAtivoParam = snAtivoParam;
        this.snAtivo = this.snAtivoParam?"S":"N";
    }
    
    

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        Validator.checkConstraintValues(ValoresPerfil.PERFIS, tipoUsuario);
        this.tipoUsuario = tipoUsuario;
    }

    public String getSnAtivo() {
        return snAtivo;
    }

    public void setSnAtivo(String snAtivo) {
        this.snAtivo = snAtivo;
        this.snAtivoParam = this.snAtivo.equalsIgnoreCase("S");
    }
    
    
}
