package br.com.unitunes.entity;

import br.com.unitunes.entity.CategoriaMidia;
import br.com.unitunes.entity.MidiaAutor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-09T20:42:52")
@StaticMetamodel(Midia.class)
public class Midia_ { 

    public static volatile SingularAttribute<Midia, Long> codMidia;
    public static volatile SingularAttribute<Midia, String> tipoMidia;
    public static volatile SingularAttribute<Midia, byte[]> imagem;
    public static volatile SingularAttribute<Midia, CategoriaMidia> codCategoria;
    public static volatile SingularAttribute<Midia, Double> duracao;
    public static volatile ListAttribute<Midia, MidiaAutor> codAutor;
    public static volatile SingularAttribute<Midia, Double> valorMidia;
    public static volatile SingularAttribute<Midia, byte[]> conteudoMidia;
    public static volatile SingularAttribute<Midia, Date> dataCriacao;
    public static volatile SingularAttribute<Midia, String> nomeMidia;
    public static volatile SingularAttribute<Midia, String> descricao;

}