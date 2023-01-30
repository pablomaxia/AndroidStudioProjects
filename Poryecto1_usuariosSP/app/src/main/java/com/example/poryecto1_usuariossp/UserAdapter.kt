package com.example.poryecto1_usuariossp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poryecto1_usuariossp.databinding.ItemUserBinding

//UserAdapter recoje un listado de User y hereda de un Adapter, pero estará
//personalizado con una plantilla referida a la clase interna de ViewHolder en UserAdaper
//Se le pasa al constructor una referencia al Interfaz que servirá de oyente de
//eventos para comunicarnos con el Activity
class UserAdapter(
        private val users:List<User>,
        private val listener:OnClickItemListener
    ):RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    //lateinit hace que el compilador espere a que más tarde la variable sea inicializada
    //ya que nunca puede ser null.
    //tb se podría haber hecho context??=null pero no es apropiado ya que nunca será null
    private lateinit var context:Context

    //Se crea la vista con item_user. Primero inflamos el item_user y creamos un viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context=parent.context
        val view=LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);

        return ViewHolder(view)
    }

    //Este método funciona igual que un for_each
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val user= users[position]
        with(holder){
            setListener(user)
            binding.tvName.text=user.name.toString()
            binding.tvOrder.text=user.id.toString()
        }
    }

    override fun getItemCount(): Int =users.size

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        val binding=ItemUserBinding.bind(view)

        fun setListener(user:User){
                binding.root.setOnClickListener{
                    listener.onClicK(user)
                }
        }

    }



}