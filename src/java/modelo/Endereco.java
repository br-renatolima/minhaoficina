/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e")
    , @NamedQuery(name = "Endereco.findById", query = "SELECT e FROM Endereco e WHERE e.id = :id")
    , @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep")})
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Bairro")
    private String bairro;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cep")
    private String cep;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Complemento")
    private String complemento;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Numero")
    private String numero;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Rua")
    private String rua;
    
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id")
//    private Pessoa idPessoa;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEndereco")
//    private Collection<Oficina> oficinaCollection;
    
    @JoinColumn(name = "uf")
    @ManyToOne
    private Uf uf;
    

    public Endereco() {
    }

    public Endereco(Integer id) {
        this.id = id;
    }

    public Endereco(Integer id, String cep) {
        this.id = id;
        this.cep = cep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

//    @XmlTransient
//    public Collection<Cliente> getClienteCollection() {
//        return clienteCollection;
//    }
//
//    public void setClienteCollection(Collection<Cliente> clienteCollection) {
//        this.clienteCollection = clienteCollection;
//    }

//    @XmlTransient
//    public Collection<Oficina> getOficinaCollection() {
//        return oficinaCollection;
//    }
//
//    public void setOficinaCollection(Collection<Oficina> oficinaCollection) {
//        this.oficinaCollection = oficinaCollection;
//    }

    public Uf getIdUf() {
        return uf;
    }

    public void setIdUf(Uf idUf) {
        this.uf = idUf;
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
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Endereco[ id=" + id + " ]";
    }

//    public Collection<Pessoa> getPessoaCollection() {
//        return pessoaCollection;
//    }
//
//    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
//        this.pessoaCollection = pessoaCollection;
//    }

//    public Pessoa getIdPessoa() {
//        return idPessoa;
//    }
//
//    public void setIdPessoa(Pessoa idPessoa) {
//        this.idPessoa = idPessoa;
//    }
//    
}
