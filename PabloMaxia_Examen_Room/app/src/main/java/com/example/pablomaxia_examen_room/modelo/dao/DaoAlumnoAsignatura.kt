package com.example.pablomaxia_examen_room.modelo.dao

import androidx.room.*
import com.example.pablomaxia_examen_room.modelo.entidades.Alumno
import com.example.pablomaxia_examen_room.modelo.entidades.AlumnoAsignatura
import com.example.pablomaxia_examen_room.modelo.entidades.Asignatura

@Dao
interface DaoAlumnoAsignatura {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun crear(alumno_asignatura: AlumnoAsignatura)

    @Query("SELECT * from alumno_asignatura")
    fun ver(): List<AlumnoAsignatura>

    @Update
    fun editar(alumno_asignatura: AlumnoAsignatura)

    @Delete
    fun borrar(alumno_asignatura: AlumnoAsignatura)

    // APARTADO 4
    @Query(
        "SELECT alumno.id_alumno, alumno.nombre, alumno.apellido, alumno.id_grupo_fk FROM alumno, asignatura " +
                "INNER JOIN alumno_asignatura ON alumno.id_alumno = alumno_asignatura.id_alumno_fk " +
                "WHERE asignatura.id_asignatura = alumno_asignatura.id_asignatura_fk " +
                "AND asignatura.nombre = :asignaturaNombre"
    )
    fun verPorNombreAsignatura(asignaturaNombre: String): List<Alumno>

    @Query(
        "SELECT asignatura.id_asignatura, asignatura.nombre FROM asignatura, alumno " +
                "INNER JOIN alumno_asignatura ON asignatura.id_asignatura = alumno_asignatura.id_asignatura_fk " +
                "WHERE alumno.id_alumno = :id"
    )
    fun verAsignaturaPorIdAlumno(id: Int): Asignatura
}