package com.example.pablomaxia_examen_room

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.pablomaxia_examen_room.modelo.bd.AppBD
import com.example.pablomaxia_examen_room.modelo.entidades.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = AppBD.getAppDB(context = applicationContext)

        // Instanciar los daos
        val daoAlumno = app!!.daoAlumno()
        val daoGrupo = app.daoGrupo()
        val daoAsignatura = app.daoAsignatura()
        val daoProfesor = app.daoProfesor()
        val daoAlumnoAsignatura = app.daoAlumnoAsignatura()
        val daoDepartamento = app.daoDepartamento()

        // DEPARTAMENTOS
        val departamento1 = Departamento(id = "1", nombre = "Informática")
        val departamento2 = Departamento(id = "2", nombre = "Biología")

        val listaDepar = listOf(departamento1, departamento2)
        // Profesores
        val prof1 = Profesor(id = 0, nombre = "Antonio", idDepartamento = "1")
        val prof2 = Profesor(id = 0, nombre = "Enrique", idDepartamento = "2")

        val listaProf = listOf(prof1, prof2)

        // Grupos
        val grupo1 = Grupo(id = "1", nombre = "2ºDAM", aula = "G")
        val grupo2 = Grupo(id = "2", nombre = "2ºDAW", aula = "H")

        val listaGrupo = listOf(grupo1, grupo2)

        // Asignaturas
        val asig1 = Asignatura(id = 0, nombre = "Acceso a Datos")
        val asig2 = Asignatura(id = 0, nombre = "Programación de Móviles")

        val listaAsig = listOf(asig1, asig2)

        // Alumnos
        val alu1 = Alumno(id = 0, nombre = "Pepe", apellido = "Pérez", "1")
        val alu2 = Alumno(id = 0, nombre = "María", apellido = "López", "2")
        val alu3 = Alumno(id = 0, nombre = "Paco", apellido = "Gómez", "1")

        val listaAlu = listOf(alu1, alu2, alu3)

        // Alumno_Asignatura
        val aluAsig1 = AlumnoAsignatura(alumnoId = 1, asignaturaId = 1)
        val aluAsig2 = AlumnoAsignatura(alumnoId = 1, asignaturaId = 2)
        val aluAsig3 = AlumnoAsignatura(alumnoId = 2, asignaturaId = 1)
        val aluAsig4 = AlumnoAsignatura(alumnoId = 3, asignaturaId = 2)

        val listaAluAsig = listOf(aluAsig1, aluAsig2, aluAsig3, aluAsig4)


        // Inicio del hilo secundario
        Thread {
            app.clearAllTables()
            /* INSERTAR DATOS EN TABLAS */
            // INSERTAR DEPARTAMENTOS
            for (depar in listaDepar) {
                daoDepartamento.crear(depar)
            }

            for (prof in listaProf) {
                daoProfesor.crear(prof)
            }

            for (grupo in listaGrupo) {
                daoGrupo.crear(grupo)
            }

            for (asig in listaAsig) {
                daoAsignatura.crear(asig)
            }

            for (alu in listaAlu) {
                daoAlumno.crear(alu)
            }

            for (aluAsig in listaAluAsig) {
                daoAlumnoAsignatura.crear(aluAsig)
            }

            /* FIN DE INSERTAR DATOS EN TABLAS */
            // EJERCICIO 3
            val listaAluGrupo = daoAlumno.verPorNombreGrupo("2ºDAM")
            for (alu in listaAluGrupo) {
                val grupo = daoGrupo.verPorId(alu.grupoId)
                Log.d(
                    ":::ALU_GRUPO",
                    "Nombre: ${alu.nombre}, Apellido:${alu.apellido}, Nombre_Grupo:${grupo.nombre}, Aula_Grupo:${grupo.aula}"
                )
            }
            // EJERCICIO 4
            val listaAlumnosAsignatura = daoAlumnoAsignatura.verPorNombreAsignatura(
                "Acceso a Datos"
            )
            for (alu in listaAlumnosAsignatura) {
                val grupo = daoGrupo.verPorId(alu.grupoId)
                val asignatura = daoAlumnoAsignatura.verAsignaturaPorIdAlumno(alu.id)
                Log.d(
                    ":::ALU_ASIG",
                    "Nombre: ${alu.nombre}, Apellido: ${alu.apellido}," +
                            " Nombre_Grupo: ${grupo.nombre}, Aula_Grupo: ${grupo.aula}," +
                            " Asignatura: ${asignatura.nombre}"
                )
            }

            // EJERCICIO 5
            val listaProfesoresDepartamento = daoProfesor.verPorNombreDpto("Informática")
            for (prof in listaProfesoresDepartamento) {
                val departamento = daoDepartamento.verPorId(prof.idDepartamento)
                Log.d(
                    ":::PROBAR_MIGRACION",
                    "Nombre: ${prof.nombre}, Departamento: ${departamento.nombre}"
                )
            }
        }.start()
    }

}