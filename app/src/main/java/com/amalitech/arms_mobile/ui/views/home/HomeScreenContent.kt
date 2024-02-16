package com.amalitech.arms_mobile.ui.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.amalitech.arms_mobile.R
import com.amalitech.arms_mobile.ui.views.home.sections.CelebrationSection
import com.amalitech.arms_mobile.ui.views.home.sections.WhoIsOutSection
import kotlinx.coroutines.launch


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val scrollState: ScrollState = rememberScrollState()

    Box(
        modifier = modifier.background(color = Color(0xffFAFAFA))
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            AppBar()
            Text(
                text = "Good day, Precious 😊",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_small))
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
            )
            Text(
                text = "Here's what's going on today.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
            )
            Spacer(modifier = Modifier.height(16.dp))
            WhoIsOutSection(viewModel.leaveState, onReload = {
                viewModel.viewModelScope.launch {
                    viewModel.getLeaveData()
                }
            })
            Spacer(modifier = Modifier.height(24.dp))
            CelebrationSection(viewModel.celebrationState, onReload = {
                viewModel.viewModelScope.launch {
                    viewModel.getCelebrationData()
                }
            })
        }
    }
}


@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.padding_large),
                bottom = dimensionResource(id = R.dimen.padding_small),
            )
            .padding(horizontal = dimensionResource(id = R.dimen.padding_large)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar_photo_placeholder),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.padding_extra_large_2))
                .clickable { },
        )
        Spacer(modifier = Modifier.weight(1F))
        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = null,
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_medium))
                .size(dimensionResource(id = R.dimen.padding_large))
                .clickable { },
        )
        Icon(
            painter = painterResource(id = R.drawable.notification),
            contentDescription = null,
            modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.padding_medium))
                .size(dimensionResource(id = R.dimen.padding_large))
                .clickable { },
        )
    }
}