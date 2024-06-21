package com.example.papper.features.storage.create_file.view.confirm_creating

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.OutlinedTextFieldComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel

@Composable
fun ConfirmTextField(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
    convertedText: String,
) {
    var textFieldState by remember {
        mutableStateOf(convertedText)
    }

    Log.e("TAG", "ConfirmTextField: ${textFieldState}")

    OutlinedTextFieldComponent(
        modifier = modifier,
        onValueChange = { newTitle ->
            viewModel.updateTitle(newTitle)
            textFieldState = newTitle
        },
        value = textFieldState,
        placeholder = stringResource(id = R.string.converted_text),
        //label = stringResource(id = R.string.converted_text),
        singleLine = false,
    )
}