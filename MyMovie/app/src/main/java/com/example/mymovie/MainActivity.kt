package com.example.mymovie

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonElement
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity() {
//  Views Init Section
    private lateinit var infoTextView: TextView
    private lateinit var movieNameView: TextView
    private lateinit var movieDirectorView: TextView
    private lateinit var movieActorsView: TextView
    private lateinit var movieGenreView: TextView
    private lateinit var movieInfoLayout: LinearLayout
//  Variables Section
    private lateinit var moviesCollection: MoviesCollection
    lateinit var randomMovie: Number
    private lateinit var moviesStorage: SharedPreferences
//  Utility Section
    private val gson = Gson() // TODO: learn how to customzie gson implementation
    private val randomFunction = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitViews()
        moviesStorage = this.getSharedPreferences("movies_shown", Context.MODE_PRIVATE)
        initMovies()
    }

    private fun InitViews() {
        infoTextView = findViewById(R.id.infoField)
        movieNameView = findViewById(R.id.filmName)
        movieDirectorView = findViewById(R.id.filmDirector)
        movieActorsView = findViewById(R.id.filmActors)
        movieGenreView = findViewById(R.id.filmGenre)
        movieInfoLayout = findViewById(R.id.filmInfoLayout)
    }

    private fun initMovies() {
        val stream = resources.openRawResource(R.raw.movies)
        moviesCollection = gson.fromJson(InputStreamReader(stream), MoviesCollection::class.java)
        if (moviesStorage.all.keys.size == moviesCollection.movies.size) {
            infoTextView.text = "All Movies were already shown"
        } else {
            infoTextView.text = "Click the 'Next Movie' Button to see the next Movie"
        }
    }

    private fun getRandomMovie(): Movie? {
        if (moviesStorage.all.keys.size == moviesCollection.movies.size) {
            return null
        }
        var movie_id: Int = moviesCollection.movies[randomFunction.nextInt(moviesCollection.movies.size)].id
        val EMPTY_SEARCH = "empty"
        while (true) {
            val search: String? = moviesStorage.getString(movie_id.toString(), EMPTY_SEARCH)
            if (search.equals(EMPTY_SEARCH)) {
                return moviesCollection.movies.find { it.id == movie_id }
            }
            movie_id = moviesCollection.movies[randomFunction.nextInt(moviesCollection.movies.size)].id
        }
    }

    private fun setRandomMovie(rand_movie: Movie?) {
        if (rand_movie != null) {
            infoTextView.visibility = View.GONE
            movieInfoLayout.visibility = View.VISIBLE
            movieNameView.text = rand_movie.name
            movieDirectorView.text = rand_movie.director
            movieActorsView.text = rand_movie.actors.joinToString { it }
            movieGenreView.text = rand_movie.genre

            moviesStorage.edit().putString(rand_movie.id.toString(), rand_movie.name).apply()
        } else {
            infoTextView.text = "All Movie were already shown"
            infoTextView.visibility = View.VISIBLE
            movieInfoLayout.visibility = View.GONE
        }
    }

    fun onReset(view: View) {
        infoTextView.text = "Click the 'Next Movie' Button to see the next Movie"
        infoTextView.visibility = View.VISIBLE
        movieInfoLayout.visibility = View.GONE
        moviesStorage.edit().clear().apply()
    }


    fun onNext(view: View) {
        setRandomMovie(getRandomMovie())
    }
}