package com.vibuy.legacy.domain.interactor

import com.vibuy.legacy.domain.executor.PostExecutionThread
import com.vibuy.legacy.domain.executor.ThreadExecutor
import com.vibuy.legacy.domain.interactor.baseUseCases.SingleUseCase
import com.vibuy.legacy.domain.model.CatalogItem
import com.vibuy.legacy.domain.repository.BigBurgerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */
open class GetCatalogsUseCase @Inject constructor(
    private val repository: BigBurgerRepository,
    threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : SingleUseCase<List<CatalogItem>, Void>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: Void?): Single<List<CatalogItem>> {
        return repository.getCatalogs()
    }
}