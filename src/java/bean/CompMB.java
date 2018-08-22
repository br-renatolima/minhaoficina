package bean;


import dao.ComplexidadeDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Complexidade;

@Named(value = "compMB")
@ManagedBean
public class CompMB implements IManagedBean{

    private String mensagem;
    private Complexidade comp;
    private ComplexidadeDAO dao;
    private List<Complexidade> lista;
    
    public CompMB() {
    }
    
    @PostConstruct
    @Override
    public void inicializar(){
        dao = new ComplexidadeDAO();
        comp = new Complexidade();
        lista = dao.listar();
        mensagem = "";
        if (comp == null) {
            // obtem parametro
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            String idParam = ctx.getRequestParameterMap().get("id");

            if (idParam != null && !idParam.equals("")) {
                try {
                    comp = getPorId(Integer.parseInt(idParam));
                } catch (NumberFormatException e) {
                    mensagem = e.getMessage();
                }
            }

            if (comp == null) {
                comp = new Complexidade();
            }
        }
    }
    
    @Override
    public String salvar() {
        mensagem = "";
        mensagem = dao.salvar(comp);
        inicializar();
        return "";
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Complexidade getComp() {
        return comp;
    }

    public void setComp(Complexidade comp) {
        this.comp = comp;
    }

    public ComplexidadeDAO getDao() {
        return dao;
    }

    public void setDao(ComplexidadeDAO dao) {
        this.dao = dao;
    }

    @Override
    public String editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Complexidade getPorId(int parseInt) {
        for (Complexidade c : lista) {
            if(c.getId()== parseInt){
                comp = c;
            }
        }
        return comp;
    }

    public List<Complexidade> getLista() {
        return lista;
    }

    public void setLista(List<Complexidade> lista) {
        this.lista = lista;
    }
}
