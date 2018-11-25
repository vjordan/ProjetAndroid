package fr.android.projetAndroidVincentJordan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        // Plant logger cf. Android Timber
        Timber.plant(Timber.DebugTree())


        // TODO build Retrofit
        val retrofit = Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        // TODO create a service
        val api = retrofit.create(HenriPotierService.Api::class.java)

        // TODO listBooks()
        api.listBooks()

        // TODO enqueue call and display book title
        api.listBooks().enqueue(object : Callback<List<Book>> {
            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<List<Book>>,
                                    response: Response<List<Book>>) {
                // TODO log books
                response.body()?.forEach { Timber.i(it.toString()) }
            }
        })

        // TODO display book as a list
    }
}