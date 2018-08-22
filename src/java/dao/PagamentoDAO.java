package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Pagamento;

public class PagamentoDAO extends MasterDao implements IDao{

    @Override
    public String salvar(Object obj) {
        return super.salvar((Pagamento) obj);
    }

    @Override
    public String atualizar(Object obj) {
        return super.atualizar((Pagamento) obj);
    }

    @Override
    public String excluir(Object obj) {
        return super.excluir((Pagamento) obj);
    }

    @Override
    public Pagamento getPorId(Integer id) {
        return (Pagamento) super.getPorId(Pagamento.class, id);
    }

    public List<Pagamento> listar() {
        List<Pagamento> lista = new ArrayList<>();
        List<Object> listaO = super.listar(Pagamento.class);
        for (Object o : listaO) {
            lista.add((Pagamento) o);
        }
        return lista;
    }
    
    

    
    
}
