package ua.com.kotlin.simplegifviewer

import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.com.kotlin.simplegifviewer.adapter.GifAdapter
import ua.com.kotlin.simplegifviewer.model.GifData
import ua.com.kotlin.simplegifviewer.model.GifViewModel
import ua.com.kotlin.simplegifviewer.model.GifViewModelFactory
import ua.com.kotlin.simplegifviewer.repository.GifRepository

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GifAdapter
    private lateinit var searchEditText: EditText

    private val itemsPerPage = 30
    private var gifDataList = mutableListOf<GifData>()

    private val viewModel: GifViewModel by lazy {
        val repository = GifRepository()
        val factory = GifViewModelFactory(repository, applicationContext)
        ViewModelProvider(this, factory)[GifViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)

        adapter = GifAdapter(this, mutableListOf())
        recyclerView.adapter = adapter

        val layoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager

        // Отримуємо розмір елементу з ресурсів (якщо потрібно)
        val itemWidth = getDimensionPixelSize(R.dimen.item_gif_width)
        val screenWidth = resources.displayMetrics.widthPixels
        val spanCount = screenWidth / itemWidth
        layoutManager.spanCount = spanCount.toInt()

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                gifDataList.clear()
                val query = searchEditText.text.toString()
                viewModel.searchGifs(query, offset = 0, limit = itemsPerPage)
                searchEditText.text.clear()
                hideKeyboard() // Додайте цей виклик, щоб ховати клавіатуру
                true
            } else {
                false
            }
        }

        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                if (recyclerView.childCount > 0) {
                    val itemWidth = getDimensionPixelSize(R.dimen.item_gif_width)
                    val screenWidth = recyclerView.width
                    val spanCount = screenWidth / itemWidth
                    layoutManager.spanCount = spanCount.toInt()
                }
            }
        })

        viewModel.gifs.observe(this) { gifDataList ->
            adapter.updateData(gifDataList)
        }
    }

    private fun getDimensionPixelSize(id: Int): Int {
        return resources.getDimensionPixelSize(id)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
