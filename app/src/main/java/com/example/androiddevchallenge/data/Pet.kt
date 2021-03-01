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

data class Pet(
    val id: String,
    val name: String,
    val breed: String,
    val age: Int,
    val sex: Sex,
    val color: String,
    val image: Image,
    val weight: Int = (1..5).random(),
    val description: String = prepareDescription(name, sex)
) {

    enum class Sex(val value: String) {
        MALE("Male"),
        FEMALE("Female")
    }

    data class Image(
        val url: String,
        val width: Int,
        val height: Int
    ) {
        val aspectRatio: Float
            get() = width.toFloat().div(height.toFloat())
    }

    val ageString: String
        get() {
            val ageInYears = (age / 12).toFloat()
            return if (ageInYears <= 0) "$age months"
            else "${ageInYears.toInt()} years"
        }
}

private fun prepareDescription(name: String, sex: Pet.Sex): String {
    val pronoun = if (sex == Pet.Sex.MALE) "He" else "She"
    return "$name is a friendly, attentive, and happy soul. $pronoun is very clever and loved to play around house. $pronoun is also very good with kids. $pronoun is up for adoption and will keep you and your family very happy."
}
