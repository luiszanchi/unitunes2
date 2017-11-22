
package br.com.unitunes.service;

import br.com.unitunes.dao.ConfigDao;
import br.com.unitunes.entity.Configuracao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name = "configuracaoService")
@ApplicationScoped
public class GerenciaConfigService implements Serializable {
    private ConfigDao ud;
    public List<Configuracao> buscarConfiguracao(){
        if (ud == null){
            ud = new ConfigDao();
        }
        return ud.getList();
    }

    public List<Configuracao> buscarConfiguracao(String usuario){
         if(ud == null){
            ud = new ConfigDao();
        }
        return ud.getQueryList("");
    }
    
}
