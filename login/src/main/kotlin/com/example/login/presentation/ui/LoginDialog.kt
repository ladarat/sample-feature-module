package com.example.login.presentation.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.login.data.repository.LoginRepositoryImp
import com.example.login.domain.LoginInfModel
import com.example.login.domain.usecase.LoginUseCaseImpl
import com.example.login.presentation.LoginStateView
import com.example.login.presentation.LoginViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginDialog(
    loginViewModel: LoginViewModel,
    isLogin: Boolean
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
        content =
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                var userText by remember { mutableStateOf("") }
                var passText by remember { mutableStateOf("") }
                TextField(
                    value = userText,
                    onValueChange = { newText ->
                        userText = newText
                    }
                )
                TextField(
                    value = passText,
                    onValueChange = { newText ->
                        passText = newText
                    }
                )

                if (isLogin) {
                    Button(
                        onClick = {
                            loginViewModel.loginByUser(userText, passText)
                        },
                        content = { Text("LOGIN") }
                    )
                } else {
                    Button(
                        onClick = {
                            loginViewModel.retryLogin()
                        },
                        content = { Text("ERROR retry") }
                    )
                }
            }
        }
    )
}

@Composable
fun LoginInfo(
    loginInfModel: LoginInfModel?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "name : ${loginInfModel?.name}")
        Text(text = "count: ${loginInfModel?.count}")
        Text(text = "age: ${loginInfModel?.age}")
    }
}

@Composable
fun LoginContainer(loginViewModel: LoginViewModel) {
    val loginSuccessState = loginViewModel.onLoginSuccess.collectAsState()
    loginSuccessState.value.let { _loginSuccessState ->
        when (_loginSuccessState) {
            is LoginStateView.nonLogin -> {
                LoginDialog(loginViewModel, true)
            }
            is LoginStateView.loading -> {
                CircularProgressIndicator()
            }
            is LoginStateView.loadError -> {
                LoginDialog(
                    loginViewModel,
                    false
                )
            }
            is LoginStateView.loadSuccess -> {
                LoginInfo(_loginSuccessState.loginInfModel)
            }
        }
    }
}
