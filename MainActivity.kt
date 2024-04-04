package com.prep.lyft

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.prep.lyft.adapters.MainRecyclerAdapter
import com.prep.lyft.databinding.ActivityMainBinding
import com.prep.lyft.models.MainDataItem
import com.prep.lyft.models.RecyclerEachItem
import com.prep.lyft.models.TutorialItem
import com.prep.lyft.utils.DataItemType
import com.prep.lyft.utils.GestureListener

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding?=null
    private val bnd get() = binding!!
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var gestureDetectorCompat: GestureDetectorCompat
    private lateinit var dataItemList: ArrayList<MainDataItem>
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bnd.root)
        drawerLayout=bnd.drawerLayout


        gestureDetectorCompat= GestureDetectorCompat(this,GestureListener(this,drawerLayout))


        bnd.listMainItem.setOnTouchListener { view, event ->
            gestureDetectorCompat.onTouchEvent(event)
            return@setOnTouchListener false
        }

        bnd.mainToolbar.setOnMenuItemClickListener {
            item->
            when (item.itemId){
                R.id.menu_drawer->{
                    toggleDrawer()
                    true
                }
                else-> true
            }
        }
        dataItemList=ArrayList<MainDataItem>()
        prepareData()

        mainRecyclerAdapter=MainRecyclerAdapter(dataItemList)

        bnd.listMainItem.setHasFixedSize(true)
        bnd.listMainItem.layoutManager=LinearLayoutManager(this)
        bnd.listMainItem.adapter=mainRecyclerAdapter

    }

    private fun toggleDrawer() {
        if (drawerLayout.isDrawerOpen(bnd.navigationView)) {
            drawerLayout.closeDrawer(bnd.navigationView)
        } else {
            drawerLayout.openDrawer(bnd.navigationView)
        }
    }

    fun prepareData(){
        val courseList=ArrayList<RecyclerEachItem>()
        courseList.add(RecyclerEachItem("Python Course",0,"1 day ago"))
        courseList.add(RecyclerEachItem("Java Course",0,"2 weeks ago"))
        courseList.add(RecyclerEachItem("Kotlin Course",0,"1 month ago"))
        courseList.add(RecyclerEachItem("Spring Course",0,"2 months ago"))

        val cheatList=ArrayList<RecyclerEachItem>()
        cheatList.add(RecyclerEachItem("Python Cheat",0,""))
        cheatList.add(RecyclerEachItem("Java Cheat",0,""))
        cheatList.add(RecyclerEachItem("C Cheat",0,""))

        dataItemList.add(MainDataItem(DataItemType.COURSE,courseList))
        dataItemList.add(MainDataItem(DataItemType.TUTORIAL, TutorialItem("Python Hello World",0,"2 hours ago",0,"2:56")))
        dataItemList.add(MainDataItem(DataItemType.CHEATSHEET,cheatList))
        dataItemList.add(MainDataItem(DataItemType.TUTORIAL, TutorialItem("Spring MVC for Beginners",0,"3 months ago",0,"5:20")))
    }



}
