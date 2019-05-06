package com.vibuy.legacy.bigburger.base.injection.module

import android.app.Application
import android.content.Context
import com.vibuy.legacy.bigburger.BuildConfig
import com.vibuy.legacy.bigburger.UiThread
import com.vibuy.legacy.bigburger.base.injection.scopes.PerApplication
import com.vibuy.legacy.cache.BBCacheImp
import com.vibuy.legacy.cache.BBSharedPreference
import com.vibuy.legacy.cache.database.db.BigBurgerDb
import com.vibuy.legacy.cache.mapper.ShoppingChartMapper
import com.vibuy.legacy.data.DataRepository
import com.vibuy.legacy.data.executor.JobExecutor
import com.vibuy.legacy.data.mapper.CartMapper
import com.vibuy.legacy.data.mapper.CatalogMapper
import com.vibuy.legacy.data.repository.Cache
import com.vibuy.legacy.data.repository.Remote
import com.vibuy.legacy.data.source.DataStoreFactory
import com.vibuy.legacy.domain.executor.PostExecutionThread
import com.vibuy.legacy.domain.executor.ThreadExecutor
import com.vibuy.legacy.domain.repository.BigBurgerRepository
import com.vibuy.legacy.remote.BigBurgerRemoteImp
import com.vibuy.legacy.remote.BigBurgerService
import com.vibuy.legacy.remote.ServiceFactory
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
                                       catalogMapper: com.vibuy.legacy.cache.mapper.CatalogMapper,
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