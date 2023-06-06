package com.vlyashuk.rainbowgoldstation

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.vlyashuk.rainbowgoldstation.databinding.FragmentMainBinding
import com.vlyashuk.rainbowgoldstation.databinding.FragmentStartBinding
import com.vlyashuk.rainbowgoldstation.databinding.FragmentWebViewBinding
import java.lang.Math.random
import kotlin.random.Random

class WebViewFragment : Fragment() {
    private var URL = "https://www.ya.ru/"
    private var package_name = "com.android.chrome"

    private var _binding: FragmentWebViewBinding? = null
    private val binding: FragmentWebViewBinding
        get() = _binding ?: throw RuntimeException("FragmentWebViewBinding? is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startChromeTabs()
    }

    private fun startChromeTabs() {
        val builder = CustomTabsIntent.Builder()
        val params = CustomTabColorSchemeParams.Builder()
        params.setToolbarColor(ContextCompat.getColor(MAIN, R.color.black))
        with(builder) {
            setDefaultColorSchemeParams(params.build())
            setShowTitle(true)
            setShareState(CustomTabsIntent.SHARE_STATE_DEFAULT)
            setInstantAppsEnabled(true)
        }
        val customBuilder = builder.build()
        if (MAIN.isPackageInstalled(package_name)) {
            customBuilder.intent.setPackage(package_name)
            customBuilder.launchUrl(MAIN, Uri.parse(URL))
        } else {
            binding.webViewActivity.loadUrl("https://www.geeksforgeeks.org")
            binding.webViewActivity.webViewClient = WebViewClient()
        }
    }

    @Suppress("DEPRECATION")
    fun Context.isPackageInstalled(packageName: String): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}