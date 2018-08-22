package bean;

import dao.ComplexidadeDAO;
import dao.MarcaDAO;
import dao.OficinaDAO;
import dao.StatusDAO;
import dao.TipoDAO;
import dao.UfDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.Complexidade;
import modelo.Endereco;
import modelo.Equipamento;
import modelo.Marca;
import modelo.Oficina;
import modelo.Ordemservico;
import modelo.Servico;
import modelo.Status;
import modelo.Tipo;
import modelo.Uf;
import modelo.Usuario;
import modelview.ordemservicoMVVM;

@ManagedBean
@SessionScoped
public class utilMB {

    private static Cliente cliente;

    public utilMB() throws UnsupportedEncodingException {
        inicializarBd();
    }

    public void inicializarBd() throws UnsupportedEncodingException {

        TipoDAO tipoDao = new TipoDAO();
        List<Tipo> listaTipo = tipoDao.listar();
        if (listaTipo.isEmpty()) {
            tipoDao.salvar(new Tipo("Tipo Genérico"));
            tipoDao.salvar(new Tipo("Ferramenta"));
            tipoDao.salvar(new Tipo("Instrumento Musical"));
        }

        UfDAO ufDao = new UfDAO();
        List<Uf> listaUf = ufDao.listar();
        if (listaUf.isEmpty()) {
            Uf uf = new Uf();
            uf.setDescricao("Rio de Janeiro");
            uf.setSigla("RJ");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("São Paulo");
            uf.setSigla("SP");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Acre");
            uf.setSigla("AC");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Sergipe");
            uf.setSigla("SE");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Santa Catarina");
            uf.setSigla("SC");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Rio Grande do Sul");
            uf.setSigla("RS");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Rio Grande do Norte");
            uf.setSigla("RN");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Roraima");
            uf.setSigla("RR");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Rondônia");
            uf.setSigla("RO");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Paraná");
            uf.setSigla("PR");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Piauí");
            uf.setSigla("PI");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Piauí");
            uf.setSigla("PI");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Pernambuco");
            uf.setSigla("PE");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Paraíba");
            uf.setSigla("PB");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Pará");
            uf.setSigla("PA");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Mato Grosso do Sul");
            uf.setSigla("MS");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Minas Gerais");
            uf.setSigla("MG");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Maranhão");
            uf.setSigla("MA");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Goiás");
            uf.setSigla("GO");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Espírito Santo");
            uf.setSigla("ES");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Distrito Federal");
            uf.setSigla("DF");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Ceará");
            uf.setSigla("CE");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Bahia");
            uf.setSigla("BA");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Amapá");
            uf.setSigla("AP");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Amazonas");
            uf.setSigla("AM");
            listaUf.add(uf);

            uf = new Uf();
            uf.setDescricao("Alagoas");
            uf.setSigla("AL");
            listaUf.add(uf);

            for (Uf uf1 : listaUf) {
                ufDao.salvar(uf1);
            }
        }

        OficinaDAO oficinaDao = new OficinaDAO();
        List<Oficina> listaOficina = oficinaDao.listar();
        Endereco en = new Endereco();
        Oficina o = new Oficina();
        if (listaOficina.isEmpty()) {
            o.setCnpj("00000000000000");
            o.setDataCadastro(new Date());
            o.setEmail("webmaster@minhaoficina.com.br");
            o.setNomeFantasia("Administração");
            o.setRazaoSocial("Administração do Sistema");
            en.setRua("rua 0");
            en.setBairro("bairro 0");
            en.setCep("00000000");
            en.setNumero("0");
            en.setComplemento("Complemento 0");
            for (Uf uf : listaUf) {
                if ("RJ".equals(uf.getSigla())) {
                    en.setIdUf(uf);
                }
            }
            o.setIdEndereco(en);
            oficinaDao.salvar(o);
        }

        UsuarioDAO usuarioDao = new UsuarioDAO();
        List<Usuario> listaUsuario = usuarioDao.listar();
        if (listaUsuario.isEmpty()) {
            Usuario u = new Usuario();
            u.setCpf("00000000000");
            u.setDataCadastro(new Date());
            u.setEmail("webmaster@minhaoficina.com.br");
            u.setIdEndereco(en);
            u.setLogin("admin");
            u.setOficina(o);
            u.setSenha("123"); // 202cb962ac59075b964b07152d234b70
            u.setNome("Administrador");
            usuarioDao.salvar(u);
        }

        StatusDAO statusDao = new StatusDAO();
        List<Status> listaStatus = statusDao.listar();
        if (listaStatus.isEmpty()) {
            Status s = new Status("NOVO");
            statusDao.salvar(s);
            s = new Status("AGUARDANDO ATENDIMENTO");
            statusDao.salvar(s);
            s = new Status("EM PROCESSAMENTO");
            statusDao.salvar(s);
            s = new Status("COM PENDÊNCIA");
            statusDao.salvar(s);
            s = new Status("FINALIZADO");
            statusDao.salvar(s);
            s = new Status("ENTREGUE");
            statusDao.salvar(s);
            s = new Status("CANCELADO");
            statusDao.salvar(s);
        }

        ComplexidadeDAO compDao = new ComplexidadeDAO();
        List<Complexidade> compLista = compDao.listar();
        if (compLista.isEmpty()) {
            Complexidade c = new Complexidade();
            c.setDescricao("Padrão");
            c.setMultiplicador(BigDecimal.ONE);
            compDao.salvar(c);
        }

        MarcaDAO marcaDao = new MarcaDAO();
        List<Marca> marcaLista = marcaDao.listar();
        if (marcaLista.isEmpty()) {
            marcaDao.salvar(new Marca("Fender"));
            marcaDao.salvar(new Marca("Strinberg"));
            marcaDao.salvar(new Marca("Bosch"));
            marcaDao.salvar(new Marca("Apple"));
            marcaDao.salvar(new Marca("Samsung"));
            marcaDao.salvar(new Marca("Microsoft"));
            marcaDao.salvar(new Marca("Nokia"));
            marcaDao.salvar(new Marca("Motorola"));
            marcaDao.salvar(new Marca("Lenovo"));
            marcaDao.salvar(new Marca("Xiaomi"));
            marcaDao.salvar(new Marca("Boss"));
            marcaDao.salvar(new Marca("Line 6"));
            marcaDao.salvar(new Marca("Crafter"));
            marcaDao.salvar(new Marca("Korg"));
            marcaDao.salvar(new Marca("Ibanez"));
            marcaDao.salvar(new Marca("Gibson"));
            marcaDao.salvar(new Marca("Tramontina"));
            marcaDao.salvar(new Marca("DeWALT"));
            marcaDao.salvar(new Marca("Leroy Merlin"));
            marcaDao.salvar(new Marca("Leroy Merlin"));
            marcaDao.salvar(new Marca("BLACK&DECKER"));
        }

    }

    public static void redirecionar(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String retirarCaracterEspecial(String s) {
        return s.replace(".", "").replace("-", "").replace("_", "");
    }

    public static String convertStringToMd5(String valor) throws UnsupportedEncodingException {
        MessageDigest mDigest;
        try {
            //Instanciamos o nosso HASH MD5, poderíamos usar outro como
            //SHA, por exemplo, mas optamos por MD5.
            mDigest = MessageDigest.getInstance("MD5");

            //Convert a String valor para um array de bytes em MD5
            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

            //Convertemos os bytes para hexadecimal, assim podemos salvar
            //no banco para posterior comparação se senhas
            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatarDataPtBr(Date data) {
        if (data == null) {
            return "";
        }
        String dataPtBr = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data);
        return dataPtBr;
    }

    static Date formatarDataSql(String source) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date nascimento = sdf.parse(source);
        java.sql.Date sqlDate = new java.sql.Date(nascimento.getTime());
        return sqlDate;
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        utilMB.cliente = cliente;
    }

    public static ordemservicoMVVM OsParaMVVM(Ordemservico os) {
        ordemservicoMVVM mv = new ordemservicoMVVM(os, os.getAtendente(), null, null, os.getOficina());
//                os.getId(),
//                os.getStatus().getDescricao(),
//                utilMB.formatarDataPtBr(os.getDataServico()),
//                utilMB.formatarDataPtBr(os.getDataEntrega()),
//                os.getAtendente().getNome(),
//                os.getOficina().getNomeFantasia(),
//                null,
//                null,
//                null,
//                converterParaReal(os.getValor())
//        );
        mv.setDataSolicitacao(utilMB.formatarDataPtBr(os.getDataServico()));
        mv.setDataEntrega(utilMB.formatarDataPtBr(os.getDataEntrega()));
        Equipamento equip = new Equipamento();
        Collection<Equipamento> listaEquip = os.getEquipamentoCollection();
        for (Equipamento e : listaEquip) {
            equip = e;
        }
        mv.setEquipamento(equip);
//        mv.setNomeEquipamento(equip.getNome());
//        mv.setMarca(equip.getIdMarca());
//        mv.setNomeMarca(equip.getIdMarca().getNome());

        Collection<Servico> listaServ = os.getServicoCollection();
        for (Servico s : listaServ) {
            //mv.setNomeServico(s.getNome());
            mv.setServico(s);
        }
        return mv;
    }

//    public static List<ordemservicoMVVM> OsParaMVVM(List<Ordemservico> ListaOs) {
//        List<ordemservicoMVVM> lista = new ArrayList<>();
//        for (Ordemservico os : ListaOs) {
//            ordemservicoMVVM mv = new ordemservicoMVVM(
//                    os.getId(),
//                    os.getStatus().getDescricao(),
//                    utilMB.formatarDataPtBr(os.getDataServico()),
//                    utilMB.formatarDataPtBr(os.getDataEntrega()),
//                    os.getAtendente().getNome(),
//                    os.getOficina().getNomeFantasia(),
//                    null,
//                    null,
//                    null,
//                    converterParaReal(os.getValor())
//            );
//            Equipamento equip = new Equipamento();
//            Collection<Equipamento> listaEquip = os.getEquipamentoCollection();
//            for (Equipamento e : listaEquip) {
//                equip = e;
//            }
//            mv.setNomeEquipamento(equip.getNome());
//            mv.setNomeMarca(equip.getIdMarca().getNome());
//
//            Collection<Servico> listaServ = os.getServicoCollection();
//            for (Servico s : listaServ) {
//                mv.setNomeServico(s.getNome());
//            }
//            lista.add(mv);
//        }
//        return lista;
//    }

    public static String converterParaReal(BigDecimal valor) {

        DecimalFormat formatoDois = new DecimalFormat("##,###,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        formatoDois.setMinimumFractionDigits(2);
        formatoDois.setParseBigDecimal(true);
        String convertido = formatoDois.format(valor);//valor.toString().replace(".", ",");
        //convertido = String.format("###.##0,00", convertido);
        return convertido;
    }
    
    public static BigDecimal converterParaValorBigDecimal(String valor) {
        BigDecimal retorno = new BigDecimal((valor.replaceAll(",", "#")).replaceAll("\\.", "").replaceAll("#", "."));
        return retorno;
    }
}
