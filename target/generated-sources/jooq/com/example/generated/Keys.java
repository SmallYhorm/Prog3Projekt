/*
 * This file is generated by jOOQ.
 */
package com.example.generated;


import com.example.generated.tables.Example;
import com.example.generated.tables.records.ExampleRecord;

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

    public static final UniqueKey<ExampleRecord> EXAMPLE__PK_EXAMPLE = Internal.createUniqueKey(Example.EXAMPLE, DSL.name("pk_example"), new TableField[] { Example.EXAMPLE.ID }, true);
}
