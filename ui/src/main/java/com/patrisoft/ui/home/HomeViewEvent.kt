package com.patrisoft.ui.home

import com.patrisoft.core.base.Event

sealed class HomeViewEvent : Event {
    object Now : HomeViewEvent()
    object Morning : HomeViewEvent()
}