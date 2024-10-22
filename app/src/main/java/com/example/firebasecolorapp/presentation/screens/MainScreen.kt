package com.example.firebasecolorapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firebasecolorapp.presentation.components.ColorCard
import com.example.firebasecolorapp.presentation.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {
    val colors by viewModel.colorList.collectAsState()
    val syncNumber by viewModel.syncNumber.collectAsState()
    val enableButton by viewModel.enableButton.collectAsState()
    val gridState = remember { LazyGridState() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Color App") },
                modifier = modifier,
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                actions = {
                    ElevatedButton(
                        onClick = { viewModel.uploadColorsToFirebase() },
                        shape = RoundedCornerShape(16.dp),
                        enabled = enableButton
                    ) {
                        Text(text = "$syncNumber", color = Color.White, fontSize = 16.sp)
                        Spacer(Modifier.size(4.dp))
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Sync Colors"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.insertColor()
                }
            ) {
                Row(modifier.padding(16.dp)) {
                    Text("Add Color")
                    Spacer(Modifier.size(4.dp))
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add Color")
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (colors.isEmpty()) {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No colors found")
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    state = gridState,
                    contentPadding = PaddingValues(4.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(colors.size) { index ->
                        ColorCard(color = colors[index].color, date = colors[index].date)
                    }
                }


                LaunchedEffect(colors) {
                    if (colors.isNotEmpty()) {
                        gridState.scrollToItem(colors.size - 1)
                    }
                }
            }
        }
    }
}
