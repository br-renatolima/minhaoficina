package dao;

import java.util.List;
import modelo.Status;
import org.hibernate.Session;


public class StatusDAO implements IDao{

    @Override
    public String salvar(Object obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.save(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            return "Algo deu errado - " + e.getMessage();
        }
        session.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String atualizar(Object obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.update(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            return "Algo deu errado - " + e.getMessage();
        }
        session.close();
        return "Atualizado com sucesso!";
    }
        
    @Override
    public String excluir(Object obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            session.delete(obj);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            return "Algo deu errado - " + e.getMessage();
        }
        session.close();
        return "Excluído com sucesso!";
    }

    @Override
    public Object getPorId(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Status s = (Status) session.getNamedQuery("Status.findById").setParameter("id", id).uniqueResult();
        session.close();
        return s;
    }
    
    public List<Status> listar() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Status> lista = session.getNamedQuery("Status.findAll").list();
        session.close();
        return lista;
    }
        
}
