package com.qdaidu.autorent

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

open class BaseActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        setLocale(this) // Устанавливаем язык перед отрисовкой UI
        super.onCreate(savedInstanceState)
    }

    private fun setLocale(context: Context) {
        val languageCode = Locale.getDefault().language

        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}