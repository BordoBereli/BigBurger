package com.vibuy.legacy.cache.database.dao

import androidx.room.*
import com.vibuy.legacy.cache.database.db.constants.DbConstants
import com.vibuy.legacy.cache.database.entity.DbCatalogEntity
import io.reactivex.Single

/**
 * Created by F.K. on 2019-05-02
 *
 */

@Dao
interface CatalogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCatalogs(list: List<DbCatalogEntity>) : List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCatalogEntity(entity: DbCatalogEntity) : Int

    @Delete
    fun deleteCatalogEntity(entity: DbCatalogEntity) : Int

    @Query(DbConstants.QUERY_GET_CATALOGS)
    fun getCatalogs() : Single<List<DbCatalogEntity>>

    @Query(DbConstants.QUERY_GET_CATALOGS_BY_REF_ID)
    fun getCatalogOf(refId: String) : Single<DbCatalogEntity>

}