package com.example.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginDialog(
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
        content =
        {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
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

                Button(
                    onClick = {

                    },
                    content = { Text("LOGIN") }
                )
            }
        }
    )
}
