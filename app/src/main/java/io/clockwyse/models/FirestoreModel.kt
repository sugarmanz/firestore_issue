package io.clockwyse.models

open class FirestoreModel {
    lateinit var id: String

    inline fun <reified T : FirestoreModel> withId(id: String): T {
        this.id = id
        return this as T
    }
}