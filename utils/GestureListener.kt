package com.prep.lyft.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

class GestureListener(val context: Context, val drawer: DrawerLayout):
    GestureDetector.SimpleOnGestureListener() {
    val threshold = 50
    val velocity_threshold = 50

    override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        val xdiff=e2.x - (e1?.x ?: 0f)
        val ydiff=e2.y - (e1?.y ?: 0f);

        if (Math.abs(xdiff)>Math.abs(ydiff)){
            if (Math.abs(xdiff)>threshold && Math.abs(velocityX)>velocity_threshold){
                if (xdiff>200){
                    //Swipe Right
                    drawer.openDrawer(GravityCompat.START)
                }else{
                    //Swipe Left
                    drawer.closeDrawer(GravityCompat.START)
                }
            }
        }
        return super.onFling(e1, e2, velocityX, velocityY)
    }
}