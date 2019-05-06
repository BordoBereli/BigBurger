package com.vibuy.legacy.domain.interactor

import com.vibuy.legacy.domain.executor.PostExecutionThread
import com.vibuy.legacy.domain.executor.ThreadExecutor
import com.vibuy.legacy.domain.interactor.baseUseCases.SingleUseCase
import com.vibuy.legacy.domain.model.CartItem
import com.vibuy.legacy.domain.repository.BigBurgerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-05
 *
 */
open class AddProductUseCase @Inject constructor(
    private val repository: BigBurgerRepository,
    threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : SingleUseCase<Boolean, CartItem>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: CartItem?): Single<Boolean> {
        return if(params != null) {
            repository.addProduct(params)
        } else {
            Single.just(false)
        }


    }
}