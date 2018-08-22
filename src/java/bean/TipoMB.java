package bean;

import dao.TipoDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.Tipo;


@ManagedBean
public class TipoMB implements IManagedBean {

    private String mensagem;
    private String nome;
    private Tipo tipo;
    private List<Tipo> lista;
    private TipoDAO dao;
    
    public TipoMB() {
    }

    public String novo(){
        mensagem = "Passou pelo método novo";
        return "";
    }
    
    @Override
    public String salvar() {
        if(nome == "" || nome == null){
            mensagem = "Nome não informado!";
            return "";
        }
        
        for (Tipo t : lista) {
            if(t.getNome() == nome){
                mensagem = "Tipo já registrado!";
                return "";
            }
        }
        tipo = new Tipo();
        tipo.setNome(nome);
        mensagem = dao.salvar(tipo);
        lista = dao.listar();
        return "";
    }

    @Override
    public String editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @PostConstruct
    public void inicializar() {
        dao = new TipoDAO();
        lista = dao.listar();
        mensagem = "";
        nome = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Tipo> getLista() {
        return lista;
    }

    public void setLista(List<Tipo> lista) {
        this.lista = lista;
    }

    public TipoDAO getDao() {
        return dao;
    }

    public void setDao(TipoDAO dao) {
        this.dao = dao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
