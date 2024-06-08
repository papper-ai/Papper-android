package com.example.papper.features.storage.create_file.view.typing_title

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.papper.R
import com.example.papper.features.common.components.WithoutlinedTextFieldComponent
import com.example.papper.features.storage.create_file.presentation.CreateFileViewModel
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun TitleTextField(
    modifier: Modifier = Modifier,
    viewModel: CreateFileViewModel,
) {
    val title = viewModel.collectAsState().value.title
    var textFieldState by remember {
        mutableStateOf(title)
    }

    WithoutlinedTextFieldComponent(
        value = textFieldState,
        onValueChange = { newTitle ->
            viewModel.updateTitle(newTitle)
            textFieldState = newTitle
        },
        modifier = modifier,
        placeholder = stringResource(id = R.string.title),
        singleLine = true
    )
}