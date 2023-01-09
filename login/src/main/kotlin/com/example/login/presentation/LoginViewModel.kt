package com.example.login.presentation

import androidx.lifecycle.ViewModel
import com.example.login.domain.LoginInfModel
import com.example.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private var _onLoginSuccess: MutableStateFlow<LoginStateView> =
        MutableStateFlow(LoginStateView.nonLogin)
    var onLoginSuccess: StateFlow<LoginStateView> = _onLoginSuccess


    fun loginByUser(user: String, pass: String) {
        CoroutineScope(Dispatchers.IO).launch {
            loginUseCase.execute(user, pass)
                .onStart {
                    _onLoginSuccess.value = LoginStateView.loading
                }
                .catch {
                    _onLoginSuccess.value = LoginStateView.loadError(it)
                }
                .collect { loginSuccess ->
                    loginSuccess.age?.let {
                        _onLoginSuccess.value = LoginStateView.loadSuccess(loginSuccess)
                    }
                }
        }
    }
    fun retryLogin(){
        _onLoginSuccess.value = LoginStateView.nonLogin
    }
}

sealed class LoginStateView {
    object nonLogin: LoginStateView()
    object loading : LoginStateView()
    data class loadError(val err: Throwable?) : LoginStateView()
    data class loadSuccess(val loginInfModel: LoginInfModel?) : LoginStateView()
}