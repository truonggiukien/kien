package com.entitybeans;

import com.entitybeans.Dishes;
import com.entitybeans.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(OrderDishChild.class)
public class OrderDishChild_ { 

    public static volatile SingularAttribute<OrderDishChild, Integer> quantity;
    public static volatile SingularAttribute<OrderDishChild, Orders> orderNo;
    public static volatile SingularAttribute<OrderDishChild, Dishes> dishID;
    public static volatile SingularAttribute<OrderDishChild, Integer> orderDishID;

}