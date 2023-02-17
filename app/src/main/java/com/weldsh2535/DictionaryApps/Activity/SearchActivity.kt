package com.weldsh2535.DictionaryApps.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.weldsh2535.DictionaryApps.Adapter.SearchAdapter
import com.weldsh2535.DictionaryApps.Model.Dictionary
import com.weldsh2535.DictionaryApps.R
import com.weldsh2535.DictionaryApps.db.DbHelper
import kotlinx.android.synthetic.main.search_activity.*

class SearchActivity : AppCompatActivity() {
    lateinit var handle: Handler
    var query = ""
    lateinit var database: DbHelper
    lateinit var adapter: SearchAdapter
    var offset: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        backs.setOnClickListener {
            finish()
        }

        bookmark.setOnClickListener {
            var inntent = Intent(this, BookMarksActivity::class.java)
            startActivity(inntent)
        }
        //  setupRecyclerView()
        //  initialise(view)

        database = DbHelper.getDatabase()
        //database.createDictionary(createSearchList())
        adapter = SearchAdapter(query, database.getAllDictionarys(), this)
        recycler_view.adapter = adapter
        recycler_view.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                offset = dx
            }
        })

        handle = Handler(Looper.getMainLooper())
        //  binding.tvSearchTextView.text = "Search Textview"
        search_edit_text.doOnTextChanged { text, start, before, count ->
            val query = text.toString().lowercase()
            adapter?.query = text.toString()
            handle?.postDelayed({
                query?.let {
                    this@SearchActivity.query = it.toString()
                    adapter?.query = this@SearchActivity.query
                    adapter?.submitItems(database.getDictionaryByQuery(it.trim()))
                }
            }, 1000)
            toggleImageView(query)
        }

        clear_search_query.setOnClickListener {
            search_edit_text.setText("")
        }
    }

    private fun toggleImageView(query: String) {
        if (query.trim().isNotEmpty()) {
            clear_search_query.visibility = View.VISIBLE
            // iv_volume.visibility = View.INVISIBLE
        } else if (query.trim().isEmpty()) {
            clear_search_query.visibility = View.INVISIBLE
            // iv_volume.visibility = View.VISIBLE
        }
    }

    private fun createSearchList(): ArrayList<Dictionary> {
        return arrayListOf<Dictionary>(
            Dictionary(1, "ብዕል", "ሀብት", 0),
            Dictionary(2, "ሀበ", "ሀብ ሀብ አለ", 0),
            Dictionary(3, "ዕስራ", "ሀያ", 0),
            Dictionary(4, "ሌሂ", "ሀገር", 0),
            Dictionary(5, "ክሌኤቱ", "ሁለት", 0),
            Dictionary(6, "ሰነየ", "ሁለት አደረገ", 0),
            Dictionary(7, "ኩሉ", "ሁሉ", 0),
            Dictionary(8, "ኩለሄ", "ሁልጊዜ", 0),
            Dictionary(9, "ከዋኔ", "ሁኔታ", 0),
            Dictionary(10, "ሀዋኪ", "ሁከት ወይም ሽብር", 0),
            Dictionary(11, "ሖረ ፤ ተማልዐ ፤ ኀለፈ", "ሄደ", 0),
            Dictionary(12, "አጥራቂ", "ህመም", 0),
            Dictionary(13, "አወሰበ", "ህግ ሰራ", 0),
            Dictionary(14, "መፀ", "ሆመጠጠ", 0),
            Dictionary(15, "ኮነ", "ሆነ", 0),
            Dictionary(16, "ከብድ ፣ ከርስ", "ሆድ", 0),
            Dictionary(17, "ጠለየ ፣ ለግለገ ፣ አቈጸለ ፣ ለምለመ ፣ ሥዕረ", "ለመለመ", 0),
            Dictionary(18, "ሰአለ ፣ ትንበለ ፣ ተማሀለለ ፣ ጸለየ", "ለመነ", 0),
            Dictionary(19, "ለመደ", "ለመደ", 0),
            Dictionary(20, "ለምአ", "ለማ", 0),
            Dictionary(21, "ሕንብርብሬ", "ለምድ", 0),
            Dictionary(22, "ሎቱ", "ለርሱ", 0),
            Dictionary(23, "ለሰለሰ ፣ ተአርዘ ፣ ጽሕደ", "ለሰለሰ", 0),
            Dictionary(24, "ገየረ ፣ መለገ", "ለሰነ", 0),
            Dictionary(25, "ኅቤሁ", "ለሱ", 0),
            Dictionary(26, "ሐመለ ፣ ሐጠጠ ፣ ቀሰመ ፣ ቀረመ ፣ ዐረረ", "ለቀመ", 0),
            Dictionary(27,"ሐጠጠ ፣ አጠበ", "ለቀመ የእንጨት", 0),
            Dictionary(28,"ሞጥሐ ፣ ለብሰ ፣ ተዐረዘ ፣ ነዘፈ", "ለበሰ", 0),
            Dictionary(29,"ሐሰለ", "ለበበ", 0),
            Dictionary(30,"ቀፈለ ፣ ወዘረ", "ለበጠ", 0),
            Dictionary(31,"አንበዘ", "ለብ አጣ",0),
            Dictionary(32,"ለከ", "ለአንተ",0),
            Dictionary(33,"አቀመ ፣ ሰፈረ ፣ ከፈረ ፣ መጠነ ፣ ዐመተ", "ለካ",0),
            Dictionary(34,"ከፈረ", "ለካ የስፍር",0),
            Dictionary(35,"ሎሰ ፣ ጸንቀቀ", "ለወሰ",0),
            Dictionary(36,"ሎጠ ፣ ወለጠ ፣ ", "ለወጠ",0),
            Dictionary(37,"ጽሕደ", "ለዘበ",0),
            Dictionary(38,"ለጎመ", "ለጎመ",0),
            Dictionary(39,"ዘመመ", "ለጓመ",0),
            Dictionary(40,"ቀስተወ", "ለጠጠ",0),
            Dictionary(41,"ጎዕተየ", "ለፋ",0),
            Dictionary(42,"ብጎዕ", "ሊጥ",0),
            Dictionary(43,"ልሕመ", "ላመ",0)
        )
    }

}