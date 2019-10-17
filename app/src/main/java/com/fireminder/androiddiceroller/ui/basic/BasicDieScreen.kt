package com.fireminder.androiddiceroller.ui.basic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fireminder.androiddiceroller.R
import com.fireminder.androiddiceroller.implementations.BaseEvaluator
import com.fireminder.androiddiceroller.implementations.BaseParser
import com.fireminder.androiddiceroller.implementations.BaseResultGenerator
import com.fireminder.androiddiceroller.implementations.BaseTower
import com.fireminder.androiddiceroller.ui.BaseActivity
import com.fireminder.androiddiceroller.ui.BaseViewModel
import com.fireminder.androiddiceroller.ui.ClearRollFavoriteActionsView
import com.fireminder.androiddiceroller.ui.RealRng
import com.fireminder.androiddiceroller.interfaces.Result
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
              launchDialog(model)
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

  private fun launchDialog(model: BaseViewModel) {
    val inflater = LayoutInflater.from(context)
    val dialog = inflater.inflate(R.layout.dialog_result, null)
    val result = evaluate(model.currentInput.value!!)
    dialog.findViewById<TextView>(R.id.value_text).text = result.score().toString()
    dialog.findViewById<TextView>(R.id.result_text).text = result.prettyPrint()
    AlertDialog.Builder(context as AppCompatActivity)
      .setView(dialog)
      .show()
  }

  private fun evaluate(input: String): Result {
    val tower = BaseTower(BaseParser(), BaseEvaluator(RealRng()), RealRng(), BaseResultGenerator())
    return tower.roll(input)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    context.unregisterReceiver(receiver)
  }

}
