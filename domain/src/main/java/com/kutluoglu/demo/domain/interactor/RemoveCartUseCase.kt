package com.kutluoglu.demo.domain.interactor

import com.kutluoglu.demo.domain.executor.PostExecutionThread
import com.kutluoglu.demo.domain.executor.ThreadExecutor
import com.kutluoglu.demo.domain.interactor.baseUseCases.SingleUseCase
import com.kutluoglu.demo.domain.model.CartItem
import com.kutluoglu.demo.domain.repository.BigBurgerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-06
 *
 */
open class RemoveCartUseCase @Inject constructor(
    private val repository: BigBurgerRepository,
    threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean, CartItem>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: CartItem?): Single<Boolean> {
        return if (params != null) {
            repository.removeProduct(params)
        } else {
            Single.just(false)}
    }
}