package com.example.chef_who.customer.presentation.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chef_who.R


@Composable
fun UserProfile(
    onLogout: () -> Unit,
    onBackBtnClick: () -> Unit,
    onClickSeller: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Text("Profile", style = MaterialTheme.typography.labelMedium.copy(fontSize = 24.sp))


        Spacer(Modifier.height(24.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp) // Adjust size as needed
                    .clip(CircleShape) // Clip the image into a circle
                    .border(2.dp, Color.White, CircleShape) // Add border
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_profile), // Replace with your image resource
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize() // Ensure the image fills the circle
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {

                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colorScheme.primary
            )
            Text(
                "Profile Picture",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(0.2f)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = onClickSeller)
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colorScheme.primary
            )
            Text(
                "To become A seller",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(0.2f)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colorScheme.primary
            )
        }


        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {

                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colorScheme.primary
            )
            Text(
                "Settings",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(0.2f)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colorScheme.primary
            )
        }


        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {

                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.question_mark),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colorScheme.primary
            )
            Text(
                "Help Center",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(0.2f)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable(onClick = onLogout)
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.log_out),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colorScheme.primary
            )
            Text(
                "Logout",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.weight(0.2f)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}

@Composable
fun DefaultBackArrow(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Arrow Back",
            modifier = Modifier.size(20.dp)
        )
    }
}