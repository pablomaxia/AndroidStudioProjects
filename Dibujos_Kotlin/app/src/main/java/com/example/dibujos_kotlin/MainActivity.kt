package com.example.dibujos_kotlin

import android.graphics.*
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
           Inicializaremos los elementos esenciales para dibujar:
           1) ImageView, es el objeto que se mostrará en pantalla dibujado.
           2) Bitmap es una matriz de píxeles de ese objeto que es lo que pintaremos.
           3) Canvas es un "lienzo", es la herramienta con la que dibujaremos en ese Bitmap
           4) Paint es la pintura con la que pintaremos.
           ImageView es la vista y por tanto la tenemos que tener identificada en nuestros layouts,
           le tenemos que dar una altura y anchura y un identificador. Para un juego lo normal es que ocupe toda la
           pantalla del móvil, aunque podemos combinarla mediante layouts con otros elementos.
        */

        /*
           Inicializaremos los elementos esenciales para dibujar:
           1) ImageView, es el objeto que se mostrará en pantalla dibujado.
           2) Bitmap es una matriz de píxeles de ese objeto que es lo que pintaremos.
           3) Canvas es un "lienzo", es la herramienta con la que dibujaremos en ese Bitmap
           4) Paint es la pintura con la que pintaremos.
           ImageView es la vista y por tanto la tenemos que tener identificada en nuestros layouts,
           le tenemos que dar una altura y anchura y un identificador. Para un juego lo normal es que ocupe toda la
           pantalla del móvil, aunque podemos combinarla mediante layouts con otros elementos.
        */
        val myImageView = findViewById<View>(R.id.imagen) as ImageView

        val myBitmap = Bitmap.createBitmap(750, 1500, Bitmap.Config.ARGB_8888)
        val myCanvas = Canvas(myBitmap)

        /*
            Ahora podemos dibujar con nuestro canvas en el bitmap que hemos creado y que luego asociaremos a la ImageView
            Para ello usaremos la clase Paint que es la pintura con la que pintaré.

        */
        val paint = Paint()

        //Fondo de pantalla negro
        myCanvas.drawColor(Color.BLACK)

        //dibujo punto (no se verá)
        paint.color = Color.argb(255, 255, 10, 10)
        myCanvas.drawPoint(0f, 0f, paint)

        //dibujamos una linea
        myCanvas.drawLine(0f, 0f, 750f, 1500f, paint)

        //escribimos un texto de tamaño 100 y cambiamos de color
        paint.textSize = 100f
        paint.color = Color.BLUE
        myCanvas.drawText("hola mundo", 10f, 750f, paint)

        //dibujamos un rectángulo
        paint.color = Color.GREEN
        myCanvas.drawRect(500f, 10f, 200f, 200f, paint)

        //dibujamos un círculo
        paint.color = Color.YELLOW
        myCanvas.drawCircle(200f, 200f, 100f, paint)

        //dibujamos un óvalo
        paint.color = Color.MAGENTA
        myCanvas.drawOval(100f, 150f, 150f, 100f, paint)

        //dibujamos un rectángulo con las esquinas redondeadas
        paint.color = Color.CYAN
        myCanvas.drawRoundRect(RectF(0f,100f,100f,200f
        ),6f,6f, paint)

        //finalmente asociamos el canvas a la ImageView
        myImageView.setImageBitmap(myBitmap)


    }
}