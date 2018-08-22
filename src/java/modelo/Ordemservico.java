package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "ordemservico")

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "modelo.Ordemservico.findAll", query = "SELECT o FROM Ordemservico o")
    , @NamedQuery(name = "modelo.Ordemservico.findById", query = "SELECT o FROM Ordemservico o WHERE o.id = :id")
    , @NamedQuery(name = "Ordemservico.findByOficina", query = "SELECT o FROM Ordemservico o WHERE o.oficina = :idoficina")
    , @NamedQuery(name = "Ordemservico.findByDataEntrega", query = "SELECT o FROM Ordemservico o WHERE o.dataEntrega = :dataEntrega")
    , @NamedQuery(name = "Ordemservico.findByDataServico", query = "SELECT o FROM Ordemservico o WHERE o.dataServico = :dataServico")})

public class Ordemservico implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataEntrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrega;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataServico")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataServico;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor")
    private BigDecimal valor;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "equipamento_ordemservico",
        joinColumns = @JoinColumn(name = "ordemservicoCollection_Id"),
        inverseJoinColumns = @JoinColumn(name = "equipamentoCollection_Id")
    )
    private Collection<Equipamento> equipamentoCollection;
    
    @JoinTable(name = "servico_ordemservico", joinColumns = {
        @JoinColumn(name = "OrdemServico", referencedColumnName = "Id")}, inverseJoinColumns = {
        @JoinColumn(name = "Servico", referencedColumnName = "Id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Servico> servicoCollection;
    
    @JoinColumn(name = "clienteId")
    @ManyToOne
    private Cliente cliente;
    
    @JoinColumn(name = "status")
    @ManyToOne
    private Status status;
    
    @JoinColumn(name = "atendenteId")
    @ManyToOne
    private Atendente atendente;
    
    @JoinColumn(name = "oficina")
    @ManyToOne
    private Oficina oficina;
    
    @Transient
    private String dataSolicitacao;
    @Transient
    private String dataFim;

    
    @OneToMany(mappedBy = "Ordemservico", fetch = FetchType.EAGER)
    private List<Pagamento> Pagamentos;

    public Ordemservico() {
    }

    public Ordemservico(Integer id) {
        this.id = id;
    }

    public Ordemservico(Integer id, Date dataEntrega, Date dataServico) {
        this.id = id;
        this.dataEntrega = dataEntrega;
        this.dataServico = dataServico;
    }

    public Ordemservico(Integer id, Date dataEntrega, Date dataServico, Collection<Equipamento> equipamentoCollection, Collection<Servico> servicoCollection, Cliente cliente, String dataSolicitacao, String dataFim) {
        this.id = id;
        this.dataEntrega = dataEntrega;
        this.dataServico = dataServico;
        this.equipamentoCollection = equipamentoCollection;
        this.servicoCollection = servicoCollection;
        this.cliente = cliente;
        this.dataSolicitacao = dataSolicitacao;
        this.dataFim = dataFim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataServico() {
        return dataServico;
    }

    public void setDataServico(Date dataServico) {
        this.dataServico = dataServico;
    }

    @XmlTransient
    public Collection<Equipamento> getEquipamentoCollection() {
        return equipamentoCollection;
    }

    
    public void setEquipamentoCollection(Collection<Equipamento> equipamentoCollection) {
        this.equipamentoCollection = equipamentoCollection;
    }

    @XmlTransient
    public Collection<Servico> getServicoCollection() {
        return servicoCollection;
    }

    public void setServicoCollection(Collection<Servico> servicoCollection) {
        this.servicoCollection = servicoCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordemservico)) {
            return false;
        }
        Ordemservico other = (Ordemservico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Ordemservico[ id=" + id + " ]";
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

//    public Map<String, Integer> getListaMapStatus() {
//        return listaMapStatus;
//    }
//
//    public void setListaMapStatus(Map<String, Integer> listaMapStatus) {
//        this.listaMapStatus = listaMapStatus;
//    }
//    

    public List<Pagamento> getPagamentos() {
        return Pagamentos;
    }

    public void setPagamentos(List<Pagamento> Pagamentos) {
        this.Pagamentos = Pagamentos;
    }
}
