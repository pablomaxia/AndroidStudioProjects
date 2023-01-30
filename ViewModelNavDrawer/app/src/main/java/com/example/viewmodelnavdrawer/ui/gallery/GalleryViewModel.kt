package com.example.viewmodelnavdrawer.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    // TODO: Implement the ViewModel
    var mText: MutableLiveData<String>?
    var mNombres: MutableLiveData<ArrayList<String>>?

    init {
        this.mText = MutableLiveData<String>("Este es el fragmento main")
        this.mNombres = MutableLiveData(ArrayList<String>())
    }

    fun addNombre(nombre: String) {
        val nombres: ArrayList<String>? = mNombres?.value
        nombres?.add(nombre)
    }
}