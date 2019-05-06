package com.vibuy.legacy.cache.mapper

import com.vibuy.legacy.cache.database.entity.DbCatalogEntity
import com.vibuy.legacy.data.model.Catalog
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */
class CatalogMapper @Inject constructor() : EntityMapper<Catalog, DbCatalogEntity> {
    override fun mapToCached(type: Catalog): DbCatalogEntity {
        return DbCatalogEntity(type.ref, type.title, type.description, type.thumbnail, type.price)
    }

    override fun mapFromCached(type: DbCatalogEntity): Catalog {
        return Catalog(type.ref, type.title, type.description, type.thumbnail, type.price)
    }
}