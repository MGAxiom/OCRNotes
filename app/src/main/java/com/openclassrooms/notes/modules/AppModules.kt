package com.openclassrooms.notes.modules

import com.openclassrooms.notes.repository.NotesRepository
import com.openclassrooms.notes.viewmodel.OCRViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NotesRepository() }
    viewModel { OCRViewModel(get()) }
}