package fr.android.projetAndroidVincentJordan

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import fr.android.projetAndroidVincentJordan.fragments.FragmentDetails
import fr.android.projetAndroidVincentJordan.fragments.FragmentListBooks

class LibraryActivity : AppCompatActivity(), FragmentListBooks.Listener, FragmentDetails.ListenerDetails {

    private var currentDetailsFragment: Fragment? = null

    override fun onBookSelected(book: Book) {
        val detailsFragment = FragmentDetails.newInstance(book, resources.configuration.orientation == 2)
        currentDetailsFragment = detailsFragment
        supportFragmentManager.beginTransaction()
                .replace(R.id.list_fragment, detailsFragment, FragmentDetails::class.java.name).addToBackStack("list_fragment")
                .commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        supportFragmentManager.beginTransaction()
                .replace(R.id.list_fragment, FragmentListBooks(), FragmentListBooks::class.java.name)
                .commit()
    }

    override fun onCloseFragment() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
                .replace(R.id.list_fragment, FragmentListBooks(), FragmentListBooks::class.java.name).addToBackStack("list_fragment")
                .commit()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        if(currentDetailsFragment != null) {
            supportFragmentManager.putFragment(outState!!, "savedDetailFragment", currentDetailsFragment!!)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }

}