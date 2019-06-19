package com.kutluoglu.demo.bigburger.base.injection.module

import android.app.Application
import android.content.Context
import com.kutluoglu.demo.bigburger.BuildConfig
import com.kutluoglu.demo.bigburger.UiThread
import com.kutluoglu.demo.bigburger.base.injection.scopes.PerApplication
import com.kutluoglu.demo.cache.BBCacheImp
import com.kutluoglu.demo.cache.BBSharedPreference
import com.kutluoglu.demo.cache.database.db.BigBurgerDb
import com.kutluoglu.demo.cache.mapper.ShoppingChartMapper
import com.kutluoglu.demo.data.DataRepository
import com.kutluoglu.demo.data.executor.JobExecutor
import com.kutluoglu.demo.data.mapper.CartMapper
import com.kutluoglu.demo.data.mapper.CatalogMapper
import com.kutluoglu.demo.data.repository.Cache
import com.kutluoglu.demo.data.repository.Remote
import com.kutluoglu.demo.data.source.DataStoreFactory
import com.kutluoglu.demo.domain.executor.PostExecutionThread
import com.kutluoglu.demo.domain.executor.ThreadExecutor
import com.kutluoglu.demo.domain.repository.BigBurgerRepository
import com.kutluoglu.demo.remote.BigBurgerRemoteImp
import com.kutluoglu.demo.remote.BigBurgerService
import com.kutluoglu.demo.remote.ServiceFactory
import dagger.Module
import dagger.Provides

/**
 * Created by F.K. on 2019-05-02
 *
 */

@Module(includes = [ViewModelModule::class, MainActivityModule::class])
class AppModule {
    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor) : ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideSharedPreferences(context: Context): BBSharedPreference {
        return BBSharedPreference(context)
    }


    @Provides
    @PerApplication
    internal fun provideBigBurgerRepository(factory: DataStoreFactory,
                                            mapper: CatalogMapper, cartMapper: CartMapper
    ) : BigBurgerRepository {
        return DataRepository(factory, mapper, cartMapper)
    }

    @Provides
    @PerApplication
    internal fun provideBigBurgerCache(bigBurgerDb: BigBurgerDb,
                                       bbSharedPreference: BBSharedPreference,
                                       catalogMapper: com.kutluoglu.demo.cache.mapper.CatalogMapper,
                                       shoppingChartMapper: ShoppingChartMapper
    ) : Cache {
        return BBCacheImp(bigBurgerDb, bbSharedPreference, catalogMapper, shoppingChartMapper)
    }

    @Provides
    @PerApplication
    internal fun provideBBService(): BigBurgerService {
        return ServiceFactory.getBBService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideBigBurgerRemote(service: BigBurgerService): Remote {
        return BigBurgerRemoteImp(service)
    }

    @Provides
    @PerApplication
    internal fun provideBigBurgerDatabase(context: Context): BigBurgerDb {
        return BigBurgerDb.getInstance(context)
    }

}