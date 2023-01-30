package com.example.pablomaxia_examen_room.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pablomaxia_examen_room.modelo.Constantes

@Entity(tableName = Constantes.NOMBRE_TABLA_DEPARTAMENTO)
data class Departamento(
    @PrimaryKey
    @ColumnInfo(name = "id_departamento")
    val id: String,
    val nombre: String,
)
