package bean;

import dao.ComplexidadeDAO;
import dao.OficinaDAO;
import dao.ServicoDAO;
import dao.TipoDAO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Atendente;
import modelo.Cliente;
import modelo.Complexidade;
import modelo.Oficina;
import modelo.Ordemservico;
import modelo.Servico;
import modelo.Tipo;

@ManagedBean
public class ServicoMB implements IManagedBean{

    private Servico servico;
    private List<Servico> lista;
    private ServicoDAO dao;
    private Complexidade complexidade;
    private ComplexidadeDAO cDao;
    private Integer idComplexidade;
    private LinkedHashMap<String, Integer> ListaMapComplexidade;
    private TipoDAO tDao;
    private Tipo tipo;
    private Integer idTipo;
    private LinkedHashMap<String, Integer> ListaMapTipo;
    private Integer idOficina;        
    private String mensagem;
    private String strValor;
    
    public ServicoMB() {
    }
    
    @Override
    @PostConstruct
    public void inicializar() {
        mensagem = "";
        dao = new ServicoDAO();
        HttpSession session = SessionUtils.getSession();
        Atendente a = (Atendente) session.getAttribute("usu");
        idOficina = a.getOficina().getId();
        lista = dao.listarPorOficina(idOficina);
        
        tipo = null;
        tDao = new TipoDAO();
        List<Tipo> tempTipos = tDao.listar();
        ListaMapTipo = new LinkedHashMap<>();
        for (Tipo tempTipo : tempTipos) {
            ListaMapTipo.put(tempTipo.getNome(), tempTipo.getId());
        }
        
        complexidade = null;
        cDao = new ComplexidadeDAO();
        List<Complexidade> tempComps = cDao.listar();
        ListaMapComplexidade = new LinkedHashMap<>();
        for (Complexidade tempComp : tempComps) {
            ListaMapComplexidade.put(tempComp.getDescricao(), tempComp.getId());
        }
        
        if (servico == null) {
            // obtem parametro
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            String idParam = ctx.getRequestParameterMap().get("id");

            if (idParam != null && !idParam.equals("")) {
                try {
                    servico = getServicoPorId(Integer.parseInt(idParam));
                } catch (NumberFormatException e) {
                    mensagem = e.getMessage();
                }
            }

            if (servico == null) {
                servico = new Servico();
            }
        }
    }

    @Override
    public String salvar() {
        if(idTipo == 0){
            mensagem = "Selecione um Tipo para seu Serviço!";
            return "";
        }
        OficinaDAO oDao = new OficinaDAO();
        
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        idOficina = Integer.parseInt(ctx.getRequestParameterMap().get("idoficina"));
        Oficina o = (Oficina) oDao.getPorId(idOficina);
        
        servico.setOficina(o);
        
        complexidade = (Complexidade) cDao.getPorId(idComplexidade);
        servico.setIdComplexidade(complexidade);
        
        tipo = (Tipo) tDao.getPorId(idTipo);
        servico.setTipo(tipo);
        servico.setValor(utilMB.converterParaValorBigDecimal(strValor));
        mensagem = dao.salvar(servico);
        lista = dao.listarPorOficina(idOficina);
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

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<Servico> getLista() {
        return lista;
    }

    public void setLista(List<Servico> lista) {
        this.lista = lista;
    }

    public ServicoDAO getDao() {
        return dao;
    }

    public void setDao(ServicoDAO dao) {
        this.dao = dao;
    }

    private Servico getServicoPorId(int parseInt) {
        servico = (Servico) dao.getPorId(parseInt);
        return servico;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Complexidade getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(Complexidade complexidade) {
        this.complexidade = complexidade;
    }

    public LinkedHashMap<String, Integer> getListaMapComplexidade() {
        return ListaMapComplexidade;
    }

    public void setListaMapComplexidade(LinkedHashMap<String, Integer> ListaMapComplexidade) {
        this.ListaMapComplexidade = ListaMapComplexidade;
    }

    public TipoDAO gettDao() {
        return tDao;
    }

    public void settDao(TipoDAO tDao) {
        this.tDao = tDao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LinkedHashMap<String, Integer> getListaMapTipo() {
        return ListaMapTipo;
    }

    public void setListaMapTipo(LinkedHashMap<String, Integer> ListaMapTipo) {
        this.ListaMapTipo = ListaMapTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdComplexidade() {
        return idComplexidade;
    }

    public void setIdComplexidade(Integer idComplexidade) {
        this.idComplexidade = idComplexidade;
    }

    public ComplexidadeDAO getcDao() {
        return cDao;
    }

    public void setcDao(ComplexidadeDAO cDao) {
        this.cDao = cDao;
    }

    public Integer getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    public String getStrValor() {
        return strValor;
    }

    public void setStrValor(String strValor) {
        this.strValor = strValor;
    }
    
}
