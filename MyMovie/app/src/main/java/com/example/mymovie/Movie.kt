package com.example.mymovie

    data class Movie(val id: Int,
                     val name: String,
                     val image: String,
                     val director: String,
                     val actors: Array<String>,
                     val genre: String)
