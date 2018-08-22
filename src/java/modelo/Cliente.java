package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@RequestScoped
@Entity
@Table(name = "cliente")


@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id")
    , @NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
})

public class Cliente extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
//    @Transient
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteId")
//    private Collection<Telefone> telefoneCollection;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Telefone telefone;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, targetEntity = Ordemservico.class)
    private Collection<Ordemservico> ordemservicoCollection;
    
    @Transient
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Collection<Equipamento> equipamentoCollection;

    public Cliente() {
        inicializar();
    }



    public Cliente(Date dataCadastro) {
        inicializar();
        super.setDataCadastro(dataCadastro);
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

//    @XmlTransient
//    public Collection<Telefone> getTelefoneCollection() {
//        return telefoneCollection;
//    }
//
//    public void setTelefoneCollection(Collection<Telefone> telefoneCollection) {
//        this.telefoneCollection = telefoneCollection;
//    }

    @XmlTransient
    public Collection<Ordemservico> getOrdemservicoCollection() {
        return ordemservicoCollection;
    }

    public void setOrdemservicoCollection(Collection<Ordemservico> ordemservicoCollection) {
        this.ordemservicoCollection = ordemservicoCollection;
    }

    @XmlTransient
    public Collection<Equipamento> getEquipamentoCollection() {
        return equipamentoCollection;
    }

    public void setEquipamentoCollection(Collection<Equipamento> equipamentoCollection) {
        this.equipamentoCollection = equipamentoCollection;
    }

    @Override
    public void setIdEndereco(Endereco idEndereco) {
        super.setIdEndereco(idEndereco); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Endereco getIdEndereco() {
        return super.getIdEndereco(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNome() {
        return super.getNome(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDataCadastro(Date dataCadastro) {
        super.setDataCadastro(dataCadastro); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getDataCadastro() {
        return super.getDataCadastro(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCpf(String cpf) {
        super.setCpf(cpf); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCpf() {
        return super.getCpf(); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (super.getId() != null ? super.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((super.getId() == null && other.getId() != null) || (super.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cliente[ id=" + super.getId() + " ]";
    }

    private void inicializar() {
        this.equipamentoCollection = new ArrayList<>();
        this.ordemservicoCollection = new ArrayList<>();
//        this.telefoneCollection = new ArrayList<>();
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    
}
