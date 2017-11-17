
package br.com.unitunes.service;

import br.com.unitunes.dao.MidiaDao;
import br.com.unitunes.dao.UsuarioDao;
import br.com.unitunes.entity.Midia;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "gerService")
@ApplicationScoped
public class GerenciarMidiaService {
    private MidiaDao ud;
    public List<Midia> midias(){
        if (ud == null){
            ud = new MidiaDao();
        }
        return ud.getList();
    }
    public void cadastrarMidia(Midia u){
        if(ud == null){
            ud = new MidiaDao();
        }
        Boolean a = ud.adicionar(u);
        if(a)
            System.out.println("Midia Cadastrada Com Sucesso");
    }
    public Midia buscaMidia(Long codMidia){
        if(ud == null){
            ud = new MidiaDao();
        }
        return ud.encontrar(codMidia);
    }
    public void editarMidia(Midia u){
        if(ud == null){
            ud = new MidiaDao();
        }
        Boolean a = ud.atualizar(u);
        if(a)
            System.out.println("Midia Editada Com Sucesso");
        else 
            System.out.println("Midia não alterada");
    }
    public void excluirMidia(Midia u){
                if(ud == null){
            ud = new MidiaDao();
        }
        Boolean a = ud.excluir(u);
        if(a)
            System.out.println("Midia Excluida Com Sucesso");
        else 
            System.out.println("Midia não excluida");
    }
    
      public List<Midia> buscarMidiasUsuario(String usuario){
         if(ud == null){
            ud = new MidiaDao();
        }
        return ud.getQueryList("from Midia mid where mid.codAutor.codUsuario =" + usuario);
    }
      
    public List<Midia> buscarMidiasCompra(String usuario){
         if(ud == null){
            ud = new MidiaDao();
        }
        return ud.getQueryList("from Midia mid where mid.codAutor.codUsuario != " + usuario);
    }
    
    public List<Midia> buscarMidiasComprav2(String usuario){
         if(ud == null){
            ud = new MidiaDao();
        }
        return ud.getQueryList("from Midia mid where mid.codMidia not in "
                + "(select cmp.codMidia from Compra cmp where cmp.codUsuario ="+ usuario +")"
                        + " and mid.codAutor.codUsuario != " + usuario);
    }
    
}
