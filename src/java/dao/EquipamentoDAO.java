package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Equipamento;

public class EquipamentoDAO extends MasterDao implements IDao{

    @Override
    public String salvar(Object obj) {
        Equipamento e = (Equipamento) obj;
        return super.salvar(e);
    }

    @Override
    public String atualizar(Object obj) {
        Equipamento e = (Equipamento) obj;
        return super.atualizar(e);
    }

    @Override
    public String excluir(Object obj) {
        return super.excluir(obj);
    }
    
    public List<Equipamento> listar() {
        List<Object> listaO = super.listar(Equipamento.class);//sessao.getNamedQuery("Equipamento.findAll").list(); //sessao.createCriteria(Equipamento.class).list();
        List<Equipamento> lista = new ArrayList<>();
        listaO.forEach((obj) -> {
            lista.add((Equipamento) obj);
        });
        return lista;        
    }

    @Override
    public Equipamento getPorId(Integer id) {
        return (Equipamento) super.getPorId(Equipamento.class, id);
    }
}
