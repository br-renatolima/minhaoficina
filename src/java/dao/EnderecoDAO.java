package dao;

import java.util.List;
import modelo.Endereco;
import org.hibernate.Session;

public class EnderecoDAO implements IDao{

    @Override
    public String salvar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Endereco e = (Endereco) obj;
        sessao.persist(e);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String atualizar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Endereco e = (Endereco) obj;
        sessao.update(e);
        sessao.getTransaction().commit();
        sessao.close();
        return "Atualizado com sucesso!";
    }

    @Override
    public String excluir(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Endereco e = (Endereco) obj;
        sessao.delete(e);
        sessao.getTransaction().commit();
        sessao.close();
        return "Excluido com sucesso!";
    }
    
    public List<Endereco> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        List<Endereco> lista = sessao.createCriteria(Endereco.class).list();
        sessao.getTransaction().commit();
        sessao.close();
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
