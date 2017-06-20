package Entidades;

import Entidades.Pagamento;
import Entidades.Pessoa;
import Entidades.Servicos;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-20T09:05:07")
@StaticMetamodel(Contrato.class)
public class Contrato_ { 

    public static volatile SingularAttribute<Contrato, Servicos> servicosIdServicos;
    public static volatile SingularAttribute<Contrato, Pagamento> pagamentoIdPagamento;
    public static volatile SingularAttribute<Contrato, Integer> idContrato;
    public static volatile SingularAttribute<Contrato, String> servicosDescricao;
    public static volatile SingularAttribute<Contrato, Pessoa> pessoaIdPessoas;

}