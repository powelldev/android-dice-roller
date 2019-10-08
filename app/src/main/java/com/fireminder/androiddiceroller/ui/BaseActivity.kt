package com.fireminder.androiddiceroller.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_KEY_EVENT
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
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

  private lateinit var viewPager: ViewPager
  private lateinit var adapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_base)
      viewPager = findViewById(R.id.viewPager)
      adapter = MyPagerAdapter()
      viewPager.adapter = adapter
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

class MyPagerAdapter : PagerAdapter() {

  override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view == `object`
  }

  override fun getCount(): Int {
    return 2
  }

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val view: View = when(position) {
      0 -> { BasicDieScreen(container.context)}
      1 -> { AdvancedDieScreen(container.context) }
      else -> {View(container.context)}
    }
    container.addView(view)
    return view
  }

}