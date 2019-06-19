package com.kutluoglu.demo.remote

import com.kutluoglu.demo.data.model.Catalog
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by F.K. on 2019-05-02
 *
 */
interface BigBurgerService {
    /**
     * Get catalogs
     */
    @GET("mobiletest1.json")
    fun getCatalogs() : Single<List<Catalog>>
}