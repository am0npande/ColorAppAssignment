package com.example.firebasecolorapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColorCard(
    color: String,
    date: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Padding around the card
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp) // Rounded corners for the card
    ) {
        val bgColor = Color(android.graphics.Color.parseColor(color))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(bgColor)
                .padding(16.dp)
        ) {
            // Top row: Color text with underline
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Column {
                    Text(
                        text = color,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    HorizontalDivider(
                        color = Color.White,
                        thickness = 2.dp,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .width(120.dp)
                    )
                }
            }


            Spacer(modifier = Modifier.height(60.dp)) // Spacer to push content to bottom

            // Bottom row: "CREATED AT" and date
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Created at",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Text(
                        text = date,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun preview(){
    ColorCard("0xFFFF00FF","12/12/2023")
}