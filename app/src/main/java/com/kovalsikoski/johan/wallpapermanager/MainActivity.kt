package com.kovalsikoski.johan.wallpapermanager

import android.app.WallpaperManager
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var measureWidth: Int = 0
    private var measureHeight: Int = 0

    private lateinit var resizedImage: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getResolutionMeasure()

        resizedImage = resizeImage(R.drawable.wallpaper1, measureWidth, measureHeight)


        val wallpaperManager = WallpaperManager.getInstance(applicationContext)

        wallpaperManager.clear() //Clear and set back default System's wallpaper

        try {
            wallpaperManager.setBitmap(resizedImage)
        } catch (e: IOException){
            e.message
        }

    }

    private fun getResolutionMeasure() {
        val size = Point()
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getRealSize(size)

        measureWidth = size.x
        measureHeight = size.y
    }

    private fun resizeImage(imageResourcePath: Int, reqWidth: Int, reqHeight: Int): Bitmap {

        val drawableToBitmap = BitmapFactory.decodeResource(resources, imageResourcePath)

        return Bitmap.createScaledBitmap(drawableToBitmap, reqWidth, reqHeight, true)
    }

}