package io.clockwyse.ui.firestore

import io.clockwyse.models.Device

interface FirestoreContract {

    interface View {
        fun updateView(device: Device)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun refresh()
    }

}