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
package com.example.androiddevchallenge.data

object PuppyRepository {
    val puppies = listOf(
        Pet(
            id = "1",
            name = "Rubix",
            breed = "German Shepherd",
            age = 49,
            sex = Pet.Sex.MALE,
            color = "Black, Yellow",
            image = Pet.Image(
                url = "https://cdn.pixabay.com/photo/2019/03/23/05/15/schafer-dog-4074699__340.jpg",
                width = 533,
                height = 340
            ),
        ),
        Pet(
            id = "2",
            name = "Nash",
            breed = "American Staffordshire Terrier",
            age = 6,
            sex = Pet.Sex.MALE,
            color = "Black",
            image = Pet.Image(
                url = "https://d3olh6krvu9a10.cloudfront.net/static/cache/500x500/American%20Staffordshire%20Terrier.jpeg",
                width = 500,
                height = 500
            )
        ),
        Pet(
            id = "3",
            name = "Shiny",
            breed = "Chihuahua",
            age = 5,
            sex = Pet.Sex.FEMALE,
            color = "White",
            image = Pet.Image(
                url = "https://dogtime.com/assets/uploads/gallery/chihuahua-puppies/chihuahua-puppy-9.jpg",
                width = 680,
                height = 548
            )
        ),
        Pet(
            id = "4",
            name = "Jack",
            breed = "Jack Russel Terrier",
            age = 12,
            sex = Pet.Sex.MALE,
            color = "White",
            image = Pet.Image(
                url = "https://i.pinimg.com/originals/88/04/99/880499fd0fa34463118b6f856ed06e79.jpg",
                width = 482,
                height = 720
            )
        ),
        Pet(
            id = "5",
            name = "Luke",
            breed = "Doberman",
            age = 2,
            sex = Pet.Sex.MALE,
            color = "Brown",
            image = Pet.Image(
                url = "https://preview.pixlr.com/images/800wm/1347/2/1347210647.jpg",
                width = 800,
                height = 610
            )
        ),
        Pet(
            id = "6",
            name = "Rosy",
            breed = "Golden Retriever",
            age = 4,
            sex = Pet.Sex.FEMALE,
            color = "Golden",
            image = Pet.Image(
                url = "https://i.ytimg.com/vi/a6KGPBflhiM/sddefault.jpg",
                width = 640,
                height = 480
            )
        ),
        Pet(
            id = "7",
            name = "Dexter",
            breed = "Dachshund",
            age = 18,
            sex = Pet.Sex.MALE,
            color = "Brown",
            image = Pet.Image(
                url = "https://i.pinimg.com/564x/c7/4b/74/c74b74d773e25ce6f01cc095ecad0ed4.jpg",
                width = 474,
                height = 640
            )
        ),
        Pet(
            id = "8",
            name = "Bee",
            breed = "Mixed Shepherd",
            age = 2,
            sex = Pet.Sex.FEMALE,
            color = "Multicolor",
            image = Pet.Image(
                url = "https://dogtime.com/assets/uploads/2016/09/beagle-puppy-5-e1568913038666.jpg",
                width = 680,
                height = 384
            )
        ),
        Pet(
            id = "9",
            name = "Copper",
            breed = "Beagle",
            age = 6,
            sex = Pet.Sex.MALE,
            color = "Beige",
            image = Pet.Image(
                url = "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/50657462/6/?bust=1614266140&width=600",
                width = 600,
                height = 623
            )
        ),
        Pet(
            id = "10",
            name = "Tacky",
            breed = "Labrador Retriever",
            age = 3,
            sex = Pet.Sex.MALE,
            color = "Brown",
            image = Pet.Image(
                url = "https://www.purina.com.au/-/media/project/purina/main/breeds/puppies/puppy-bulldog.jpg",
                width = 825,
                height = 475
            )
        )
    )
}
