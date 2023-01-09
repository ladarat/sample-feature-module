package com.example.sample_feature_module

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.login.data.repository.LoginRepositoryImp
import com.example.login.data.service.LoginService
import com.example.login.domain.usecase.LoginUseCase
import com.example.login.domain.usecase.LoginUseCaseImpl
import com.example.login.presentation.LoginViewModel
import com.example.login.presentation.ui.LoginContainer
import com.example.login.presentation.ui.LoginDialog
import com.example.sample_feature_module.ui.theme.SamplefeaturemoduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SamplefeaturemoduleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginContainer(
                        LoginViewModel(
                            LoginUseCaseImpl(LoginRepositoryImp())
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SamplefeaturemoduleTheme {
        Greeting("Android")
    }
}