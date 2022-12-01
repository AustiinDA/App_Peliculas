package com.iessanalberto.dam2.proyecto_tfg

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat
import com.iessanalberto.dam2.proyecto_tfg.interfaces.TheMovieDBService
import com.iessanalberto.dam2.proyecto_tfg.modelos.Genre
import com.iessanalberto.dam2.proyecto_tfg.modelos.GetMovieById
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tituloTextView)

        val resumeTextView = findViewById<TextView>(R.id.resumeTextView)
        val yearTextView = findViewById<TextView>(R.id.yearTextView)
        val headerImg = findViewById<AppCompatImageView>(R.id.headerImg)
        //Moshi se encarga de la serialización de los arhcivos JSON

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        /*Creamos la instancia de nuestra interfaz usando retrofit para las llamadas de red,
        usamos metodos asíncronos onResponse y onFailure */

        val tmdbService: TheMovieDBService = retrofit.create(TheMovieDBService::class.java)

        tmdbService.getMovieById(564).enqueue(object : Callback<GetMovieById> {

            override fun onResponse(call: Call<GetMovieById>, response: Response<GetMovieById>) {
                Log.i("MainActivity", response.toString())

                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Llamada de red fallida", Toast.LENGTH_SHORT)
                        .show()
                    return
                }

                //Comprobamos si la  respuesta es nula y asignamos propiedades a la view
                val body = response.body()!!
                textView.text = body.title
                resumeTextView.text = body.overview
                yearTextView.text = body.release_date


                Picasso.get().load(body.poster_path).into(headerImg)
            }

            override fun onFailure(call: Call<GetMovieById>, t: Throwable) {
                Log.i("MainActivity", t.message ?: "Null message")
            }

        })


    }


}
