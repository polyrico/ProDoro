package com.apps.adrcotfas.goodtime.di

import com.apps.adrcotfas.goodtime.labels.LabelsViewModel
import com.apps.adrcotfas.goodtime.main.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    single { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
    viewModelOf(::MainViewModel)
    viewModelOf(::LabelsViewModel)
}