package ua.com.kotlin.simplegifviewer

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class FullScreenGifActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_gif)

        val gifImageView = findViewById<ImageView>(R.id.fullScreenGifImageView)

        val gifUrl = intent.getStringExtra("gif_url")

        if (!gifUrl.isNullOrEmpty()) {
            Glide.with(this)
                .asGif()
                .load(gifUrl)
                .into(gifImageView)
        }
    }
}
