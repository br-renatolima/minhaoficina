package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Telefone;

public class TelefoneDAO extends MasterDao implements IDao{

    @Override
    public String salvar(Object obj) {
        Telefone t = (Telefone) obj;
        return super.salvar(t);
    }

    @Override
    public String atualizar(Object obj) {
        Telefone t = (Telefone) obj;
        return super.atualizar(t);
    }

    @Override
    public String excluir(Object obj) {
        Telefone t = (Telefone) obj;
        return super.excluir(t);
    }
    
    public List<Telefone> listar(Object obj) {
        List<Telefone> lista = new ArrayList<>();
        List<Object> temp = super.listar(obj.getClass().getName());
        for (Object o : temp) {
            lista.add((Telefone) o);
        }
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        return super.getPorId(Telefone.class, id);
    }
    
}
