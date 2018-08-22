package bean;

import dao.AtendenteDAO;
import dao.MasterDao;
import dao.OficinaDAO;
import dao.UfDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Atendente;
import modelo.Oficina;
import modelo.Ordemservico;
import modelo.Status;
import modelo.Uf;
import modelview.ordemservicoMVVM;

@ManagedBean
@SessionScoped
public class OficinaMB implements IManagedBean {

    private Oficina oficina;
    private OficinaDAO dao;
    private AtendenteDAO aDao;
    private List<Oficina> lista;
    private List<Atendente> listaAtendente;
    private List<Uf> listaUf;
    private List<Ordemservico> listaOs;
    private List<ordemservicoMVVM> listaOsMVVM;
    private Map<String, Integer> listaMap;
    private Map<String, Integer> listaMapAtendente;
    private Map<String, Integer> listaMapUf;
    private String mensagem;
    private Atendente atendente;
    private Integer idUf;
    private String cpf;
    private List<Status> listaStatus;
    private Map<String, Integer> listaMapStatus;
    private Integer idStatus;
    private MasterDao masterDao;

    public OficinaMB() {

    }

    @PostConstruct
    @Override
    public void inicializar() {
        mensagem = "";
        cpf = "";
        dao = new OficinaDAO();
        aDao = new AtendenteDAO();
        masterDao = new MasterDao();
        listaMap = new LinkedHashMap<>();
        lista = dao.listar();
        for (Oficina o : lista) {
            listaMap.put(o.getNomeFantasia(), o.getId());
        }

        UfDAO ufdao = new UfDAO();
        listaUf = new ArrayList<>();
        listaUf = ufdao.listar();
        listaMapUf = new LinkedHashMap<>();
        for (Uf u : listaUf) {
            listaMapUf.put(u.getSigla() + " - " + u.getDescricao(), u.getId());
        }

        if (oficina == null) {
            // obtem parametro
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            String idParam = ctx.getRequestParameterMap().get("id");

            if (idParam != null && !idParam.equals("")) {
                try {
                    oficina = getOficinaPorId(Integer.parseInt(idParam));
                } catch (NumberFormatException e) {
                    mensagem = e.getMessage();
                }
            }

            if (oficina == null) {
                oficina = new Oficina();
                
            }
        }

    }

    @Override
    public String salvar() {
        for (Uf u : listaUf) {
            if (idUf == u.getId()) {
                oficina.getIdEndereco().setIdUf(u);
            }
        }
        oficina.setDataCadastro(new Date());
        mensagem = dao.salvar(oficina);
        lista = dao.listar();
        return mensagem;
    }

    public String incluirAtendente() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String idParam = ctx.getRequestParameterMap().get("id");
        oficina = getOficinaPorId(Integer.parseInt(idParam));
        if (oficina.getId() == null) {
            mensagem = "Oficina inexistente!";
            return "";
        }
        Atendente a = this.getAtendenteByCpf();
        if (atendente == null) {
            mensagem = "CPF não encontrado!";
            return "";
        }
        if (atendente.getOficina() != null) {
            mensagem = "O atendente já está vinculado a uma oficina!";
            return "";
        }
        atendente.setOficina(oficina);
        mensagem = aDao.atualizar(a);
        oficina = null;
        return "";
    }

    public Atendente getAtendenteByCpf() {
        atendente = aDao.getPorCpf(utilMB.retirarCaracterEspecial(cpf));
        return atendente;
    }

    public void setMinhasOs(String id) {

        listaOs = new ArrayList<>();
        listaOsMVVM = new ArrayList<>();
        listaOs = dao.listarOs(id);
        for (Ordemservico os : listaOs) {

            List<Object> listaObj = masterDao.listar("Status");
            listaMapStatus = new LinkedHashMap<>();
            listaStatus = new ArrayList<>();
            for (Object o : listaObj) {
                listaStatus.add((Status) o);
                listaMapStatus.put(((Status) o).getDescricao(), ((Status) o).getId());
            }
            
            switch (os.getStatus().getId()) {
                // Caso status = NOVO
                case 1:
                    listaMapStatus.remove("FINALIZADO");
                    listaMapStatus.remove("ENTREGUE");
                    break;
                // Caso status = AGUARDANDO ATENDIMENTO
                case 2:
                    listaMapStatus.remove("NOVO");
                    listaMapStatus.remove("FINALIZADO");
                    listaMapStatus.remove("ENTREGUE");
                    break;
                // Caso status = EM PROCESSAMENTO
                case 3:
                    listaMapStatus.remove("NOVO");
                    listaMapStatus.remove("AGUARDANDO ATENDIMENTO");
                    listaMapStatus.remove("ENTREGUE");
                    break;
                // Caso status = COM PENDÊNCIA
                case 4:
                    listaMapStatus.remove("NOVO");
                    listaMapStatus.remove("FINALIZADO");
                    listaMapStatus.remove("ENTREGUE");
                    break;
                // Caso status = FINALIZADO
                case 5:
                    listaMapStatus.remove("NOVO");
                    listaMapStatus.remove("AGUARDANDO ATENDIMENTO");
                    listaMapStatus.remove("EM PROCESSAMENTO");
                    listaMapStatus.remove("COM PENDÊNCIA");
                    break;
                // Caso status = ENTREGUE
                case 6:
                    listaMapStatus.remove("NOVO");
                    listaMapStatus.remove("AGUARDANDO ATENDIMENTO");
                    listaMapStatus.remove("EM PROCESSAMENTO");
                    listaMapStatus.remove("COM PENDÊNCIA");
                    listaMapStatus.remove("FINALIZADO");
                    listaMapStatus.remove("CANCELADO");
                    break;
                // Caso status = CANCELADO
                case 7:
                    listaMapStatus.remove("NOVO");
                    listaMapStatus.remove("AGUARDANDO ATENDIMENTO");
                    listaMapStatus.remove("EM PROCESSAMENTO");
                    listaMapStatus.remove("COM PENDÊNCIA");
                    listaMapStatus.remove("FINALIZADO");
                    listaMapStatus.remove("ENTREGUE");
                    break;
            }

            ordemservicoMVVM mvvm = utilMB.OsParaMVVM(os);
            mvvm.setListaMapStatus(listaMapStatus);
            mvvm.setOrdemServico(os);

            listaOsMVVM.add(mvvm);
        }
    }

    @Override
    public String editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public OficinaDAO getDao() {
        return dao;
    }

    public void setDao(OficinaDAO dao) {
        this.dao = dao;
    }

    public List<Oficina> getLista() {
        return lista;
    }

    public void setLista(List<Oficina> lista) {
        this.lista = lista;
    }

    public Map<String, Integer> getListaMap() {
        return listaMap;
    }

    public void setListaMap(Map<String, Integer> listaMap) {
        this.listaMap = listaMap;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Atendente> getListaAtendente() {
        return listaAtendente;
    }

    public void setListaAtendente(List<Atendente> listaAtendente) {
        this.listaAtendente = listaAtendente;
    }

    public Map<String, Integer> getListaMapAtendente() {
        return listaMapAtendente;
    }

    public void setListaMapAtendente(Map<String, Integer> listaMapAtendente) {
        this.listaMapAtendente = listaMapAtendente;
    }

    public AtendenteDAO getaDao() {
        return aDao;
    }

    public void setaDao(AtendenteDAO aDao) {
        this.aDao = aDao;
    }

    public Map<String, Integer> getListaMapUf() {
        return listaMapUf;
    }

    public void setListaMapUf(Map<String, Integer> listaMapUf) {
        this.listaMapUf = listaMapUf;
    }

    public Integer getIdUf() {
        return idUf;
    }

    public void setIdUf(Integer idUf) {
        this.idUf = idUf;
    }

    public List<Uf> getListaUf() {
        return listaUf;
    }

    public void setListaUf(List<Uf> listaUf) {
        this.listaUf = listaUf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private Oficina getOficinaPorId(int id) {
        for (Oficina o : lista) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public List<Ordemservico> getListaOs() {
        return listaOs;
    }

    public void setListaOs(List<Ordemservico> listaOs) {
        this.listaOs = listaOs;
    }

    public List<ordemservicoMVVM> getListaOsMVVM() {
        return listaOsMVVM;
    }

    public void setListaOsMVVM(List<ordemservicoMVVM> listaOsMVVM) {
        this.listaOsMVVM = listaOsMVVM;
    }

    public Map<String, Integer> getListaMapStatus() {
        return listaMapStatus;
    }

    public void setListaMapStatus(Map<String, Integer> listaMapStatus) {
        this.listaMapStatus = listaMapStatus;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

}
