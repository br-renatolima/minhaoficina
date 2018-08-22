package dao;


public interface IDao {
    public String salvar(Object obj);
    public String atualizar(Object obj);
    public String excluir(Object obj);
    public Object getPorId(Integer id);
}
