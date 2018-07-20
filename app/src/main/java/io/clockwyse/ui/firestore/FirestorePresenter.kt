package io.clockwyse.ui.firestore

import com.google.firebase.firestore.FirebaseFirestore
import io.clockwyse.models.Device
import com.google.firebase.firestore.FirebaseFirestoreSettings



object FirestorePresenter : FirestoreContract.Presenter {

    private var view: FirestoreContract.View? = null

    private val nullDevice get() = Device(name="Device was null!")
    private val deviceRef by lazy { FirebaseFirestore.getInstance().collection("demo").document("demo") }

    init {
        val firestore = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()
        firestore.firestoreSettings = settings

        FirebaseFirestore.setLoggingEnabled(true)
        deviceRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                exception.printStackTrace()
            } else if (snapshot != null) {
                val data = snapshot.toObject(Device::class.java)?.withId<Device>(snapshot.id) ?: nullDevice
                println("Name: ${data.name}")
                view?.updateView(data)
            }
        }
    }

    override fun attach(view: FirestoreContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun refresh() {
        deviceRef.get().addOnCompleteListener {
            val snapshot = it.result
            view?.updateView(snapshot.toObject(Device::class.java)?.withId(snapshot.id) ?: Device(name = "Failed to sync"))
        }
    }

}
