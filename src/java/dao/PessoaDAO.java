package dao;

import java.util.List;
import modelo.Pessoa;
import org.hibernate.Session;

public class PessoaDAO implements IDao {

    @Override
    public String salvar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Pessoa p = (Pessoa) obj;
        sessao.persist(p);
        sessao.getTransaction().commit();
        sessao.close();
        return "Salvo com sucesso!";
    }

    @Override
    public String atualizar(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Pessoa p = (Pessoa) obj;
        sessao.update(p);
        sessao.getTransaction().commit();
        sessao.close();
        return "Atualizado com sucesso!";
    }

    @Override
    public String excluir(Object obj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        Pessoa p = (Pessoa) obj;
        sessao.delete(p);
        sessao.getTransaction().commit();
        sessao.close();
        return "Excluído com sucesso!";
    }
    
    
    public List<Pessoa> listar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        List<Pessoa> lista = sessao.createCriteria(Pessoa.class).list();
        sessao.getTransaction().commit();
        sessao.close();
        return lista;
    }

    @Override
    public Object getPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
