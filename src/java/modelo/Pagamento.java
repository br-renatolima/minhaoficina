package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "pagamento")

@NamedQueries({
    @NamedQuery(name = "modelo.Pagamento.findAll", query = "SELECT p FROM Pagamento p")
    ,@NamedQuery(name = "modelo.Pagamento.findById", query = "SELECT p FROM Pagamento p WHERE p.Id = :id")
})
public class Pagamento implements Serializable {

    @Id
    @GeneratedValue
    private Long Id;
    private Date DataPagamento;
    private BigDecimal Valor;
    @ManyToOne(fetch = FetchType.EAGER)
    private Ordemservico Ordemservico;

    public Pagamento(BigDecimal Valor, Ordemservico Ordemservico) {
        this.Valor = Valor;
        this.Ordemservico = Ordemservico;
    }

    public Pagamento(Date DataPagamento, BigDecimal Valor, Ordemservico Ordemservico) {
        this.DataPagamento = DataPagamento;
        this.Valor = Valor;
        this.Ordemservico = Ordemservico;
    }

    public Pagamento() {
        DataPagamento = new Date();
    }

    public Long getID() {
        return Id;
    }

    public void setID(Long ID) {
        this.Id = ID;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Date getDataPagamento() {
        return DataPagamento;
    }

    public void setDataPagamento(Date DataPagamento) {
        this.DataPagamento = DataPagamento;
    }

    public BigDecimal getValor() {
        return Valor;
    }

    public void setValor(BigDecimal Valor) {
        this.Valor = Valor;
    }

    public Ordemservico getOrdemservico() {
        return Ordemservico;
    }

    public void setOrdemservico(Ordemservico Ordemservico) {
        this.Ordemservico = Ordemservico;
    }
    
}
