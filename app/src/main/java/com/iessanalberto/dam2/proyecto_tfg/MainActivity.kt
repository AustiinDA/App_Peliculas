package com.iessanalberto.dam2.proyecto_tfg

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.iessanalberto.dam2.proyecto_tfg.recursos.Constantes
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tituloTextView)

        val resumeTextView = findViewById<TextView>(R.id.resumeTextView)
        val yearTextView = findViewById<TextView>(R.id.yearTextView)
        val headerImg = findViewById<AppCompatImageView>(R.id.headerImg)
        val generoTV = findViewById<TextView>(R.id.genreDescTextView)
        val directorTV = findViewById<TextView>(R.id.directorDescTextView)
        val duracionTV = findViewById<TextView>(R.id.duracionTextView)

        viewModel.actualizarPelicula(564)
        // Observando el objeto con los cambios y una estructura para manejar posible errores
        viewModel.movieByIdLiveData.observe(this) { respuesta ->
            if (respuesta == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }


            //Comprobamos si la  respuesta es nula y asignamos propiedades a la view
            //Textos
            textView.text = respuesta.title
            resumeTextView.text = respuesta.overview
            yearTextView.text = respuesta.release_date
            duracionTV.text = getString(R.string.formato_tiempo, respuesta.runtime)

            val generos = respuesta.genres.joinToString(
                separator = ", ",
                transform = { genre -> genre.name }
            )

            generoTV.text = generos


            //Poster
            val poster: String = Constantes.POSTER_PATH
            val fullPoster: String = poster + respuesta.poster_path
            Picasso.get().load(fullPoster).into(headerImg)

        }

/*        //Creditos
        viewModel.actualizarCreditosPelicula(564)
        viewModel.movieCreditsByIdLiveData.observe(this) { respuesta ->
            if (respuesta == null) {
                Toast.makeText(
                    this@MainActivity,
                    "Llamada de red fallida",
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }

            val director = respuesta.crew.filter { item ->
                item.job == "Director"
            }

            directorTV.text = director.joinToString(
                transform = { item -> item.name }
            )
        }*/


//        tmdbService.getMovieCreditsById(
//            564,
//            Constantes.API_KEY,
//            Constantes.LANGUAGE
//        ).enqueue(object : Callback<GetMovieCreditsById> {
//            override fun onResponse(
//                call: Call<GetMovieCreditsById>,
//                response: Response<GetMovieCreditsById>
//            ) {
//                Log.i("MainActivity", response.toString())
//
//                if (!response.isSuccessful) {
//                    Toast.makeText(this@MainActivity, "Llamada de red fallida", Toast.LENGTH_SHORT)
//                        .show()
//                    return
//                }
//
//                val body = response.body()!!
//
//                if (body.crew.isNotEmpty()) {
//
//                    val director = body.crew.filter { item ->
//                        item.job == "Director"
//                    }
//
//                    directorTV.text = director.joinToString(
//                        transform = { item -> item.name }
//                    )
//
//                } else {
//                    generoTV.visibility = View.GONE
//                }
//            }
//
//            override fun onFailure(call: Call<GetMovieCreditsById>, t: Throwable) {
//                Log.i("MainActivity", t.message ?: "Null message")
//            }
//        })

        //   }


    }


}

