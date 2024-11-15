package com.adiev.posananta.spending_overview.di

import com.adiev.posananta.spending_overview.persentation.SpendingOverviewViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val spendingOverviewModule = module {
    viewModel { SpendingOverviewViewModel(get(), get()) }
}