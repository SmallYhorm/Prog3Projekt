/*
 * This file is generated by jOOQ.
 */
package com.example.generated;


import com.example.generated.tables.Ingredient;
import com.example.generated.tables.Recipe;
import com.example.generated.tables.records.IngredientRecord;
import com.example.generated.tables.records.RecipeRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<RecipeRecord> RECIPE__PK_RECIPE = Internal.createUniqueKey(Recipe.RECIPE, DSL.name("pk_Recipe"), new TableField[] { Recipe.RECIPE.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<IngredientRecord, RecipeRecord> INGREDIENT__FK_INGREDIENT_PK_RECIPE = Internal.createForeignKey(Ingredient.INGREDIENT, DSL.name("fk_Ingredient_pk_Recipe"), new TableField[] { Ingredient.INGREDIENT.RECIPE_ID }, Keys.RECIPE__PK_RECIPE, new TableField[] { Recipe.RECIPE.ID }, true);
}
