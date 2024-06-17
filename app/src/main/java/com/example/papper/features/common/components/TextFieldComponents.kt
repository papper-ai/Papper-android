package com.example.papper.features.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.papper.R
import com.example.papper.theme.Buttons
import com.example.papper.theme.TypingText
import com.example.papper.theme.TypingText2
import com.example.papper.theme.dimens

@Composable
fun OutlinedTextFieldComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    label: String? = null,
    singleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    isEnable: Boolean = true,
    isError: Boolean = false,
) {
    OutlinedTextField(
        modifier = modifier
            .height(MaterialTheme.dimens.buttonsHeight)
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen
            ),
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        keyboardOptions = KeyboardOptions(
            capitalization = keyboardCapitalization,
            autoCorrect = true,
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        textStyle = MaterialTheme.typography.TypingText,
        singleLine = singleLine,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.TypingText,
            )
        },
//        label = {
//            if (label != "") {
//                Text(
//                    modifier = Modifier
//                        .background(color = MaterialTheme.colorScheme.primary),
//                    text = label.orEmpty(),
//                    style = MaterialTheme.typography.Buttons,
//                    color = MaterialTheme.colorScheme.onPrimary
//                )
//            }
//        },
        enabled = isEnable,
        isError = isError,
        shape = RoundedCornerShape(MaterialTheme.dimens.textFieldCornerRadius),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = Color.Transparent,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.onPrimary,
            disabledBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

@Composable
fun OutlinedPasswordTextFieldComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    singleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    isEnable: Boolean = true,
    isError: Boolean = false,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .height(MaterialTheme.dimens.buttonsHeight)
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen
            ),
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        keyboardOptions = KeyboardOptions(
            capitalization = keyboardCapitalization,
            autoCorrect = true,
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.TypingText,
        singleLine = singleLine,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.TypingText,
            )
        },
        enabled = isEnable,
        isError = isError,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    painter = if (passwordVisible) painterResource(id = R.drawable.baseline_visibility_off_24_icon) else painterResource(id = R.drawable.round_visibility_24_icon),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        },
        shape = RoundedCornerShape(MaterialTheme.dimens.textFieldCornerRadius),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = Color.Transparent,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorTextColor = MaterialTheme.colorScheme.onPrimary,
            disabledBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

@Composable
fun WithoutlinedTextFieldComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    singleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)
            },
            keyboardOptions = KeyboardOptions(
                capitalization = keyboardCapitalization,
                autoCorrect = true,
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(textAlign = TextAlign.Center) + MaterialTheme.typography.TypingText2,
            singleLine = singleLine,
            placeholder = {
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = placeholder,
                    style = MaterialTheme.typography.TypingText2,
                    textAlign = TextAlign.Center,
                )
            },
            shape = RoundedCornerShape(MaterialTheme.dimens.textFieldCornerRadius),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
        )
    }
}

@Composable
fun WithoutlinedPasswordTextFieldComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    singleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    isVisible: Boolean
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)
            },
            keyboardOptions = KeyboardOptions(
                capitalization = keyboardCapitalization,
                autoCorrect = true,
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(textAlign = TextAlign.Center) + MaterialTheme.typography.TypingText2,
            singleLine = singleLine,
            placeholder = {
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = placeholder,
                    style = MaterialTheme.typography.TypingText2,
                    textAlign = TextAlign.Center,
                )
            },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(MaterialTheme.dimens.textFieldCornerRadius),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
        )
    }
}

@Composable
fun ChatOutlinedTextFieldComponent(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String,
    singleLine: Boolean,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    keyboardType: KeyboardType = KeyboardType.Text,
    isEnable: Boolean = true,
    onClick: () -> Unit,
    isBtnEnable: Boolean,
    isSendingMsg: Boolean,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                min = MaterialTheme.dimens.buttonsHeight,
                max = MaterialTheme.dimens.buttonsHeight * 4
            )
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.dimens.gapBetweenComponentScreen,
                end = MaterialTheme.dimens.gapBetweenComponentScreen,
            )
            .clipToBounds(),
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        keyboardOptions = KeyboardOptions(
            capitalization = keyboardCapitalization,
            autoCorrect = true,
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        textStyle = MaterialTheme.typography.TypingText,
        singleLine = singleLine,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.TypingText,
            )
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier
                    .padding(MaterialTheme.dimens.bottomGap)
                    .size(MaterialTheme.dimens.sendBtnSize),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                onClick = { onClick() },
                enabled = isBtnEnable,
                content = {
                    if (isSendingMsg) {
                        CircularProgressIndicator(
                            modifier = modifier
                                .size(15.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                    }
                    else {
                        Icon(
                            painter = painterResource(id = R.drawable.send_icon),
                            contentDescription = "send",
                        )
                    }
                }
            )
        },
        enabled = isEnable,
        shape = RoundedCornerShape(MaterialTheme.dimens.cardCornerRadius),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = Color.Transparent,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            disabledBorderColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )

//    val text = remember { mutableStateOf("") }
//    val interactionSource = remember { MutableInteractionSource() }
//
//    BasicTextField(
//        value = value,
//        onValueChange = { newValue ->
//            onValueChange(newValue)
//        },
//        modifier = Modifier
//            .padding(
//                top = 0.dp,
//                bottom = 0.dp,
//                start = MaterialTheme.dimens.gapBetweenComponentScreen,
//                end = MaterialTheme.dimens.gapBetweenComponentScreen,
//            )
//            .fillMaxWidth()
//            .heightIn(
//                min = MaterialTheme.dimens.buttonsHeight,
//                max = MaterialTheme.dimens.buttonsHeight * 4
//            )
//            .wrapContentHeight()
//            .border(
//                1.dp,
//                color = if (isBtnEnable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
//                shape = RoundedCornerShape(MaterialTheme.dimens.cardCornerRadius)
//            )
//            .clip(RoundedCornerShape(MaterialTheme.dimens.cardCornerRadius)),
//
//        keyboardOptions = KeyboardOptions(
//            capitalization = KeyboardCapitalization.None,
//            autoCorrect = true,
//            keyboardType = keyboardType,
//            imeAction = ImeAction.Next
//        ),
//        textStyle = MaterialTheme.typography.TypingText,
//        singleLine = singleLine,
//        interactionSource = interactionSource,
//        decorationBox = { innerTextField ->
//            Column(
//                modifier = Modifier
//                    .verticalScroll(rememberScrollState())
//            ) {
//                innerTextField()
//            }
//        }
//    )
}