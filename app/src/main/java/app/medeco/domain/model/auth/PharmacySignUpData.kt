package app.medeco.domain.model.auth

import android.net.Uri
import app.medeco.util.Gender

data class PharmacySignUpData(
    val name: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String,
    val phone: String,
    val gender: Gender,
    val nationalId: String,
    val birthdate: Long,
    val pharmacyName: String,
    val pharmacyHotline: String,
    val pharmacyLogo: ByteArray,
    val acceptsExpired: Boolean
)