package dao;

import java.util.List;
import modelo.Complexidade;
import org.hibernate.Session;

public class ComplexidadeDAO implements IDao{

    @Override
    public String salvar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Complexidade c = (Complexidade) obj;
        sessao.persist(c);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String atualizar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Complexidade c = (Complexidade) obj;
        sessao.update(c);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String excluir(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Complexidade c = (Complexidade) obj;
        sessao.delete(c);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }
    
    public List<Complexidade> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        List<Complexidade> lista = sessao.createCriteria(Complexidade.class).list();
        sessao.getTransaction().commit();
        sessao.close();
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Object o = sessao.getNamedQuery("Complexidade.findById").setParameter("id", id).uniqueResult();
        sessao.getTransaction().commit();
        sessao.close();
        return o;
    }
    
}
