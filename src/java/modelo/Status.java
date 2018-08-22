package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s"),
    @NamedQuery(name = "Status.findById", query = "SELECT s FROM Status s WHERE s.id = :id")
})
public class Status implements Serializable {
    @GeneratedValue
    @Id
    private Integer id;
    
    private String descricao;
    
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER, targetEntity = Ordemservico.class)
    private Collection<Ordemservico> ordemservicoCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status(String descricao) {
        this.descricao = descricao;
    }

    public Status(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Status() {
    }

    public Collection<Ordemservico> getOrdemservicoCollection() {
        return ordemservicoCollection;
    }

    public void setOrdemservicoCollection(Collection<Ordemservico> ordemservicoCollection) {
        this.ordemservicoCollection = ordemservicoCollection;
    }
}
