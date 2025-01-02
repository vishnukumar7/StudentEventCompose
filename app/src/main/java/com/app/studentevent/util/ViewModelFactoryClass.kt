package com.app.studentevent.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.studentevent.page.MainViewModel

class ViewModelFactoryClass(private var application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(application) as T
            }
            else -> throw IllegalArgumentException("Unknown View Model")
        }
    }
}