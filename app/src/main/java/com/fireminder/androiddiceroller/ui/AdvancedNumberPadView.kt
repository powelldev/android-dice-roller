package com.fireminder.androiddiceroller.ui

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.fireminder.androiddiceroller.R

class AdvancedNumberPadView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

  private val one: View
  private val two: View
  private val three: View
  private val four: View
  private val five: View
  private val six: View
  private val seven: View
  private val eight: View
  private val nine: View
  private val zero: View

  private val plus: View
  private val minus: View

  private val keepHigh: View
  private val dropHigh: View
  /*
  private val reroll: View
  private val compound: View
  private val greaterThan: View
  private val lessThan: View
   */

  private val keepLow: View
  private val dropLow: View
  /*
  private val rerollOnce: View
  private val explode: View
  private val penetrate: View
  private val countFailures: View
   */

  init {
    inflate(context, R.layout.advanced_number_pad, this)
    one = findViewById(R.id.number_pad_1)
    one.setOnClickListener { notify("1") }

    two = findViewById(R.id.number_pad_2)
    two.setOnClickListener { notify("2") }

    three = findViewById(R.id.number_pad_3)
    three.setOnClickListener { notify("3") }

    four = findViewById(R.id.number_pad_4)
    four.setOnClickListener { notify("4") }

    five = findViewById(R.id.number_pad_5)
    five.setOnClickListener { notify("5") }

    six = findViewById(R.id.number_pad_6)
    six.setOnClickListener { notify("6") }

    seven = findViewById(R.id.number_pad_7)
    seven.setOnClickListener { notify("7") }

    eight = findViewById(R.id.number_pad_8)
    eight.setOnClickListener { notify("8") }

    nine = findViewById(R.id.number_pad_9)
    nine.setOnClickListener { notify("9") }

    zero = findViewById(R.id.number_pad_0)
    zero.setOnClickListener { notify("0") }

    plus = findViewById(R.id.plus)
    plus.setOnClickListener { notify("+") }

    minus = findViewById(R.id.minus)
    minus.setOnClickListener { notify("-") }

    keepHigh = findViewById(R.id.keepHigh)
    keepHigh.setOnClickListener { notify("KH") }

    keepLow = findViewById(R.id.keepLow)
    keepLow.setOnClickListener { notify("KL") }

    dropLow = findViewById(R.id.dropLow)
    dropLow.setOnClickListener { notify("DL") }

    dropHigh = findViewById(R.id.dropHigh)
    dropHigh.setOnClickListener { notify("DH") }

  }

  private fun notify(string: String) {
    context.sendBroadcast(
      Intent(BaseActivity.ACTION_NUMPAD_KEY_EVENT)
        .apply { putExtra(Intent.EXTRA_KEY_EVENT, string) });
  }
}
