package com.amalitech.home.home

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.amalitech.domain.R
import com.amalitech.home.home.sections.CelebrationSection
import com.amalitech.home.home.sections.LeaveSection
import kotlinx.coroutines.launch


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    expandCelebrations: () -> Unit,
    expandWhoIsOut: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState: ScrollState = rememberScrollState()
    val photo by viewModel.photo.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val leaveState by viewModel.leaveState.collectAsStateWithLifecycle()
    val celebrationState by viewModel.celebrationState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.background(color = Color(0xffFAFAFA))
    ) {
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            AppBar(
                image = photo
            )
            Text(
                text = "Good day, $email 😊",
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
            LeaveSection(
                leaveState,
                onReload = {
                    viewModel.getLeaveData()
                },
                navigateToView = expandWhoIsOut,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CelebrationSection(
                celebrationState,
                onReload = {
                    viewModel.viewModelScope.launch {
                        viewModel.getCelebrationData()
                    }
                },
                navigateToView = expandCelebrations,
            )
        }
    }
}


@Composable
fun AppBar(
    image: String?
) {
    Row(
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.padding_large),
                bottom = dimensionResource(id = R.dimen.padding_small),
            )
            .padding(horizontal = dimensionResource(id = R.dimen.padding_large)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (image != null) {
            val byteArray = Base64.decode(image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.padding_extra_large_2))
                    .clip(CircleShape)
                    .clickable { },
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.avatar_photo_placeholder),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.padding_extra_large_2))
                    .clickable { },
            )
        }
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
