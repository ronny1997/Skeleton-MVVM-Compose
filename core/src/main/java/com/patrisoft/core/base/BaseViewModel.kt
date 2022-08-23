package com.patrisoft.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrisoft.core.dispacher.main
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel<VS : ViewState, E : Event>(initialVal: VS) : ViewModel() {
    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(SAFE_LAUNCH_EXCEPTION).e(exception)
        handleError(exception)
    }

    open fun handleError(exception: Throwable) {}

    private val state = MutableStateFlow<VS>(initialVal)
    val viewState = state.asStateFlow()


    abstract fun onEvent(event: E)


    protected fun updateViewState(viewState: VS) = safeLaunch {
        state.emit(viewState)
    }

    private fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(main) {
            block()
        }
    }

    companion object {
        private const val SAFE_LAUNCH_EXCEPTION = "ViewModel-ExceptionHandler"
    }
}