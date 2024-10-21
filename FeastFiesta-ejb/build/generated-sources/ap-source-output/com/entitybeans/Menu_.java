package com.entitybeans;

import com.entitybeans.MenuDishChild;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile SingularAttribute<Menu, Integer> menuID;
    public static volatile SingularAttribute<Menu, String> description;
    public static volatile SingularAttribute<Menu, String> menuName;
    public static volatile CollectionAttribute<Menu, MenuDishChild> menuDishChildCollection;
    public static volatile SingularAttribute<Menu, String> picture;

}