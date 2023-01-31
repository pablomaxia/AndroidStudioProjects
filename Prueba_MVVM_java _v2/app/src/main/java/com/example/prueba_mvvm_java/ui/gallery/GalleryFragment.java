package com.example.prueba_mvvm_java.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.prueba_mvvm_java.databinding.FragmentGalleryBinding;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    private ArrayList<String> nombres=new ArrayList<>();
    GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Así se instancia un viewModel
        galleryViewModel =new ViewModelProvider(this).get(GalleryViewModel.class);


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(this.getViewLifecycleOwner(), textView::setText);



        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                galleryViewModel.addNombre("pepe ");


            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombres=galleryViewModel.getNombres().getValue();
                for(String s:nombres){
                    Log.d("MVVM", s);
                }
            }
        });
        return root;


    }
    @Override
    public void onResume() {
        super.onResume();
        galleryViewModel.getNombres().observe(this.getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                // Aquí puedes actualizar la interfaz de usuario con los nuevos datos
                Log.d("MVVM", ""+ strings.toString());

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}