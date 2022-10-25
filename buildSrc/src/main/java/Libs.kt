package com.emirk.movieapp

import Versions

object Libs {

    object Gradle {
        const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:" + Versions.coreKtx
        const val appCompat = "androidx.appcompat:appcompat:" + Versions.appCompat
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:" + Versions.constraint
        const val vmLifeCycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.viewModel
        const val navigation = "androidx.navigation:navigation-fragment-ktx:" + Versions.navigation
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:" + Versions.navigation
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.livedata
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:" + Versions.runtime
        const val extensions = "androidx.lifecycle:lifecycle-extensions:" + Versions.extensions
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.safeArgs
        const val legacy = "androidx.legacy:legacy-support-v4:" + Versions.legacy
    }

    object Google {
        const val material = "com.google.android.material:material:" + Versions.material
        const val services = "com.google.gms:google-services:" + Versions.googleServices
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:" + Versions.hilt
        const val compiler = "com.google.dagger:hilt-android-compiler:" + Versions.hiltCompiler
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:" + Versions.hilt
    }

    object Network {
        const val gson: String = "com.google.code.gson:gson:" + Versions.gson
        const val retrofit = "com.squareup.retrofit2:retrofit:" + Versions.retrofit
        const val converter = "com.squareup.retrofit2:converter-gson:" + Versions.retrofit
        const val okhttp = "com.squareup.okhttp3:okhttp:" + Versions.ok_http
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:" + Versions.ok_http
    }

    object Log {
        const val timber = "com.jakewharton.timber:timber:" + Versions.timber
    }

    object Image {
        const val coil = "io.coil-kt:coil:" + Versions.coil
        const val glide = "com.github.bumptech.glide:glide:" + Versions.glide
        const val glideAnnotation = "com.github.bumptech.glide:compiler:" + Versions.glide
    }

    object Room {
        const val ktx = "androidx.room:room-ktx:" + Versions.room
        const val runtime = "androidx.room:room-runtime:" + Versions.room
        const val compiler = "androidx.room:room-compiler:" + Versions.room
    }

    object Test {
        const val junit = "junit:junit:" + Versions.junit
        const val testExt = "androidx.test.ext:junit:" + Versions.testExt
        const val espresso = "androidx.test.espresso:espresso-core:" + Versions.espresso
    }

    object Paging {
        const val runtime = "androidx.paging:paging-runtime:" + Versions.paging
    }
}