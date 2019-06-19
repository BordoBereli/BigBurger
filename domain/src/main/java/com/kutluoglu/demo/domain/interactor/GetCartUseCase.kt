package com.kutluoglu.demo.domain.interactor

import com.kutluoglu.demo.domain.executor.PostExecutionThread
import com.kutluoglu.demo.domain.executor.ThreadExecutor
import com.kutluoglu.demo.domain.interactor.baseUseCases.FlowableUseCase
import com.kutluoglu.demo.domain.model.CartItem
import com.kutluoglu.demo.domain.repository.BigBurgerRepository
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