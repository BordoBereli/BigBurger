package com.vibuy.legacy.cache.database.db.exception

/**
 * Created by F.K. on 2019-05-02
 *
 */

class DbException : Exception() {
    override val message: String?
        get() = "There is an error on Big Burger DB operation: " + super.message
}