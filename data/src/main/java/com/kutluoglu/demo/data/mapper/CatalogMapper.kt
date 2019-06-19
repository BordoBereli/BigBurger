package com.kutluoglu.demo.data.mapper

import com.kutluoglu.demo.data.model.Catalog
import com.kutluoglu.demo.domain.model.CatalogItem
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */
class CatalogMapper @Inject constructor() : Mapper<Catalog, CatalogItem> {
    override fun mapFromEntityToDomainModel(type: Catalog): CatalogItem {
        return CatalogItem(type.ref, type.title, type.description, type.thumbnail, type.price)
    }

    override fun mapToEntityFromDomainModel(type: CatalogItem): Catalog {
        return Catalog(type.ref, type.title, type.description, type.thumbnail, type.price)
    }

}