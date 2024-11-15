package com.adiev.posananta.spending_details.di

import com.adiev.posananta.spending_details.domain.UpsertSpendingUseCase
import com.adiev.posananta.spending_details.persentation.SpendingDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val spendingDetailModule = module {
    single { UpsertSpendingUseCase(get()) }
    viewModel { SpendingDetailViewModel(get()) }
}