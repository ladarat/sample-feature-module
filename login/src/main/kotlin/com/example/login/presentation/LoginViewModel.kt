package com.example.login.presentation

import androidx.lifecycle.ViewModel
import com.example.login.domain.LoginInfModel
import com.example.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private var _onLoginSuccess: MutableStateFlow<LoginStateView> =
        MutableStateFlow(LoginStateView.NonLogin)
    var onLoginSuccess: StateFlow<LoginStateView> = _onLoginSuccess


    fun loginByUser(user: String, pass: String) {
        CoroutineScope(Dispatchers.IO).launch {
            loginUseCase.execute(user, pass)
                .onStart {
                    _onLoginSuccess.value = LoginStateView.Loading
                }
                .catch {
                    _onLoginSuccess.value = LoginStateView.LoadError(it)
                }
                .collect { loginSuccess ->
                    loginSuccess.age?.let {
                        _onLoginSuccess.value = LoginStateView.LoadSuccess(loginSuccess)
                    } ?: run {
                        _onLoginSuccess.value = LoginStateView.LoadError(Throwable())
                    }
                }
        }
    }

    fun retryLogin() {
        _onLoginSuccess.value = LoginStateView.NonLogin
    }
}

sealed class LoginStateView {
    object NonLogin : LoginStateView()
    object Loading : LoginStateView()
    data class LoadError(val err: Throwable?) : LoginStateView()
    data class LoadSuccess(val loginInfModel: LoginInfModel?) : LoginStateView()
}