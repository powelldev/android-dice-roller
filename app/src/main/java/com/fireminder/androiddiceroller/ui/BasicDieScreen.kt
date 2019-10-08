package com.fireminder.androiddiceroller.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fireminder.androiddiceroller.R
import com.fireminder.androiddiceroller.implementations.BaseEvaluator
import com.fireminder.androiddiceroller.implementations.BaseParser
import com.fireminder.androiddiceroller.implementations.BaseResultGenerator
import com.fireminder.androiddiceroller.implementations.BaseTower
import kotlinx.android.synthetic.main.fragment_basic_die.view.*

class BasicDieScreen @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): FrameLayout(context, attrs, defStyleAttr) {

  init {
    inflate(context, R.layout.fragment_basic_die, this)
  }

  private lateinit var receiver: BroadcastReceiver

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()

    val model = ViewModelProviders.of(context as AppCompatActivity).get(BaseViewModel::class.java)
    val modelObserver = Observer<String> { input ->
      formula_text.text = input
    }

    model.currentInput.observe(context as AppCompatActivity, modelObserver)

    receiver = object : BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && intent.action != null) {
          when (intent.action!!) {
            BaseActivity.ACTION_NUMPAD_KEY_EVENT -> {
              val string = intent.extras[Intent.EXTRA_KEY_EVENT]
              model.currentInput.value = "${model.currentInput.value}$string"
            }
            ClearRollFavoriteActionsView.DiceBagAction.Clear.action -> {
              model.currentInput.value = ""
            }
            ClearRollFavoriteActionsView.DiceBagAction.Favorite.action -> {
            }
            ClearRollFavoriteActionsView.DiceBagAction.Roll.action -> {
              val dialog = AlertDialog.Builder(context as AppCompatActivity)
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
        addAction(BaseActivity.ACTION_NUMPAD_KEY_EVENT)
        addAction(ClearRollFavoriteActionsView.DiceBagAction.Favorite.action)
        addAction(ClearRollFavoriteActionsView.DiceBagAction.Clear.action)
        addAction(ClearRollFavoriteActionsView.DiceBagAction.Roll.action)
      }
    context.registerReceiver(receiver, filter)
  }

  private fun evaluate(input: String): String {
    val tower = BaseTower(BaseParser(), BaseEvaluator(RealRng()), RealRng(), BaseResultGenerator())
    val result = tower.roll(input)
    return result.prettyPrint()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    context.unregisterReceiver(receiver)
  }

}
