package com.adiev.posananta

import android.app.Application
import com.adiev.posananta.balance.di.balanceModule
import com.adiev.posananta.core.di.coreModule
import com.adiev.posananta.spending_details.di.spendingDetailModule
import com.adiev.posananta.spending_overview.di.spendingOverviewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                coreModule,
                balanceModule,
                spendingOverviewModule,
                spendingDetailModule,
            )
        }
    }
}