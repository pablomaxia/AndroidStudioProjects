package com.example.viewmodelnavdrawer.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelnavdrawer.R
import com.example.viewmodelnavdrawer.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private var galleryViewModel:GalleryViewModel? = null
    private var nombres: ArrayList<String> = ArrayList<String>()
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
                R.id.button -> anadirNombre("pepe")
                R.id.button2 -> verNombres()
            }
        }
        val button = this.binding.button
        val button2 = this.binding.button2

        button.setOnClickListener(buttonListener)
        button2.setOnClickListener(buttonListener)
        return root
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
}