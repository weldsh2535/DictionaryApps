package com.weldsh2535.DictionaryApps.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.weldsh2535.DictionaryApps.Model.Dictionary
import com.weldsh2535.DictionaryApps.R
import com.weldsh2535.DictionaryApps.Utils.TextToSpeech
import com.weldsh2535.DictionaryApps.db.DbHelper
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import kotlinx.android.synthetic.main.item_list.view.*

class SearchAdapter(
    var query: String,
    var arrayList: ArrayList<Dictionary>,
    var context: Activity
) :
    RecyclerView.Adapter<SearchAdapter.Wh>() {
    private var changeRememberStatusListener: ((Int, Int) -> Unit)? = null
    lateinit var bottomSheetDialog: BottomSheetDialog;

    inner class Wh(var view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun onBind(dictionary: Dictionary, position: Int) {

            var database = DbHelper.getDatabase()
            itemView.setOnClickListener {
                var view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet, null, false)
                if (this@SearchAdapter::bottomSheetDialog.isInitialized) {
                    bottomSheetDialog.dismiss();
                }

                bottomSheetDialog = BottomSheetDialog(context)
                bottomSheetDialog.setContentView(view)

                view.geez.text = dictionary.geez
                view.amharic.text = dictionary.amharic

                view.iv_back.setOnClickListener {
                    bottomSheetDialog?.dismiss()
                }
                view.iv_volume.setOnClickListener {
                    textToSpeech(dictionary.amharic)
                }
                view.iv_share.setOnClickListener {
                    val sharingIntent = Intent(Intent.ACTION_SEND)

                    // type of the content to be shared
                    sharingIntent.type = "text/plain"

                    // Body of the content
                    val shareBody = dictionary.toString()

                    // subject of the content. you can share anything
                    val shareSubject = dictionary.toString()

                    // passing body of the content
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)

                    // passing subject of the content
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
                    context.startActivity(Intent.createChooser(sharingIntent, "Share using"))
                }

                view.iv_bookmark.setOnClickListener {
                    if (dictionary.is_favourite == 0) {
                        view.iv_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                        dictionary.is_favourite = 1
                        database.updateDictionary(dictionary)
                        submitItems(database.getDictionaryByQuery(query))
                        changeRememberStatusListener?.invoke(dictionary.id, 1)

                    } else {
                        view.iv_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                        dictionary.is_favourite = 0
                        changeRememberStatusListener?.invoke(dictionary.id, 0)

                        database.updateDictionary(dictionary)
                        submitItems(database.getDictionaryByQuery(query))
                    }
                }
                if (dictionary.is_favourite == 1) {
                    view.iv_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                } else {
                    view.iv_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                }
                /*view.type.text = dictionary.type
                view.transcription.text = dictionary.transcript
                view.countable.text = dictionary.countable*/
                bottomSheetDialog.show()
            }

            itemView.tvGeez.text = dictionary.geez
            itemView.tvAmharic.text = dictionary.amharic
/*           if (query == "")
               itemView.amharic.text = dictionary.amharic
            else
                itemView.amharic.text = dictionary.amharic.spannable(query, itemView.context)*/

            itemView.bookmark.setOnClickListener {
                if (dictionary.is_favourite == 0) {
                    itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                    dictionary.is_favourite = 1
                    database.updateDictionary(dictionary)
                    submitItems(database.getDictionaryByQuery(query))
                    changeRememberStatusListener?.invoke(dictionary.id, 1)
                } else {
                    itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                    dictionary.is_favourite = 0
                    changeRememberStatusListener?.invoke(dictionary.id, 0)
                    database.updateDictionary(dictionary)
                    submitItems(database.getDictionaryByQuery(query))
                }
            }
            if (dictionary.is_favourite == 1) {
                itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
            } else {
                itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Wh {
        return Wh(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: Wh, position: Int) {
        holder.onBind(arrayList[position], position)
    }

    override fun getItemCount(): Int {
        Log.i("MyTag", arrayList.size.toString())
        return arrayList.size
    }

    fun submitItems(submitList: ArrayList<Dictionary>) {
        arrayList.clear()
        arrayList.addAll(submitList)
        notifyDataSetChanged()
    }

    fun setChangeRememberStatusListener(block: (Int, Int) -> Unit) {
        changeRememberStatusListener = block
    }

    private lateinit var t: android.speech.tts.TextToSpeech
    fun textToSpeech(text: String) {
        var tts = TextToSpeech(context, text, false)
    }

}
