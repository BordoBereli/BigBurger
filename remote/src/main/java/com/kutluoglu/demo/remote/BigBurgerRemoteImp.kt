package com.kutluoglu.demo.remote

import com.kutluoglu.demo.data.model.Catalog
import com.kutluoglu.demo.data.repository.Remote
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */
open class BigBurgerRemoteImp @Inject constructor(
    private val bigBurgerService: BigBurgerService
) : Remote {
    override fun getCatalogs(): Single<List<Catalog>> {
        return bigBurgerService.getCatalogs()
    }
}