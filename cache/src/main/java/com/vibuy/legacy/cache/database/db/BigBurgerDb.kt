package com.vibuy.legacy.cache.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vibuy.legacy.cache.database.dao.CatalogDao
import com.vibuy.legacy.cache.database.dao.ShoppingCartDao
import com.vibuy.legacy.cache.database.db.constants.DbConstants
import com.vibuy.legacy.cache.database.entity.DbCatalogEntity
import com.vibuy.legacy.cache.database.entity.DbShoppingCartEntity
import com.vibuy.legacy.cache.database.utils.DateConverter
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */

@Database(entities = [DbCatalogEntity::class, DbShoppingCartEntity::class],  version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)

abstract class BigBurgerDb @Inject constructor() : RoomDatabase() {

    abstract fun catalogDao() : CatalogDao

    abstract fun shoppingCartDao() : ShoppingCartDao

    companion object {

        @Volatile private var INSTANCE: BigBurgerDb? = null

        fun getInstance(context: Context): BigBurgerDb =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                        ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        BigBurgerDb::class.java, DbConstants.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }
}
