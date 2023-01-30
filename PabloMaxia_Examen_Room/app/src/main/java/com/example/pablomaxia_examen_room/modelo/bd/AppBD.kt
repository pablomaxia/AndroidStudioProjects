package com.example.pablomaxia_examen_room.modelo.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.pablomaxia_examen_room.modelo.Constantes
import com.example.pablomaxia_examen_room.modelo.dao.*
import com.example.pablomaxia_examen_room.modelo.entidades.*

@Database(
    entities = [
        Alumno::class,
        Asignatura::class,
        Grupo::class,
        Profesor::class,
        AlumnoAsignatura::class,
        Departamento::class
    ],
    version = 2
)
abstract class AppBD : RoomDatabase() {
    abstract fun daoAlumno(): DaoAlumno
    abstract fun daoGrupo(): DaoGrupo
    abstract fun daoAsignatura(): DaoAsignatura
    abstract fun daoProfesor(): DaoProfesor
    abstract fun daoAlumnoAsignatura(): DaoAlumnoAsignatura
    abstract fun daoDepartamento(): DaoDepartamento

    companion object {
        @Volatile
        private var INSTANCE: AppBD? = null

        fun getAppDB(context: Context): AppBD? {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppBD::class.java,
                    Constantes.NOMBRE_BASE_DATOS
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        // Migración para añadir departamentos
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS departamento (id_departamento VARCHAR NOT NULL PRIMARY KEY, nombre VARCHAR)"
                )
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS profesor (id_profesor INT NOT NULL PRIMARY KEY, nombre VARCHAR, id_departamento_fk VARCHAR, FOREIGN KEY (id_departamento_fk) REFERENCES departamento(id_departamento))"
                )
            }
        }
    }
}