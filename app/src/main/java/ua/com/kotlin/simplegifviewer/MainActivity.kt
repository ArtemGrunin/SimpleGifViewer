import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.com.kotlin.simplegifviewer.R
import ua.com.kotlin.simplegifviewer.adapter.GifAdapter
import ua.com.kotlin.simplegifviewer.model.GifViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GifAdapter
    private lateinit var searchEditText: EditText
    private val viewModel: GifViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        searchEditText = findViewById(R.id.searchEditText)

        adapter = GifAdapter(this, mutableListOf())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val query = searchEditText.text.toString()
                viewModel.searchGifs(query)
                true
            } else {
                false
            }
        }
    }
}
