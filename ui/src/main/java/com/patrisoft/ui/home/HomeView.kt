package com.patrisoft.ui.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrisoft.core.Tabs
import com.patrisoft.core.utils.color
import com.patrisoft.domain.model.AttributesDto
import com.patrisoft.domain.model.EnergyPriceDto
import com.patrisoft.domain.model.HourEnergyPriceDto
import com.patrisoft.ui.R
import com.patrisoft.ui.home.componet.CustomTab
import com.patrisoft.ui.home.utils.buildDataRee
import com.patrisoft.ui.theme.DarkYellow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun HomeView(
    visible: MutableState<Boolean>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    visible.value = false

    val state by viewModel.viewState.collectAsState()
    when (state) {
        is HomeViewState.ShowProgress -> {}
        is HomeViewState.ShowData -> {
            val dataEnergy = (state as HomeViewState.ShowData).dataEnergy
            EnergyView(
                dataEnergy.energyPriceDto,
                dataEnergy.showProgress,
                viewModel = viewModel
            )
        }
        is HomeViewState.ShowError -> {}
    }
}

@Composable
fun EnergyView(
    energyPriceDto: EnergyPriceDto?,
    showProgress: Boolean,
    viewModel: HomeViewModel
) {
    Scaffold(
        backgroundColor = Color("#c19300".toColorInt()),
        topBar = { TopBar() },
        drawerShape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showProgress) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.White
                )
            }
            val attributes = energyPriceDto?.included?.getOrNull(0)?.attributes
            val actualPrice = attributes?.values?.firstOrNull() { it.actualHour }

            InfoHourNow(
                actualPrice = actualPrice?.value.toString(),
                energyMarket = energyPriceDto?.data?.type ?: ""
            )
            ContentInfo(
                attributes,
                indicatorTab = { tab, indext ->
                    when (tab) {
                        Tabs.Historial -> {}
                        Tabs.Hoy -> viewModel.onEvent(HomeViewEvent.Now)
                        Tabs.Mañana -> viewModel.onEvent(HomeViewEvent.Morning)
                    }
                })

            ListHours(attributes)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(), title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.energy_forecast),
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        },
        backgroundColor = DarkYellow
    )
}

@Composable
fun InfoHourNow(actualPrice: String, energyMarket: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Icon(
            modifier = Modifier
                .width(35.dp),
            painter = painterResource(id = R.drawable.ic_ray_2),
            contentDescription = null // decorative element
        )
        Column(
            modifier = Modifier.padding(start = 9.dp),
        ) {
            val df = SimpleDateFormat("EEE, d MMM", Locale.getDefault())
            val date: String = df.format(Calendar.getInstance().time)
            Text(text = "Today")
            Text(text = date)
        }
    }

    Text(text = "$actualPrice/kwh", fontSize = 50.sp)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = energyMarket, fontSize = 13.sp)
        Icon(
            modifier = Modifier
                .width(16.dp)
                .padding(start = 4.dp),
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = null // decorative element
        )
    }
}

@Composable
fun ContentInfo(
    attributes: AttributesDto?,
    indicatorTab: (Tabs, Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        backgroundColor = Color("#c19300".toColorInt()),
        shape = RoundedCornerShape(22.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color("#57ffffff".toColorInt())),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTab(
                indicatorTab = { tab, indext -> indicatorTab(tab, indext) }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "El mas bajo")
                    Text(text = attributes?.minimum.toString(), fontSize = 22.sp)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Precio medio")
                    Text(text = attributes?.medium.toString(), fontSize = 22.sp)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "El mas alto")
                    Text(text = attributes?.maximum.toString(), fontSize = 22.sp)
                }
            }
        }
    }
}

@Composable
fun ListHours(attributes: AttributesDto?) {
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(22.dp),
        backgroundColor = Color("#c19300".toColorInt())
    ) {
        val listState = rememberLazyListState()
        val indexItemSelect = attributes?.values?.indexOfFirst { it.actualHour }
        LazyColumn(
            modifier = Modifier
                .background(Color("#57ffffff".toColorInt())),
            state = listState,
        ) {
            itemsIndexed(attributes?.values ?: listOf()) { indext, item ->
                ItemHour(item)
                Divider()
            }
        }
        DisposableEffect(Unit) {
            coroutineScope.launch {
                indexItemSelect?.let {
                    listState.animateScrollToItem(indexItemSelect)
                }
            }
            onDispose { }
        }
    }
}

@Composable
fun ItemHour(item: HourEnergyPriceDto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val hourRange =
            item.datetime.hour.toString() + ":00"
        Text(
            modifier = Modifier
                .width(45.dp), text = hourRange, fontSize = 15.sp
        )
        Icon(
            modifier = Modifier
                .width(30.dp),
            painter = painterResource(id = R.drawable.ic_ray_2),
            tint = item.color.color,
            contentDescription = null // decorative element
        )

        Text(
            modifier = Modifier
                .width(110.dp), text = item.value.toString() + "/kwh", fontSize = 16.sp
        )

    }
}


@Preview
@Composable
fun HomeViewPreview() = EnergyView(buildDataRee(), false, hiltViewModel())