package com.addhen.checkin.di.module

import com.addhen.checkin.data.repository.PostDataRepository
import com.addhen.checkin.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Development related dagger modules.
 */
@Module(includes = [AppModule::class])
internal object DevelopmentAppModule {

  @Provides
  @Singleton
  @JvmStatic
  fun providePostRepository(postRepository: PostDataRepository): PostRepository = postRepository
}
