package DAOs;
import Entidades.Pessoa;
import java.util.ArrayList;
import java.util.List;

public class DAOPessoa extends DAOGenerico<Pessoa> {

    public DAOPessoa() {
        super(Pessoa.class);
    }

    public int autoIdPessoa() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPessoa) FROM Pessoa e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Pessoa> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Pessoa e WHERE e.nome LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Pessoa> listById(int id) {
        return em.createQuery("SELECT e FROM Pessoa e WHERE e.idPessoa = :id").setParameter("id", id).getResultList();
    }

    public List<Pessoa> listInOrderNome() {
        return em.createQuery("SELECT e FROM Pessoa e ORDER BY e.nome").getResultList();
    }

    public List<Pessoa> listInOrderId() {
        return em.createQuery("SELECT e FROM Pessoa e ORDER BY e.idPessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Pessoa> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            System.out.println(lf.get(i).getDataNasc());
            System.out.println("aqui");
            System.out.println(lf.get(i).getDataCadastro());
            ls.add(lf.get(i).getIdPessoas()
 + "-" + lf.get(i).getNome()
 + "-" + lf.get(i).getRg()
 + "-" + lf.get(i).getCpf()
 + "-" + lf.get(i).getTelefone()
 + "-" + lf.get(i).getEmail()
 + "-" + lf.get(i).getLogin()
 + "-" + lf.get(i).getSenha()
 + "-" + lf.get(i).getDataNasc()
 + "-" + lf.get(i).getSexo()
 + "-" + lf.get(i).getDataCadastro()
 + "-" + lf.get(i).getCidadeIdCidade()
 + "-" + lf.get(i).getContratoList()
);
        }
        return ls;
    }
}
