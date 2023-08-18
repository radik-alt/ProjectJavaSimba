package com.example.projectjavasimba.module_navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.projectjavasimba.presentation.MainActivity

class NavComponent(
    private val context: Context,
) : AppRouter {
    override fun openHomeActivity(activity: Activity) {
        context.startActivity(
            Intent(activity, MainActivity::class.java)
        )
    }
}