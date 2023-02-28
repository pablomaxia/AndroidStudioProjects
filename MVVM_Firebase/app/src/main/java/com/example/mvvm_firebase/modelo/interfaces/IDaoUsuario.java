package com.example.mvvm_firebase.modelo.interfaces;

import com.example.mvvm_firebase.modelo.entidades.Usuario;

import java.util.List;

public interface IDaoUsuario {
    void crear(Usuario usuario, String nombreDocumento, String nombreColeccion);
    Usuario verUno(String nombreDocumento, String nombreColeccion);
    List<Usuario> ver(String nombreDocumento, String nombreColeccion);
    void editar(Usuario usuario, String nombreDocumento, String nombreColeccion);
    void borrarUno(String nombreDocumento, String nombreColeccion);
}
