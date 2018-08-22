package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Ordemservico;
import org.hibernate.Session;

public class OrdemservicoDAO extends MasterDao implements IDao{

    @Override
    public String salvar(Object obj) {
        return super.salvar((Ordemservico) obj);
    }

    @Override
    public String atualizar(Object obj) {
        return super.atualizar((Ordemservico) obj);
    }

    @Override
    public String excluir(Object obj) {
        return super.excluir((Ordemservico) obj);
    }
    
    public List<Ordemservico> listar() {
        List<Object> listaO = super.listar(Ordemservico.class);
        List<Ordemservico> lista = new ArrayList<>();
        for (Object o : listaO) {
            lista.add((Ordemservico) o);
        }
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        Ordemservico obj;
        obj = (Ordemservico) super.getPorId(Ordemservico.class, id);
        return obj;
    }
    
}
