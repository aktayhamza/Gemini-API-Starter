package com.example.geminiapistarter.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.geminiapistarter.components.CustomTexFieldType.CustomTextFieldBorderWidthCategory.StandardTextFieldBorderWidth
import com.example.geminiapistarter.components.CustomTexFieldType.CustomTextFieldShapeCategory.StandardTextFieldShape
import com.example.geminiapistarter.components.CustomTexFieldType.CustomTextFieldWidthSizeCategory.StandardTextFieldWidth
import com.example.geminiapistarter.ui.theme.MontserratLabelStyle
import com.example.geminiapistarter.ui.theme.MontserratTinyStyle
import com.example.geminiapistarter.ui.theme.theme.Gray10
import com.example.geminiapistarter.ui.theme.theme.Gray100
import com.example.geminiapistarter.ui.theme.theme.Gray30
import com.example.geminiapistarter.ui.theme.theme.Gray50
import com.example.geminiapistarter.ui.theme.theme.Gray90

object CustomTexFieldType {
    @Composable
    fun customTextFieldColor(
        focusedTextColor: Color = Gray100,
        unfocusedTextColor: Color = Gray90,
        focusedPlaceholderColor: Color = Gray50,
        unfocusedPlaceholderColor: Color = Gray50,
        selectionColors: TextSelectionColors = TextSelectionColors(
            handleColor = focusedTextColor,
            backgroundColor = focusedTextColor.copy(0.5f)
        ),
        disabledContainerColor: Color = Gray10,
        errorContainerColor: Color = Gray10,
        focusedBorderColor: Color = Gray50,
        unfocusedBorderColor: Color = Gray30,
    ): TextFieldColors = OutlinedTextFieldDefaults.colors(
        // Text
        focusedTextColor = focusedTextColor,
        unfocusedTextColor = focusedTextColor,
        disabledTextColor = focusedTextColor,
        errorTextColor = focusedTextColor,
        // Selection
        selectionColors = selectionColors,
        // Supporting
        focusedSupportingTextColor = focusedTextColor,
        unfocusedSupportingTextColor = focusedTextColor,
        disabledSupportingTextColor = focusedTextColor,
        errorSupportingTextColor = focusedTextColor,
        // Container
        disabledContainerColor = disabledContainerColor,
        errorContainerColor = errorContainerColor,
        // Indicator
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        // LeadingIcon
        focusedLeadingIconColor = focusedTextColor,
        unfocusedLeadingIconColor = unfocusedTextColor,
        disabledLeadingIconColor = focusedTextColor,
        errorLeadingIconColor = focusedTextColor,
        // TrailingIcon
        focusedTrailingIconColor = focusedTextColor,
        unfocusedTrailingIconColor = unfocusedTextColor,
        disabledTrailingIconColor = focusedTextColor,
        errorTrailingIconColor = focusedTextColor,
        // Label
        focusedLabelColor = focusedTextColor,
        unfocusedLabelColor = focusedTextColor,
        disabledLabelColor = focusedTextColor,
        errorLabelColor = focusedTextColor,
        // Prefix
        focusedPrefixColor = focusedTextColor,
        unfocusedPrefixColor = focusedTextColor,
        disabledPrefixColor = focusedTextColor,
        errorPrefixColor = focusedTextColor,
        // Suffix
        focusedSuffixColor = focusedTextColor,
        unfocusedSuffixColor = focusedTextColor,
        disabledSuffixColor = focusedTextColor,
        errorSuffixColor = focusedTextColor
    )

    object CustomTextFieldWidthSizeCategory {
        val StandardTextFieldWidth: Dp = 350.dp
    }

    object CustomTextFieldBorderWidthCategory {
        val StandardTextFieldBorderWidth: Dp = 1.dp
    }

    object CustomTextFieldShapeCategory {
        val StandardTextFieldShape = RoundedCornerShape(10.dp)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SATextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        titleLabel: String? = null,
        helpText: String? = null,
        enabled: Boolean = true,
        readOnly: Boolean = false,
        textStyle: TextStyle = MontserratLabelStyle.Regular.copy(
            color = Gray90
        ),
        label: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        supportingText: @Composable (() -> Unit)? = null,
        prefix: @Composable (() -> Unit)? = null,
        suffix: @Composable (() -> Unit)? = null,
        isError: Boolean = false,
        width: Dp = StandardTextFieldWidth,
        height: Dp = 48.dp,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        singleLine: Boolean = true,
        maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
        minLines: Int = 1,
        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        shape: Shape = StandardTextFieldShape,
        colors: TextFieldColors = customTextFieldColor()
    ) {
        val privateModifier = if (height > 0.dp) Modifier.height(height) else Modifier
        titleLabel?.let {
            Text(
                text = titleLabel,
                style = MontserratLabelStyle.Regular.copy(
                    color = Gray90
                ),
                modifier = Modifier.width(width)
            )

            Spacer(modifier = Modifier.padding(4.dp))
        }
        var helpTextHeight by remember {
            mutableStateOf(12.dp)
        }
        val localDensity = LocalDensity.current

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            minLines = minLines,
            textStyle = textStyle,
            singleLine = singleLine,
            readOnly = readOnly,
            enabled = enabled,
            interactionSource = interactionSource,
            modifier = Modifier
                .semantics(mergeDescendants = true) {}
                .then(privateModifier)
                .width(width)
                .then(modifier),
            decorationBox = @Composable { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = value,
                    visualTransformation = visualTransformation,
                    innerTextField = innerTextField,
                    placeholder = placeholder,
                    label = label,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    prefix = prefix,
                    suffix = suffix,
                    singleLine = singleLine,
                    enabled = enabled,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        end = 12.dp,
                        top = 12.dp,
                        bottom = helpTextHeight
                    )
                ) {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled,
                        isError,
                        interactionSource,
                        colors,
                        shape,
                        focusedBorderThickness = StandardTextFieldBorderWidth,
                        unfocusedBorderThickness = StandardTextFieldBorderWidth
                    )
                    if (height > 84.dp && helpText != null) {
                        Box(
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Text(
                                text = helpText,
                                style = MontserratTinyStyle.Regular.copy(
                                    color = Gray50
                                ),
                                onTextLayout = {
                                    helpTextHeight = 16.dp + maxOf(
                                        with(localDensity) { it.size.height.toDp() },
                                        12.dp
                                    )
                                },
                                modifier = Modifier
                                    .padding(bottom = 16.dp, end = 14.dp)
                                    .align(Alignment.BottomEnd)
                            )
                        }
                    }
                }
            }
        )
        if (supportingText != null) {
            supportingText()
        }
    }
}