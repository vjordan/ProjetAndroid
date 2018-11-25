package fr.android.projetAndroidVincentJordan

import retrofit2.Call
import retrofit2.http.GET

interface HenriPotierService {

    // TODO Method GET books which return a List<Book>
    interface Api {
        @GET("books")
        fun listBooks(): Call<List<Book>>
    }
}
