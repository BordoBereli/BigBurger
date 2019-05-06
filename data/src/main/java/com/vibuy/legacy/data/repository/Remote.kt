package com.vibuy.legacy.data.repository

import com.vibuy.legacy.data.model.Catalog
import io.reactivex.Single

/**
 * Created by F.K. on 2019-05-01
 *
 */
interface Remote {
    /**
     * Retrieve a [List] of [Catalog] from the remote API Service
     */
    fun getCatalogs(): Single<List<Catalog>>
}