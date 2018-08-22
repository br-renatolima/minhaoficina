package bean;

import javax.annotation.PostConstruct;

public interface IManagedBean {

    @PostConstruct
    public void inicializar();

    public String salvar();

    public String editar();

    public void excluir();

}
