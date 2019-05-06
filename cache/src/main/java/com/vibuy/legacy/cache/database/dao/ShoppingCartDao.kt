package com.vibuy.legacy.cache.database.dao

import androidx.room.*
import com.vibuy.legacy.cache.database.db.constants.DbConstants
import com.vibuy.legacy.cache.database.entity.DbShoppingCartEntity
import io.reactivex.Flowable

/**
 * Created by F.K. on 2019-05-02
 *
 */

@Dao
interface ShoppingCartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: DbShoppingCartEntity) : Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateProduct(product: DbShoppingCartEntity) : Int

    @Delete
    fun deleteProduct(product: DbShoppingCartEntity) : Int

    @Query(DbConstants.QUERY_GET_SHOPPING_CHART)
    fun getAllProducts() : Flowable<List<DbShoppingCartEntity>>

}