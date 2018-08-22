package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Atendente;
import modelo.Usuario;
import org.hibernate.Session;

public class UsuarioDAO extends MasterDao implements IDao {

    @Override
    public String salvar(Object o) {
        return super.salvar((Usuario) o);
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        List<Object> listaO = super.listar(Usuario.class); //sessao.createCriteria(Usuario.class).list();
        listaO.forEach((o) -> {
            lista.add((Usuario) o);
        });
        return lista;
    }

    @Override
    public String atualizar(Object o) {
        return super.atualizar((Usuario) o);
    }

    @Override
    public String excluir(Object o) {
        return super.excluir((Usuario) o);
    }

    @Override
    public Object getPorId(Integer id) {
        return super.getPorId(Usuario.class, id);
    }
    
    
    public Object getAtendentePorId(Integer id) {
        return super.getPorId(Atendente.class, id);
    }
    
    

}
