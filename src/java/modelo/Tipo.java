package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t")
    , @NamedQuery(name = "Tipo.findById", query = "SELECT t FROM Tipo t WHERE t.id = :id")})
public class Tipo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    
    private Integer id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Nome")
    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipo")
    private Collection<Equipamento> equipamentoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo")
    private Collection<Servico> servicoCollection;

    public Tipo(String nome) {
        this.nome = nome;
    }

    public Tipo() {
    }

    public Tipo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Equipamento> getEquipamentoCollection() {
        return equipamentoCollection;
    }

    public void setEquipamentoCollection(Collection<Equipamento> equipamentoCollection) {
        this.equipamentoCollection = equipamentoCollection;
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
        if (!(object instanceof Tipo)) {
            return false;
        }
        Tipo other = (Tipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tipo[ id=" + id + " ]";
    }

    public Collection<Servico> getServicoCollection() {
        return servicoCollection;
    }

    public void setServicoCollection(Collection<Servico> servicoCollection) {
        this.servicoCollection = servicoCollection;
    }
    
}
