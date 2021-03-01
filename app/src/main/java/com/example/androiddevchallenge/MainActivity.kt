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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.navigation.Navigation
import com.example.androiddevchallenge.ui.screens.details.DetailsScreen
import com.example.androiddevchallenge.ui.screens.splash.SplashScreen
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val puppies = com.example.androiddevchallenge.data.PetsDataListing.puppies
    NavHost(navController, startDestination = Navigation.SplashScreen.title) {
        composable(Navigation.SplashScreen.title) {
            SplashScreen(navigateToHome = {
                navController.navigate(Navigation.HomeScreen.title)
            })
        }
        composable(Navigation.HomeScreen.title) {
            com.example.androiddevchallenge.ui.screens.home.HomeScreen(
                navigateToPuppyDetails = { pet ->
                    navController.navigate(Navigation.DetailScreen.title + "/${pet.id}")
                }
            )
        }
        composable(Navigation.DetailScreen.title + "/{id}") { backStackEntry ->
            val puppyId = backStackEntry.arguments?.getString("id")
            val puppy =
                puppies.find { it.id == puppyId } ?: throw IllegalStateException("puppy not found")
            DetailsScreen(
                puppy = puppy,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
