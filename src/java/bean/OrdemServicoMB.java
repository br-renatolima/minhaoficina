package bean;

import dao.AtendenteDAO;
import dao.ClienteDAO;
import dao.EquipamentoDAO;
import dao.OficinaDAO;
import dao.OrdemservicoDAO;
import dao.PagamentoDAO;
import dao.ServicoDAO;
import dao.StatusDAO;
import infraestrutura.EnviaEmail;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Atendente;
import modelo.Cliente;
import modelo.Equipamento;
import modelo.Oficina;
import modelo.Ordemservico;
import modelo.Pagamento;
import modelo.Servico;
import modelo.Status;

@SessionScoped
@ManagedBean
public class OrdemServicoMB implements IManagedBean {

    private OrdemservicoDAO daoOs;
    private Ordemservico ordemServico;
    private List<Ordemservico> listaOs;
    private Integer idStatus;

    private OficinaDAO daoOficina;
    private Oficina oficina;
    private Integer idOficina;
    private List<Oficina> listaOficina;

    private Atendente atendente;
    private AtendenteDAO daoAtendente;
    private Integer idAtendente;

    private ClienteDAO daoCliente;
    private Cliente cliente;
    private List<Cliente> listaCliente;

    private String CPF;
    private String dataPtBr;
    private String mensagem;
    private String dataEntrega;
    private String nomeEquipamento;
    private String valorPago;

    private BigDecimal valorPagto;
    private List<Pagamento> listaPagamento;
    private PagamentoDAO daoP;
    
    private StatusDAO daoStatus;

    private Equipamento equipamento;
    private EquipamentoDAO daoE;
    private Integer idEquipamento;
    private List<Equipamento> listaEquipamento;
    private LinkedHashMap<String, Integer> listaIdEquipamento;
    private Collection<Equipamento> listaEquipamentoOs;

    private ServicoDAO daoServico;
    private Servico servico;
    private List<Servico> listaServico;
    private List<Servico> listaServicoOs;
    private Map<String, Integer> listaMapServico;
    private Integer idServico;

    public OrdemServicoMB() {

    }

    @PostConstruct
    @Override
    public void inicializar() {
        daoOs = new OrdemservicoDAO();
        daoCliente = new ClienteDAO();
        daoAtendente = new AtendenteDAO();
        daoStatus = new StatusDAO();
        ordemServico = new Ordemservico();
        daoP = new PagamentoDAO();
        
        servico = new Servico();
        mensagem = "";
        CPF = "";
        dataPtBr = "";
        dataEntrega = "";
        valorPago = "";
        listaCliente = new ArrayList<>();
        listaEquipamento = new ArrayList<>();
        listaOs = new ArrayList<>();
        listaIdEquipamento = new LinkedHashMap<>();

        HttpSession session = SessionUtils.getSession();
        atendente = (Atendente) session.getAttribute("usu");
    }

    @Override
    public String salvar() {

        if (dataEntrega == null || idEquipamento == 0 || idServico == 0 || valorPago == "") {
            mensagem = "Informe os dados obrigatórios!";
            return "";
        }

        if (cliente != null) {

            ordemServico = new Ordemservico();
            ordemServico.setCliente(cliente);
            ordemServico.setAtendente(atendente);
            ordemServico.setOficina(oficina);
            

            try {
                ordemServico.setDataEntrega(utilMB.formatarDataSql(dataEntrega));
            } catch (ParseException ex) {
                Logger.getLogger(OrdemServicoMB.class.getName()).log(Level.SEVERE, null, ex);
            }

            Date data = new Date();
            ordemServico.setDataServico(data);

            listaEquipamentoOs = new ArrayList<>();
            for (Equipamento e : listaEquipamento) {
                if (e.getId() == idEquipamento) {
                    listaEquipamentoOs.add(e);
                }
            };

            listaServicoOs = new ArrayList<>();
            BigDecimal valor = BigDecimal.ZERO;
            
            for (Servico s : listaServico) {
                if (s.getId() == idServico) {
                    listaServicoOs.add(s);
                    valor = (s.getValor().add(valor)).multiply(s.getIdComplexidade().getMultiplicador());
                }
            }
            
            valorPagto = utilMB.converterParaValorBigDecimal(valorPago);
            
            if(valorPagto.compareTo(valor) == 1){
                mensagem = "O valor de pagamento informado é maior que da Ordem de Serviço!";
                return "";
            }

            ordemServico.setServicoCollection(listaServicoOs);
            ordemServico.setValor(valor);
            ordemServico.setEquipamentoCollection(listaEquipamentoOs);
            ordemServico.setStatus((Status) daoStatus.getPorId(1));
            mensagem = daoOs.salvar(ordemServico);

            if (mensagem.contains("sucesso")) {
                Pagamento pagto = new Pagamento();
                pagto.setValor(valorPagto);
                pagto.setOrdemservico(ordemServico);
                daoP.salvar(pagto);

                EnviaEmail envia = new EnviaEmail();
                envia.getMensagem().setDestino(ordemServico.getCliente().getEmail());
                envia.getMensagem().setTitulo("Nova Ordem de Serviço - #" + ordemServico.getId() + "");
                envia.getMensagem().setMensagem("Prezado sr(a) "
                        + ordemServico.getCliente().getNome() + ", <br/><br/>"
                        + "sua Ordem de Serviço de número <b>#" + ordemServico.getId() + "</b> "
                        + "foi registrada com sucesso! <br/>"
                        + "Para acompanhar o andamento da sua ordem de serviço, acesse : "
                        + "<a href='www.facebook.com' target='_blank'>www.facebook.com</a> "
                        + "ou entre em contato com a oficina " + ordemServico.getOficina().getNomeFantasia()
                        + "<br/><br/>"
                        + "<i>Minha Oficina - 2018</i>"
                );
                envia.enviaEmail();
            }
        } else {
            mensagem = "Cliente nulo!";
        }
        return "";
    }

    public String obterCliente() {
        mensagem = "";
        cliente = this.getClienteByCPF();

        if (cliente != null) {
            dataPtBr = utilMB.formatarDataPtBr(cliente.getDataCadastro());
            utilMB.setCliente(cliente);

            popularEquipamentos();
            popularOs();

            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            idOficina = Integer.parseInt(ctx.getRequestParameterMap().get("idoficina"));
            daoOficina = new OficinaDAO();
            oficina = (Oficina) daoOficina.getPorId(idOficina);
            idAtendente = Integer.parseInt(ctx.getRequestParameterMap().get("idatendente"));
            atendente = (Atendente) daoAtendente.getPorId(idAtendente);

            listaMapServico = new LinkedHashMap<>();
            daoServico = new ServicoDAO();
            listaServico = daoServico.listarPorOficina(idOficina);
            for (Servico s : listaServico) {
                listaMapServico.put(s.getNome(), s.getId());
            }
            return "";
        }
        inicializar();
        mensagem = "Cliente não encontrado!";
        return "";
    }

    public void obterServico() {
        servico = (Servico) daoServico.getPorId(idServico);
        servico.setValorComplexidade(servico.getValor().multiply(servico.getIdComplexidade().getMultiplicador()));
    }
    
    private void popularEquipamentos() {

        if (this.cliente == null) {
            mensagem = "Cliente não encontrado!";
            return;
        }

        daoE = new EquipamentoDAO();

        List<Equipamento> temp = new ArrayList<>();
        temp = daoE.listar();

        listaIdEquipamento = new LinkedHashMap<>();
        listaEquipamento = new ArrayList<>();

        for (Equipamento e : temp) {
            if (e.getCliente().getId() == cliente.getId()) {
                listaIdEquipamento.put(e.getNome(), e.getId());
                listaEquipamento.add(e);
            }
        }
    }

    private void popularOs() {

        List<Ordemservico> temp = daoOs.listar();
        if (temp.isEmpty() || temp.size() <= 0) {
            mensagem = "Cliente sem OS!";
            return;
        }

        if (this.cliente == null) {
            mensagem = "Cliente não encontrado!";
            return;
        }

        listaOs = new ArrayList<>();
        for (Ordemservico os : temp) {
            if (os.getCliente().getId() == this.cliente.getId() && os.getOficina().getId() == this.atendente.getOficina().getId()) {
                os.setDataFim(utilMB.formatarDataPtBr(os.getDataEntrega()));
                os.setDataSolicitacao(utilMB.formatarDataPtBr(os.getDataServico()));
                listaOs.add(os);
            }
        }
    }

    private Cliente getClienteByCPF() {
        listaCliente = new ArrayList<>();
        listaCliente = daoCliente.listar();

        for (Cliente c : listaCliente) {
            if (c.getCpf() == null ? this.CPF == null : c.getCpf().equals(this.CPF)) {
                setCliente(c);
                return c;
            }
        }
        return null;
    }

    public String atualizarStatus() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();

        Integer idOrdemServico = Integer.parseInt(ctx.getRequestParameterMap().get("idordemservico"));

        Status status = (Status) daoStatus.getPorId(idStatus);
        ordemServico = (Ordemservico) daoOs.getPorId(idOrdemServico);
        ordemServico.setStatus(status);

        mensagem = daoOs.atualizar(ordemServico);
        if (mensagem.contains("sucesso")) {
            mensagem = "Ordem de Serviço Atualizada!";
            EnviaEmail envia = new EnviaEmail();
            envia.getMensagem().setDestino(ordemServico.getCliente().getEmail());
            envia.getMensagem().setTitulo("Ordem de Serviço #" + ordemServico.getId() + " atualizada");
            envia.getMensagem().setMensagem("Prezado sr "
                    + ordemServico.getCliente().getNome() + ", <br/><br/>"
                    + "sua Ordem de Serviço de número <b>#" + ordemServico.getId() + "</b> "
                    + "foi atualizada! <br/>"
                    + "Para acompanhar o andamento da sua ordem de serviço, acesse : "
                    + "<a href=www.facebook.com target=_blank>www.facebook.com</a> "
                    + "ou entre em contato com a oficina " + ordemServico.getOficina().getNomeFantasia()
                    + "<br/><br/>"
                    + "<i>Minha Oficina - 2018</i>"
            );
            envia.enviaEmail();
        }
        return "";
    }

    public void listarServicosDisponiveis(){
        equipamento = daoE.getPorId(idEquipamento);
        listaServico = daoServico.listarPorTipo(equipamento.getIdTipo().getId());
        listaMapServico.clear();
        for (Servico s : listaServico) {
            if(s.getOficina().getId() == atendente.getOficina().getId()){
                listaMapServico.put(s.getNome(), s.getId());
            }
        }
    }
                
    
    
    
    
    
    
    
    
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String Cpf) {
        this.CPF = utilMB.retirarCaracterEspecial(Cpf);
    }

    public OrdemservicoDAO getDaoOs() {
        return daoOs;
    }

    public void setDaoOs(OrdemservicoDAO daoOs) {
        this.daoOs = daoOs;
    }

    public Ordemservico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(Ordemservico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public List<Ordemservico> getListaOs() {
        return listaOs;
    }

    public void setListaOs(List<Ordemservico> listaOs) {
        this.listaOs = listaOs;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getListaEquipamento() {
        return listaEquipamento;
    }

    public void setListaEquipamento(List<Equipamento> listaEquipamento) {
        this.listaEquipamento = listaEquipamento;
    }

    public LinkedHashMap<String, Integer> getListaIdEquipamento() {
        return listaIdEquipamento;
    }

    public void setListaIdEquipamento(LinkedHashMap<String, Integer> listaIdEquipamento) {
        this.listaIdEquipamento = listaIdEquipamento;
    }

    public String getDataPtBr() {
        return dataPtBr;
    }

    public void setDataPtBr(String dataPtBr) {
        this.dataPtBr = dataPtBr;
    }

    public ClienteDAO getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(ClienteDAO daoCliente) {
        this.daoCliente = daoCliente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String editar() {
        return "";
    }

    /**
     * Não é permitido excluir Ordens de Serviço!
     */
    @Override
    public void excluir() {
    }

    public Integer getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Integer idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public Collection<Equipamento> getListaEquipamentoOs() {
        return listaEquipamentoOs;
    }

    public void setListaEquipamentoOs(Collection<Equipamento> listaEquipamentoOs) {
        this.listaEquipamentoOs = listaEquipamentoOs;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) throws ParseException {
        this.dataEntrega = dataEntrega;
        this.ordemServico.setDataEntrega(utilMB.formatarDataSql(dataEntrega));
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public ServicoDAO getDaoServico() {
        return daoServico;
    }

    public void setDaoServico(ServicoDAO daoServico) {
        this.daoServico = daoServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<Servico> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<Servico> listaServico) {
        this.listaServico = listaServico;
    }

    public Map<String, Integer> getListaMapServico() {
        return listaMapServico;
    }

    public void setListaMapServico(Map<String, Integer> listaMapServico) {
        this.listaMapServico = listaMapServico;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    public OficinaDAO getDaoOficina() {
        return daoOficina;
    }

    public void setDaoOficina(OficinaDAO daoOficina) {
        this.daoOficina = daoOficina;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public List<Oficina> getListaOficina() {
        return listaOficina;
    }

    public void setListaOficina(List<Oficina> listaOficina) {
        this.listaOficina = listaOficina;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public AtendenteDAO getDaoAtendente() {
        return daoAtendente;
    }

    public void setDaoAtendente(AtendenteDAO daoAtendente) {
        this.daoAtendente = daoAtendente;
    }

    public Integer getIdAtendente() {
        return idAtendente;
    }

    public void setIdAtendente(Integer idAtendente) {
        this.idAtendente = idAtendente;
    }

    public List<Servico> getListaServicoOs() {
        return listaServicoOs;
    }

    public void setListaServicoOs(List<Servico> listaServicoOs) {
        this.listaServicoOs = listaServicoOs;
    }

    public StatusDAO getDaoStatus() {
        return daoStatus;
    }

    public void setDaoStatus(StatusDAO daoStatus) {
        this.daoStatus = daoStatus;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public BigDecimal getValorPagto() {
        return valorPagto;
    }

    public void setValorPagto(BigDecimal valorPagto) {
        this.valorPagto = valorPagto;
    }

    public EquipamentoDAO getDaoE() {
        return daoE;
    }

    public void setDaoE(EquipamentoDAO daoE) {
        this.daoE = daoE;
    }

    public List<Pagamento> getListaPagamento() {
        return listaPagamento;
    }

    public void setListaPagamento(List<Pagamento> listaPagamento) {
        this.listaPagamento = listaPagamento;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }

}
