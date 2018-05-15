package com.kovalsikoski.johan.wallpapermanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val wallpaperList: MutableList<Bitmap> = mutableListOf()

    private var measureWidth: Int = 0
    private var measureHeight: Int = 0

    private lateinit var adapter: WallpaperAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getResolutionMeasure()

        wallpaperList.add(resizeImage(R.drawable.wallpaper1, measureWidth, measureHeight))
        wallpaperList.add(resizeImage(R.drawable.wallpaper2, measureWidth, measureHeight))
        wallpaperList.add(resizeImage(R.drawable.wallpaper3, measureWidth, measureHeight))
        wallpaperList.add(resizeImage(R.drawable.wallpaper4, measureWidth, measureHeight))
        wallpaperList.add(resizeImage(R.drawable.wallpaper5, measureWidth, measureHeight))

        initRecyclerView()

    }

    private fun initRecyclerView() {
        val recyclerView = wallpaperRecyclerView
        adapter = WallpaperAdapter(wallpaperList, this)
        recyclerView.adapter = adapter

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = layoutManager
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

    fun convertToBitmap(drawable: Drawable, widthPixels: Int, heightPixels: Int): Bitmap {

        val mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(mutableBitmap)

        drawable.setBounds(0, 0, widthPixels, heightPixels)
        drawable.draw(canvas)

        return mutableBitmap
    }
}