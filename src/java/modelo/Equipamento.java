package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "equipamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "modelo.Equipamento.findAll", query = "SELECT e FROM Equipamento e")
    , @NamedQuery(name = "modelo.Equipamento.findById", query = "SELECT e FROM Equipamento e WHERE e.id = :id")
    , @NamedQuery(name = "Equipamento.findByDataCadastro", query = "SELECT e FROM Equipamento e WHERE e.dataCadastro = :dataCadastro")})
public class Equipamento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Nome")
    private String nome;
    
    
    @ManyToMany(mappedBy = "equipamentoCollection", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Ordemservico> ordemservicoCollection;
    
    @JoinColumn(name = "ClienteId", referencedColumnName = "Id")
    @ManyToOne
    private Cliente cliente;
    
    @JoinColumn(name = "IdMarca", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Marca idMarca;
    
    @JoinColumn(name = "IdTipo", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Tipo idTipo;

    public Equipamento() {
    }

    public Equipamento(Integer id) {
        this.id = id;
    }

    public Equipamento(Integer id, Date dataCadastro) {
        this.id = id;
        this.dataCadastro = dataCadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Ordemservico> getOrdemservicoCollection() {
        return ordemservicoCollection;
    }

    
    public void setOrdemservicoCollection(Collection<Ordemservico> ordemservicoCollection) {
        this.ordemservicoCollection = ordemservicoCollection;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente clienteId) {
        this.cliente = clienteId;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
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
        if (!(object instanceof Equipamento)) {
            return false;
        }
        Equipamento other = (Equipamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Equipamento[ id=" + id + " ]";
    }
    
}
