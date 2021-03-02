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

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
            SetPicAndButtons(puppy = puppy, navigateBack)
            SetPetInfo(puppy = puppy)
        }
    }
}

@Composable
private fun SetPicAndButtons(puppy: Pet, navigateBack: () -> kotlin.Unit) {
    val context = LocalContext.current
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
                    showToast(
                        "Yay! You've Adopted \uD83C\uDF89 ${puppy.name} \uD83D\uDC36",
                        context
                    )
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
                    openShareOptions(puppy, context)
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

fun openShareOptions(puppy: Pet, context: Context) {
    val sharingIntent = Intent(Intent.ACTION_SEND)
    sharingIntent.type = "image/*"
    sharingIntent.putExtra(Intent.EXTRA_STREAM, puppy.image.url)
    context.startActivities(arrayOf(Intent.createChooser(sharingIntent, "Share with")))
}

private fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Composable
private fun SetPetInfo(puppy: Pet) {
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
            SetPetDetailItem(value = puppy.sex.value)
            SetPetDetailItem(value = puppy.color)
            SetPetDetailItem(value = puppy.ageString)
            SetPetDetailItem(value = "${puppy.weight} kg")
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
private fun SetPetDetailItem(value: String) {
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
