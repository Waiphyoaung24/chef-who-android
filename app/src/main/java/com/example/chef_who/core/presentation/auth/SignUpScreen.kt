package com.example.chef_who.core.presentation.auth

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chef_who.R
import com.example.chef_who.core.domain.models.User

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onTextChange: (String, String) -> Unit,
    onRegister: () -> Unit,
    user: User,
    navigateToHomeScreen: () -> Unit,
    navigateToLoginScreen: (Int) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(64.dp))
        Text(
            "Hey there,",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(32.dp))
        Text(
            "Create an Account",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(20.dp))
        MyTextField(
            "First Name",
            user.first_name,
            onTextChange = onTextChange,
            imageVector = Icons.Default.Accessibility
        )
        MyTextField(
            "Last Name",
            user.last_name,
            onTextChange = onTextChange,
            imageVector = Icons.Default.Accessibility
        )
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
        CheckBoxPolicy(modifier = Modifier)
        Spacer(Modifier.heightIn(12.dp))
        ButtonComponent(modifier = Modifier, "Sign Up", onRegister = onRegister)
        Spacer(modifier = Modifier.height(12.dp))
        RedirectLoginText(
            modifier = Modifier,
            "Already Registered? Click here to Login",
            onClick = navigateToLoginScreen
        )
    }
}


@Composable
fun MyTextField(
    labelValue: String,
    value: String,
    onTextChange: (String, String) -> Unit,
    imageVector: ImageVector
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(4.dp)),
        value = value,
        onValueChange = {
            onTextChange(it, labelValue)
        },
        textStyle = MaterialTheme.typography.labelMedium,
        leadingIcon = { Icon(imageVector = imageVector, contentDescription = "") },
        label = { Text(text = labelValue, style = MaterialTheme.typography.labelMedium) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.background
        ),
        keyboardOptions = KeyboardOptions.Default,
    )
}

@Composable
fun MyPasswordTextField(
    labelValue: String,
    value: String,
    onTextChange: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector
) {

    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(4.dp)),
        value = value,
        onValueChange = { onTextChange(it, labelValue) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Password,
                contentDescription = ""
            )
        },
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.background
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            var desc = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show password"
            }
            IconButton(onClick = { passwordVisible.value != passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = "")
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),

        )
}

@Composable
fun CheckBoxPolicy(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(top = 24.dp, start = 14.dp, end = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checked = remember { mutableStateOf<Boolean>(false) }
        Checkbox(colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary
        ), checked = checked.value, onCheckedChange =
        {
            checked.value = it
        })
        ClickableTexComponent(
            modifier = Modifier,
            "By continuing you accept our Privacy Policy and Terms and Conditions"
        )
    }
}

@Composable
fun ClickableTexComponent(modifier: Modifier = Modifier, value: String) {
    val initialText = "By continuing you accept our"
    val privacyPolicyText = " Privacy Policy"
    val andText = " and"
    val termsAndCondition = " Terms and Conditions"
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)

        }
        append(andText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = termsAndCondition, annotation = termsAndCondition)
            append(termsAndCondition)
        }
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
            Log.d("test", "{$span}")
        }
    })

}

@Composable
fun RedirectLoginText(modifier: Modifier = Modifier, value: String, onClick: (Int) -> Unit) {
    val initialText = "Already Registered? Click"
    val privacyPolicyText = " here "
    val andText = "to Login"
    val termsAndCondition = ""
    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)

        }
        append(andText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = termsAndCondition, annotation = termsAndCondition)
            append(termsAndCondition)
        }
    }
    ClickableText(text = annotatedString, onClick = onClick)

}

@Composable
fun ButtonComponent(modifier: Modifier = Modifier, value: String, onRegister: () -> Unit) {
    Button(
        onClick = onRegister,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .padding(top = 12.dp, start = 14.dp, end = 14.dp),

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        ),
                    ), shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = value, style = MaterialTheme.typography.titleSmall, color = Color.White)
        }
    }
}

