package com.gemtastic.model.entities.database;

import com.gemtastic.model.entities.database.Language;
import com.gemtastic.model.entities.database.Quote;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-10T16:53:32")
@StaticMetamodel(Webshop.class)
public class Webshop_ { 

    public static volatile SingularAttribute<Webshop, String> database;
    public static volatile SingularAttribute<Webshop, Quote> quote;
    public static volatile SingularAttribute<Webshop, String> paymentsys;
    public static volatile SingularAttribute<Webshop, Integer> price;
    public static volatile SingularAttribute<Webshop, Integer> timeest;
    public static volatile SingularAttribute<Webshop, Language> language;
    public static volatile SingularAttribute<Webshop, Integer> id;
    public static volatile SingularAttribute<Webshop, Boolean> login;

}