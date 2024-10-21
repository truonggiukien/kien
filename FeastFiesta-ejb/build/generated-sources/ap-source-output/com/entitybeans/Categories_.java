package com.entitybeans;

import com.entitybeans.Dishes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(Categories.class)
public class Categories_ { 

    public static volatile SingularAttribute<Categories, String> name;
    public static volatile CollectionAttribute<Categories, Dishes> dishesCollection;
    public static volatile SingularAttribute<Categories, Integer> categoryID;

}