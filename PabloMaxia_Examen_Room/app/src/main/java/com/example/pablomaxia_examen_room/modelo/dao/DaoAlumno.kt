package com.example.pablomaxia_examen_room.modelo.dao

import androidx.room.*
import com.example.pablomaxia_examen_room.modelo.entidades.Alumno

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

    // APARTADO 3
    @Query(
        "SELECT alumno.id_alumno, alumno.nombre, alumno.apellido, alumno.id_grupo_fk FROM alumno, grupo " +
                "WHERE alumno.id_grupo_fk = grupo.id_grupo " +
                "AND grupo.nombre = :grupoNombre"
    )
    fun verPorNombreGrupo(grupoNombre: String): List<Alumno>
}