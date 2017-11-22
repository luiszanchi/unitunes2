
package br.com.unitunes.service;

import br.com.unitunes.dao.compraMidiaDao;
import br.com.unitunes.entity.Compra;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "compraService")
@ApplicationScoped
public class GerenciarCompraService implements Serializable{
    private compraMidiaDao ud;
    public List<Compra> Compras(){
        if (ud == null){
            ud = new compraMidiaDao();
        }
        return ud.getList();
    }
    
    public Compra buscaCompra(Long codUsuario){
        if(ud == null){
            ud = new compraMidiaDao();
        }
        return ud.encontrar(codUsuario);
    }
      public void cadastrarCompra(Compra u){
        if(ud == null){
            ud = new compraMidiaDao();
        }
        Boolean a = ud.adicionar(u);
        if(a)
            System.out.println("Compra Cadastrada Com Sucesso");
        }
    
        public void excluirCompra(Compra u){
                if(ud == null){
            ud = new compraMidiaDao();
        }
        Boolean a = ud.excluir(u);
        if(a)
            System.out.println("Compra Excluida Com Sucesso");
        else 
            System.out.println("Compra não excluido");
    }
        
        public List<Compra> buscarCompras(String usuario){
         if(ud == null){
            ud = new compraMidiaDao();
        }
        return ud.getQueryList("");
    }
        
    
}
