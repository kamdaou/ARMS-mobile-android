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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.amalitech.arms_mobile.R


@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    val scrollState: ScrollState = rememberScrollState()

    Box(
        modifier = modifier
            .background(color = Color(0xffFAFAFA))
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
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
                Image(
                    painter = painterResource(id = R.drawable.avatar_photo_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.padding_extra_large_2))
                        .clickable { },
                )
                Spacer(
                    modifier = Modifier.weight(1F)
                )
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
            Text(
                text = "Good day, Precious 😊",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_small)
                    )
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
            )
            Text(
                text = "Here's what's going on today.",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(id = R.dimen.padding_small)
                    )
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_large))
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            HorizontalListBuilder(
                title = "Who's out",
                items = listOf("Babita Sagoe", "Grace Nalon", "Bruce Marvin", "Precious Ainoo")
            ) { i, item ->
                Column(
                    modifier = Modifier
                        .padding(all = dimensionResource(id = R.dimen.padding_small))
                        .width(160.dp)
                ) {
                    AsyncImage(
                        model = if (i % 2 == 1) "https://ca.slack-edge.com/T017QJT2H7G-U04BD8S7XH7-e72d73d981a8-512" else "https://ca.slack-edge.com/T017QJT2H7G-U064KN73751-f66d78ac27f9-512",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .height(120.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_small),
                                end = dimensionResource(id = R.dimen.padding_small).times(0.5F),
                            )
                            .clip(CircleShape)
                            .border(width = 1.dp, color = Color(0xffE5EAEF), shape = CircleShape)
                            .background(color = Color(0xffF5F7F9))
                    ) {
                        Text(
                            text = "National Service Personnel",
                            style = MaterialTheme.typography.labelSmall.copy(lineHeight = 0.sp),
                            modifier = Modifier.padding(
                                horizontal = dimensionResource(id = R.dimen.padding_small),
                                vertical = dimensionResource(id = R.dimen.padding_small).div(2),
                            )
                        )
                    }
                    Text(
                        text = item,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(
                            vertical = dimensionResource(id = R.dimen.padding_small).div(2),
                        ),
                    )
                    Text(
                        text = "Employee",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            HorizontalListBuilder(
                title = "Celebrations",
                items = listOf("Babita Sagoe", "Grace Nalon", "Bruce Marvin", "Precious Ainoo")
            ) { i, item ->
                Column(
                    modifier = Modifier
                        .padding(all = dimensionResource(id = R.dimen.padding_small))
                        .width(160.dp)
                ) {
                    AsyncImage(
                        model = if (i % 2 == 1) "https://ca.slack-edge.com/T017QJT2H7G-U065V6WE02D-ff090dd41aa1-512" else "https://ca.slack-edge.com/T017QJT2H7G-U064C1K04PP-eb3eccd5ebcc-72",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .height(120.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    )
                    Text(
                        text = item,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(
                            vertical = dimensionResource(id = R.dimen.padding_small).div(2),
                        ),
                    )
                    Text(
                        text = "Employee",
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Text(
                        modifier = Modifier.padding(
                            vertical = dimensionResource(id = R.dimen.padding_small).div(2)
                        ),
                        text = if (i % 2 == 0) "Happy Birthday" else "1st Anniversary",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    )
                    Text(
                        text = "Jan 24",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}