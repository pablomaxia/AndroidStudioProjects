package com.example.viewmodelnavdrawer.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelnavdrawer.R
import com.example.viewmodelnavdrawer.databinding.FragmentGalleryBinding
import com.example.viewmodelnavdrawer.modelo.data.Alumno

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private var galleryViewModel: GalleryViewModel? = null
    private var nombres: ArrayList<String> = ArrayList<String>()
    private var alumnos: ArrayList<Alumno> = ArrayList<Alumno>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        galleryViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel!!.mText!!.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val buttonListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.button -> anadirAlumno()
                R.id.button2 -> verAlumnos()
            }
        }
        val button = this.binding.button
        val button2 = this.binding.button2

        button.setOnClickListener(buttonListener)
        button2.setOnClickListener(buttonListener)
        return root
    }

    override fun onResume() {
        super.onResume()
        galleryViewModel!!.mAlumnos!!.observe(this.viewLifecycleOwner,
            Observer<java.util.ArrayList<Alumno?>> { alumnos -> // Aquí puedes actualizar la interfaz de usuario con los nuevos datos
                Log.d("MVVM", "" + alumnos.toString())
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun anadirNombre(nombre: String) {
        galleryViewModel!!.addNombre(nombre)
        Log.d(":::MVVM", nombre)
    }

    fun verNombres() {
        nombres = galleryViewModel!!.mNombres?.value!!
        Log.d(":::MVVM", "aaa")
        for (s in nombres) {
            Toast.makeText(this.context, s, Toast.LENGTH_SHORT).show()
            Log.d(":::MVVM", s)
        }
    }

    fun anadirAlumno() {
        val alumno: Alumno = Alumno(id = 0, nombre = "", apellido = "")
        alumno.nombre = "a"
        alumno.apellido = "aa"
        galleryViewModel!!.addAlumno(alumno)
        Log.d(":::MVVM", alumno.toString())
    }

    fun verAlumnos() {
        alumnos = galleryViewModel!!.mAlumnos?.value!!
        Log.d(":::MVVM", "aaa")
        for (s in alumnos) {
            Toast.makeText(this.context, s.nombre + " " + s.apellido, Toast.LENGTH_SHORT).show()
            Log.d(":::MVVM", s.nombre + " " + s.apellido)
        }
    }
}