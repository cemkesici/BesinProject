package com.cem.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cem.model.Besin

class BesinDetayiViewModel: ViewModel() {
    val besinLiveData=MutableLiveData<Besin>()

    fun roomVerisiniAl(){
        val muz=Besin("Muz","100","50","10","2","www.test.com")
        besinLiveData.value=muz
    }
}