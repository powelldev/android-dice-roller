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
import com.fireminder.androiddiceroller.implementations.BaseEvaluator
import com.fireminder.androiddiceroller.implementations.BaseParser
import com.fireminder.androiddiceroller.implementations.BaseResultGenerator
import com.fireminder.androiddiceroller.implementations.BaseTower
import com.fireminder.androiddiceroller.interfaces.Rng
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {

    companion object {
        const val ACTION_NUMPAD_KEY_EVENT = "com.fireminder.androiddiceroller.KEY_ACTION"
    }

    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val model = ViewModelProviders.of(this).get(BaseViewModel::class.java)
        val modelObserver = Observer<String> { input ->
            formula_text.text = input
        }

        model.currentInput.observe(this, modelObserver)

        receiver = object : BroadcastReceiver() {
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
                        ClearRollFavoriteActionsView.DiceBagAction.Favorite.action -> {
                        }
                        ClearRollFavoriteActionsView.DiceBagAction.Roll.action -> {
                            val dialog = AlertDialog.Builder(this@BaseActivity)
                              .setTitle("roll result")
                              .setMessage(evaluate(model.currentInput.value!!))
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

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    private fun evaluate(input: String): String {
        val tower = BaseTower(BaseParser(), BaseEvaluator(RealRng()), RealRng(), BaseResultGenerator())
        val result = tower.roll(input)
        return result.prettyPrint()
    }

}
class RealRng : Rng {
    override fun next(lowerBoundInclusive: Int, upperBoundInclusive: Int): Int {
        return (lowerBoundInclusive..upperBoundInclusive).shuffled().first()
    }
}

class BaseViewModel : ViewModel() {
    val currentInput: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")
    }
}