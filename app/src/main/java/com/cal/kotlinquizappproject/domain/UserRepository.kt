package com.cal.kotlinquizappproject.domain

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class UserRepository(private val context: Context) {

    private val dbHelper = UserDatabaseHelper(context)

    fun insertUser(user: UserModel) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(UserDatabaseHelper.COLUMN_NAME, user.name)
            put(UserDatabaseHelper.COLUMN_PICTURE, user.picture)
            put(UserDatabaseHelper.COLUMN_SCORE, user.score)
        }
        db.insert(UserDatabaseHelper.TABLE_USERS, null, values)
        db.close()
    }

    fun getAllUsers(): List<UserModel> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(UserDatabaseHelper.TABLE_USERS, null, null, null, null, null, "${UserDatabaseHelper.COLUMN_SCORE} DESC")
        val users = mutableListOf<UserModel>()

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_NAME))
                val picture = getString(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_PICTURE))
                val score = getInt(getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_SCORE))
                users.add(UserModel(id, name, picture, score))
            }
        }
        cursor.close()
        db.close()
        return users
    }

    fun deleteAllUsers() {
        val db = dbHelper.writableDatabase
        db.delete(UserDatabaseHelper.TABLE_USERS, null, null)
        db.close()
    }

    // New method to update user
    fun updateUser(user: UserModel) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(UserDatabaseHelper.COLUMN_NAME, user.name)
            put(UserDatabaseHelper.COLUMN_PICTURE, user.picture)
            put(UserDatabaseHelper.COLUMN_SCORE, user.score)
        }
        db.update(UserDatabaseHelper.TABLE_USERS, values, "${UserDatabaseHelper.COLUMN_ID} = ?", arrayOf(user.id.toString()))
        db.close()
    }
}
