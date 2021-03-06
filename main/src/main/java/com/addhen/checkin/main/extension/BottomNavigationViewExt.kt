/*
 * Copyright (c) 2015 - 2018 Henry Addo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.addhen.checkin.main.extension

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.forEach
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

// Credits
// https://github.com/DroidKaigi/conference-app-2018/blob/master/app/src/main/java/io/github/droidkaigi/confsched2018/util/ext/BottomNavigationViewExt.kt
@SuppressLint("RestrictedApi")
fun BottomNavigationView.disableShiftMode() {
  val menuView = this.getChildAt(0) as BottomNavigationMenuView
  try {
    val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
    shiftingMode.isAccessible = true
    shiftingMode.setBoolean(menuView, false)
    shiftingMode.isAccessible = false
    menuView.forEach {
      val item = it as BottomNavigationItemView
      // NOTE: This removing the BottomNavigationItem labels including small and large text container.
      item.removeViewAt(1)

      // set the gravity of item icon to Center.
      val lp = FrameLayout.LayoutParams(
          ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.MATCH_PARENT)
      lp.gravity = Gravity.CENTER
      item.getChildAt(0).layoutParams = lp

      // remove the default top margin.
      val topMargin = item.javaClass.getDeclaredField("mDefaultMargin")
      topMargin.isAccessible = true
      topMargin.setInt(item, 0)
      topMargin.isAccessible = false

      // item.setShiftingMode(false)
      // set once again checked value, so view will be updated

      item.setChecked(item.itemData.isChecked)
    }
  } catch (e: NoSuchFieldException) {
    Timber.e(e, "Unable to get shift mode field")
  } catch (e: IllegalAccessException) {
    Timber.e(e, "Unable to change value of shift mode")
  }
}
