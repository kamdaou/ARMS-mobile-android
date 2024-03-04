package com.amalitech.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amalitech.domain.R


// Font Family
val Satoshi = FontFamily(
    Font(R.font.satoshi_regular),
    Font(R.font.satoshi_bold, FontWeight.Bold),
    Font(R.font.satoshi_black, FontWeight.Black),
    Font(R.font.satoshi_medium, FontWeight.Medium),
    Font(R.font.satoshi_medium, FontWeight.SemiBold),
)
val SFProDisplay = FontFamily(
    Font(R.font.sfprodisplay_regular),
    Font(R.font.sfprodisplay_bold, FontWeight.Bold),
    Font(R.font.sfprodisplay_medium, FontWeight.Medium),
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = Satoshi,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.5.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = Satoshi,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Satoshi,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Satoshi,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = SFProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = Color(0xff1D1D1D)
    ),
    labelSmall = TextStyle(
        fontFamily = Satoshi,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        lineHeight = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = Satoshi,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        color = Color(0xff8F95B2)
    ),
)