/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unitunes.bean;

import br.com.unitunes.entity.Midia;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.unitunes.service.GerenciarMidiaService;
import br.com.unitunes.service.GerenciarUsuariosService;
import br.com.unitunes.session.SessionContext;
import br.com.unitunes.session.User;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author LuisFernandoTorriani
 */
@ManagedBean(name = "GerenciarMidia")
@ViewScoped
public class MidiaBean  implements Serializable{
    private List<Midia> midias;
    private GerenciarMidiaService gerenciarMidiaService;
    private Midia midia = new Midia();
    private boolean isLivro;
    private boolean isPodcast;
    private boolean isDesabilitado = true;
    private boolean isMusica;
    private boolean isVideo;
    UploadedFile imagem;
    UploadedFile conteudo;
  
    public void setImagem(UploadedFile imagem) {
        this.imagem = imagem;
    }
    public void setArquivo(UploadedFile arquivo) {
        this.conteudo = arquivo;
    }
    public UploadedFile getArquivo() {
        return conteudo;
    }

    public UploadedFile getImagem() {
        return imagem;
    }

    public List<Midia> getMidias() {
        return midias;
    }

    public void setMidias(List<Midia> Midias) {
        this.midias = Midias;
    }

    public GerenciarMidiaService getGerenciarMidiaService() {
        return gerenciarMidiaService;
    }

    public void setGerenciarMidiaService(GerenciarMidiaService gerenciarMidiaService) {
        this.gerenciarMidiaService = gerenciarMidiaService;
    }

    public Midia getMidia() {
        return midia;
    }
    

    public void setMidia(Midia midia) {
        this.midia = midia;
    }
        public boolean isIsDesabilitado() {
        return isDesabilitado;
    }

    public void setIsDesabilitado(boolean isDesabilitado) {
        this.isDesabilitado = isDesabilitado;
    }
 
       public boolean isIsPodcast() {
        return isPodcast;
    }

    public void setIsPodcast(boolean isPodcast) {
        this.isPodcast = isPodcast;
    } 
    
    public String salvaMidia(){
        if (midia.getCategoria() == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione uma Categoria", "Selecione uma Categoria"));
            return null;
        }
        if (imagem == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione uma Imagem", "Selecione uma Imagem"));
            return null;
        }
        if (conteudo == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione uma Midia", "Selecione uma Midia"));
            return null;
        }
        
        midia.setDataCriacao(new Date(System.currentTimeMillis()));
                
        byte[] ArraybytesImagem = imagem.getContents();
        midia.setImagem(ArraybytesImagem);
        
        byte[] ArraybytesConteudo = conteudo.getContents();
        midia.setConteudoMidia(ArraybytesConteudo);
        
        User user = (User) SessionContext.getInstance().getAttribute("user");
        midia.setCodAutor(new GerenciarUsuariosService().buscaUsuario(Long.parseLong(user.getCodUsuario())));
        
        if (this.gerenciarMidiaService == null){
            this.gerenciarMidiaService = new GerenciarMidiaService();
        }
        this.gerenciarMidiaService.cadastrarMidia(midia);
        return "gerenciarMidia.xhtml?faces-redirect=true";
    } 
       
    public void CarregaImagem(FileUploadEvent event) {
        
        if (event.getFile().getFileName().contains("JPG")|| event.getFile().getFileName().contains("jpg") || 
                        event.getFile().getFileName().contains("png")|| event.getFile().getFileName().contains("PNG")){
            imagem = event.getFile();
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Selecione uma Imagem JPG ou PNG"));
        }
    }
    
     public void CarregaConteudo(FileUploadEvent event) {
         
         //TODO
         if (event.getFile().getFileName().contains("pdf")|| event.getFile().getFileName().contains("PDF") || 
                event.getFile().getFileName().contains("TXT")|| event.getFile().getFileName().contains("txt")||
                    event.getFile().getFileName().contains("MP3")|| event.getFile().getFileName().contains("mp3") ||
                        event.getFile().getFileName().contains("MP4")|| event.getFile().getFileName().contains("mp4") ||
                            event.getFile().getFileName().contains("AVI")|| event.getFile().getFileName().contains("avi")){
            conteudo = event.getFile();
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Midia Invalida"));
        }
        
    }
    
     public boolean isIsLivro() {
        return isLivro;
    }

    public void setIsLivro(boolean isLivro) {
        this.isLivro = isLivro;
    }

    public boolean isIsMusica() {
        return isMusica;
    }

    public void setIsMusica(boolean isMusica) {
        this.isMusica = isMusica;
    }

    public boolean isIsVideo() {
        return isVideo;
    }

    public void setIsVideo(boolean isVideo) {
        this.isVideo = isVideo;
    }  
    
    public void proximaEtapa(){
        
        if (midia.getTipoMidia().equalsIgnoreCase("L")){
               isLivro = true;
               isPodcast = false;
               isMusica = false;
               isVideo = false;
        }else if (midia.getTipoMidia().equalsIgnoreCase("P")){
               isLivro = false;
               isPodcast= true;
               isMusica= false;
               isVideo= false;
        }
        else if (midia.getTipoMidia().equalsIgnoreCase("M")){
               isLivro = false;
               isPodcast= false;
               isMusica= true;
               isVideo= false;
        }else if (midia.getTipoMidia().equalsIgnoreCase("V")){
               isLivro = false;
               isPodcast= false;
               isMusica= false;
               isVideo= true;
        }else{
               isLivro = false;
               isPodcast= false;
               isMusica= false;
               isVideo= false;
        }
        isDesabilitado = false;
    }
    
    public String labelConteudo(){
        if (conteudo != null){
           return "Substituir Conteudo";
        }
        return "Selecione um Arquivo";
    }
    
    public String labelImagem(){
        if (imagem != null){
           return "Substituir Imagem";
        }
        return "Selecione uma Imagem";
    }
    
    
    
   /* 
    byte[] imgBytes = rs.getBytes("binaryfile");
try{
    FileOutputStream fos = new FileOutputStream("C:/imagens/"+ imagem.getFileName());
     fos.write(imgBytes);
     FileDescriptor fd = fos.getFD();
     fos.flush();
     fd.sync();
     fos.close(); 
 }
 catch(Exception e){
    String erro = e.toString();
}*/
   
}
