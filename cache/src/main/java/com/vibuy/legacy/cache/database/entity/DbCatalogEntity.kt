package com.vibuy.legacy.cache.database.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.vibuy.legacy.cache.database.db.constants.DbConstants
import kotlinx.android.parcel.Parcelize


/**
 * Created by F.K. on 2019-05-02
 *
 */

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = DbConstants.TABLE_NAME_CATALOG,
        primaryKeys = ["ref", "title"])

data class DbCatalogEntity (
        @ColumnInfo(name = "ref")val ref: String,
        @ColumnInfo(name = "title")val title: String,
        @ColumnInfo(name = "description")val description: String,
        @ColumnInfo(name = "thumbnail")val thumbnail: String,
        @ColumnInfo(name = "price")val price: Int
) : Parcelable