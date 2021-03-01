/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun SplashScreen(navigateToHome: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                Fab(
                    navigateToHome
                )
            },
            content = {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    HeaderImage()
                    TextDetails()
                }
            }
        )
    }
}

@Composable
private fun HeaderImage() {
    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        CoilImage(
            data = "https://www.wallpaperup.com/uploads/wallpapers/2016/07/16/996380/58f70e3441b33122f78dadeaea6e0f52-700.jpg",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            fadeIn = true
        )
    }
}

@Composable
private fun TextDetails() {
    Text(
        text = "Hello",
        style = MaterialTheme.typography.h1
    )
    Text(
        text = "Human",
        style = MaterialTheme.typography.h1
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "You've decided to adopt a pet? We help you find your new friend.",
        style = MaterialTheme.typography.h6
    )
}

@Composable
private fun Fab(navigateToHome: () -> Unit) {
    ExtendedFloatingActionButton(
        icon = { Icon(Icons.Filled.Pets, "") },
        text = { Text("Let's Adopt") },
        onClick = {
            navigateToHome.invoke()
        },
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    )
}
