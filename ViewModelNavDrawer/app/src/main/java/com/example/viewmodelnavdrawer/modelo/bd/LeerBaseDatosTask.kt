package com.example.viewmodelnavdrawer.modelo.bd

import android.os.AsyncTask
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import com.example.viewmodelnavdrawer.modelo.data.Alumno

class LeerBaseDatosTask :
    AsyncTask<Void?, Void?, List<Alumno>>() {
    override fun doInBackground(vararg params: Void?): List<Alumno>? {
        return AppBD.getAppDB(ApplicationProvider.getApplicationContext())!!.daoAlumno()
            .ver()
    }

    override fun onPostExecute(alumnos: List<Alumno>) {
        showProfessor(alumnos)
    }

    private fun showProfessor(alumnos: List<Alumno>) {
        for (alumno in alumnos) {
            showProfessorUnit(alumno)
        }
    }

    private fun showProfessorUnit(alumno: Alumno) {
        Log.d(
            "TAG",
            "ID Alumno: ${alumno.id}, Nombre: ${alumno.nombre}, Apellido: ${alumno.apellido}\n"
        );
    }
}
