package com.weldsh2535.DictionaryApps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

open class CopyDb(
    private val context: Context,
    private val assetsName: String
) : SQLiteOpenHelper(context, assetsName, null, 1) {
    private var mDatabase: SQLiteDatabase? = null

    init {
        if (!isExist()) {

            readableDatabase
            isCopyDatabase()
        }
        openDatabase()
    }

    private fun openDatabase() {
        if (mDatabase != null && mDatabase?.isOpen == true) {
            return
        }
        val path = context.applicationContext.getDatabasePath(assetsName).path
        mDatabase = openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE)
    }

    fun getDatabase(): SQLiteDatabase {
        return mDatabase!!
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.disableWriteAheadLogging()
    }

    private fun isCopyDatabase() {
        val inputStream = context.applicationContext.assets.open(assetsName)
        val outputFile = context.applicationContext.getDatabasePath(assetsName).absolutePath
        val outputStream = FileOutputStream(outputFile)
        val buff = ByteArray(1024)
        var length = inputStream.read(buff)
        while (length > 0) {
            outputStream.write(buff, 0, length)
            length = inputStream.read(buff)
        }
        outputStream.flush()
        outputStream.close()

    }

    private fun isExist(): Boolean {
        return context.applicationContext.getDatabasePath(assetsName).exists()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createDictionary =
            "CREATE TABLE tbl_dictionary (id INTEGER, geez TEXT, amharic TEXT, is_favourite INTEGER)"
        db?.execSQL(createDictionary)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS tbl_dictionary")
    }
}