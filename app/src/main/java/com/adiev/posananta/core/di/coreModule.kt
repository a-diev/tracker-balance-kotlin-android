package com.adiev.posananta.core.di

import android.content.Context
import androidx.room.Room
import com.adiev.posananta.core.data.CoreRepositoryImpl
import com.adiev.posananta.core.data.RoomSpendingDataSource
import com.adiev.posananta.core.data.local.SpendingDatabase
import com.adiev.posananta.core.domain.CoreRepository
import com.adiev.posananta.core.domain.LocalSpendingDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            SpendingDatabase::class.java,
            "spending_database_db"
        ).build()
    }

    single { get<SpendingDatabase>().dao }

    single {
        androidApplication().getSharedPreferences(
            "spending_tracker_preferences", Context.MODE_PRIVATE
        )
    }

    singleOf(::RoomSpendingDataSource).bind<LocalSpendingDataSource>()
    singleOf(::CoreRepositoryImpl).bind<CoreRepository>()
}