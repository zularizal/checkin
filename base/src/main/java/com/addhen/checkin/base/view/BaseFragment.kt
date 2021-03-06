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

package com.addhen.checkin.base.view

import android.view.Menu
import android.view.MenuInflater
import androidx.annotation.MenuRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<out T : BaseViewModel, B : ViewDataBinding>(
  @MenuRes
  private val menu: Int = 0,
  clazz: Class<T>
) : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  protected lateinit var binding: B
  val viewModel: T by lazy {
    ViewModelProviders.of(this@BaseFragment, viewModelFactory).get(clazz)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    if (this.menu != 0) {
      inflater.inflate(this.menu, menu)
    }
    super.onCreateOptionsMenu(menu, inflater)
  }
}
