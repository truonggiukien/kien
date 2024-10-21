package com.entitybeans;

import com.entitybeans.Orders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(Events.class)
public class Events_ { 

    public static volatile SingularAttribute<Events, Integer> eventID;
    public static volatile SingularAttribute<Events, Date> beginDate;
    public static volatile SingularAttribute<Events, Integer> quantity;
    public static volatile SingularAttribute<Events, Date> endDate;
    public static volatile SingularAttribute<Events, String> eventName;
    public static volatile SingularAttribute<Events, String> discount;
    public static volatile CollectionAttribute<Events, Orders> ordersCollection;

}