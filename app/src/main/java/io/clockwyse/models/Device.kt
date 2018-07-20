package io.clockwyse.models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Device(
        val action: String = "",
        val building: String = "",
        val campus: String = "",
        val status: String = "",
        val name: String = "",
        val policy: String = ""
) : FirestoreModel() { override fun toString() = name }