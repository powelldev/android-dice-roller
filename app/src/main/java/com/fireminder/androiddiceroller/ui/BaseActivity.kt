package com.fireminder.androiddiceroller.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_KEY_EVENT
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.fireminder.androiddiceroller.R
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {

    companion object {
        const val ACTION_NUMPAD_KEY_EVENT = "com.fireminder.androiddiceroller.KEY_ACTION"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val model = ViewModelProviders.of(this).get(BaseViewModel::class.java)
        val modelObserver = Observer<String> { input ->
            formula_text.text = input
        }

        model.currentInput.observe(this, modelObserver)

        val receiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent != null && intent.action != null) {
                    when (intent.action!!) {
                        ACTION_NUMPAD_KEY_EVENT -> {
                            val string = intent.extras[EXTRA_KEY_EVENT]
                            model.currentInput.value = "${model.currentInput.value}$string"
                        }
                        ClearRollFavoriteActionsView.DiceBagAction.Clear.action -> {
                            model.currentInput.value = ""
                        }
                        ClearRollFavoriteActionsView.DiceBagAction.Favorite.action -> {}
                        ClearRollFavoriteActionsView.DiceBagAction.Roll.action -> {
                            val dialog = AlertDialog.Builder(this@BaseActivity)
                                .setTitle("roll result")
                                .create()
                                .show()
                        }

                    }
                }
            }
        }
        val filter = IntentFilter()
            .apply {
                addAction(ACTION_NUMPAD_KEY_EVENT)
                addAction(ClearRollFavoriteActionsView.DiceBagAction.Favorite.action)
                addAction(ClearRollFavoriteActionsView.DiceBagAction.Clear.action)
                addAction(ClearRollFavoriteActionsView.DiceBagAction.Roll.action)
            }
        registerReceiver(receiver, filter)
    }
}

class BaseViewModel : ViewModel() {
    val currentInput: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
}