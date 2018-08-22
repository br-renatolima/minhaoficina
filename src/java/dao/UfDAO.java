/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Uf;
import org.hibernate.Session;

/**
 *
 * @author rlima
 */
public class UfDAO{
    
    public void salvar(Uf uf) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        sessao.persist(uf);
        sessao.getTransaction().commit();
        sessao.close();
    }
    
    public List<Uf> listar(){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<Uf> lista = sessao.getNamedQuery("Uf.findAll").list();//createCriteria(Uf.class).list();
        sessao.close();
        return lista;
    }
    
    public void editar(Uf uf){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        sessao.update(uf);
        sessao.getTransaction().commit();
        sessao.close();        
    }
    
        public void excluir(Uf uf){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        sessao.delete(uf);
        sessao.getTransaction().commit();
        sessao.close();        
    }
    
}
