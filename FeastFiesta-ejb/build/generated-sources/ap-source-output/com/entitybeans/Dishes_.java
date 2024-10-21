package com.entitybeans;

import com.entitybeans.Categories;
import com.entitybeans.DishIngChild;
import com.entitybeans.FavoriteList;
import com.entitybeans.MenuDishChild;
import com.entitybeans.OrderDishChild;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(Dishes.class)
public class Dishes_ { 

    public static volatile SingularAttribute<Dishes, String> itemName;
    public static volatile CollectionAttribute<Dishes, OrderDishChild> orderDishChildCollection;
    public static volatile SingularAttribute<Dishes, BigDecimal> price;
    public static volatile CollectionAttribute<Dishes, FavoriteList> favoriteListCollection;
    public static volatile SingularAttribute<Dishes, Integer> dishID;
    public static volatile CollectionAttribute<Dishes, MenuDishChild> menuDishChildCollection;
    public static volatile CollectionAttribute<Dishes, DishIngChild> dishIngChildCollection;
    public static volatile SingularAttribute<Dishes, String> picture;
    public static volatile SingularAttribute<Dishes, Categories> categoryID;

}