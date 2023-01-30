package com.example.pablomaxia_examen_room.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pablomaxia_examen_room.modelo.Constantes

@Entity(
    tableName = Constantes.NOMBRE_TABLA_PROFESOR,
    foreignKeys = [
        ForeignKey(
            entity = Departamento::class,
            parentColumns = ["id_departamento"],
            childColumns = ["id_departamento_fk"]
        )
    ]
)
data class Profesor(
    @PrimaryKey(autoGenerate = true)
    val id: Int, val nombre: String,
    @ColumnInfo(name = "id_departamento_fk")
    val idDepartamento: String

)
