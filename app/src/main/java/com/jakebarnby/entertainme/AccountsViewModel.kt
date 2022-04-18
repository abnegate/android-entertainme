package com.jakebarnby.entertainme

import androidx.lifecycle.ViewModel
import com.jakebarnby.entertainme.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AccountsViewModel : ViewModel() {
    private val _accounts = MutableStateFlow(emptyList<Account>())
    val accounts = _accounts.asStateFlow()

    fun getConnectedAccounts() {
    }
}