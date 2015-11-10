package com.gemtastic.model.entities.database;

import com.gemtastic.model.entities.database.Webshop;
import com.gemtastic.model.entities.database.Website;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-10T16:53:32")
@StaticMetamodel(Language.class)
public class Language_ { 

    public static volatile SingularAttribute<Language, String> language;
    public static volatile ListAttribute<Language, Webshop> webshopList;
    public static volatile SingularAttribute<Language, Integer> id;
    public static volatile ListAttribute<Language, Website> websiteList;

}