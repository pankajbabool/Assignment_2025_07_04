package com.example.assignment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import assignment.composeapp.generated.resources.Res
import assignment.composeapp.generated.resources.montserrat
import assignment.composeapp.generated.resources.montserrat_black
import assignment.composeapp.generated.resources.montserrat_black_italic
import assignment.composeapp.generated.resources.montserrat_bold
import assignment.composeapp.generated.resources.montserrat_bold_italic
import assignment.composeapp.generated.resources.montserrat_extrabold
import assignment.composeapp.generated.resources.montserrat_extrabold_italic
import assignment.composeapp.generated.resources.montserrat_extralight
import assignment.composeapp.generated.resources.montserrat_extralight_italic
import assignment.composeapp.generated.resources.montserrat_italic
import assignment.composeapp.generated.resources.montserrat_light
import assignment.composeapp.generated.resources.montserrat_light_italic
import assignment.composeapp.generated.resources.montserrat_medium
import assignment.composeapp.generated.resources.montserrat_medium_italic
import assignment.composeapp.generated.resources.montserrat_semibold
import assignment.composeapp.generated.resources.montserrat_semibold_italic
import assignment.composeapp.generated.resources.montserrat_thin
import assignment.composeapp.generated.resources.montserrat_thin_italic
import org.jetbrains.compose.resources.Font

// Font weights: [900=Black], [800=ExtraBold], [700=Bold], [600=SemiBold], [500=Medium], [400=Normal], [300=Light], [200=ExtraLight], [100=Thin]
// Font Size: Display(57,45,36) > Headline(32,28,24) > Title(22,16,14) > Body(16,14,12) > Label(14,12,11)


@Composable fun montserrat()                = Font(Res.font.montserrat)
@Composable fun montserratBlack()           = Font(Res.font.montserrat_black,             FontWeight.Black)
@Composable fun montserratBlackItalic()     = Font(Res.font.montserrat_black_italic,      FontWeight.Black,       FontStyle.Italic)
@Composable fun montserratBold()            = Font(Res.font.montserrat_bold,              FontWeight.Bold)
@Composable fun montserratBoldItalic()      = Font(Res.font.montserrat_bold_italic,       FontWeight.Bold,        FontStyle.Italic)
@Composable fun montserratExtraBold()       = Font(Res.font.montserrat_extrabold,         FontWeight.ExtraBold)
@Composable fun montserratExtraBoldItalic() = Font(Res.font.montserrat_extrabold_italic,  FontWeight.ExtraBold,   FontStyle.Italic)
@Composable fun montserratExtraLight()      = Font(Res.font.montserrat_extralight,        FontWeight.ExtraLight)
@Composable fun montserratExtraLightItalic()= Font(Res.font.montserrat_extralight_italic, FontWeight.ExtraLight,  FontStyle.Italic)
@Composable fun montserratItalic()          = Font(Res.font.montserrat_italic,                            style = FontStyle.Italic)
@Composable fun montserratLight()           = Font(Res.font.montserrat_light,             FontWeight.Light)
@Composable fun montserratLightItalic()     = Font(Res.font.montserrat_light_italic,      FontWeight.Light,       FontStyle.Italic)
@Composable fun montserratMedium()          = Font(Res.font.montserrat_medium,            FontWeight.Medium)
@Composable fun montserratMediumItalic()    = Font(Res.font.montserrat_medium_italic,     FontWeight.Medium,      FontStyle.Italic)
@Composable fun montserratSemiBold()        = Font(Res.font.montserrat_semibold,          FontWeight.SemiBold)
@Composable fun montserratSemiBoldItalic()  = Font(Res.font.montserrat_semibold_italic,   FontWeight.SemiBold,    FontStyle.Italic)
@Composable fun montserratThin()            = Font(Res.font.montserrat_thin,              FontWeight.Thin)
@Composable fun montserratThinItalic()      = Font(Res.font.montserrat_thin_italic,       FontWeight.Thin,        FontStyle.Italic)

@Composable fun getW100Font() = montserratThin()
@Composable fun getW200Font() = montserratExtraLight()
@Composable fun getW300Font() = montserratLight()
@Composable fun getW400Font() = montserrat()
@Composable fun getW500Font() = montserratMedium()
@Composable fun getW600Font() = montserratSemiBold()
@Composable fun getW700Font() = montserratBold()
@Composable fun getW800Font() = montserratExtraBold()
@Composable fun getW900Font() = montserratBlack()

@Composable
fun MontserratFont() = FontFamily(
    montserrat(),
    montserratBlack(),
    montserratBlackItalic(),
    montserratBold(),
    montserratBoldItalic(),
    montserratExtraBold(),
    montserratExtraBoldItalic(),
    montserratExtraLight(),
    montserratExtraLightItalic(),
    montserratItalic(),
    montserratLight(),
    montserratLightItalic(),
    montserratMedium(),
    montserratMediumItalic(),
    montserratSemiBold(),
    montserratSemiBoldItalic(),
    montserratThin(),
    montserratThinItalic()
)

@Composable
fun MontserratTypography() = Typography().run {
    val fontFamily = MontserratFont()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),

        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),

        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),

        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),

        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}