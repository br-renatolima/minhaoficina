package modelview;

import java.math.BigDecimal;
import java.util.Map;
import modelo.Atendente;
import modelo.Equipamento;
import modelo.Oficina;
import modelo.Ordemservico;
import modelo.Servico;

public class ordemservicoMVVM {
    private Integer id;
//    private String descricaoStatus;
    private String dataSolicitacao;
    private String dataEntrega;
//    private String nomeAtendente;
//    private String nomeOficina;
//    private String nomeEquipamento;
//    private String nomeServico;
//    private String nomeMarca;
    private String valor;
    private Ordemservico ordemServico;
    private Atendente atendente;
    private Equipamento equipamento;
    private Servico servico;
    private Oficina oficina;
    private Map<String, Integer> listaMapStatus;

    public ordemservicoMVVM() {
    }

    public ordemservicoMVVM(Ordemservico ordemServico, Atendente atendente, Equipamento equipamento, Servico servico, Oficina oficina) {
        this.ordemServico = ordemServico;
        this.atendente = atendente;
        this.equipamento = equipamento;
        this.servico = servico;
        this.oficina = oficina;
    }

//    public ordemservicoMVVM(Integer id, String descricaoStatus, String dataSolicitacao, String dataEntrega, String nomeAtendente, String nomeOficina, String nomeEquipamento, String nomeServico, String nomeMarca, String valor) {
//        this.id = id;
//        this.descricaoStatus = descricaoStatus;
//        this.dataSolicitacao = dataSolicitacao;
//        this.dataEntrega = dataEntrega;
//        this.nomeAtendente = nomeAtendente;
//        this.nomeOficina = nomeOficina;
//        this.nomeEquipamento = nomeEquipamento;
//        this.nomeServico = nomeServico;
//        this.nomeMarca = nomeMarca;
//        this.valor = valor;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getDescricaoStatus() {
//        return descricaoStatus;
//    }
//
//    public void setDescricaoStatus(String descricaoStatus) {
//        this.descricaoStatus = descricaoStatus;
//    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

//    public String getNomeAtendente() {
//        return nomeAtendente;
//    }
//
//    public void setNomeAtendente(String nomeAtendente) {
//        this.nomeAtendente = nomeAtendente;
//    }
//
//    public String getNomeOficina() {
//        return nomeOficina;
//    }
//
//    public void setNomeOficina(String nomeOficina) {
//        this.nomeOficina = nomeOficina;
//    }
//
//    public String getNomeEquipamento() {
//        return nomeEquipamento;
//    }
//
//    public void setNomeEquipamento(String nomeEquipamento) {
//        this.nomeEquipamento = nomeEquipamento;
//    }
//
//    public String getNomeMarca() {
//        return nomeMarca;
//    }
//
//    public void setNomeMarca(String nomeMarca) {
//        this.nomeMarca = nomeMarca;
//    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Ordemservico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(Ordemservico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Map<String, Integer> getListaMapStatus() {
        return listaMapStatus;
    }

    public void setListaMapStatus(Map<String, Integer> listaMapStatus) {
        this.listaMapStatus = listaMapStatus;
    }

//    public String getNomeServico() {
//        return nomeServico;
//    }
//
//    public void setNomeServico(String nomeServico) {
//        this.nomeServico = nomeServico;
//    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }
    
}
