package com.vibuy.legacy.domain.interactor

import com.vibuy.legacy.domain.executor.PostExecutionThread
import com.vibuy.legacy.domain.executor.ThreadExecutor
import com.vibuy.legacy.domain.interactor.baseUseCases.FlowableUseCase
import com.vibuy.legacy.domain.model.CartItem
import com.vibuy.legacy.domain.repository.BigBurgerRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-05
 *
 */
open class GetCartUseCase @Inject constructor(
    private val repository: BigBurgerRepository,
    threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<CartItem>, Void>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseFlowable(params: Void?): Flowable<List<CartItem>> {
        return repository.getProducts()
    }
}