package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class LinksMB {
    private String home;
    private String registrarCliente;
    private String login;
    private String registrarUsuario;
    private String registrarEquipamento;
    private String registrarTipo;
    private String registrarStatus;
    private String registrarOficina;
    private String registrarMarca;
    private String registrarComplexidade;
    private String registrarServico;
    private String painelCliente;
    private String painelUsuario;
    private String solicitarOrdemServico;
    private String acompanharOrdemServico;

    public LinksMB() {
        inicializar();
    }
    
    
    private void inicializar(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
 
        this.home = "../index.xhtml";
        this.login = "../usuario/login.xhtml";
        this.solicitarOrdemServico = "../ordemservico/solicitar.xhtml";
        this.acompanharOrdemServico = "../ordemservico/acompanhar.xhtml";
        this.registrarCliente = "../cliente/registrar.xhtml";
        this.registrarUsuario = "../usuario/registrar.xhtml";
        this.registrarTipo = "../tipo/registrar.xhtml";
        this.registrarStatus = "../status/registrar.xhtml";
        this.registrarMarca = "../marca/registrar.xhtml";
        this.registrarOficina = "../oficina/registrar.xhtml";
        this.registrarEquipamento = "../equipamento/registrar.xhtml";
        this.registrarComplexidade = "../complexidade/registrar.xhtml";
        this.registrarServico = "../servico/registrar.xhtml";
        this.painelCliente = "../cliente/painel.xhtml";
        this.painelUsuario = "../usuario/painel.xhtml";
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getRegistrarCliente() {
        return registrarCliente;
    }

    public void setRegistrarCliente(String registrarCliente) {
        this.registrarCliente = registrarCliente;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRegistrarUsuario() {
        return registrarUsuario;
    }

    public void setRegistrarUsuario(String registrarUsuario) {
        this.registrarUsuario = registrarUsuario;
    }

    public String getPainelCliente() {
        return painelCliente;
    }

    public void setPainelCliente(String painelCliente) {
        this.painelCliente = painelCliente;
    }

    public String getNovaOrdemServico() {
        return solicitarOrdemServico;
    }

    public void setNovaOrdemServico(String novaOrdemServico) {
        this.solicitarOrdemServico = novaOrdemServico;
    }

    public String getRegistrarEquipamento() {
        return registrarEquipamento;
    }

    public void setRegistrarEquipamento(String registrarEquipamento) {
        this.registrarEquipamento = registrarEquipamento;
    }

    public String getSolicitarOrdemServico() {
        return solicitarOrdemServico;
    }

    public void setSolicitarOrdemServico(String solicitarOrdemServico) {
        this.solicitarOrdemServico = solicitarOrdemServico;
    }

    public String getRegistrarTipo() {
        return registrarTipo;
    }

    public void setRegistrarTipo(String registrarTipo) {
        this.registrarTipo = registrarTipo;
    }

    public String getRegistrarMarca() {
        return registrarMarca;
    }

    public void setRegistrarMarca(String registrarMarca) {
        this.registrarMarca = registrarMarca;
    }

    public String getRegistrarOficina() {
        return registrarOficina;
    }

    public void setRegistrarOficina(String registrarOficina) {
        this.registrarOficina = registrarOficina;
    }

    public String getPainelUsuario() {
        return painelUsuario;
    }

    public void setPainelUsuario(String painelUsuario) {
        this.painelUsuario = painelUsuario;
    }

    public String getRegistrarServico() {
        return registrarServico;
    }

    public void setRegistrarServico(String registrarServico) {
        this.registrarServico = registrarServico;
    }

    public String getRegistrarComplexidade() {
        return registrarComplexidade;
    }

    public void setRegistrarComplexidade(String registrarComplexidade) {
        this.registrarComplexidade = registrarComplexidade;
    }

    public String getRegistrarStatus() {
        return registrarStatus;
    }

    public void setRegistrarStatus(String registrarStatus) {
        this.registrarStatus = registrarStatus;
    }

    public String getAcompanharOrdemServico() {
        return acompanharOrdemServico;
    }

    public void setAcompanharOrdemServico(String acompanharOrdemServico) {
        this.acompanharOrdemServico = acompanharOrdemServico;
    }
    
}
