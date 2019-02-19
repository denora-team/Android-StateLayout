package ir.denora.statelayout_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.denora.statelayout.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        BTN_content.setOnClickListener { stateLayout.showContent() }
        BTN_loading.setOnClickListener { stateLayout.showLoading() }
        BTN_empty.setOnClickListener { stateLayout.showEmpty() }
        BTN_notFound.setOnClickListener { stateLayout.showNotFound() }
        BTN_noNetwork.setOnClickListener { stateLayout.showNoNetwork() }
        BTN_error.setOnClickListener { stateLayout.showError() }

        stateLayout.onStateChangedListener = object : OnStateChangedListener {
            override fun onStateChanged(state: State) {
                when (state) {
                    ContentState -> {
                        //  TODO
                    }
                    LoadingState -> {
                        //  TODO
                    }
                    EmptyState -> {
                        //  TODO
                    }
                    NotFoundState -> {
                        //  TODO
                    }
                    NoNetworkState -> {
                        //  TODO
                    }
                    ErrorState -> {
                        //  TODO
                    }
                }
            }
        }
    }
}
