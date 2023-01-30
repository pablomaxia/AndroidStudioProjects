package com.example.pablomaxia_examen_room.modelo.dao

import androidx.room.*
import com.example.pablomaxia_examen_room.modelo.entidades.Asignatura
import com.example.pablomaxia_examen_room.modelo.entidades.Grupo

@Dao
interface DaoAsignatura {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crear(asignatura: Asignatura)

    @Query("SELECT * from asignatura")
    fun ver(): List<Asignatura>

    @Update
    fun editar(asignatura: Asignatura)

    @Delete
    fun borrar(asignatura: Asignatura)

    @Query(
        "SELECT * FROM asignatura " +
                "WHERE asignatura.nombre = :nombre"
    )
    fun verPorNombre(nombre: String): Asignatura
}