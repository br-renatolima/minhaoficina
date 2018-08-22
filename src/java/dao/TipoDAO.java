package dao;

import java.util.List;
import modelo.Tipo;
import org.hibernate.Session;

public class TipoDAO implements IDao{

    @Override
    public String salvar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Tipo t = (Tipo) obj;
        sessao.persist(t);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String atualizar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Tipo t = (Tipo) obj;
        sessao.update(t);
        sessao.getTransaction().commit();
        sessao.close();
        return "Atualizado com sucesso!";
    }

    @Override
    public String excluir(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Tipo t = (Tipo) obj;
        sessao.delete(t);
        sessao.getTransaction().commit();
        sessao.close();
        return "Excluído com sucesso!";
    }
            
    public List<Tipo> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Tipo> lista = sessao.getNamedQuery("Tipo.findAll").list();//.createCriteria(Tipo.class).list();
        sessao.close();
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Object o = sessao.getNamedQuery("Tipo.findById").setParameter("id", id).uniqueResult();
        sessao.getTransaction().commit();
        sessao.close();
        return o;
    }
    
    

}
