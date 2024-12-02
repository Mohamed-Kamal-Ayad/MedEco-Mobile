package app.medeco.util

import app.medeco.R

enum class Gender(val stringRes: Int, val networkValue: String) {
    MALE(R.string.male, "male"),
    FEMALE(R.string.female, "female")
}

enum class UserType(val networkValue: String, val prefsValue: Int) {
    USER("user", 0),
    PHARMACY("pharmacy", 1)
}