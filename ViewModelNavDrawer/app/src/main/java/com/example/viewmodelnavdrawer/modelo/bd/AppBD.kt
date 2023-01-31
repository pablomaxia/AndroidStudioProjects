package com.example.viewmodelnavdrawer.modelo.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.viewmodelnavdrawer.modelo.Constantes
import com.example.viewmodelnavdrawer.modelo.dao.DaoAlumno
import com.example.viewmodelnavdrawer.modelo.data.Alumno
import kotlin.coroutines.CoroutineContext

@Database(
    entities = [
        Alumno::class,
    ],
    version = 1
)
abstract class AppBD : RoomDatabase() {
    abstract fun daoAlumno(): DaoAlumno

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
                    //.addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        // Migración para añadir departamentos
        /*private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS departamento (id_departamento VARCHAR NOT NULL PRIMARY KEY, nombre VARCHAR)"
                )
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS profesor (id_profesor INT NOT NULL PRIMARY KEY, nombre VARCHAR, id_departamento_fk VARCHAR, FOREIGN KEY (id_departamento_fk) REFERENCES departamento(id_departamento))"
                )
            }
        }*/
    }
}