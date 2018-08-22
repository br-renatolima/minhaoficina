package bean;

import dao.ClienteDAO;
import dao.EquipamentoDAO;
import infraestrutura.EnviaEmail;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.Equipamento;
import modelo.Ordemservico;
import modelo.Pessoa;
import modelview.ordemservicoMVVM;

@ManagedBean
public class ClienteMB implements IManagedBean {

    private Cliente cliente;
    private Pessoa pessoa;
    private List<Cliente> clientes;
    private ClienteDAO dao;
    private Collection<Ordemservico> listaOs;
    private String CPF;
    private String mensagemSucesso;
    private String mensagemErro;
    private String dataPtBr;
    private List<Equipamento> listaEquipamentos;
    private List<ordemservicoMVVM> listaIndex;
    

    public ClienteMB() {
    }

    @Override
    @PostConstruct
    public void inicializar() {
        this.mensagemSucesso = "";
        this.mensagemErro = "";
        //this.cliente = new Cliente();
        this.pessoa = new Pessoa();
        this.dao = new ClienteDAO();
        this.clientes = dao.listar();
        this.listaEquipamentos = new ArrayList<>();
        
        
        if (cliente == null) {
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            String idParam = ctx.getRequestParameterMap().get("id");

            if (idParam != null && !idParam.equals("")) {
                try {
                    this.cliente = this.getPorId(Integer.parseInt(idParam));
                } catch (NumberFormatException e) {
                    mensagemErro = e.getMessage();
                }
            }

            if (this.cliente == null) {
                this.cliente = new Cliente();
            }
        }
    }

    @Override
    public String salvar() {
        Cliente c = this.getClienteByCPF();
        if (c == null) {
            java.sql.Date data = new java.sql.Date(new Date().getYear(), new Date().getMonth(), new Date().getDay());
            cliente.setDataCadastro(data);
            cliente.setCpf(CPF);
            dao.salvar(cliente);
            mensagemSucesso = "Cliente salvo com sucesso!";
            EnviaEmail envia = new EnviaEmail();
                envia.getMensagem().setDestino(cliente.getEmail());
                envia.getMensagem().setTitulo("Bem vindo " + cliente.getNome() + "!");
                envia.getMensagem().setMensagem("Olá " + cliente.getNome() + ", "
                        + "seja bem-vindo(a) ao Minha Oficina! <br/><br/>"
                        + "O minha oficina é um projeto independente onde você pode consultar suas "
                        + "Ordens de Serviço solicitadas, bem como seus status, valores, e ainda "
                        + "consultar oficinas perto de você!"
                        + "<br/><br/>"
                        + "Acesse o portal do <a href='www.facebook.com'>Minha Oficina</a> "
                        + "e resolva seus problemas!"
                        + "<i>Minha Oficina - 2018</i>"
                );
                envia.enviaEmail();
        }else{
            mensagemErro = "Este CPF já está cadastrado!";
        }
        return "";
    }

    public String listarServicos() throws Exception {
        Cliente c = this.getClienteByCPF();

        if (c == null) {
            this.mensagemErro = "Cliente não encontrado!";
            return "";
        }

        this.listaOs = c.getOrdemservicoCollection();
        if (this.listaOs.isEmpty()) {
            this.mensagemErro = "Cliente sem serviços!";
            return "";
        }
        
        listaIndex = new ArrayList<>();
        for (Ordemservico os : listaOs) {
            os.setDataSolicitacao(utilMB.formatarDataPtBr(os.getDataServico()));
            os.setDataFim(utilMB.formatarDataPtBr(os.getDataEntrega()));
            ordemservicoMVVM indexItem = utilMB.OsParaMVVM(os);
            listaIndex.add(indexItem);
        }

        return "";
    }

    public String obterCliente() {
        Cliente c = this.getClienteByCPF();
        if (c != null) {
            this.cliente = c;
            utilMB.setCliente(cliente);
            dataPtBr = utilMB.formatarDataPtBr(cliente.getDataCadastro());
            popularEquipamentos();
            return "";
        }
        mensagemErro = "Cliente não encontrado!";
        return "";
    }

    private void popularEquipamentos() {
        EquipamentoDAO daoE = new EquipamentoDAO();
        List<Equipamento> temp = daoE.listar();

        for (Equipamento e : temp) {
            if (e.getCliente().getId() == this.cliente.getId()) {
                this.listaEquipamentos.add(e);
            }
        }
    }

    private Cliente getClienteByCPF() {
        for (Cliente c : clientes) {
            if (c.getCpf() == null ? this.CPF == null : c.getCpf().equals(this.CPF)) {
                this.cliente = c;
                return c;
            }
        }
        return null;
    }

    @Override
    public String editar() {
        String msg = dao.atualizar(cliente);
        if(msg.contains("sucesso")){
            mensagemSucesso = msg;
        }else{
            mensagemErro = "msg";
        }
        return "";
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cliente getCliente() {
        this.dataPtBr = utilMB.formatarDataPtBr(cliente.getDataCadastro());
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ClienteDAO getDao() {
        return dao;
    }

    public void setDao(ClienteDAO dao) {
        this.dao = dao;
    }

    public Collection<Ordemservico> getListaOs() {
        return listaOs;
    }

    public void setListaOs(Collection<Ordemservico> listaOs) {
        this.listaOs = listaOs;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = utilMB.retirarCaracterEspecial(CPF);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagem) {
        this.mensagemErro = mensagem;
    }

    public List<Equipamento> getListaEquipamentos() {
        return listaEquipamentos;
    }

    public void setListaEquipamentos(List<Equipamento> listaEquipamentos) {

        this.listaEquipamentos = listaEquipamentos;
    }

    public String getDataPtBr() {
        return dataPtBr;
    }

    public void setDataPtBr(String dataPtBr) {
        this.dataPtBr = dataPtBr;
    }

    public List<ordemservicoMVVM> getListaIndex() {
        return listaIndex;
    }

    public void setListaIndex(List<ordemservicoMVVM> listaIndex) {
        this.listaIndex = listaIndex;
    }

    public String getMensagemSucesso() {
        return mensagemSucesso;
    }

    public void setMensagemSucesso(String mensagemSucesso) {
        this.mensagemSucesso = mensagemSucesso;
    }

    private Cliente getPorId(int parseInt) {
        return (Cliente) dao.getPorId(parseInt);
    }

}
