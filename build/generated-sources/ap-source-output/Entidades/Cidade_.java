package Entidades;

import Entidades.Pessoa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-06-20T09:05:07")
@StaticMetamodel(Cidade.class)
public class Cidade_ { 

    public static volatile SingularAttribute<Cidade, String> uf;
    public static volatile SingularAttribute<Cidade, Integer> idCidade;
    public static volatile SingularAttribute<Cidade, String> nome;
    public static volatile SingularAttribute<Cidade, Date> dataCadastro;
    public static volatile ListAttribute<Cidade, Pessoa> pessoaList;

}