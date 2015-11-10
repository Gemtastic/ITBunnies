package com.gemtastic.model.entities.database;

import com.gemtastic.model.entities.database.Language;
import com.gemtastic.model.entities.database.Quote;
import com.gemtastic.model.entities.database.Websitetype;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-10T10:08:39")
@StaticMetamodel(Website.class)
public class Website_ { 

    public static volatile SingularAttribute<Website, Integer> pages;
    public static volatile SingularAttribute<Website, Quote> quote;
    public static volatile SingularAttribute<Website, Integer> price;
    public static volatile SingularAttribute<Website, Integer> timeest;
    public static volatile SingularAttribute<Website, Language> language;
    public static volatile SingularAttribute<Website, Integer> id;
    public static volatile SingularAttribute<Website, Websitetype> type;

}