package dao;

import java.util.List;
import modelo.Oficina;
import modelo.Ordemservico;
import org.hibernate.Session;

public class OficinaDAO implements IDao{

    @Override
    public String salvar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Oficina o = (Oficina) obj;
        sessao.persist(o);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String atualizar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Oficina o = (Oficina) obj;
        sessao.update(o);
        sessao.getTransaction().commit();
        sessao.close();
        return "Atualizado com sucesso!";
    }

    @Override
    public String excluir(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Oficina o = (Oficina) obj;
        sessao.delete(o);
        sessao.getTransaction().commit();
        sessao.close();
        return "Excluído com sucesso!";   
    }
    
    public List<Oficina> listar () {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        List<Oficina> lista = sessao.createCriteria(Oficina.class).list();
        sessao.getTransaction().commit();
        sessao.close();
        return lista;   
    }

    public List<Ordemservico> listarOs (String idoficina) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Ordemservico> lista = sessao.getNamedQuery("Ordemservico.findByOficina").setParameter("idoficina", idoficina).list();
        sessao.close();
        return lista;   
    }

    @Override
    public Object getPorId(Integer id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Object o = sessao.getNamedQuery("Oficina.findById").setParameter("id", id).uniqueResult();
        sessao.getTransaction().commit();
        sessao.close();
        return o;
    }
    
}
