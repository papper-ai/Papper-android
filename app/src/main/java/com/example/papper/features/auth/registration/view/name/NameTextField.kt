//package com.example.papper.features.auth.registration.view.name
//
//import android.annotation.SuppressLint
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import com.example.papper.R
//import com.example.papper.features.auth.registration.presentation.RegistrationViewModel
//import com.example.papper.features.common.components.WithoutlinedTextFieldComponent
//import org.orbitmvi.orbit.compose.collectAsState
//
//@SuppressLint("StateFlowValueCalledInComposition")
//@Composable
//fun NameTextField(
//    modifier: Modifier = Modifier,
//    viewModel: RegistrationViewModel,
//) {
//    val name = viewModel.collectAsState().value.name
//    var textFieldState by remember {
//        mutableStateOf(name)
//    }
//
//    WithoutlinedTextFieldComponent(
//        value = textFieldState,
//        onValueChange = { newName ->
//            viewModel.updateName(newName)
//            textFieldState = newName
//        },
//        modifier = modifier,
//        placeholder = stringResource(id = R.string.fill_name),
//        singleLine = true
//    )
//}