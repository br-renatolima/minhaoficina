package modelo;

import bean.utilMB;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

//@Entity
//@Table(name = "pessoa")
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
@RequestScoped
public class Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Cpf")
    private String cpf;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataCadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
    @Transient
    private String dataCadastroPTBR;
    
// @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Email")
    private String email;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Nome")
    private String nome;
    
    @Transient
    @JoinColumn(name = "IdEndereco", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Endereco idEndereco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.replace(".", "").replace("-", "").replace("_", "");
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = new Date();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getDataCadastroPTBR() {
        this.setDataCadastroPTBR(utilMB.formatarDataPtBr(this.dataCadastro));
        return dataCadastroPTBR;
    }

    public void setDataCadastroPTBR(String dataCadastroPTBR) {
        this.dataCadastroPTBR = dataCadastroPTBR;
    }
    
}
