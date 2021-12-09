package dev.bwaim.loteria.app

import android.app.Application
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
internal class LoteriaApplication : Application(), ImageLoaderFactory {
    @Inject
    lateinit var imageLoaderFactory: ImageLoaderFactory

    override fun newImageLoader() = imageLoaderFactory.newImageLoader()
}
