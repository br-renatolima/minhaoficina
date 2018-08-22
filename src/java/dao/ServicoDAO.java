package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Servico;
import org.hibernate.Session;

public class ServicoDAO extends MasterDao implements IDao{

    @Override
    public String salvar(Object obj) {
        Servico s = (Servico) obj;
        return super.salvar(s);
    }

    @Override
    public String atualizar(Object obj) {
        Servico s = (Servico) obj;
        return super.atualizar(s);
    }

    @Override
    public String excluir(Object obj) {
        Servico s = (Servico) obj;
        return super.excluir(s);
    }
    
    public List<Servico> listar() {
        List<Servico> lista = new ArrayList<>();
        List<Object> temp = super.listar(Servico.class);
        for (Object o : temp) {
            lista.add((Servico) o);
        }
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        Object o = super.getPorId(Servico.class, id);
        return o;
    }
    
    public List<Servico> listarPorOficina(Integer idOficina) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        List<Servico> lista = sessao.getNamedQuery("Servico.findByOficina").setParameter("idoficina", idOficina).list();
        sessao.getTransaction().commit();
        sessao.close();
        return lista;
    }

    public List<Servico> listarPorTipo(Integer idTipo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        List<Servico> lista = sessao.getNamedQuery("Servico.findByTipo").setParameter("idtipo", idTipo).list();
        sessao.getTransaction().commit();
        sessao.close();
        return lista;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}