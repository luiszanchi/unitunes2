
import br.com.unitunes.dao.UsuarioDao;
import br.com.unitunes.entity.Usuario;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LuisFernandoTorriani
 */
public class teste {

    public static void main(String[] args) {
        UsuarioDao ud = new UsuarioDao();
        List<Usuario> usuarios = ud.getList();
        for (Usuario u : usuarios) {

            System.out.println(u.toString());
        }
    }
}
