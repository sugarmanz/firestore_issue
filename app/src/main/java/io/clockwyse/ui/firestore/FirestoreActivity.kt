package io.clockwyse.ui.firestore

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

import io.clockwyse.R
import io.clockwyse.models.Device
import kotlinx.android.synthetic.main.data.*

class FirestoreActivity : AppCompatActivity(), FirestoreContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data)
    }

    override fun onStart() {
        super.onStart()

        FirebaseFirestore.setLoggingEnabled(true)
        firestore_refresh.setOnClickListener {
            FirestorePresenter.refresh()
        }
    }

    override fun onResume() {
        super.onResume()

        FirestorePresenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        FirestorePresenter.detach()
    }

    override fun updateView(device: Device) {
        firestore_action.text = device.action
        firestore_status.text = device.status
        firestore_name.text = device.name
        firestore_policy.text = device.policy
        firestore_id.text = device.id
    }

}
