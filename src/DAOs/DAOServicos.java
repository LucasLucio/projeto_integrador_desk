package DAOs;

import Entidades.Servicos;
import java.util.ArrayList;
import java.util.List;

public class DAOServicos extends DAOGenerico<Servicos> {

    public DAOServicos() {
        super(Servicos.class);
    }

    public int autoIdServicos() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idServicos) FROM Servicos e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Servicos> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Servicos e WHERE e.nome LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Servicos> listById(int id) {
        return em.createQuery("SELECT e FROM Servicos e WHERE e.idServicos = :id").setParameter("id", id).getResultList();
    }

    public List<Servicos> listInOrderNome() {
        return em.createQuery("SELECT e FROM Servicos e ORDER BY e.nome").getResultList();
    }

    public List<Servicos> listInOrderId() {
        return em.createQuery("SELECT e FROM Servicos e ORDER BY e.idServicos").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Servicos> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdServicos()
                    + "-" + lf.get(i).getNome()
                    + "-" + lf.get(i).getDescricao()
                    + "-" + lf.get(i).getValor()
            );
        }
        return ls;
    }
}
