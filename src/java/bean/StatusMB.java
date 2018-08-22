package bean;

import dao.StatusDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import modelo.Status;

@Named("statusMB")
@ManagedBean
public class StatusMB implements IManagedBean{

    private Status status;
    private List<Status> lista;
    private StatusDAO dao;
    private String mensagem;
    
    public StatusMB() {
    }

    @Override
    @PostConstruct
    public void inicializar() {
        dao = new StatusDAO();
        lista = dao.listar();
        status = new Status();
        mensagem = "";
    }

    @Override
    public String salvar() {
        if(status.getDescricao() == "" || status.getDescricao() == null){
                mensagem = "Nome não pode estar em branco!";
                return "";
        }
            
        for (Status s : lista) {
            if(s.getDescricao().toUpperCase() == null ? status.getDescricao().toUpperCase() == null : s.getDescricao().toUpperCase().equals(status.getDescricao().toUpperCase())){
                mensagem = "Status já existe na base!";
                return "";
            }
        }
                
        mensagem = dao.salvar(status);
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Status> getLista() {
        return lista;
    }

    public void setLista(List<Status> lista) {
        this.lista = lista;
    }

    public StatusDAO getDao() {
        return dao;
    }

    public void setDao(StatusDAO dao) {
        this.dao = dao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
