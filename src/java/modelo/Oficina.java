package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "oficina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficina.findAll", query = "SELECT o FROM Oficina o")
    , @NamedQuery(name = "Oficina.findById", query = "SELECT o FROM Oficina o WHERE o.id = :id")
    , @NamedQuery(name = "Oficina.findByDataCadastro", query = "SELECT o FROM Oficina o WHERE o.dataCadastro = :dataCadastro")})
public class Oficina implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Cnpj")
    private String cnpj;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Email")
    private String email;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "NomeFantasia")
    private String nomeFantasia;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "RazaoSocial")
    private String razaoSocial;
    
//    @OneToMany(mappedBy = "oficinaId")
//    private Collection<Telefone> telefoneCollection;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Telefone telefone;
    
    //@JoinColumn(name = "IdEndereco", referencedColumnName = "Id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Endereco idEndereco;
    
    @OneToMany(mappedBy = "oficina")
    private Collection<Servico> servicoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oficina", fetch = FetchType.EAGER )
    private Collection<Atendente> atendenteCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oficina")
    private Collection<Ordemservico> ordemservicoCollection;

    public Oficina() {
        this.setIdEndereco(new Endereco());
        this.setTelefone(new Telefone());
    }

    public Oficina(Integer id) {
        this.id = id;
    }

    public Oficina(Integer id, Date dataCadastro) {
        this.id = id;
        this.dataCadastro = dataCadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

//    @XmlTransient
//    public Collection<Telefone> getTelefoneCollection() {
//        return telefoneCollection;
//    }
//
//    public void setTelefoneCollection(Collection<Telefone> telefoneCollection) {
//        this.telefoneCollection = telefoneCollection;
//    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }

    @XmlTransient
    public Collection<Servico> getServicoCollection() {
        return servicoCollection;
    }

    public void setServicoCollection(Collection<Servico> servicoCollection) {
        this.servicoCollection = servicoCollection;
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
        if (!(object instanceof Oficina)) {
            return false;
        }
        Oficina other = (Oficina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Oficina[ id=" + id + " ]";
    }

    public Collection<Atendente> getAtendenteCollection() {
        return atendenteCollection;
    }

    public void setAtendenteCollection(Collection<Atendente> atendenteCollection) {
        this.atendenteCollection = atendenteCollection;
    }

    public Collection<Ordemservico> getOrdemservicoCollection() {
        return ordemservicoCollection;
    }

    public void setOrdemservicoCollection(Collection<Ordemservico> ordemservicoCollection) {
        this.ordemservicoCollection = ordemservicoCollection;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    
}
