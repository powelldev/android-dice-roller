package com.fireminder.androiddiceroller.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.fireminder.androiddiceroller.R
import com.fireminder.androiddiceroller.interfaces.Rng
import com.fireminder.androiddiceroller.ui.advanced.AdvancedDieScreen
import com.fireminder.androiddiceroller.ui.basic.BasicDieScreen
import com.google.android.material.tabs.TabLayout

class BaseActivity : AppCompatActivity() {

    companion object {
        const val ACTION_NUMPAD_KEY_EVENT = "com.fireminder.androiddiceroller.KEY_ACTION"
    }

  private lateinit var viewPager: ViewPager
  private lateinit var tabLayout: TabLayout
  private lateinit var adapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_base)
      viewPager = findViewById(R.id.viewPager)
      tabLayout = findViewById(R.id.tabLayout)
      adapter = MyPagerAdapter()
      viewPager.adapter = adapter
      tabLayout.setupWithViewPager(viewPager)
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

  override fun getPageTitle(position: Int): CharSequence? {
    return when (position) {
      0 -> "Basic"
      1 -> "Advanced"
      else -> ""
    }
  }

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val view: View = when(position) {
      0 -> {
        BasicDieScreen(container.context)
      }
      1 -> {
        AdvancedDieScreen(container.context)
      }
      else -> {View(container.context)}
    }
    container.addView(view)
    return view
  }

}