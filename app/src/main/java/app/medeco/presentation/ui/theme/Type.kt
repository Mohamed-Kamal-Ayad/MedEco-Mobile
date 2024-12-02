package app.medeco.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.medeco.R

private val font = FontFamily(
    Font(R.font.rubik_regular),
    Font(R.font.rubik_semibold, weight = FontWeight.SemiBold),
    Font(R.font.rubik_bold, weight = FontWeight.Bold)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = font
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        fontFamily = font
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        fontFamily = font
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        fontFamily = font
    ),
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 96.sp,
        fontFamily = font
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 60.sp,
        fontFamily = font
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        fontFamily = font
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        fontFamily = font
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        fontFamily = font
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        fontFamily = font
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = font
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        fontFamily = font
    )
)