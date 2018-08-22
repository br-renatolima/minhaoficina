package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@RequestScoped
@Entity
@Table(name = "atendente")

@NamedQueries({
    @NamedQuery(name = "modelo.Atendente.findAll", query = "SELECT a FROM Atendente a")
    ,@NamedQuery(name = "modelo.Atendente.findById", query = "SELECT a FROM Atendente a WHERE a.id = :id")
    ,@NamedQuery(name = "Atendente.findByOficina", query = "SELECT a FROM Atendente a WHERE a.oficina = :oficina")
    ,@NamedQuery(name = "Atendente.findByCpf", query = "SELECT a FROM Atendente a WHERE a.cpf = :cpf")
    })

public class Atendente extends Pessoa implements Serializable{

    //@Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atendente")
    private Collection<Ordemservico> ordemservicoCollection;
    
    @JoinColumn(name = "oficina")
    @ManyToOne
    private Oficina oficina;

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
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public Collection<Ordemservico> getOrdemservicoCollection() {
        return ordemservicoCollection;
    }

    public void setOrdemservicoCollection(Collection<Ordemservico> ordemservicoCollection) {
        this.ordemservicoCollection = ordemservicoCollection;
    }

    @Override
    public void setDataCadastroPTBR(String dataCadastroPTBR) {
        super.setDataCadastroPTBR(dataCadastroPTBR);
    }

    @Override
    public String getDataCadastroPTBR() {
        return super.getDataCadastroPTBR();
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    
}
