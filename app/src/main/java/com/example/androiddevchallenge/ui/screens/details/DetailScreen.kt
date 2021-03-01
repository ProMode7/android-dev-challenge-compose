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
package com.example.androiddevchallenge.ui.screens.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.data.Pet
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DetailsScreen(
    puppy: Pet,
    navigateBack: () -> Unit
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(ScrollState(0))
    ) {
        Column(Modifier.fillMaxWidth()) {
            setPicAndButtons(puppy = puppy, navigateBack)
            setPetInfo(puppy = puppy)
        }
    }
}

@androidx.compose.runtime.Composable
private fun setPicAndButtons(puppy: Pet, navigateBack: () -> kotlin.Unit) {
    ConstraintLayout {
        val (image, buttonRow) = createRefs()
        CoilImage(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(
                    Color.LightGray
                ),
            data = puppy.image.url,
            contentDescription = puppy.name,
            contentScale = ContentScale.Crop,
            fadeIn = true,
        )
        Row(
            modifier = Modifier
                .constrainAs(buttonRow) {
                    top.linkTo(image.bottom)
                    bottom.linkTo(image.bottom)
                    end.linkTo(image.end)
                }
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        horizontal = 10.dp
                    ),
                icon = { Icon(Icons.Filled.Home, "") },
                text = { Text("Adopt Me") },
                onClick = {
                    navigateBack
                },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            )
            /*  ExtendedFloatingActionButton(
                  modifier = androidx.compose.ui.Modifier.weight(1f).padding(
                      horizontal = 5.dp
                  ), text = { Text("Favorite") },
                  onClick = {
                      navigateBack
                  },
                  elevation = FloatingActionButtonDefaults.elevation(8.dp)
              )*/
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        horizontal = 10.dp
                    ),
                icon = { Icon(androidx.compose.material.icons.Icons.Filled.Share, "") },
                text = { Text("Share") },
                onClick = {
                    navigateBack
                },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            )
        }
        IconButton(
            modifier = Modifier.padding(10.dp),
            onClick = navigateBack
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }
    }
}

@Composable
private fun setPetInfo(puppy: Pet) {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = "Hello, My name is",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = puppy.name,
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "and I am a ${puppy.breed}",
            style = MaterialTheme.typography.h6
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            setPetDetailItem(value = puppy.sex.value)
            setPetDetailItem(value = puppy.color)
            setPetDetailItem(value = puppy.ageString)
            setPetDetailItem(value = "${puppy.weight} kg")
        }

        Text(
            text = "More about me",
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = puppy.description,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun setPetDetailItem(value: String) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .aspectRatio(1f),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = 80.dp
                )
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.body1.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium)
            )
        }
    }
}

