package com.example.pablomaxia_examen_room.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pablomaxia_examen_room.modelo.Constantes

@Entity(
    tableName = Constantes.NOMBRE_TABLA_ALUMNO,
    foreignKeys = [ForeignKey(
        entity = Grupo::class,
        parentColumns = ["id_grupo"],
        childColumns = ["id_grupo_fk"]
    )]
)
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_alumno")
    val id: Int, val nombre: String, val apellido: String,
    @ColumnInfo(name = "id_grupo_fk")
    val grupoId: String
)
