package com.example.viewmodelnavdrawer.modelo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.viewmodelnavdrawer.modelo.Constantes

@Entity(
    tableName = Constantes.NOMBRE_TABLA_ALUMNO,
)
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_alumno")
    var id: Int,
    var nombre: String, var apellido: String
)