package dao;

import java.util.List;
import org.hibernate.Session;

public class MasterDao implements IDao{
    String mensagem;

    /**
     *
     * @param obj
     * @return String = "Salvo com Sucesso!" ou "Ocorreu um erro com a Mensagem - "
     */
    @Override
    public String salvar(Object obj) {
        mensagem = "";
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.persist(obj);
            sessao.getTransaction().commit();
            mensagem = (obj.getClass().getName() + " Salvo com sucesso!").replace("modelo.", "");
        } catch (Exception e) {
            sessao.getTransaction().rollback();
            mensagem = "Ocorreu um erro com a mensagem - " + e.getMessage();
        }
        sessao.close();
        return mensagem;
    }

    /**
     *
     * @param obj
     * @return String = "Atualizado com Sucesso!" ou "Ocorreu um erro com a Mensagem - "
     */
    @Override
    public String atualizar(Object obj) {
        mensagem = "";
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(obj);
            sessao.getTransaction().commit();
            mensagem = (obj.getClass().getName()).replace("modelo.", "") + " atualizado com sucesso!";
        } catch (Exception e) {
            sessao.getTransaction().rollback();
            mensagem = "Ocorreu um erro com a mensagem - " + e.getMessage();
        }
        sessao.close();
        return mensagem;
    }

    @Override
    public String excluir(Object obj) {
        mensagem = "";
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.delete(obj);
            sessao.getTransaction().commit();
        } catch (Exception e) {
            sessao.getTransaction().rollback();
            mensagem = "Ocorreu um erro com a mensagem - " + e.getMessage();
        }
        sessao.close();
        return "Excluído com sucesso!";   
    }

    public Object getPorId(Object obj, Integer id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Object o = sessao.getNamedQuery(obj.getClass().getName() + ".findById").setParameter("id", id).uniqueResult();
        sessao.close();
        return o;
    }

    public Object getPorId(Class c, Integer id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Object o = sessao.getNamedQuery(c.getName() + ".findById").setParameter("id", id).uniqueResult();
        sessao.close();
        return o;
    }

    @Override
    public Object getPorId(Integer id) {
        throw new UnsupportedOperationException("Método não implementado.");
    }
    
    public List<Object> listar(String classe){
    Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Object> lista = sessao.getNamedQuery(classe + ".findAll").list();
        sessao.close();
        return lista;
    }
    
    public List<Object> listar(Class classe){
    Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Object> lista = sessao.getNamedQuery(classe.getName() + ".findAll").list();
        sessao.close();
        return lista;
    }
    
}
