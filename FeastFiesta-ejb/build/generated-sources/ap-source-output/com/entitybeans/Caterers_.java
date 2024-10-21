package com.entitybeans;

import com.entitybeans.Orders;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(Caterers.class)
public class Caterers_ { 

    public static volatile SingularAttribute<Caterers, Integer> catererId;
    public static volatile SingularAttribute<Caterers, String> address;
    public static volatile SingularAttribute<Caterers, Date> dateOfJoin;
    public static volatile SingularAttribute<Caterers, String> phone;
    public static volatile SingularAttribute<Caterers, String> name;
    public static volatile CollectionAttribute<Caterers, Orders> ordersCollection;

}