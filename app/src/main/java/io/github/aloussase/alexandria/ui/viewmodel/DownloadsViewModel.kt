package io.github.aloussase.alexandria.ui.viewmodel

import android.app.DownloadManager
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.util.Log
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference
import androidx.core.net.toUri

sealed class DownloadsEvent {
    data class InitiateDownload(val url: String) : DownloadsEvent()
}

class DownloadsViewModel(
    val context: WeakReference<Context>
) : ViewModel() {

    fun onEvent(event: DownloadsEvent) {
        when (event) {
            is DownloadsEvent.InitiateDownload -> onInitiatedDownload(event.url)
        }
    }

    private fun onInitiatedDownload(url: String) {
        val request = DownloadManager
            .Request(url.toUri())
            .setNotificationVisibility(VISIBILITY_VISIBLE)
        Log.d("DownloadsViewModel", "Starting download for: $url")
        val manager = context.get()?.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

}