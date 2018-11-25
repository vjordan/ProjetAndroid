package fr.android.projetAndroidVincentJordan.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.android.projetAndroidVincentJordan.Book
import fr.android.projetAndroidVincentJordan.R


class MyBookViewHolder(private val context: Context, private val books: List<Book>, private val onItemClick: (book: Book) -> Unit) :
        RecyclerView.Adapter<MyBookViewHolder.MyViewHolder>() {

    class MyViewHolder(val rowView: View) : RecyclerView.ViewHolder(rowView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyBookViewHolder.MyViewHolder {
        val rowView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_book, parent, false) as View

        return MyViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val titleView = holder.rowView.findViewById<TextView>(R.id.list_bookTitle)
        val coverView = holder.rowView.findViewById<ImageView>(R.id.list_bookCover)

        titleView.text = books[position].title
        Picasso.get().load(books[position].cover).into(coverView);

        holder.rowView.setOnClickListener {
            Log.i("click", "clickOnBook")
            onItemClick(books[position])
        }
        if (context.resources.configuration.orientation == 1){
            titleView.setEms(10)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = books.size

}