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

package com.addhen.checkin.view.posts

import com.addhen.checkin.base.CoroutineDispatchers
import com.addhen.checkin.base.view.BaseViewModel
import com.addhen.checkin.data.model.Post
import javax.inject.Inject

class PostItemViewModel @Inject constructor(
  dispatchers: CoroutineDispatchers,
  val post: Post
) : BaseViewModel(dispatchers) {

  fun onClickPost() {
    // TODO navigate to post details page
  }

  fun isLiked(): Boolean {
    return if (post.like != null)
      post.like.postId === post.id && post.user.id === post.like.userId
    else false
  }
}
