package ua.edu.znu.geoquizcomposeedu.util

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember

private const val TAG = "CompositionLifecycle"

@Composable
fun logCompositionLifecycle(name: String): Any = remember {
    LifecycleRememberObserver(name)
}

private class LifecycleRememberObserver(
    private val name: String) : RememberObserver {
    override fun onRemembered() {
        Log.d(TAG, "$name.onEnter")
    }

    override fun onForgotten() {
        Log.d(TAG, "$name.onLeave")
    }
    override fun onAbandoned()=Unit
}
