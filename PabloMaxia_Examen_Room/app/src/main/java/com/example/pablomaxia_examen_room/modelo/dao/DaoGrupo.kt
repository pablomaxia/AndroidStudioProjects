package com.example.pablomaxia_examen_room.modelo.dao

import androidx.room.*
import com.example.pablomaxia_examen_room.modelo.entidades.Grupo

@Dao
interface DaoGrupo {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crear(grupo: Grupo)

    @Query("SELECT * FROM grupo")
    fun ver(): List<Grupo>

    @Update
    fun editar(grupo: Grupo)

    @Delete
    fun borrar(grupo: Grupo)

    @Query(
        "SELECT * FROM grupo " +
                "WHERE grupo.id_grupo = :id"
    )
    fun verPorId(id: String): Grupo
}