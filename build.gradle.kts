// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    // KSP version must start with the Kotlin version
    id("com.google.devtools.ksp") version "2.0.21-1.0.28" apply false
}