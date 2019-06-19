package com.kutluoglu.demo.cache.database.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.kutluoglu.demo.cache.database.db.constants.DbConstants
import kotlinx.android.parcel.Parcelize


/**
 * Created by F.K. on 2019-05-02
 *
 */

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = DbConstants.TABLE_NAME_SHOPPING_CHART,
        primaryKeys = ["ref", "title"])

data class DbShoppingCartEntity (
        @ColumnInfo(name = "ref")val ref: String,
        @ColumnInfo(name = "title")val title: String,
        @ColumnInfo(name = "description")val description: String,
        @ColumnInfo(name = "thumbnail")val thumbnail: String,
        @ColumnInfo(name = "price")val price: Float,
        @ColumnInfo(name = "quantity")var quantity: Int
) : Parcelable