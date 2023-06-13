package com.cem.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cem.model.Besin

class BesinListesiViewModel:ViewModel() {
    val besinler=MutableLiveData<List<Besin>>()
    val besinHataMsg=MutableLiveData<Boolean>()
    val besinYukleniyor= MutableLiveData<Boolean>()

    fun refreshData(){
        val muz=Besin("Muz","100","50","10","2","www.test.com")
        val cilek=Besin("cilek","100","50","10","2","www.test.com")
        val elma=Besin("elma","100","50","10","2","www.test.com")

        val besinList= arrayListOf<Besin>(muz,cilek,elma)
        besinler.value=besinList
        besinHataMsg.value=false
        besinYukleniyor.value=false
    }
}