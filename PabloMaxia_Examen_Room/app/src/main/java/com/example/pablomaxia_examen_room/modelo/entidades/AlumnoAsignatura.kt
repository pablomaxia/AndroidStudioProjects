package com.example.pablomaxia_examen_room.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.pablomaxia_examen_room.modelo.Constantes

@Entity(
    tableName = Constantes.NOMBRE_TABLA_ALUMNO_ASIGNATURA,
    primaryKeys = ["id_alumno_fk", "id_asignatura_fk"],
    foreignKeys = [
        ForeignKey(
            entity = Alumno::class,
            parentColumns = ["id_alumno"],
            childColumns = ["id_alumno_fk"]
        ),
        ForeignKey(
            entity = Asignatura::class,
            parentColumns = ["id_asignatura"],
            childColumns = ["id_asignatura_fk"]
        )
    ]
)
data class AlumnoAsignatura(
    @ColumnInfo(name = "id_alumno_fk", index = true) val alumnoId: Int,
    @ColumnInfo(name = "id_asignatura_fk", index = true) val asignaturaId: Int
)
