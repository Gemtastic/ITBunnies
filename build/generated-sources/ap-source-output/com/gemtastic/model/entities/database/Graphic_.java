package com.gemtastic.model.entities.database;

import com.gemtastic.model.entities.database.Graphicstype;
import com.gemtastic.model.entities.database.Quote;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-10T16:53:32")
@StaticMetamodel(Graphic.class)
public class Graphic_ { 

    public static volatile SingularAttribute<Graphic, Quote> quote;
    public static volatile SingularAttribute<Graphic, Integer> size;
    public static volatile SingularAttribute<Graphic, Integer> price;
    public static volatile SingularAttribute<Graphic, Integer> timeest;
    public static volatile SingularAttribute<Graphic, String> format;
    public static volatile SingularAttribute<Graphic, Integer> id;
    public static volatile SingularAttribute<Graphic, Graphicstype> type;

}