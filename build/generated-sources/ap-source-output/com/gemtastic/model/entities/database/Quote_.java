package com.gemtastic.model.entities.database;

import com.gemtastic.model.entities.database.Comment;
import com.gemtastic.model.entities.database.Graphic;
import com.gemtastic.model.entities.database.Webshop;
import com.gemtastic.model.entities.database.Website;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-10T16:53:32")
@StaticMetamodel(Quote.class)
public class Quote_ { 

    public static volatile ListAttribute<Quote, Comment> commentList;
    public static volatile ListAttribute<Quote, Graphic> graphicList;
    public static volatile SingularAttribute<Quote, Date> querydate;
    public static volatile SingularAttribute<Quote, Integer> quotesum;
    public static volatile ListAttribute<Quote, Webshop> webshopList;
    public static volatile SingularAttribute<Quote, Integer> id;
    public static volatile ListAttribute<Quote, Website> websiteList;

}