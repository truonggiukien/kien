package com.entitybeans;

import com.entitybeans.DishIngChild;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(Ingredients.class)
public class Ingredients_ { 

    public static volatile SingularAttribute<Ingredients, Integer> ingredientID;
    public static volatile SingularAttribute<Ingredients, String> unit;
    public static volatile SingularAttribute<Ingredients, BigDecimal> quantity;
    public static volatile SingularAttribute<Ingredients, String> name;
    public static volatile CollectionAttribute<Ingredients, DishIngChild> dishIngChildCollection;

}