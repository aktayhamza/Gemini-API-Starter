package com.example.geminiapistarter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.geminiapistarter.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

object MontserratLabelStyle {
    val Regular = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        lineHeight = 22.sp
    )

    val Medium = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        fontSize = 14.sp,
        lineHeight = 22.sp
    )

    val SemiBold = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
        fontWeight = FontWeight(600),
        fontSize = 14.sp,
        lineHeight = 22.sp
    )

    val Bold = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontWeight = FontWeight(700),
        fontSize = 14.sp,
        lineHeight = 22.sp
    )
}

object MontserratTinyStyle {
    val Regular = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
        fontWeight = FontWeight(400),
        fontSize = 12.sp,
        lineHeight = 20.sp
    )

    val Medium = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight(500),
        fontSize = 12.sp,
        lineHeight = 20.sp
    )

    val SemiBold = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
        fontWeight = FontWeight(600),
        fontSize = 12.sp,
        lineHeight = 20.sp
    )

    val Bold = TextStyle(
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontWeight = FontWeight(700),
        fontSize = 12.sp,
        lineHeight = 20.sp
    )
}