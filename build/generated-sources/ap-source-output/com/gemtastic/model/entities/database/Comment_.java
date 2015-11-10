package com.gemtastic.model.entities.database;

import com.gemtastic.model.entities.database.Quote;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-10T10:08:39")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Quote> quote;
    public static volatile SingularAttribute<Comment, Date> created;
    public static volatile SingularAttribute<Comment, String> author;
    public static volatile SingularAttribute<Comment, Integer> id;
    public static volatile SingularAttribute<Comment, String> message;

}