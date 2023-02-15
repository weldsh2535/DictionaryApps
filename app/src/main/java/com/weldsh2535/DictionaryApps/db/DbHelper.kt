package com.weldsh2535.DictionaryApps.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.weldsh2535.DictionaryApps.Model.Dictionary

class DbHelper (context: Context) :
    CopyDb(context, "dictionary.db") {
    companion object {
        private const val TBL_DICTIONAERY = "tbl_dictionary"
        @SuppressLint("StaticFieldLeak")
        private var database: DbHelper? = null
        fun init(context: Context) {
            database = DbHelper(context)
        }
        fun getDatabase(): DbHelper = database!!
    }
    //Insert Data
    fun createDictionary(dictionary: Any): Any? {
        val database = this.writableDatabase
        var data = createDictinary()
        var success: Any? = null
        data.forEach {
            // Log.i("MyTag",it.amharic)
            val contentValues = ContentValues()
            contentValues.put("id", it.id)
            contentValues.put("amharic", it.amharic)
            contentValues.put("geez", it.geez)
            contentValues.put("is_favourite", it.is_favourite)
            //Inserting row
            success = database.insert(TBL_DICTIONAERY, null, contentValues)
        }
        database.close()
        return success
    }

    //update Data
    fun updateDictionary(dictionary: Dictionary) {
        val database = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put("id", dictionary.id)
        contentValues.put("amharic", dictionary.amharic)
        contentValues.put("geez", dictionary.geez)
        contentValues.put("is_favourite", dictionary.is_favourite)
        //updating row
        database.update(
            "$TBL_DICTIONAERY", contentValues, "id = ?", arrayOf("${dictionary.id}")
        )
    }

    //get all data by query
    fun getDictionaryByQuery(str: String): ArrayList<Dictionary> {
        var wordList: ArrayList<Dictionary> = ArrayList()
        var selectedQuery = "select * from $TBL_DICTIONAERY where tbl_dictionary.amharic like '%$str%'"

        var cursor: Cursor? = null

        try {
            cursor = getDatabase().rawQuery(selectedQuery, null)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            getDatabase().execSQL(selectedQuery)
        }

        var id: Int
        var geez: String
        var amharic: String
        var isFavourite: Int
        var contact: Dictionary

        if (cursor!!.moveToFirst()) {
            do {
                id = cursor.getInt(0)
                geez = cursor.getString(1)
                amharic = cursor.getString(2)
                isFavourite = cursor.getInt(3)
                contact = Dictionary(
                    id, geez, amharic, isFavourite
                )
                wordList.add(contact)
            } while (cursor.moveToNext())
        }
        return wordList
    }

    //update dictionary
    fun updateDictionary(remember: Int, id: Int): Int {
        val cv = ContentValues()
        cv.put("is_favourite", remember)
        val success = getDatabase().update("$TBL_DICTIONAERY", cv, "dictionary.id == $id", null)
        getDatabase().close()
        return success
    }

    //get all dictionary by int
    fun getAllDictionarys(offset: Int): ArrayList<Dictionary> {
        var wordsList = ArrayList<Dictionary>()
        var selectedQuery = "select * from $TBL_DICTIONAERY limit 10 offset $offset"
        var cursor: Cursor? = null
        try {
            cursor = getDatabase().rawQuery(selectedQuery, null)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            getDatabase().execSQL(selectedQuery)
        }

        var id: Int
        var geez: String
        var amharic: String
        var isFavourite: Int
        var contact: Dictionary

        if (cursor!!.moveToFirst()) {
            do {
                id = cursor.getInt(0)
                geez = cursor.getString(1)
                amharic = cursor.getString(2)
                isFavourite = cursor.getInt(3)
                contact = Dictionary(
                    id,
                    geez,
                    amharic,
                    isFavourite
                )
                wordsList.add(contact)
            } while (cursor.moveToNext())
        }
        return wordsList
    }

    //get all BookMark dictionary
    fun getAllBookMarkDictionarys(): ArrayList<Dictionary> {
        var wordsList = ArrayList<Dictionary>()
        val selectedQuery = "select * from $TBL_DICTIONAERY where is_favourite = 1"
        var cursor: Cursor? = null

        var id: Int
        var geez: String
        var amharic: String
        var isFavourite: Int
        var contact: Dictionary

        try {
            cursor = getDatabase().rawQuery(selectedQuery, null)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            getDatabase().execSQL(selectedQuery)
        }

        if (cursor!!.moveToFirst()) {
            do {
                id = cursor.getInt(0)
                geez = cursor.getString(1)
                amharic = cursor.getString(2)
                isFavourite = cursor.getInt(3)
                contact = Dictionary(
                    id,
                    geez,
                    amharic,
                    isFavourite
                )
                wordsList.add(contact)
            } while (cursor.moveToNext())
        }
        return wordsList
    }

    //get all dictionary
    fun getAllDictionarys(): ArrayList<Dictionary> {
        var wordsList = ArrayList<Dictionary>()
        val selectedQuery = "select * from $TBL_DICTIONAERY"
        var cursor: Cursor? = null

        var id: Int
        var geez: String
        var amharic: String
        var isFavourite: Int
        var contact: Dictionary

        try {
            cursor = getDatabase().rawQuery(selectedQuery, null)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            getDatabase().execSQL(selectedQuery)
        }

        if (cursor!!.moveToFirst()) {
            do {
                id = cursor.getInt(0)
                geez = cursor.getString(1)
                amharic = cursor.getString(2)
                isFavourite = cursor.getInt(3)
                contact = Dictionary(
                    id,
                    geez,
                    amharic,
                    isFavourite
                )
                wordsList.add(contact)
            } while (cursor.moveToNext())
        }
        return wordsList
    }
    //create dictionary
    fun createDictinary(): ArrayList<Dictionary> {
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