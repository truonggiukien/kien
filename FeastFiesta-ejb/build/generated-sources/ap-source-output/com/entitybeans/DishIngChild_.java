package com.entitybeans;

import com.entitybeans.Dishes;
import com.entitybeans.Ingredients;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2024-10-10T16:51:35")
@StaticMetamodel(DishIngChild.class)
public class DishIngChild_ { 

    public static volatile SingularAttribute<DishIngChild, Integer> dishIngID;
    public static volatile SingularAttribute<DishIngChild, Ingredients> ingredientID;
    public static volatile SingularAttribute<DishIngChild, BigDecimal> quantity;
    public static volatile SingularAttribute<DishIngChild, Dishes> dishID;

}