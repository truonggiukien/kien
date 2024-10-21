package com.entitybeans;

import com.entitybeans.FavoriteList;
import com.entitybeans.Feedbacks;
import com.entitybeans.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile SingularAttribute<Customers, String> password;
    public static volatile SingularAttribute<Customers, String> address;
    public static volatile SingularAttribute<Customers, String> phone;
    public static volatile CollectionAttribute<Customers, FavoriteList> favoriteListCollection;
    public static volatile SingularAttribute<Customers, Integer> customerID;
    public static volatile SingularAttribute<Customers, String> name;
    public static volatile CollectionAttribute<Customers, Orders> ordersCollection;
    public static volatile CollectionAttribute<Customers, Feedbacks> feedbacksCollection;
    public static volatile SingularAttribute<Customers, String> email;
    public static volatile SingularAttribute<Customers, String> username;

}