package com.cal.kotlinquizappproject.domain

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class UserRepository(private val context: Context) {

    private val dbHelper = UserDatabaseHelper(context)

    fun insertOrUpdateUser(user: UserModel) {
        val existingUser = getUserByName(user.name)
        if (existingUser != null) {
            if (user.score > existingUser.score) {
                // Update the existing user with the higher score
                val updatedUser = existingUser.copy(score = user.score, picture = user.picture)
                updateUser(updatedUser)
            }
        } else {
            // Insert new user
            insertUser(user)
        }
    }

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

    // Method to update user
    private fun updateUser(user: UserModel) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(UserDatabaseHelper.COLUMN_NAME, user.name)
            put(UserDatabaseHelper.COLUMN_PICTURE, user.picture)
            put(UserDatabaseHelper.COLUMN_SCORE, user.score)
        }
        db.update(UserDatabaseHelper.TABLE_USERS, values, "${UserDatabaseHelper.COLUMN_ID} = ?", arrayOf(user.id.toString()))
        db.close()
    }

    // Method to get user by name
    fun getUserByName(name: String): UserModel? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(UserDatabaseHelper.TABLE_USERS, null, "${UserDatabaseHelper.COLUMN_NAME} = ?", arrayOf(name), null, null, null)
        var user: UserModel? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_ID))
            val userName = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_NAME))
            val picture = cursor.getString(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_PICTURE))
            val score = cursor.getInt(cursor.getColumnIndexOrThrow(UserDatabaseHelper.COLUMN_SCORE))
            user = UserModel(id, userName, picture, score)
        }
        cursor.close()
        db.close()
        return user
    }
}
