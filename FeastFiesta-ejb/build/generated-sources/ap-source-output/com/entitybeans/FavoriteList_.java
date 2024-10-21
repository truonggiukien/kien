package com.entitybeans;

import com.entitybeans.Customers;
import com.entitybeans.Dishes;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(FavoriteList.class)
public class FavoriteList_ { 

    public static volatile SingularAttribute<FavoriteList, Integer> favoriteID;
    public static volatile SingularAttribute<FavoriteList, Dishes> dishID;
    public static volatile SingularAttribute<FavoriteList, Customers> customerID;

}