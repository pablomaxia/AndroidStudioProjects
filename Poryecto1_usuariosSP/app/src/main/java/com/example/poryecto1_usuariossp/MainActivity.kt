package com.example.poryecto1_usuariossp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poryecto1_usuariossp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickItemListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }

        //Uso de Shared Preferences para almacenar datos persistentes en la aplicacion
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val texto = preferences.getString("valor_1", " ")
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
        preferences.edit().putString("valor_1", "hola").commit()

    }

    private fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()

        users.add(User(1, "Pepe", ""))
        users.add(User(2, "Luísa", ""))
        users.add(User(3, "David", ""))
        users.add(User(4, "Pepe", ""))
        users.add(User(5, "Luísa", ""))
        users.add(User(6, "David", ""))
        return users

    }

    override fun onClicK(user: User) {
        Toast.makeText(this, "Pulsado ${user.name}", Toast.LENGTH_SHORT).show()
    }

}