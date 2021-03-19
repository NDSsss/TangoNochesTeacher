package com.tangonoches.teacher.navigation

import android.content.Context
import android.content.Intent
import com.tangonoches.teacher.MainActivity
import ru.nds.common.navigation.CoreCoordinator

class GlobalCoordinator : CoreCoordinator() {
    var appContext: Context? = null

    fun openMainActivity() {
        appContext?.let { ctx ->
            ctx.startActivity(Intent(ctx, MainActivity::class.java))
        }
    }
}