package co.za.fat.googlebooks_api.ui

//import co.za.fat.googlebooks_api.R
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.za.fat.googlebooks_api.R
import co.za.fat.googlebooks_api.data.Book
import com.squareup.picasso.Picasso


class BooksAdapter(private val context: Context) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
    private var bookList = emptyList<Book>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val tvTitle =
            itemView.findViewById<TextView>(R.id.tv_title)
        private val tvPageCount =
            itemView.findViewById<TextView>(R.id.tv_page_count)
        private val tvPublishedDate =
            itemView.findViewById<TextView>(R.id.tv_published_date)
        private val ivBookThumbnail =
            itemView.findViewById<ImageView>(R.id.iv_book_thumbnail)
        private val tvPublisher =
            itemView.findViewById<TextView>(R.id.tv_publisher)
        private val expandTextView =
            itemView.findViewById<Button>(R.id.ib_expand)
        private val tvExpandable =
            itemView.findViewById<TextView>(R.id.tv_expandable)

        fun bind(book: Book) {
            tvTitle.text = book.title
            tvPublishedDate.text = book.publishedDate
            tvExpandable.text = book.description
            tvPageCount.text = "No of pages: " + book.pageCount.toString()
            if (book.publisher != "") {
                tvPublisher.text = book.publisher
            }


            val uri = book.imageLinks //.replace("\"", "")

            val builder = Picasso.Builder(context)
            builder.listener { _, _, exception -> exception.printStackTrace() }


            builder.build()
                .load(uri)
                .error(R.drawable.ic_launcher_background)
                .resize(600, 600)
                .into(ivBookThumbnail)

            expandTextView.setOnClickListener {
                when (expandTextView.text) {
                    "show more" -> {
                        expandTextView.text = "show less"
                        tvExpandable.maxLines = 50
                    }
                    "show less" -> {
                        expandTextView.text = "show more"
                        tvExpandable.maxLines = 3
                    }

                }

            }
        }

        override fun onClick(p0: View?) {
            val intent = Intent(context, BookDetailsActivity::class.java)
            context.startActivity(intent)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount() = bookList.size

    fun setData(book: List<Book>, keyWord: String, viewModel: BooksViewModel) {
        viewModel.isLoading
        this.bookList = book
            .filter {
                it.keyWords.contains(keyWord)
            }
        notifyDataSetChanged()
    }
}
//class BooksAdapter(private val onItemClicked: (Book) -> Unit):ListAdapter<Book, BooksAdapter.BookViewHolder>(DiffCallback) {
//    companion object {
//        private val DiffCallback = object : DiffUtil.ItemCallback<Book>() {
//            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//    class BookViewHolder(private var binding: BookItemBinding): RecyclerView.ViewHolder(binding.root
//    ) {
//        fun bind(book: Book) {
//            binding.tvTitle.text = book.title
//            binding.tvPublishedDate.text= book.publishedDate
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
//        val viewHolder = BookViewHolder(
//            BookItemBinding.inflate(
//                LayoutInflater.from( parent.context),
//                parent,
//                false
//            )
//        )
//        viewHolder.itemView.setOnClickListener {
//            val position = viewHolder.adapterPosition
//            onItemClicked(getItem(position))
//        }
//        return viewHolder
//    }
//
//    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//}