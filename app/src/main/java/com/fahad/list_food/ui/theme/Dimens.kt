package com.fahad.list_food.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
  val sizeName: String,
  val small1: Dp = 0.dp,
  val small2: Dp = 0.dp,
  val small3: Dp = 0.dp,
  val medium1: Dp = 0.dp,
  val medium2: Dp = 0.dp,
  val medium3: Dp = 0.dp,
  val large: Dp = 0.dp,
  val buttonHeight: Dp = 40.dp,
  val logoSize: Dp = 42.dp,
  val imageSize0: Dp = 0.dp,
  val imageSize: Dp = 0.dp
)

val VerySmallDimens = Dimens(
  sizeName = "Very Small",
  small1 = 2.dp,
  small2 = 3.dp,
  small3 = 6.dp,
  medium1 = 10.dp,
  medium2 = 18.dp,
  medium3 = 24.dp,
  large = 36.dp,
  buttonHeight = 24.dp,
  logoSize = 28.dp,
  imageSize0 = 50.dp,
  imageSize = 95.dp
)

val CompactDimens = Dimens(
  sizeName = "Compact",
  small1 = 4.dp,
  small2 = 5.dp,
  small3 = 8.dp,
  medium1 = 12.dp,
  medium2 = 26.dp,
  medium3 = 30.dp,
  large = 45.dp,
  buttonHeight = 30.dp,
  logoSize = 36.dp,
  imageSize0 = 70.dp,
  imageSize = 150.dp
)

val CompactMediumDimens = Dimens(
  sizeName = "Compact Medium",
  small1 = 6.dp,
  small2 = 13.dp,
  small3 = 15.dp,
  medium1 = 17.dp,
  medium2 = 30.dp,
  medium3 = 35.dp,
  large = 65.dp,
  imageSize0 = 85.dp,
  imageSize = 155.dp
)

val MediumDimens = Dimens(
  sizeName = "Medium",
  small1 = 10.dp,
  small2 = 15.dp,
  small3 = 17.dp,
  medium1 = 25.dp,
  medium2 = 36.dp,
  medium3 = 40.dp,
  large = 110.dp,
  logoSize = 55.dp,
  imageSize0 = 120.dp,
  imageSize = 200.dp
)

val LargeDimens = Dimens(
  sizeName = "Large",
  small1 = 12.dp,
  small2 = 18.dp,
  small3 = 24.dp,
  medium1 = 35.dp,
  medium2 = 40.dp,
  medium3 = 50.dp,
  large = 150.dp,
  logoSize = 70.dp,
  imageSize0 = 160.dp,
  imageSize = 350.dp
)

val ExpandedDimens = Dimens(
  sizeName = "Expanded",
  small1 = 15.dp,
  small2 = 20.dp,
  small3 = 25.dp,
  medium1 = 35.dp,
  medium2 = 30.dp,
  medium3 = 45.dp,
  large = 130.dp,
  logoSize = 72.dp,
  imageSize0 = 140.dp,
  imageSize = 350.dp
)

