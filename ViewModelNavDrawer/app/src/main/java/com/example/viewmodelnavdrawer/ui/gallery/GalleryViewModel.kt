package com.example.viewmodelnavdrawer.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.viewmodelnavdrawer.modelo.bd.AppBD
import com.example.viewmodelnavdrawer.modelo.data.Alumno
import kotlin.collections.ArrayList

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    // TODO: Implement the ViewModel
    var mText: MutableLiveData<String>?
    var mNombres: MutableLiveData<ArrayList<String>>?
    var alumno: MutableLiveData<Alumno>?
    var mAlumnos: MutableLiveData<ArrayList<Alumno>>?
    var daoAlumno = AppBD.getAppDB(application)!!.daoAlumno()

    init {
        this.mText = MutableLiveData<String>("Este es el fragmento main")
        this.mNombres = MutableLiveData(ArrayList<String>())
        this.alumno = null
        this.mAlumnos = daoAlumno.ver() as MutableLiveData<ArrayList<Alumno>>
    }

    fun addNombre(nombre: String) {
        val nombres: ArrayList<String>? = mNombres?.value
        nombres?.add(nombre)
    }

    fun addAlumno(alumno: Alumno) {
        val alumnos = mAlumnos?.value
        daoAlumno.crear(alumno)
        mAlumnos!!.postValue(alumnos)
    }
}