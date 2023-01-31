package com.example.viewmodelnavdrawer.modelo.dao

import androidx.room.*
import com.example.viewmodelnavdrawer.modelo.data.Alumno

@Dao
interface DaoAlumno {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crear(alumno: Alumno)

    @Query("SELECT * from alumno")
    fun ver(): List<Alumno>

    @Update
    fun editar(alumno: Alumno)

    @Delete
    fun borrar(alumno: Alumno)

}