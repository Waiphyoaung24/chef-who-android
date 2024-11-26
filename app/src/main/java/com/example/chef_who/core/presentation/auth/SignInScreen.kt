package com.example.chef_who.core.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.chef_who.R
import com.example.chef_who.core.domain.models.User
import com.example.chef_who.customer.presentation.detail_screen.BackButtonTopBar

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onTextChange: (String, String) -> Unit,
    user: User,
    onLogin:()->Unit,
    navigateUp: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(62.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButtonTopBar(onBackClick = navigateUp)
            Spacer(modifier = Modifier.weight(0.7f))
            Text(
                text = "Hey there,",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))

        }
        Spacer(Modifier.height(32.dp))
        Text(
            "Welcome back",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(64.dp))
        MyTextField(
            "Email",
            user.email,
            onTextChange = onTextChange,
            imageVector = Icons.Default.Email
        )
        MyPasswordTextField(
            "Password",
            user.password,
            onTextChange,
            imageVector = Icons.Default.Password
        )
        Spacer(Modifier.heightIn(24.dp))
        ButtonComponent(modifier = Modifier, "Sign In", onRegister = onLogin)

    }
}