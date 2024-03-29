package com.amalitech.arms_mobile.ui.views.home

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.Routes
import com.amalitech.arms_mobile.ui.components.ScreenPlaceHolder
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = {
        4
    })

    val navItems: Array<Pair<Int, String>> = arrayOf(
        Pair(R.drawable.home, "Home"),
        Pair(R.drawable.clock, "Clock"),
        Pair(R.drawable.calendar_tick, "Leaves"),
        Pair(R.drawable.wallet_check, "Loans"),
    )

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Gray
            ) {
                navItems.mapIndexed { index, pair ->
                    NavigationBarItem(
                        selected = index == pagerState.currentPage,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xffDD5928),
                            unselectedIconColor = Color(0xff818181),
                            selectedTextColor = Color(0xffDD5928),
                            unselectedTextColor = Color(0xff818181),
                            indicatorColor = Color.White
                        ),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = pair.first),
                                contentDescription = null,
                            )
                        },
                        label = {
                            Text(text = pair.second)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            key = { it },
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            userScrollEnabled = false,
        ) {
            if (it == 0)
                HomeScreenContent(
                    modifier = Modifier.fillMaxHeight(),
                    expandCelebrations = {
                        navController.navigate(Routes.Celebrations.route)
                    },
                    expandWhoIsOut = {
                        navController.navigate(Routes.Leaves.route)
                    }
                )
            else
                ScreenPlaceHolder()
        }
    }
}