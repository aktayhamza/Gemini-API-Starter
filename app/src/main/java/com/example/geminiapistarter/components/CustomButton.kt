package com.example.geminiapistarter.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.geminiapistarter.ui.theme.theme.Gray10
import com.example.geminiapistarter.ui.theme.theme.Gray30
import com.example.geminiapistarter.ui.theme.theme.Gray50
import com.example.geminiapistarter.ui.theme.theme.NavyBlue80
import com.example.geminiapistarter.ui.theme.theme.NavyBlue90

object GeminiButtonType {

    @Composable
    fun geminiButtonColor(
        containerColor: Color = NavyBlue80,
        contentColor: Color = White,
        disabledContainerColor: Color = Gray30,
        disabledContentColor: Color = Gray50,
    ) : ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    private fun geminiButtonElevation(
        defaultElevation: Dp = GeminiButtonElevation.DefaultElevation,
        pressedElevation: Dp = GeminiButtonElevation.PressedElevation,
        focusedElevation: Dp = GeminiButtonElevation.FocusedElevation,
        hoveredElevation: Dp = GeminiButtonElevation.HoveredElevation,
        disabledElevation: Dp = GeminiButtonElevation.DisabledElevation,
    ): ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        hoveredElevation = hoveredElevation,
        disabledElevation = disabledElevation,
    )

    object GeminiButtonElevation {
        val DefaultElevation = 0.dp
        val PressedElevation = 0.dp
        val FocusedElevation = 0.dp
        val HoveredElevation = 0.dp
        val DisabledElevation = 0.dp
    }

    object GeminiButtonHeightSizeCategory {
        val StandardButtonHeight: Dp = 48.dp
        val LargeButtonHeight: Dp = 54.dp
    }

    object GeminiButtonWidthSizeCategory {
        val StandardButtonWidth: Dp = 350.dp
    }

    object GeminiButtonBorderWidthCategory {
        val StandardButtonBorderWidth: Dp = 2.dp
    }

    object GeminiButtonShapeCategory {
        val SmallShape = RoundedCornerShape(4.dp)
        val StandardButtonShape = RoundedCornerShape(10.dp)
        val LargeButtonShape = RoundedCornerShape(22.dp)
    }

    data class GeminiButtonSize(
        val height: Dp = GeminiButtonHeightSizeCategory.StandardButtonHeight,
        val width: Dp = GeminiButtonWidthSizeCategory.StandardButtonWidth
    )

    data class GeminiButtonBorderStroke(
        val width: Dp = GeminiButtonBorderWidthCategory.StandardButtonBorderWidth,
        val color: Color = Gray10
    )

    @Composable
    fun GeminiButton(
        modifier: Modifier = Modifier,
        size: GeminiButtonSize = GeminiButtonSize(),
        enabled: Boolean = true,
        text: @Composable (() -> Unit)? = null,
        rightIcon: @Composable (() -> Unit)? = null,
        leftIcon: @Composable (() -> Unit)? = null,
        horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
        shape: Shape = GeminiButtonShapeCategory.StandardButtonShape,
        colors: ButtonColors = geminiButtonColor(),
        elevation: ButtonElevation? = geminiButtonElevation(),
        border: GeminiButtonBorderStroke? = null,
        rippleColor: Color = NavyBlue90,
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        onClick: () -> Unit
    ) {
        var privateModifier: Modifier = Modifier
        if (size.width > 0.dp) privateModifier = privateModifier.width(size.width)
        if (size.height > 0.dp) privateModifier = privateModifier.height(size.height)
        val rowModifier =
            if (horizontalArrangement == Arrangement.Center) Modifier else Modifier.fillMaxWidth()
        CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme(rippleColor)) {
            Button(
                onClick = onClick,
                modifier = privateModifier
                    .then(modifier),
                colors = colors,
                elevation = elevation,
                enabled = enabled,
                shape = shape,
                border = border?.let { BorderStroke(border.width, border.color) },
                contentPadding = contentPadding,
                interactionSource = interactionSource,
            ) {
                Row(
                    horizontalArrangement = horizontalArrangement,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = rowModifier
                ) {
                    leftIcon?.let {
                        it()
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    }
                    text?.let {
                        it()
                    }
                    rightIcon?.let {
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                        it()
                    }
                }
            }
        }
    }
}