package com.example.mvvm_firebase.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_firebase.databinding.FragmentSlideshowBinding;
import com.example.mvvm_firebase.modelo.entidades.Usuario;
import com.example.mvvm_firebase.ui.usuarios.UsuarioFragment;

import java.io.Serializable;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private Button buttonCreate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        buttonCreate = binding.buttonCreate;

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario test = new Usuario(0,"TEST_SLIDESHOW");
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", (Serializable) test);
                UsuarioFragment usuarioFragment = new UsuarioFragment();
                usuarioFragment.setArguments(bundle);
            }
        });
        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}