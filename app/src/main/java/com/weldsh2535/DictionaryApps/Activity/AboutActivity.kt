package com.weldsh2535.DictionaryApps.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.weldsh2535.DictionaryApps.R
import kotlinx.android.synthetic.main.about_activity.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.about_activity)

        toolbar2.setNavigationOnClickListener { onBackPressed() }
        youtubeIcon.setOnClickListener {
            val url = "https://www.youtube.com/channel/UCbVpit08dgJhRm6DknoimcQ"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        facebookIcon.setOnClickListener {
            val url = "https://www.facebook.com/profile.php?id=100061172022235"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        instagramIcon.setOnClickListener {
            val url = "https://www.instagram.com/weldsh22?igshid=ZDdkNTZiNTM/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        telegramIcon.setOnClickListener {
            val url = "https://t.me/weldsh2535"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

}