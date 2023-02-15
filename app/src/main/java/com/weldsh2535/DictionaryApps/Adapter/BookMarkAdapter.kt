package com.weldsh2535.DictionaryApps.Adapter

import android.app.Activity
import android.content.Intent
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

class BookMarkAdapter(
    var arrayList: ArrayList<Dictionary>,
    var context: Activity,
    var onClick: OnBookMarkDictionary
) :
    RecyclerView.Adapter<BookMarkAdapter.ViewHolder>() {
    private var changeRememberStatusListener: ((Int, Int) -> Unit)? = null
    lateinit var bottomSheetDialog: BottomSheetDialog

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(dictionary: Dictionary) {
            itemView.tvGeez.text = dictionary.geez
            var database = DbHelper.getDatabase()
            itemView.tvAmharic.text = dictionary.amharic
            itemView.setOnClickListener {
                var view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet, null, false)
                if (this@BookMarkAdapter::bottomSheetDialog.isInitialized) {
                    bottomSheetDialog.dismiss()
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

                    // subject of the content. you caSuppressLintn share anything
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
                        submitItems(database.getAllDictionarys())
                        changeRememberStatusListener?.invoke(dictionary.id, 1)
                    } else {
                        view.iv_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                        dictionary.is_favourite = 0
                        changeRememberStatusListener?.invoke(dictionary.id, 0)
                        database.updateDictionary(dictionary)
                        submitItems(database.getAllDictionarys())
                    }
                }

                if (dictionary.is_favourite == 1) {
                    view.iv_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                } else {
                    view.iv_bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                }

                /* view.type.text = dictionary.type
                 view.transcription.text = dictionary.transcript
                 view.countable.text = dictionary.countable*/
                bottomSheetDialog.show()
            }

            itemView.bookmark.setOnClickListener {
                onClick.onClick(dictionary, itemView)
            }

            if (dictionary.is_favourite == 1) {
                itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
            } else {
                itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
            }
        }
    }

    fun submitItems(submitList: ArrayList<Dictionary>) {
        arrayList.clear()
        arrayList.addAll(submitList)
        notifyDataSetChanged()
    }

    private fun textToSpeech(text: String) {
        var tts = TextToSpeech(context, text, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnBookMarkDictionary {
        fun onClick(course: Dictionary, itemView: View)
    }
}
