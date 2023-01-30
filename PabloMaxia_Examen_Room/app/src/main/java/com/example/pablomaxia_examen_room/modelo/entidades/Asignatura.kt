package com.example.pablomaxia_examen_room.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pablomaxia_examen_room.modelo.Constantes

@Entity(tableName = Constantes.NOMBRE_TABLA_ASIGNATURA)
data class Asignatura(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_asignatura")
    val id: Int, val nombre: String
)
