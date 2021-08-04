package ru.inc.simplecoinmarketcap.di.component

import dagger.Component
import ru.inc.simplecoinmarketcap.di.module.*
import ru.inc.simplecoinmarketcap.view_model.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ApiModule::class,
        DataBaseModule::class,
        CryptoRepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(viewModel: MainViewModel)
}