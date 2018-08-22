package bean;

import dao.ClienteDAO;
import dao.EquipamentoDAO;
import dao.MarcaDAO;
import dao.TipoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.Equipamento;
import modelo.Marca;
import modelo.Tipo;

@ManagedBean
public class EquipamentoMB implements IManagedBean{

    private Equipamento equipamento;
    private List<Equipamento> lista;
    private EquipamentoDAO eDao;
    private TipoDAO tDao;
    private List<Tipo> listaTipo;
    private Map<String, Integer> listaMapTipo;
    private Map<String, Integer> listaMapMarca;
    private Map<String, Integer> listaMapEquipamento;
    private MarcaDAO mDao;
    private List<Marca> listaMarca;
    private int idTipo;
    private Tipo tipo;
    private int idMarca;
    private String nome;
    private String mensagem;
    private Marca marca;
    private Cliente cliente;
    private int idCliente;
    private String cpf;
    
    public EquipamentoMB() {
    }

    @Override
    public String salvar() {
        
        if((idTipo == 0) || (idMarca == 0) || (equipamento.getNome() == "")){
            mensagem = "Preencha os campos obrigatórios!";
            return "";
        }
        
//        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
//        idCliente = Integer.parseInt(ctx.getRequestParameterMap().get("idcliente"));
//        ClienteDAO cDao = new ClienteDAO();
        cliente = getClienteByCpf(); //cDao.getPorId(idCliente);
        
        equipamento.setDataCadastro(new Date());
        
        for(Tipo t : listaTipo){
            if(t.getId() == idTipo){
                tipo = t;
            }
        }
        
        for(Marca m : listaMarca){
            if(m.getId() == idMarca){
                marca = m;
            }
        }
        
//        equipamento.setNome(nome);
        equipamento.setIdMarca(marca);
        equipamento.setIdTipo(tipo);
        equipamento.setCliente(cliente);
        
        mensagem = eDao.salvar(equipamento);
        
        inicializar();
        cpf = "";
        equipamento = null;
        return mensagem;
    }

    @Override
    public String editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @PostConstruct
    @Override
    public void inicializar() {
        this.eDao = new EquipamentoDAO();
        tDao = new TipoDAO();
        mDao = new MarcaDAO();
        nome = "";
        mensagem = "";
        //cpf = "";
        popularEquipamentos();
        
        this.listaMapTipo = new LinkedHashMap<>();
        this.listaTipo = tDao.listar();
        for (Tipo t : listaTipo) {
            this.listaMapTipo.put(t.getNome(), t.getId());
        }
        
        this.listaMapMarca = new LinkedHashMap<>();
        this.listaMarca = mDao.listar();
        for (Marca m : listaMarca) {
            this.listaMapMarca.put(m.getNome(), m.getId());
        }
        
        
        if (equipamento == null) {
            // obtem parametro
            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            String idParam = ctx.getRequestParameterMap().get("id");

            if (idParam != null && !idParam.equals("")) {
                try {
                    this.equipamento = this.getEquipamentoPorId(Integer.parseInt(idParam));
                } catch (NumberFormatException e) {
                    mensagem = e.getMessage();
                }
            }

            if (this.equipamento == null) {
                this.equipamento = new Equipamento();
            }
        }
    }

    private void popularEquipamentos() {
        //cliente = utilMB.getCliente();
        
        if(cliente == null){
            return;
        }
        
        EquipamentoDAO daoE = new EquipamentoDAO();
        
        List<Equipamento> temp = new ArrayList<>();
        temp = daoE.listar();
        
        listaMapEquipamento = new HashMap<>();
        lista = new ArrayList<>();
        
        for(Equipamento e : temp){
            if(e.getCliente().getId() == cliente.getId()){
                listaMapEquipamento.put(e.getNome(), e.getId());
                lista.add(e);
            }
        }
    }
    
    public Cliente getClienteByCpf() {
        ClienteDAO cDao = new ClienteDAO();
        cliente = cDao.getPorCpf(utilMB.retirarCaracterEspecial(cpf));
        if(cliente != null){
            nome = cliente.getNome();
            popularEquipamentos();
            return cliente;
        }
        nome = "Cliente não encontrado!";
        return null;
    }
    
    
    public EquipamentoDAO geteDao() {
        return eDao;
    }

    public void seteDao(EquipamentoDAO eDao) {
        this.eDao = eDao;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public List<Tipo> getListaTipo() {
        return listaTipo;
    }

    public void setListaTipo(List<Tipo> listaTipo) {
        this.listaTipo = listaTipo;
    }

    public List<Marca> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(List<Marca> listaMarca) {
        this.listaMarca = listaMarca;
    }
    
    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getLista() {
        return lista;
    }

    public void setLista(List<Equipamento> lista) {
        this.lista = lista;
    }

    public EquipamentoDAO getDao() {
        return eDao;
    }

    public void setDao(EquipamentoDAO dao) {
        this.eDao = dao;
    }

    public Map<String, Integer> getListaMapTipo() {
        return listaMapTipo;
    }

    public void setListaMapTipo(Map<String, Integer> listaMapTipo) {
        this.listaMapTipo = listaMapTipo;
    }

    public TipoDAO gettDao() {
        return tDao;
    }

    public void settDao(TipoDAO tDao) {
        this.tDao = tDao;
    }

    public MarcaDAO getmDao() {
        return mDao;
    }

    public void setmDao(MarcaDAO mDao) {
        this.mDao = mDao;
    }

    public Map<String, Integer> getListaMapMarca() {
        return listaMapMarca;
    }

    public void setListaMapMarca(Map<String, Integer> listaMapMarca) {
        this.listaMapMarca = listaMapMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return utilMB.getCliente();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Map<String, Integer> getListaMapEquipamento() {
        return listaMapEquipamento;
    }

    public void setListaMapEquipamento(Map<String, Integer> listaMapEquipamento) {
        this.listaMapEquipamento = listaMapEquipamento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Equipamento getEquipamentoPorId(int id) {
        Equipamento e = (Equipamento) eDao.getPorId(id);
        return e;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}
