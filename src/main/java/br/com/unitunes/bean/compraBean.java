
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.bean;

import br.com.unitunes.entity.Compra;
import br.com.unitunes.entity.Midia;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import br.com.unitunes.service.GerenciarMidiaService;
import br.com.unitunes.service.GerenciarCompraService;
import br.com.unitunes.session.Config;
import br.com.unitunes.session.SessionContext;
import br.com.unitunes.session.User;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;




/**
 *
 * @author Tito Kern
 */

@ManagedBean(name = "CompraMidia", eager = true)
@SessionScoped
public class compraBean  implements Serializable{

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    private List<Midia> midiasUsuario;
    private GerenciarMidiaService gerenciarMidiaService;
    private GerenciarCompraService gerenciarCompraService;
    private User user;
    private boolean isLivro;
    private boolean isPodcast;
    private boolean isMusica;
    private boolean isVideo;
    private String tipo = "";
    private Midia midiaCompraConfirma;
    private Long codigoMidiaCompra;
    private Config config;

    public Long getCodigoMidiaCompra() {
        return codigoMidiaCompra;
    }

    public void setCodigoMidiaCompra(Long codigoMidiaCompra) {
        
        for (Midia midia : midiasUsuario) {
            if (Objects.equals(codigoMidiaCompra, midia.getCodMidia())){
                midiaCompraConfirma = midia;
            }
        }
        if(config == null || (config != null && config.getCaminhoFoto() == null) || (config != null && config.getCaminoBase() == null)){
                config = (Config) SessionContext.getInstance().getAttribute("config");
        }
         
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(config.getCaminhoFoto()+"foto.jpg");
            fos.write(midiaCompraConfirma.getImagem());
            FileDescriptor fd = fos.getFD();
            fos.flush();
            fd.sync();
            fos.close(); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        try {
            fos = new FileOutputStream(config.getCaminoBase()+"musica.mp3");
            fos.write(midiaCompraConfirma.getConteudoMidia());
            FileDescriptor fd = fos.getFD();
            fos.flush();
            fd.sync();
            fos.close(); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
        this.codigoMidiaCompra = codigoMidiaCompra;
    }

    public void cadastraCompra (){
        if (this.gerenciarCompraService == null){
            this.gerenciarCompraService = new GerenciarCompraService();
            }
        
            Compra compra = new Compra();
            compra.setCodMidia(midiaCompraConfirma.getCodMidia());
            
            if(user == null || (user != null && user.getCodUsuario() == null)){
                user = (User) SessionContext.getInstance().getAttribute("user");
            }
            
            if(user.getCodUsuario().isEmpty()){
                user.setCodUsuario("1");
            }
            compra.setCodUsuario(Long.parseLong(user.getCodUsuario()));
                    
            this.gerenciarCompraService.cadastrarCompra(compra);
    }
    
    public Midia getMidiaCompraConfirma() {
        return midiaCompraConfirma;
    }

    public void setMidiaCompraConfirma(Midia midiaCompra) {
        this.midiaCompraConfirma = midiaCompra;
    }


    public List<Midia> getMidiasPorTipo() {
        return midiasPorTipo;
    }

    public void setMidiasPorTipo(List<Midia> midiasPorTipo) {
        this.midiasPorTipo = midiasPorTipo;
    }
    List<Midia> midiasPorTipo = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        if (this.gerenciarMidiaService == null){
            this.gerenciarMidiaService = new GerenciarMidiaService();
        }
        if(user == null){        
             user = (User) SessionContext.getInstance().getAttribute("user");
        }
        midiasUsuario = this.gerenciarMidiaService.buscarMidiasComprav2(user.getCodUsuario());
       
        
        if(config == null || (config != null && config.getCaminhoFoto() == null) || (config != null && config.getCaminoBase() == null)){
                config = (Config) SessionContext.getInstance().getAttribute("config");
        }
        
        FileOutputStream fos;
        for (Midia midia : midiasUsuario) {
            
            try {
                fos = new FileOutputStream(config.getCaminoBase()+midia.getCodMidia().toString()+".jpg");
                fos.write(midia.getImagem());
                FileDescriptor fd = fos.getFD();
                fos.flush();
                fd.sync();
                fos.close(); 
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
    
    public void atualizar(){
        if (this.gerenciarMidiaService == null){
            this.gerenciarMidiaService = new GerenciarMidiaService();
        }
        if(user == null){        
             user = (User) SessionContext.getInstance().getAttribute("user");
        }        
         midiasUsuario = this.gerenciarMidiaService.buscarMidiasComprav2(user.getCodUsuario());
        
    }
    
    
        public void selecionarMidias(){
        
               isLivro = false;
               isPodcast = false;
               isMusica = false;
               isVideo = false;
               
        if (tipo != null && tipo.equalsIgnoreCase("L")){
               isLivro = true;
               isPodcast = false;
               isMusica = false;
               isVideo = false;
        }else if (tipo != null && tipo.equalsIgnoreCase("P")){
               isLivro = false;
               isPodcast= true;
               isMusica= false;
               isVideo = false;
        }
        else if (tipo != null && tipo.equalsIgnoreCase("M")){
               isLivro = false;
               isPodcast = false;
               isMusica = true;
               isVideo = false;
        }else if (tipo != null && tipo.equalsIgnoreCase("V")){
               isLivro = false;
               isPodcast = false;
               isMusica = false;
               isVideo = true;
        }
        
        midiasPorTipo = new ArrayList<>();
        
        if (midiasUsuario != null){
            for (Midia midia1 : midiasUsuario) {
                if(midia1.getTipoMidia().equalsIgnoreCase(tipo)){
                    midiasPorTipo.add(midia1);
                }
            }
        }
        
    }
    
    
    public List<Midia> getMidias() {
        return midiasUsuario;
    }
   
    public void setMidias(List<Midia> midias) {
        this.midiasUsuario = midias;
    }
   
}

