package app.medeco.domain.model.auth

import android.net.Uri
import app.medeco.util.Gender
import app.medeco.util.UserType

data class SignUpData(
    val fullName: String,
    val phone: String,
    val nationalId: String,
    val email: String,
    val password: String,
    val passwordConf: String,
    val birthDate: Long,
    val gender: Gender,
    val type: UserType,
    // below specific for UserType.PHARMACY
    val pharmacyName: String? = null,
    val pharmacyHotline: String? = null,
    val pharmacyLogo: Uri? = null,
)
