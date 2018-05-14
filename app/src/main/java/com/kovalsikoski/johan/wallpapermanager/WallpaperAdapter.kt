package com.kovalsikoski.johan.wallpapermanager

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.cardview_wallpaper.view.*
import java.io.IOException

class WallpaperAdapter(private val wallpaperList: MutableList<Bitmap>,
                       private val context: Context) : RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_wallpaper, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = wallpaperList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wp = wallpaperList[position]

        holder.let {
            it.bindView(wp)

            it.itemView.setOnLongClickListener {
                mySetWallpaper(wp)
                Toast.makeText(context, R.string.wallpaper_toast, Toast.LENGTH_LONG).show()
                true
            }
        }
    }

    private fun mySetWallpaper(imgPath: Bitmap) {

        val wallpaperManager = WallpaperManager.getInstance(context.applicationContext)

        try {
            wallpaperManager.setBitmap(imgPath)
        } catch (e: IOException) {
            e.message
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(wallpaper: Bitmap) {

            val imgView = itemView.wallpaperImageView
            imgView.setImageBitmap(wallpaper)
        }
    }
}