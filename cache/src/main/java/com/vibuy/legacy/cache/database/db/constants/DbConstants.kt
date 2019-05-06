package com.vibuy.legacy.cache.database.db.constants

/**
 * Created by F.K. on 2019-05-02.
 */
object DbConstants {
    const val DATABASE_NAME = "BigBurger.db"
    /**
     * CatalogItem Table Info
     */
    const val TABLE_NAME_CATALOG = "CatalogList"
    const val QUERY_GET_CATALOGS = "SELECT * FROM $TABLE_NAME_CATALOG"
    const val QUERY_GET_CATALOGS_BY_REF_ID = "SELECT * FROM $TABLE_NAME_CATALOG WHERE ref = :refId"

    /**
     * Shopping Chart Table Info
     */
    const val TABLE_NAME_SHOPPING_CHART = "ShoppingChart"
    const val QUERY_GET_SHOPPING_CHART = "SELECT * FROM $TABLE_NAME_SHOPPING_CHART ORDER BY price DESC"
}