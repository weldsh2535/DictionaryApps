package com.weldsh2535.DictionaryApps.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.weldsh2535.DictionaryApps.Adapter.BookMarkAdapter
import com.weldsh2535.DictionaryApps.Model.Dictionary
import com.weldsh2535.DictionaryApps.R
import com.weldsh2535.DictionaryApps.db.DbHelper
import kotlinx.android.synthetic.main.bookmark_activity.*
import kotlinx.android.synthetic.main.item_list.view.*

class BookMarksActivity : AppCompatActivity() {

    lateinit var database:DbHelper
    lateinit var adapter:BookMarkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bookmark_activity)

        database = DbHelper.getDatabase()
        adapter = BookMarkAdapter(database.getAllBookMarkDictionarys(),
            this,
            object :BookMarkAdapter.OnBookMarkDictionary{
                override fun onClick(course: Dictionary, itemView: View) {
                    if (course.is_favourite == 0){
                        itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                        course.is_favourite = 1
                        database.updateDictionary(course)
                        adapter.submitItems(database.getAllBookMarkDictionarys())
                    } else {
                        itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                        course.is_favourite = 0
                        database.updateDictionary(course)
                        adapter.submitItems(database.getAllBookMarkDictionarys())
                    }

                    Log.i("MyTag", "onCreate: ${database.getAllBookMarkDictionarys().size}")
                    if (database.getAllBookMarkDictionarys().size == 0){
                        animationView.visibility = View.VISIBLE
                        placeHolderTxt.visibility = View.VISIBLE
                        rv_bookmark.visibility = View.INVISIBLE
                    }else{
                        animationView.visibility = View.INVISIBLE
                        placeHolderTxt.visibility = View.INVISIBLE
                        rv_bookmark.visibility = View.VISIBLE
                    }
                }
            })

        Log.i("MyTag", "onCreate: ${database.getAllBookMarkDictionarys().size}")

        rv_bookmark.adapter = adapter

        if (database.getAllBookMarkDictionarys().size == 0) {
            animationView.visibility = View.VISIBLE
            placeHolderTxt.visibility = View.VISIBLE
            rv_bookmark.visibility = View.INVISIBLE
        } else {
            animationView.visibility = View.INVISIBLE
            placeHolderTxt.visibility = View.INVISIBLE
            rv_bookmark.visibility = View.VISIBLE

        }
        backbookmarks.setOnClickListener {
            finish()
        }

    }
    override fun onResume() {
        super.onResume()
        if (database.getAllBookMarkDictionarys().size == 0) {
            animationView.visibility = View.VISIBLE
            placeHolderTxt.visibility = View.VISIBLE
            rv_bookmark.visibility = View.INVISIBLE
        } else {
            animationView.visibility = View.INVISIBLE
            placeHolderTxt.visibility = View.INVISIBLE
            rv_bookmark.visibility = View.VISIBLE
        }

    }

}