package com.example.pablomaxia_examen_room.modelo.dao

import androidx.room.*
import com.example.pablomaxia_examen_room.modelo.entidades.Departamento
import com.example.pablomaxia_examen_room.modelo.entidades.Grupo
import com.example.pablomaxia_examen_room.modelo.entidades.Profesor

@Dao
interface DaoDepartamento {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crear(departamento: Departamento)

    @Query("SELECT * from departamento")
    fun ver(): List<Departamento>

    @Update
    fun editar(departamento: Departamento)

    @Delete
    fun borrar(departamento: Departamento)

    @Query(
        "SELECT * FROM departamento " +
                "WHERE departamento.id_departamento = :id"
    )
    fun verPorId(id: String): Departamento
}