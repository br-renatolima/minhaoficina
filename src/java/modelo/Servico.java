package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "servico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s")
    , @NamedQuery(name = "modelo.Servico.findById", query = "SELECT s FROM Servico s WHERE s.id = :id")
    , @NamedQuery(name = "Servico.findByOficina", query = "SELECT s FROM Servico s WHERE s.oficina = :idoficina")
    , @NamedQuery(name = "Servico.findByTipo", query = "SELECT s FROM Servico s WHERE s.tipo = :idtipo")
    //, @NamedQuery(name = "Servico.findByIdOficina", query = "SELECT s FROM Servico s WHERE s.id = :id")
    , @NamedQuery(name = "Servico.findByValor", query = "SELECT s FROM Servico s WHERE s.valor = :valor")})
public class Servico implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    private String nome;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Valor")
    private BigDecimal valor;
    
    @Transient
    private BigDecimal valorComplexidade;
    
    @ManyToMany(mappedBy = "servicoCollection", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Ordemservico> ordemservicoCollection;
    
    @JoinColumn(name = "IdComplexidade", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Complexidade idComplexidade;
    
    @ManyToOne(optional = false)
    private Oficina oficina;
    
    @ManyToOne(optional = false)
    private Tipo tipo;

    public Servico() {
    }

    public Servico(Integer id) {
        this.id = id;
    }

    public Servico(Integer id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
        this.valorComplexidade = valor.multiply(idComplexidade.getMultiplicador());
    }

    @XmlTransient
    public Collection<Ordemservico> getOrdemservicoCollection() {
        return ordemservicoCollection;
    }

    public void setOrdemservicoCollection(Collection<Ordemservico> ordemservicoCollection) {
        this.ordemservicoCollection = ordemservicoCollection;
    }

    public Complexidade getIdComplexidade() {
        return idComplexidade;
    }

    public void setIdComplexidade(Complexidade idComplexidade) {
        this.idComplexidade = idComplexidade;
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
        if (!(object instanceof Servico)) {
            return false;
        }
        Servico other = (Servico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Servico[ id=" + id + " ]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public BigDecimal getValorComplexidade() {
        return valorComplexidade;
    }

    public void setValorComplexidade(BigDecimal valorComplexidade) {
        this.valorComplexidade = valorComplexidade;
    }

    
}
