package com.entitybeans;

import com.entitybeans.Dishes;
import com.entitybeans.Menu;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(MenuDishChild.class)
public class MenuDishChild_ { 

    public static volatile SingularAttribute<MenuDishChild, Integer> menuDishID;
    public static volatile SingularAttribute<MenuDishChild, Integer> quantity;
    public static volatile SingularAttribute<MenuDishChild, Dishes> dishID;
    public static volatile SingularAttribute<MenuDishChild, Menu> menuID;

}