package com.example.uas19090075_mobileapp.Activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.uas19090075_mobileapp.Fragment.AboutFragment
import com.example.uas19090075_mobileapp.Fragment.DashboardFragment
import com.example.uas19090075_mobileapp.Fragment.ListDataFragment
import com.example.uas19090075_mobileapp.Fragment.ManipulationFragment
import com.example.uas19090075_mobileapp.R
import com.example.uas19090075_mobileapp.Util.SharedPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = SharedPreferences(this)

        // inisialisasi
        val frag_dashboard = DashboardFragment()
        val frag_list = ListDataFragment()
        val frag_Manipulation = ManipulationFragment()
        val frag_about = AboutFragment()

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, draw_layout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_open)
        draw_layout.addDrawerListener(toggle)
        toggle.syncState()

        //menentukan fragment utama saat aplikasi dijalankan
        currentFragment(frag_dashboard)

        //switch antar fragment
        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mn_dashboard -> {
                    currentFragment(frag_dashboard)
                    toolbar.title = "Dashboard"
                }
                R.id.mn_listData -> {
                    currentFragment(frag_list)
                    toolbar.title = "List Data"
                }
                R.id.mn_manipulasi -> {
                    currentFragment(frag_Manipulation)
                    toolbar.title = "Data Manipulation"
                }
                R.id.mn_logout -> {
                    currentFragment(frag_dashboard)
                    val b = AlertDialog.Builder(this)
                    b.setTitle("Logout")
                    b.setMessage("Are you sure?")
                    b.setPositiveButton("yes", { dialog: DialogInterface?, which: Int ->
                        val inten = Intent(this, LoginActivity::class.java)
                        startActivity(inten)
                    })
                    b.setNegativeButton("No") { dialog: DialogInterface?, which: Int ->

                    }
                    b.show()
                }
                R.id.mn_about -> {
                    currentFragment(frag_about)
                    toolbar.title = "About Me"
                }
            }
            draw_layout.closeDrawers()
            true
        }

    }
    override fun onBackPressed() {
        if (draw_layout.isDrawerOpen(GravityCompat.START)){
            draw_layout.closeDrawer(GravityCompat.START)
        } else{
            super.onBackPressed()
        }
    }

    // function untuk menentukan fragment yang aktif
    private fun currentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_navigation, fragment) // frame_navigation => id dari frame di activity main
            commit()
        }
    }

}