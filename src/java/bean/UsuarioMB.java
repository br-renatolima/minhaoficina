package bean;

import dao.OficinaDAO;
import dao.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Atendente;
import modelo.Oficina;
import modelo.Pessoa;
import modelo.Usuario;

@ManagedBean
public class UsuarioMB implements IManagedBean {

    private List<Usuario> lista;
    private Usuario usuario;
    private UsuarioDAO dao;
    private Atendente atendente;
    private Pessoa pessoa;
    private String nomeUsuario;
    private String mensagem;
    
    private OficinaDAO oDao;
    private List<Oficina> listaOficina;
    private LinkedHashMap<String, Integer> listaMapOficina;
    private Integer idOficina;

    public UsuarioMB() {
    }

    @Override
    public String salvar() {
        if(this.getUsuarioPorLogin(usuario.getLogin()) != null){
            mensagem = "Usuario já cadastrado com esse Login!";
            return "";
        }
        
        for (Oficina o : listaOficina) {
            if(o.getId() == idOficina) {
                usuario.setOficina(o);
            }
        }
        
        if(usuario.getOficina() == null){
            mensagem = "Por favor, associe o novo usuário a uma oficina!";
            return "";
        }
        
        this.usuario.setDataCadastro(new Date());
        mensagem = dao.salvar(this.usuario);
        return "../index.xhtml";
    }

    public FacesMessage logar() throws UnsupportedEncodingException {
        Usuario u = this.getUsuarioPorLogin(usuario.getLogin());
        FacesMessage msg = new FacesMessage("");
        
        if ( usuario.getSenha() == null ? u.getSenha() == null : usuario.getSenha().equals(u.getSenha()) ) {
            
            this.setNomeUsuario(u.getNome());

            HttpSession session = SessionUtils.getSession();
            session.setAttribute("usu", u);
            //utilMB.redirecionar("../index.xhtml");
        } else {
            msg = new FacesMessage("Usuário ou senha inválidos!");
        }
        utilMB.redirecionar("");
        return msg;
    }

    public void logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();        
        utilMB.redirecionar("");
    }

    public Usuario getUsuarioPorId(int id) {
        for (Usuario u : lista) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    private Usuario getUsuarioPorLogin(String login) {
        for (Usuario u : lista) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }

    @PostConstruct
    @Override
    public void inicializar() {
        this.usuario = new Usuario();
        this.atendente = new Atendente();
        this.pessoa = new Pessoa();
        this.dao = new UsuarioDAO();
        this.lista = dao.listar();
        
        oDao = new OficinaDAO();
        listaOficina = oDao.listar();
        listaMapOficina = new LinkedHashMap<>();
        for (Oficina o : listaOficina) {
            listaMapOficina.put(o.getNomeFantasia() + " - " + o.getCnpj(), o.getId() );
        }
    }

    public UsuarioDAO getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public String editar() {
        HttpSession session = SessionUtils.getSession();
        atendente = (Atendente) session.getAttribute("usu");
        mensagem = dao.atualizar(atendente);
        return "";
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LinkedHashMap<String, Integer> getListaMapOficina() {
        return listaMapOficina;
    }

    public void setListaMapOficina(LinkedHashMap<String, Integer> listaMapOficina) {
        this.listaMapOficina = listaMapOficina;
    }

    public Integer getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    public OficinaDAO getoDao() {
        return oDao;
    }

    public void setoDao(OficinaDAO oDao) {
        this.oDao = oDao;
    }

    public List<Oficina> getListaOficina() {
        return listaOficina;
    }

    public void setListaOficina(List<Oficina> listaOficina) {
        this.listaOficina = listaOficina;
    }


}
