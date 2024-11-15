package com.adiev.posananta.balance.di

import com.adiev.posananta.balance.persentation.BalanceViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val balanceModule = module {
    viewModel { BalanceViewModel(get()) }
}