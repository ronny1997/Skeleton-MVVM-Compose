package com.patrisoft.ui.home

import com.patrisoft.core.base.Event

sealed class HomeViewEvent : Event {
    class RefreshData() : HomeViewEvent()
}