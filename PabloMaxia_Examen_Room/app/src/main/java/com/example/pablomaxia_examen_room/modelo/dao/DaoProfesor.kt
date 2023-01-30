package com.example.pablomaxia_examen_room.modelo.dao

import androidx.room.*
import com.example.pablomaxia_examen_room.modelo.entidades.Profesor

@Dao
interface DaoProfesor {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crear(profesor: Profesor)

    @Query("SELECT * from profesor")
    fun ver(): List<Profesor>

    @Update
    fun editar(profesor: Profesor)

    @Delete
    fun borrar(profesor: Profesor)

    // APARTADO 5
    @Query(
        "SELECT profesor.id, profesor.nombre, profesor.id_departamento_fk FROM profesor, departamento " +
                "WHERE profesor.id_departamento_fk = departamento.id_departamento " +
                "AND departamento.nombre = :dptoNombre"
    )
    fun verPorNombreDpto(dptoNombre: String): List<Profesor>
}