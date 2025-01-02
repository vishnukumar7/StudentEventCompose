package com.app.studentevent.page

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application : Application) : AndroidViewModel(application) {
    private val _advertisePos = MutableLiveData<Int>()
    val advertisePo : LiveData<Int> = _advertisePos
    var initialPos = 0

    fun nextPos(){
        initialPos = initialPos.plus(1)
        _advertisePos.value=initialPos
    }


}