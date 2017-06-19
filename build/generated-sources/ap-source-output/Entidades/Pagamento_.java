package Entidades;

import Entidades.Contrato;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-12T17:13:38")
@StaticMetamodel(Pagamento.class)
public class Pagamento_ { 

    public static volatile SingularAttribute<Pagamento, Integer> idPagamento;
    public static volatile ListAttribute<Pagamento, Contrato> contratoList;
    public static volatile SingularAttribute<Pagamento, String> nome;
    public static volatile SingularAttribute<Pagamento, String> descricao;

}