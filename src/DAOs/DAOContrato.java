package DAOs;
import Entidades.Contrato;
import java.util.ArrayList;
import java.util.List;

public class DAOContrato extends DAOGenerico<Contrato> {

    public DAOContrato() {
        super(Contrato.class);
    }

    public int autoIdContrato() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idContrato) FROM Contrato e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Contrato> listByNome(String contratoDescricao) {
        return em.createQuery("SELECT e FROM Contrato e WHERE e.contratoDescricao LIKE :contratoDescricao").setParameter("contratoDescricao", "%" + contratoDescricao + "%").getResultList();
    }

    public List<Contrato> listById(int id) {
        return em.createQuery("SELECT e FROM Contrato e WHERE e.idContrato = :id").setParameter("id", id).getResultList();
    }

    public List<Contrato> listInOrderNome() {
        return em.createQuery("SELECT e FROM Contrato e ORDER BY e.contratoDescricao").getResultList();
    }

    public List<Contrato> listInOrderId() {
        return em.createQuery("SELECT e FROM Contrato e ORDER BY e.idContrato").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Contrato> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdContrato()
 + "-" + lf.get(i).getContratoDescricao()
 + "-" + lf.get(i).getDataContrato()
 + "-" + lf.get(i).getValorContrato()
 + "-" + lf.get(i).getPessoaIdPessoas()
 + "-" + lf.get(i).getPagamentoList()
 + "-" + lf.get(i).getServicosDescricaoList()
);
        }
        return ls;
    }
}
