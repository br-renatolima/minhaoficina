package bean;

import dao.MarcaDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.Marca;


@ManagedBean
public class MarcaMB implements IManagedBean{
    private String mensagem;
    private Marca marca;
    private List<Marca> lista;
    private MarcaDAO dao;
    private String nome;
    
    public MarcaMB() {
    }

    @Override
    public String salvar() {
        if(nome == ""){
            mensagem = "Preencha o nome!";
            return "";
        }
        
        for (Marca m : lista) {
            if(m.getNome().equals(nome)){
                mensagem = "Marca já registrada!";
                return "";
            }
        }
        
        marca = new Marca();
        marca.setNome(nome);
        mensagem = dao.salvar(marca);
        lista = dao.listar();
        nome = "";
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
        dao = new MarcaDAO();
        lista = dao.listar();
        mensagem = "";
        nome = "";
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Marca> getLista() {
        return lista;
    }

    public void setLista(List<Marca> lista) {
        this.lista = lista;
    }

    public MarcaDAO getDao() {
        return dao;
    }

    public void setDao(MarcaDAO dao) {
        this.dao = dao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
