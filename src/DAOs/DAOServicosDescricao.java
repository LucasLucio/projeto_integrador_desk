package DAOs;

import Entidades.ServicosDescricao;
import Entidades.ServicosDescricaoPK;
import java.util.ArrayList;
import java.util.List;

public class DAOServicosDescricao extends DAOGenerico<ServicosDescricao> {

//    package daos;
//import entidades.CardapioHasAlimento;
//public class DAOCardapioHasAlimentoPK extends DAOGenerico<CardapioHasAlimento> {
//    public DAOCardapioHasAlimentoPK() {
//        super(CardapioHasAlimento.class);
//    }
//}
    public DAOServicosDescricao() {
        super(ServicosDescricao.class);
    }

    public int autoIdServicosDescricao() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idServicosDescricao) FROM ServicosDescricao e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public ServicosDescricao obter(ServicosDescricaoPK Chaves) {
        return em.find(ServicosDescricao.class, Chaves);
    }

    public List<ServicosDescricao> listByNome(String dataServico) {
        return em.createQuery("SELECT e FROM ServicosDescricao e WHERE e.dataServico LIKE :dataServico").setParameter("dataServico", "%" + dataServico + "%").getResultList();
    }

    public List<ServicosDescricao> listById(int id) {
        return em.createQuery("SELECT e FROM ServicosDescricao e WHERE e.idServicosDescricao = :id").setParameter("id", id).getResultList();
    }

    public List<ServicosDescricao> listInOrderNome() {
        return em.createQuery("SELECT e FROM ServicosDescricao e ORDER BY e.dataServico").getResultList();
    }

    public List<ServicosDescricao> listInOrderId() {
        return em.createQuery("SELECT e FROM ServicosDescricao e ORDER BY e.idServicosDescricao").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<ServicosDescricao> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getServicosDescricaoPK().getContratoIdContrato()
                    + "-" + lf.get(i).getServicosDescricaoPK().getServicosIdServicos()
                    + "-" + lf.get(i).getServicos()
                    + "-" + lf.get(i).getContrato()
                    + "-" + lf.get(i).getValorCobrado()
                    + "-" + lf.get(i).getDataServico()
            );
        }
        return ls;
    }
}
