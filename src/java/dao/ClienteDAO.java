package dao;

import java.util.List;
import modelo.Cliente;
import modelo.Equipamento;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ClienteDAO implements IDao{

    @Override
    public String salvar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Cliente c = (Cliente) obj;
        sessao.persist(c);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String atualizar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Cliente c = (Cliente) obj;
        sessao.update(c);
        sessao.getTransaction().commit();
        sessao.close();
        return "Atualizado com sucesso!";
    }

    @Override
    public String excluir(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Cliente c = (Cliente) obj;
        sessao.delete(c);
        sessao.getTransaction().commit();
        sessao.close();
        return "Excluído com sucesso!";
    }
    
    
    public List<Cliente> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> lista = sessao.getNamedQuery("Cliente.findAll").list(); //createCriteria(Cliente.class).list();
        //sessao.getTransaction().commit();
        sessao.close();
        return lista;
    }
    
    
    public List<Equipamento> listarEquipamentos(Cliente c){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Criteria crit = sessao.createCriteria(Equipamento.class);
        crit.add(Restrictions.eq("clienteId", c.getId()));
        List<Equipamento> lista = crit.list();
        sessao.getTransaction().commit();
        sessao.close();
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Object o = sessao.getNamedQuery("Cliente.findById").setParameter("id", id).uniqueResult(); //createCriteria(Cliente.class).list();
        //sessao.getTransaction().commit();
        sessao.close();
        return o;
    }

    public Cliente getPorCpf(String cpf) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Cliente c = (Cliente) sessao.getNamedQuery("Cliente.findByCpf").setParameter("cpf", cpf).uniqueResult();
        sessao.close();
        return c;
    }
    
}
