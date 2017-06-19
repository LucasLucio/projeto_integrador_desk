package Entidades;

import Entidades.Agenda;
import Entidades.Contrato;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-12T17:13:38")
@StaticMetamodel(Servicos.class)
public class Servicos_ { 

    public static volatile SingularAttribute<Servicos, Integer> idServicos;
    public static volatile SingularAttribute<Servicos, Double> valor;
    public static volatile ListAttribute<Servicos, Contrato> contratoList;
    public static volatile SingularAttribute<Servicos, String> nome;
    public static volatile ListAttribute<Servicos, Agenda> agendaList;
    public static volatile SingularAttribute<Servicos, String> descricao;

}