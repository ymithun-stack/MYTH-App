package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val KineticDarkColorScheme = darkColorScheme(
  primary = PrimaryIndigo,
  onPrimary = OnPrimary,
  primaryContainer = PrimaryContainer,
  onPrimaryContainer = OnPrimaryContainer,
  secondary = SecondaryCyan,
  onSecondary = OnSecondary,
  secondaryContainer = SecondaryContainer,
  onSecondaryContainer = OnSecondaryContainer,
  tertiary = TertiaryBlue,
  onTertiary = OnTertiary,
  background = BackgroundDark,
  onBackground = OnSurface,
  surface = SurfaceDark,
  onSurface = OnSurface,
  surfaceVariant = SurfaceContainerHigh,
  onSurfaceVariant = OnSurfaceVariant,
  surfaceContainerLowest = SurfaceContainerLowest,
  surfaceContainerLow = SurfaceContainerLow,
  surfaceContainer = SurfaceContainer,
  surfaceContainerHigh = SurfaceContainerHigh,
  surfaceContainerHighest = SurfaceContainerHighest,
  surfaceBright = SurfaceBright,
  outline = OutlineColor,
  outlineVariant = OutlineVariant,
  error = ErrorRed,
  errorContainer = ErrorContainer,
  onError = OnError,
)

@Composable
fun KineticFlowTheme(
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = KineticDarkColorScheme,
    typography = Typography,
    content = content
  )
}
