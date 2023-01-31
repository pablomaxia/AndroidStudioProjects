package com.example.viewmodelnavdrawer.modelo.bd

import android.os.AsyncTask
import androidx.test.core.app.ApplicationProvider
import com.example.viewmodelnavdrawer.modelo.data.Alumno


class EscribirBaseDatosTask :
    AsyncTask<Alumno?, Void?, Void?>() {
    override fun doInBackground(vararg params: Alumno?): Void? {
        AppBD.getAppDB(ApplicationProvider.getApplicationContext())!!.daoAlumno().crear(
            Alumno(id = 0, nombre = "Prueba", apellido = "Prueba2")
        )
        return null
    }
}