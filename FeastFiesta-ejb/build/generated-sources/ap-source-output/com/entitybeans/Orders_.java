package com.entitybeans;

import com.entitybeans.Caterers;
import com.entitybeans.Customers;
import com.entitybeans.Events;
import com.entitybeans.OrderDishChild;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Caterers> catererID;
    public static volatile SingularAttribute<Orders, Events> eventID;
    public static volatile SingularAttribute<Orders, Integer> orderNo;
    public static volatile SingularAttribute<Orders, String> notes;
    public static volatile CollectionAttribute<Orders, OrderDishChild> orderDishChildCollection;
    public static volatile SingularAttribute<Orders, BigDecimal> totalPrice;
    public static volatile SingularAttribute<Orders, String> deliveryAddress;
    public static volatile SingularAttribute<Orders, String> name;
    public static volatile SingularAttribute<Orders, Customers> customerID;
    public static volatile SingularAttribute<Orders, Date> deliveryDate;
    public static volatile SingularAttribute<Orders, Date> orderDate;
    public static volatile SingularAttribute<Orders, String> status;

}