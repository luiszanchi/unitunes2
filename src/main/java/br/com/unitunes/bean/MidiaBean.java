
package br.com.unitunes.bean;

import br.com.unitunes.dto.midiasDTO;
import br.com.unitunes.entity.Midia;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import br.com.unitunes.service.GerenciarMidiaService;
import br.com.unitunes.service.GerenciarUsuariosService;
import br.com.unitunes.session.Config;
import br.com.unitunes.session.Pesquisa;
import br.com.unitunes.session.SessionContext;
import br.com.unitunes.session.SessionMidia;
import br.com.unitunes.session.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean(name = "GerenciarMidia", eager = true)
@ViewScoped
public class MidiaBean  implements Serializable{
    private List<Midia> midiasUsuario;
    private List<Midia> midiasUsuarioCompra;
    private GerenciarMidiaService gerenciarMidiaService;
    private Midia midia = new Midia();
    private Midia midiaGerencia = new Midia();
    private Midia midiaEditar = new Midia();
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
    private boolean editavel = false;
    UploadedFile imagem;
    UploadedFile conteudo;
    String javaUser;
    private Config config;
    private Pesquisa pesquisa;

    public String getJavaUser() {
        return javaUser;
    }
    User user;
    String tipo = "";
    
    public String carregaVideo(Midia midia){
        Date data = new Date();
        String nome = midia.getNomeMidia() + ".mp4";
        String caminho = "tmp/" + nome;
        String caminho_criar = "src/main/webapp/" + caminho;
        System.out.println(caminho + " - " + caminho_criar);
        try {
            createFile(nome, midia.getConteudoMidia());

            return caminho;
        } catch (Exception ex) {
            Logger.getLogger(Midia.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    public String carregaMusica(Midia midia) {

        Date data = new Date();
        String nome = midia.getNomeMidia() + ".mp3";
        String caminho = "tmp/" + nome;
        String caminho_criar = "src/main/webapp/" + caminho;
        System.out.println(caminho + " - " + caminho_criar);
        try {
            createFile(nome, midia.getConteudoMidia());

            return caminho;
        } catch (Exception ex) {
            Logger.getLogger(Midia.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return null;
        }
    }

    private static void createFile(String filename, byte[] conteudo) /* No, it doesn't. The only calls you had outside your catch-all `try` don't throw exceptions. */ {
        //ErrorCheck ec           = new ErrorCheck();            // Recommend not creating this until/unless you need it
        
        String fileName = ((Config) SessionContext.getInstance().getAttribute("config")).getCaminoBase()+"tmp\\"+filename; // VERY poor practice having two locals that only differ by the capitalization of one character in the middle (`filename` and `fileName`)
        Path filePath = Paths.get(fileName);
        //  File file               = new File(fileName);      <== Removed, since you never use it for anything

        try {
            File f = new File(fileName);
            if (!f.exists()) {
                // Make sure the directories exist
                Files.createDirectories(filePath.getParent());  // No need for your null check, so I removed it; based on `fileName`, it will always have a parent

                // Open the file, creating it if it doesn't exist
                BufferedWriter out = Files.newBufferedWriter(
                        filePath,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
                out.close();

                Files.write(Paths.get(fileName), conteudo);
                // do something
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Using the try-with-resources, we don't have to worry about the flush and close calls
    }

    public boolean carregarMidia(Midia midiaAtual) {
    
        SessionMidia sMidia = new SessionMidia();
        sMidia.setMidia(midiaAtual);
        SessionContext.getInstance().setAttribute("sessionmidia", sMidia);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("EditarMidia.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MidiaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;

     }

    @PostConstruct
    public void init(){
        
        if (((SessionMidia) SessionContext.getInstance().getAttribute("sessionmidia")) != null && ((SessionMidia) SessionContext.getInstance().getAttribute("sessionmidia")).getMidia() != null){
            midiaEditar = ((SessionMidia) SessionContext.getInstance().getAttribute("sessionmidia")).getMidia();
            SessionContext.getInstance().setAttribute("sessionmidia", null);
        
        if (midiaEditar.getTipoMidia().equalsIgnoreCase("L")){
               isLivro = true;
               isPodcast = false;
               isMusica = false;
               isVideo = false;
        }else if (midiaEditar.getTipoMidia().equalsIgnoreCase("P")){
               isLivro = false;
               isPodcast= true;
               isMusica= false;
               isVideo= false;
        }
        else if (midiaEditar.getTipoMidia().equalsIgnoreCase("M")){
               isLivro = false;
               isPodcast= false;
               isMusica= true;
               isVideo= false;
        }else if (midiaEditar.getTipoMidia().equalsIgnoreCase("V")){
               isLivro = false;
               isPodcast= false;
               isMusica= false;
               isVideo= true;
        }
        
        this.editavel = true;
        this.imagem = null;
        this.conteudo = null;
    }
        
        if (this.gerenciarMidiaService == null){
            this.gerenciarMidiaService = new GerenciarMidiaService();
        }
        
        if(user == null){        
             user = (User) SessionContext.getInstance().getAttribute("user");
        }
        
        isDesabilitado = true;
        midia = new Midia();
        midiaGerencia = new Midia();
        

        if(pesquisa == null || (pesquisa != null && pesquisa.getPesquisa() == null)){
            if (((Pesquisa) SessionContext.getInstance().getAttribute("pesquisa")) != null && ((Pesquisa) SessionContext.getInstance().getAttribute("pesquisa")).getPesquisa() != null ){
                pesquisa = (Pesquisa) SessionContext.getInstance().getAttribute("pesquisa");
            }      
        }
        
        if(pesquisa != null && pesquisa.getPesquisa() != null && pesquisa.getPesquisa().length() > 1){
            midiasUsuario = this.gerenciarMidiaService.buscarMidiasUsuarioPesquisa(user.getCodUsuario(), pesquisa.getPesquisa());
            midiasUsuarioCompra = this.gerenciarMidiaService.buscarMidiasCompraPesquisa(user.getCodUsuario(), pesquisa.getPesquisa());
            pesquisa = null;
            SessionContext.getInstance().setAttribute("pesquisa", pesquisa);
        }else{
            midiasUsuarioCompra = this.gerenciarMidiaService.buscarMidiasCompra(user.getCodUsuario());
            midiasUsuario = this.gerenciarMidiaService.buscarMidiasUsuario(user.getCodUsuario());    
        }

        if(config == null || (config != null && config.getCaminhoFoto() == null) || (config != null && config.getCaminoBase() == null)){
                config = (Config) SessionContext.getInstance().getAttribute("config");
        }
        
        //midiasUsuario
        FileOutputStream fos;    
        for (Midia midia : midiasUsuario) {
            try {
                fos = new FileOutputStream(config.getCaminhoFoto()+midia.getCodMidia().toString()+".jpg");
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
        
        //midiasCompradas
        for (Midia midia : midiasUsuarioCompra) {
            FileOutputStream fosCompra;
            try {
                fosCompra = new FileOutputStream(config.getCaminhoFoto()+midia.getCodMidia().toString()+".jpg");
                fosCompra.write(midia.getImagem());
                FileDescriptor fd = fosCompra.getFD();
                fosCompra.flush();
                fd.sync();
                fosCompra.close(); 
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
        midiasUsuario = this.gerenciarMidiaService.buscarMidiasUsuario(user.getCodUsuario());
        
        
         if(config == null || (config != null && config.getCaminhoFoto() == null) || (config != null && config.getCaminoBase() == null)){
                config = (Config) SessionContext.getInstance().getAttribute("config");
        }
        
        FileOutputStream fos;
        for (Midia midia : midiasUsuario) {
            
            try {
                fos = new FileOutputStream(config.getCaminhoFoto()+midia.getCodMidia().toString()+".jpg");
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
    
    
    public String EditarMidia(){
        
        try {
            
            if (midiaEditar.getCategoria() == null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione uma Categoria", "Selecione uma Categoria"));
                return null;
            }
            
            midiaEditar.setDataCriacao(new Date(System.currentTimeMillis()));
            
            if (this.imagem != null){
                byte[] ArraybytesImagem = imagem.getContents();
                midiaEditar.setImagem(ArraybytesImagem);
            }
            
            if (this.conteudo != null){
                byte[] ArraybytesConteudo = conteudo.getContents();
                midiaEditar.setConteudoMidia(ArraybytesConteudo);
            }
            
            if(user == null || (user != null && user.getCodUsuario() == null)){
                user = (User) SessionContext.getInstance().getAttribute("user");
            }
            
            if(user.getCodUsuario().isEmpty()){
                user.setCodUsuario("1");
            }
            
            midiaEditar.setCodAutor(new GerenciarUsuariosService().buscaUsuario(Long.parseLong(user.getCodUsuario())));
            
            if (this.gerenciarMidiaService == null){
                this.gerenciarMidiaService = new GerenciarMidiaService();
            }
            
            
            this.gerenciarMidiaService.editarMidia(midiaEditar);
            
            editavel = false;
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
    
    public List<midiasDTO> selecionarMidias(){
        
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
        
         List<midiasDTO> midiasPorTipo = new ArrayList<>();
        
        if (midiasUsuario != null){
            for (Midia midia1 : midiasUsuario) {
                if(midia1.getTipoMidia().equalsIgnoreCase(tipo)){
                    midiasDTO midDTO= new midiasDTO();
                    midDTO.setMid(midia1);
                    midDTO.setIsCompra(false);
                    midiasPorTipo.add(midDTO);
                }
            }
        }
        
        if (midiasUsuarioCompra != null){
            for (Midia midia1 : midiasUsuarioCompra) {
                if(midia1.getTipoMidia().equalsIgnoreCase(tipo)){
                    midiasDTO midDTO= new midiasDTO();
                    midDTO.setMid(midia1);
                    midDTO.setIsCompra(true);
                    midiasPorTipo.add(midDTO);
                }
            }
        }
        
        midiaGerencia.setTipoMidia("");
        return midiasPorTipo;
    }
    
    
    public String labelConteudo(){
        if (conteudo != null){
           return "Substituir Conteudo";
        }else if(editavel){
            return "Substituir Imagem";
        }
        return "Selecione um Arquivo";
    }
    
    public String labelImagem(){
        if (imagem != null){
           return "Substituir Imagem";
        }else if(editavel){
            return "Substituir Imagem";
        }
        
        return "Selecione uma Imagem";
    }
    
    
    
    public List<Midia> getMidiasUsuarioCompra() {
        return midiasUsuarioCompra;
    }

    public void setMidiasUsuarioCompra(List<Midia> midiasUsuarioCompra) {
        this.midiasUsuarioCompra = midiasUsuarioCompra;
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
    
    public Midia getMidiaEditar() {
        return midiaEditar;
    }

    public void setMidiaEditar(Midia midiaEditar) {
        this.midiaEditar = midiaEditar;
    }
    
   
}
