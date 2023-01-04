package com.patrisoft.ui.home.componet

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.patrisoft.core.Tabs
import com.patrisoft.ui.R

@Composable
fun CustomTab(
    indicatorTab: (Tabs, Int) -> Unit
) {
    var selectedLocalIndex   by remember { mutableStateOf(1) }
    val list = listOf(Tabs.Historial, Tabs.Hoy, Tabs.Mañana)

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        TabRow(
            selectedTabIndex = selectedLocalIndex,
            indicator = { tabPositions ->
                TabIndicator(tabPositions, selectedLocalIndex)
            },
            backgroundColor = Color("#00ffffff".toColorInt())
        ) {
            list.forEachIndexed { index, tab ->
                val selected = selectedLocalIndex == index
                Tab(
                    selected = selected,
                    onClick = {
                        selectedLocalIndex = index
                        indicatorTab(list[selectedLocalIndex], selectedLocalIndex)
                              },
                    text = { Text(text = tab.name) },
                )
            }
        }
    }
}

@Composable
fun TabIndicator(tabPositions: List<TabPosition>, selectedIndex: Int) {
    val transition = updateTransition(targetState = selectedIndex, label = "")
    val leftIndicator by transition.animateDp(label = "", transitionSpec = {
        spring(stiffness = Spring.StiffnessMedium)
    }) {
        tabPositions[it].left
    }
    val rightIndicator by transition.animateDp(label = "", transitionSpec = {
        spring(stiffness = Spring.StiffnessMedium)
    }) {
        tabPositions[it].right
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = leftIndicator)
            .width(rightIndicator - leftIndicator)
            .fillMaxSize(),
        contentAlignment = Alignment.Center


    ) {
        Icon(
            modifier = Modifier
                .padding(bottom = 5.dp),
            painter = painterResource(id = R.drawable.ic_ray_tab),
            contentDescription = null // decorative element
        )
    }
}

@Preview
@Composable
fun CustomTabPreview() = CustomTab(indicatorTab = {tab, indext ->})

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}