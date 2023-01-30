package com.example.pablomaxia_examen_room.modelo.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pablomaxia_examen_room.modelo.Constantes

@Entity(tableName = Constantes.NOMBRE_TABLA_GRUPO)
data class Grupo(
    @PrimaryKey
    @ColumnInfo(name = "id_grupo")
    val id: String, val nombre: String, val aula: String
)
