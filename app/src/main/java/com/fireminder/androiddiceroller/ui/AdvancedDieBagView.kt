package com.fireminder.androiddiceroller.ui

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.fireminder.androiddiceroller.R

class AdvancedDieBagView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

  private val dN: View
  private val d100: View
  private val d20: View
  private val d12: View
  private val d10: View
  private val d8: View
  private val d6: View
  private val d4: View
  private val d3: View
  private val d2: View
  private val dF: View

  init {
    inflate(context, R.layout.advanced_die_bag, this)
    dN = findViewById(R.id.dN)
    d100 = findViewById(R.id.d100)
    d20 = findViewById(R.id.d20)
    d12 = findViewById(R.id.d12)
    d10 = findViewById(R.id.d10)
    d8 = findViewById(R.id.d8)
    d6 = findViewById(R.id.d6)
    d4 = findViewById(R.id.d4)
    d3 = findViewById(R.id.d3)
    d2 = findViewById(R.id.d2)
    dF = findViewById(R.id.dF)

    dN.setOnClickListener {
      notify("d")
    }
    d100.setOnClickListener {
      notify("d100")
    }
    d20.setOnClickListener {
      notify("d20")
    }
    d12.setOnClickListener {
      notify("d12")
    }
    d10.setOnClickListener {
      notify("d10")
    }
    d8.setOnClickListener {
      notify("d8")
    }
    d6.setOnClickListener {
      notify("d6")
    }
    d4.setOnClickListener {
      notify("d4")
    }
    d3.setOnClickListener {
      notify("d3")
    }
    d2.setOnClickListener {
      notify("d2")
    }
    dF.setOnClickListener {
      notify("dF")
    }
  }

  private fun notify(string: String) {
    context.sendBroadcast(
      Intent(BaseActivity.ACTION_NUMPAD_KEY_EVENT)
        .apply {
          putExtra(Intent.EXTRA_KEY_EVENT, string)
        })
  }
}
