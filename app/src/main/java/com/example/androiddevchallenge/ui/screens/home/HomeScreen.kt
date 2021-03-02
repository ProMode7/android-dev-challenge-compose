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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Pet
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun HomeScreen(navigateToPuppyDetails: (Pet) -> Unit) {
    val puppies = com.example.androiddevchallenge.data.PuppyRepository.puppies
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            content = {
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    HeaderTitle(puppies, navigateToPuppyDetails)
                    SetVerticalList(puppies, navigateToPuppyDetails)
                }
            }
        )
    }
}

var popularPuppyList: List<Pet> = ArrayList()

@Composable
private fun HeaderTitle(puppies: List<Pet>, navigateToPuppyDetails: (Pet) -> Unit) {
    Text(
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
        text = "Find a friend",
        style = MaterialTheme.typography.h4
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
        text = "Popular this week",
        style = MaterialTheme.typography.h6
    )
    Spacer(modifier = Modifier.height(10.dp))
    if (popularPuppyList.isEmpty()) {
        popularPuppyList = puppies.shuffled().subList(0, puppies.size / 2)
    }
    HorizontalCarousal(
        puppies = popularPuppyList,
        navigateToPuppyDetails = navigateToPuppyDetails
    )
    Spacer(Modifier.height(10.dp))
    Text(
        modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp),
        text = "Adopt Us",
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun HorizontalCarousal(puppies: List<Pet>, navigateToPuppyDetails: (Pet) -> Unit) {
    LazyRow {
        items(items = puppies) { puppy ->
            Spacer(Modifier.width(10.dp))
            Card(
                shape = CircleShape,
                backgroundColor = Color.LightGray
            ) {
                Column(
                    horizontalAlignment =
                    Alignment.CenterHorizontally,
                    modifier = Modifier
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
                            .size(100.dp)
                            .background(Color.LightGray)
                    )
                }
            }
            Spacer(Modifier.width(10.dp))
        }
    }
}

@Composable
fun SetVerticalList(
    puppies: List<Pet>,
    navigateToPuppyDetails: (Pet) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp, 20.dp, 20.dp, 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        StaggeredGridView(
            maxColumnWidth = 300.dp,
        ) {
            puppies.forEach { Pet ->
                ListGridItem(puppy = Pet, navigateToPuppyDetails)
            }
        }
    }
}

@Composable
fun ListGridItem(puppy: Pet, navigateToPuppyDetails: (Pet) -> Unit) {
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
            modifier = Modifier
                .background(Color.LightGray)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Black.copy(alpha = 0.6f)
                )
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = puppy.name,
                style = MaterialTheme.typography.h6.copy(Color.White)
            )
            Text(
                text = "${puppy.breed}, ${puppy.sex.value}",
                style = MaterialTheme.typography.body2.copy(Color.White)
            )
            Text(
                text = "${puppy.ageString} young",
                style = MaterialTheme.typography.body2.copy(Color.White)
            )
        }
    }
}
