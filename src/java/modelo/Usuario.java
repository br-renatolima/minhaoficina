package modelo;

import bean.utilMB;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@RequestScoped
@Entity
@Table(name = "usuario")

@NamedQueries({
    @NamedQuery(name = "modelo.Usuario.findAll", query = "SELECT u FROM Usuario u")
    ,@NamedQuery(name = "modelo.Usuario.findById", query = "SELECT u FROM Usuario u WHERE Id = :id")
    })
public class Usuario extends Atendente implements Serializable {

    @Basic(optional = false)
    @Column(name = "login")
    private String Login;

    @Basic(optional = false)
    @Column(name = "senha")
    private String Senha;

    @Override
    public String getCpf() {
        return super.getCpf(); 
    }

    @Override
    public void setCpf(String cpf) {
        super.setCpf(cpf); 
    }

    @Override
    public Date getDataCadastro() {
        return super.getDataCadastro(); 
    }

    @Override
    public void setDataCadastro(Date dataCadastro) {
        super.setDataCadastro(new Date()); 
    }

    @Override
    public String getEmail() {
        return super.getEmail(); 
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); 
    }

    @Override
    public String getNome() {
        return super.getNome(); 
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome); 
    }

    @Override
    public Endereco getIdEndereco() {
        return super.getIdEndereco(); 
    }

    @Override
    public void setIdEndereco(Endereco idEndereco) {
        super.setIdEndereco(idEndereco); 
    }

    @Override
    public void setId(Integer id) {
        super.setId(id); 
    }

    @Override
    public Integer getId() {
        return super.getId(); 
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) throws UnsupportedEncodingException {
         this.Senha = utilMB.convertStringToMd5(Senha);
    }

    @Override
    public String getDataCadastroPTBR() {
        return super.getDataCadastroPTBR(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDataCadastroPTBR(String dataCadastroPTBR) {
        super.setDataCadastroPTBR(dataCadastroPTBR); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOficina(Oficina oficina) {
        super.setOficina(oficina); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Oficina getOficina() {
        return super.getOficina(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOrdemservicoCollection(Collection<Ordemservico> ordemservicoCollection) {
        super.setOrdemservicoCollection(ordemservicoCollection); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Ordemservico> getOrdemservicoCollection() {
        return super.getOrdemservicoCollection(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
