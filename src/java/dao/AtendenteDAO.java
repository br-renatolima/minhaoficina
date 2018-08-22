package dao;

import java.util.List;
import modelo.Atendente;
import org.hibernate.Session;

public class AtendenteDAO extends MasterDao implements IDao{

    @Override
    public String salvar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Atendente a = (Atendente) obj;
        sessao.persist(a);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String atualizar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Atendente a = (Atendente) obj;
        sessao.update(a);
        sessao.getTransaction().commit();
        sessao.close();
        return "Atualizado com sucesso!";
    }

    @Override
    public String excluir(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Atendente a = (Atendente) obj;
        sessao.delete(a);
        sessao.getTransaction().commit();
        sessao.close();
        return "Excluído com sucesso!";
    }
    
    public List<Atendente> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        List<Atendente> lista = sessao.createCriteria(Atendente.class).list();
        sessao.getTransaction().commit();
        sessao.close();
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        Atendente a = (Atendente) super.getPorId(Atendente.class, id);
        return a;
    }

    public Atendente getPorCpf(String cpf) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Atendente a = (Atendente) sessao.getNamedQuery("Atendente.findByCpf").setParameter("cpf", cpf).uniqueResult();
        sessao.close();
        return a;
    }
}
