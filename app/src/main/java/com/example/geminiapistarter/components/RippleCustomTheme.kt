package com.example.geminiapistarter.components

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class RippleCustomTheme(private val rippleColor: Color) : RippleTheme {
    @Composable
    override fun defaultColor() =
        RippleTheme.defaultRippleColor(
            rippleColor,
            lightTheme = true
        )

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        pressedAlpha = 0.8f,
        focusedAlpha = 0.8f,
        hoveredAlpha = 0.8f,
        draggedAlpha = 0.8f
    )
}