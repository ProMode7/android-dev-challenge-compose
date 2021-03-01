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

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.androiddevchallenge.data.Pet
import com.example.androiddevchallenge.ui.theme.gridColors
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun HomeScreen(navigateToPuppyDetails: (Pet) -> Unit) {
    val puppies = com.example.androiddevchallenge.data.PuppyRepository.puppies
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            content = {
                Column(
                    modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    HeaderTitle()
//                    Spacer(Modifier.height(5.dp))
//                    HorizontalCarousal(puppies = puppies)
                    Spacer(Modifier.height(5.dp))
                    setVerticalList(puppies, navigateToPuppyDetails)
                }
            }
        )
    }
}

@Composable
private fun HeaderTitle() {
    Text(
        text = "Find a Friend",
        style = MaterialTheme.typography.h4
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Adopt Us",
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun HorizontalCarousal(puppies: List<Pet>) {
    LazyRow {
        items(items = puppies) { puppy ->
            Spacer(Modifier.width(8.dp))
            Card(
                shape = RoundedCornerShape(25.dp),
                backgroundColor = Color.LightGray
            ) {
                Column(
                    horizontalAlignment =
                    Alignment.CenterHorizontally
                ) {
                    Text(
                        text = puppy.name,
                        Modifier.padding(12.dp),
                    )
                }
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun VerticalList(puppies: List<Pet>) {
    androidx.compose.foundation.lazy.LazyColumn {
        items(items = puppies) { puppy ->
            Spacer(Modifier.width(16.dp))
            Card(
                shape = RoundedCornerShape(25.dp),
                backgroundColor = Color.LightGray
            ) {
                Column(
                    horizontalAlignment =
                    Alignment.CenterHorizontally
                ) {
                    dev.chrisbanes.accompanist.coil.CoilImage(
                        data = puppy.image.url,
                        contentDescription = puppy.name,
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        fadeIn = true,
                        modifier = Modifier
                            .size(300.dp)
                            .background(Color.LightGray)
                    )
                    Text(
                        text = puppy.name,
                        Modifier.padding(24.dp),
                    )
                }
                Spacer(Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun setVerticalList(
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
                listGridItem(puppy = Pet, navigateToPuppyDetails)
            }
        }
    }
}

@Composable
fun listGridItem(
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
