package com.fireminder.androiddiceroller.ui.advanced

import android.content.BroadcastReceiver
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fireminder.androiddiceroller.R
import com.fireminder.androiddiceroller.ui.BaseViewModel
import kotlinx.android.synthetic.main.fragment_basic_die.view.*

class AdvancedDieScreen @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): FrameLayout(context, attrs, defStyleAttr) {

  init {
    inflate(context, R.layout.fragment_advanced_die, this)
  }

  private lateinit var receiver: BroadcastReceiver

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    val model = ViewModelProviders.of(context as AppCompatActivity).get(BaseViewModel::class.java)
    val modelObserver = Observer<String> { input ->
      formula_text.text = input
    }
    model.currentInput.observe(context as AppCompatActivity, modelObserver)
  }

}
