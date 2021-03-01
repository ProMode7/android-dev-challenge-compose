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
package com.example.androiddevchallenge.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.ui.theme.gridColors
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PetList(
    puppies: List<Pet>,
    navigateToPuppyDetails: (Pet) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 350.dp,
        ) {
            puppies.forEach { Pet ->
                PetGridItem(puppy = Pet, navigateToPuppyDetails)
            }
        }
    }
}

@Composable
fun PetGridItem(
    puppy: Pet,
    navigateToPuppyDetails: (Pet) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navigateToPuppyDetails.invoke(puppy)
            }
    ) {
        CoilImage(
            data = puppy.image.url,
            contentDescription = puppy.name,
            contentScale = ContentScale.Crop,
            fadeIn = true,
            modifier = Modifier
                .background(Color.LightGray)
                .aspectRatio(puppy.image.aspectRatio)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    gridColors
                        .random()
                        .copy(alpha = 0.5f)
                )
                .padding(vertical = 5.dp, horizontal = 5.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = puppy.name,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = puppy.breed,
                style = MaterialTheme.typography.body2
            )
            Text(
                text = "${puppy.sex.value}, ${puppy.ageString}",
                style = MaterialTheme.typography.caption
            )
        }
    }
}
