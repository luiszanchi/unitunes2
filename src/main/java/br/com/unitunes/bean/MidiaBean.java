
package br.com.unitunes.bean;

import br.com.unitunes.entity.Midia;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import br.com.unitunes.service.GerenciarMidiaService;
import br.com.unitunes.service.GerenciarUsuariosService;
import br.com.unitunes.session.SessionContext;
import br.com.unitunes.session.User;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;



/**
 *
 * @author Tito Kern
 */

@ManagedBean(name = "GerenciarMidia", eager = true)
@SessionScoped
public class MidiaBean  implements Serializable{
    private List<Midia> midiasUsuario;
    private GerenciarMidiaService gerenciarMidiaService;
    private Midia midia = new Midia();
    private Midia midiaGerencia = new Midia();
    private Midia Midiaplayer = new Midia();
    private boolean isLivro;
    private boolean isPodcast;
    private boolean isDesabilitado = true;
    private boolean isMusica;
    private boolean isVideo;
    private boolean isLivroGerencia;
    private boolean isPodcastGerencia;
    private boolean isMusicaGerencia;
    private boolean isVideoGerencia;
    UploadedFile imagem;
    UploadedFile conteudo;
    String javaUser;

    public String getJavaUser() {
        return javaUser;
    }
    User user;
    String tipo = "";

    public boolean carregarMidia(Midia midiaAtual){
         this.midiaGerencia.setNomeMidia(midiaAtual.getNomeMidia());
         this.midiaGerencia.setConteudoMidia(midiaAtual.getConteudoMidia());
         
         //TODO
         //javaUser = System.getProperty("user.home");
         javaUser = "D:\\MINHAS COISAS\\unisinos\\TrabalhoArquiteturaSoftware\\unitunes2\\src\\main\\webapp\\musica.mp3";
         File arq = new File("C:\\musica.mp3");

         FileOutputStream fos;
        try {
            fos = new FileOutputStream(javaUser);
            fos.write(midiaGerencia.getConteudoMidia());
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("GerenciarMidias.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;

     }

    public StreamedContent getMedia() {
        return media;
    }
    
     public void mediaControle(Midia midiaAtual){
         
         ByteArrayInputStream bis = new ByteArrayInputStream(midiaAtual.getConteudoMidia());
         
         InputStream stream = bis;
         
         media = new DefaultStreamedContent(stream, "audio/mpeg") ;
         
     }
    private StreamedContent media;
    
    
    
    @PostConstruct
    public void init(){
        if (this.gerenciarMidiaService == null){
            this.gerenciarMidiaService = new GerenciarMidiaService();
        }
        if(user == null){        
             user = (User) SessionContext.getInstance().getAttribute("user");
        }
        
        isDesabilitado = true;
        midia = new Midia();
        midiaGerencia = new Midia();
        
        midiasUsuario = this.gerenciarMidiaService.buscarMidiasUsuario(user.getCodUsuario());        
    }
    
    public void atualizar(){
        if (this.gerenciarMidiaService == null){
            this.gerenciarMidiaService = new GerenciarMidiaService();
        }
        if(user == null){        
             user = (User) SessionContext.getInstance().getAttribute("user");
        }        
        midiasUsuario = this.gerenciarMidiaService.buscarMidiasUsuario(user.getCodUsuario());
        
    }
    public void novaMidia(){
        midia = new Midia();
        isLivro = false;
        isPodcast = false;
        isMusica = false;
        isVideo = false;
        isDesabilitado = true; 
    }
    
    public String salvaMidia(){
        
        try {
            
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
            
            if(user == null || (user != null && user.getCodUsuario() == null)){
                user = (User) SessionContext.getInstance().getAttribute("user");
            }
            
            if(user.getCodUsuario().isEmpty()){
                user.setCodUsuario("1");
            }
            midia.setCodAutor(new GerenciarUsuariosService().buscaUsuario(Long.parseLong(user.getCodUsuario())));
            
            if (this.gerenciarMidiaService == null){
            this.gerenciarMidiaService = new GerenciarMidiaService();
            }
            this.gerenciarMidiaService.cadastrarMidia(midia);
            
            midia = new Midia();
            midiaGerencia = new Midia();
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("GerenciarMidias.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "GerenciarMidias.xhtml?faces-redirect=true";
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
         
        if (tipo != null && tipo.equalsIgnoreCase("L")){
            if(event.getFile().getFileName().contains("pdf")|| event.getFile().getFileName().contains("PDF")){
                conteudo = event.getFile();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Midia Invalida - Importe PDF"));
            }
              
        }else if (tipo != null && tipo.equalsIgnoreCase("P")){
            if(event.getFile().getFileName().contains("MP3")|| event.getFile().getFileName().contains("mp3")){
                conteudo = event.getFile();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Midia Invalida - Importe Mp3"));
            }
              
        }
        else if (tipo != null && tipo.equalsIgnoreCase("M")){
            if( event.getFile().getFileName().contains("MP3")|| event.getFile().getFileName().contains("mp3")){
                conteudo = event.getFile();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Midia Invalida - Importe Mp3"));
            }
                   
        }else if (tipo != null && tipo.equalsIgnoreCase("V")){
            if(event.getFile().getFileName().contains("MP4")|| event.getFile().getFileName().contains("mp4")){
                conteudo = event.getFile();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Midia Invalida - Importe Mp4"));
            }
        }
            
        
    }
    
    public void proximaEtapa(){
        
        
        
        if (midia.getTipoMidia().equalsIgnoreCase("L")){
               isLivro = true;
               isPodcast = false;
               isMusica = false;
               isVideo = false;
               tipo = midia.getTipoMidia();
        }else if (midia.getTipoMidia().equalsIgnoreCase("P")){
               isLivro = false;
               isPodcast= true;
               isMusica= false;
               isVideo= false;
               tipo = midia.getTipoMidia();
        }
        else if (midia.getTipoMidia().equalsIgnoreCase("M")){
               isLivro = false;
               isPodcast= false;
               isMusica= true;
               isVideo= false;
               tipo = midia.getTipoMidia();
        }else if (midia.getTipoMidia().equalsIgnoreCase("V")){
               isLivro = false;
               isPodcast= false;
               isMusica= false;
               isVideo= true;
               tipo = midia.getTipoMidia();
        }else{
               isLivro = false;
               isPodcast= false;
               isMusica= false;
               isVideo= false;
        }
        isDesabilitado = false;
    }
    
    public List<Midia> selecionarMidias(){
        
               isLivroGerencia = false;
               isPodcastGerencia = false;
               isMusicaGerencia = false;
               isVideoGerencia = false;
               
        if (tipo != null && tipo.equalsIgnoreCase("L")){
               isLivroGerencia = true;
               isPodcastGerencia = false;
               isMusicaGerencia = false;
               isVideoGerencia = false;
        }else if (tipo != null && tipo.equalsIgnoreCase("P")){
               isLivroGerencia = false;
               isPodcastGerencia= true;
               isMusicaGerencia= false;
               isVideoGerencia= false;
        }
        else if (tipo != null && tipo.equalsIgnoreCase("M")){
               isLivroGerencia = false;
               isPodcastGerencia= false;
               isMusicaGerencia= true;
               isVideoGerencia= false;
        }else if (tipo != null && tipo.equalsIgnoreCase("V")){
               isLivroGerencia = false;
               isPodcastGerencia= false;
               isMusicaGerencia= false;
               isVideoGerencia= true;
        }
        
         List<Midia> midiasPorTipo = new ArrayList<>();
        
        if (midiasUsuario != null){
            for (Midia midia1 : midiasUsuario) {
                if(midia1.getTipoMidia().equalsIgnoreCase(tipo)){
                    midiasPorTipo.add(midia1);
                }
            }
        }
        
        midiaGerencia.setTipoMidia("");
        return midiasPorTipo;
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

    public boolean isIsLivroGerencia() {
        return isLivroGerencia;
    }

    public void setIsLivroGerencia(boolean isLivroGerencia) {
        this.isLivroGerencia = isLivroGerencia;
    }

    public boolean isIsPodcastGerencia() {
        return isPodcastGerencia;
    }

    public void setIsPodcastGerencia(boolean isPodcastGerencia) {
        this.isPodcastGerencia = isPodcastGerencia;
    }

    public boolean isIsMusicaGerencia() {
        return isMusicaGerencia;
    }

    public void setIsMusicaGerencia(boolean isMusicaGerencia) {
        this.isMusicaGerencia = isMusicaGerencia;
    }

    public boolean isIsVideoGerencia() {
        return isVideoGerencia;
    }

    public void setIsVideoGerencia(boolean isVideoGerencia) {
        this.isVideoGerencia = isVideoGerencia;
    }
   
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
      public Midia getMidiaplayer() {
        return Midiaplayer;
    }

    public void setMidiaplayer(Midia Midiaplayer) {
        this.Midiaplayer = Midiaplayer;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        midia.setTipoMidia(tipo);
        this.tipo = tipo;
    }
    
    public List<Midia> getMidias() {
        return midiasUsuario;
    }
    public Midia getMidiaGerencia() {
        return midiaGerencia;
    }

    public void setMidiaGerencia(Midia midiaGerencia) {
        this.midiaGerencia = midiaGerencia;
    }
    public void setMidias(List<Midia> midias) {
        this.midiasUsuario = midias;
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
   
}
