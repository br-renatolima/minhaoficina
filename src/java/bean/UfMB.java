/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UfDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import modelo.Uf;


@ManagedBean
public class UfMB {
    private Uf uf;
    private List<Uf> lista;

    public UfMB() {
        inicializar();
    }
 
    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public List<Uf> getLista() {
        return lista;
    }

    public void setLista(List<Uf> lista) {
        this.lista = lista;
    }
    
    public void salvar(){
        UfDAO dao = new UfDAO();
        dao.salvar(uf);
        
        lista = dao.listar();
    }
    
    
    public void inicializar(){
        this.uf = new Uf();
        UfDAO dao = new UfDAO();
        lista = dao.listar();
    }
    
}
