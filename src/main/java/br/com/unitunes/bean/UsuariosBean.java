/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.bean;

import br.com.unitunes.dao.UsuarioDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.unitunes.entity.Usuario;
import br.com.unitunes.service.GerenciarUsuariosService;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author LuisFernandoTorriani
 */
@ManagedBean(name = "usuariosGerenciar")
@ViewScoped
public class UsuariosBean implements Serializable{
    private List<Usuario> usuarios;
    
    private GerenciarUsuariosService gerenciarUsuariosService;
   
    private Usuario user = new Usuario();
   
    private Usuario userEdit;
    private Long userEditPK;
    
    private Usuario userDelete;
    private Long userDeletePK;

    public Usuario getUserDelete() {
        return userDelete;
    }

    public void setUserDelete(Usuario userDelete) {
        this.userDelete = userDelete;
    }

    public Long getUserDeletePK() {
        return userDeletePK;
    }

    public void setUserDeletePK(Long userDeletePK) {
        this.userDeletePK = userDeletePK;
    }

    
    public Long getUserEditPK() {
        return userEditPK;
    }

    public void setUserEditPK(Long userEditPK) {
        this.userEditPK = userEditPK;
    }
    
    

    public UsuariosBean() {
    }
    
    public void carregarEditar(Long codUsuario){
        verificaUsuarioService();
        this.userEdit = this.gerenciarUsuariosService.buscaUsuario(codUsuario);
    }
    public void carregarExcluir(Long codUsuario){
        verificaUsuarioService();
        this.userDelete = this.gerenciarUsuariosService.buscaUsuario(codUsuario);
    }
    public void verificaUsuarioService(){
        if(this.gerenciarUsuariosService == null){
            this.gerenciarUsuariosService = new GerenciarUsuariosService();
        }
    }

    public UsuariosBean(Usuario user) {
        this.user = user;
    }

    public Usuario getUserEdit() {
        return userEdit;
    }

    public void setUserEdit(Usuario userEdit) {
        this.userEdit = userEdit;
    }
    

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    public String salvaUsuario(){
        System.out.println(this.user.getNomeUsuario()+" - "+this.user.getSnAtivo()+" - "+this.user.getTipoUsuario());
        this.gerenciarUsuariosService.cadastrarUsuario(user);
        return "gerenciarUsuario.xhtml?faces-redirect=true";
    } 
    public String editaUsuario(){
        System.out.println(this.userEdit.getNomeUsuario()+" - "+this.userEdit.getSnAtivo()+" - "+this.userEdit.getTipoUsuario());
        this.gerenciarUsuariosService.editarUsuario(userEdit);
        return "gerenciarUsuario.xhtml?faces-redirect=true";
    } 
    public String excluiUsuario(){
        System.out.println(this.userDelete.getNomeUsuario()+" - "+this.userDelete.getSnAtivo()+" - "+this.userDelete.getTipoUsuario());
        this.gerenciarUsuariosService.excluirUsuario(userDelete);
        return "gerenciarUsuario.xhtml?faces-redirect=true";
    }
    
    
    public UsuariosBean(List<Usuario> usuarios, GerenciarUsuariosService gerenciarUsuariosService) {
        this.usuarios = usuarios;
        this.gerenciarUsuariosService = gerenciarUsuariosService;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    @PostConstruct
    public void init(){
        if(this.gerenciarUsuariosService == null){
            this.gerenciarUsuariosService = new GerenciarUsuariosService();
        }
        this.usuarios = this.gerenciarUsuariosService.usuarios();
        if(this.user == null){
            this.user = new Usuario();
        }
    }

    public GerenciarUsuariosService getGerenciarUsuariosService() {
        return gerenciarUsuariosService;
    }

    public void setGerenciarUsuariosService(GerenciarUsuariosService gerenciarUsuariosService) {
        this.gerenciarUsuariosService = gerenciarUsuariosService;
    }

    
}
