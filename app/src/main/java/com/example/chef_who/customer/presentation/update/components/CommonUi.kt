package com.example.chef_who.customer.presentation.update.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chef_who.R

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowError(message: String, onRetry: () -> Unit) {
    Column {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = message,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onRetry
        ) {
            Text(stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun ShowVerticalDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        color = Color.LightGray.copy(alpha = 0.2f)
    )
}

@Preview
@Composable
private fun ShowErrorPreview() {
    ShowError(
        message = "Error Message\nSomething went wrong!",
        onRetry = { })
}
