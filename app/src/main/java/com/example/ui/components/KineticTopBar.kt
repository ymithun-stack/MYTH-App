package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.SecondaryCyan

private const val KINETIC_LOGO_URL =
  "https://lh3.googleusercontent.com/aida/AEtjO1XvgoVc2w4HShW3HTJa_NewEqHEMrIeLa4fwWaeHidme4DGlmPUjHo-7Hba_XYtBm9aRkxbMuD6BB8ESWz1mlg0f-VJtRTd4MaGLW0rbU30Pf8eYf66-56i6pi5pah3DRpwxMHZE3_A0wGJEp7KrMEorX9QAURZsWYBM57IEsoQ1AiZyqZ6MUfh1E6IZ9YOLwT6W0Gl-h0vnaE3-TVnhqpyQNhcVUxsj2LsnTR5QsyAbG_WKCcLUCW6xOk"

@Composable
fun KineticTopBar(
  onScheduleAuditClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    color = MaterialTheme.colorScheme.surfaceContainerLowest.copy(alpha = 0.95f),
    tonalElevation = 6.dp,
    modifier = modifier
      .fillMaxWidth()
      .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f))
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(horizontal = 16.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Brand Logo & Title
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        // Logo Container with fallback icon
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f), RoundedCornerShape(8.dp)),
          contentAlignment = Alignment.Center
        ) {
          SubcomposeAsyncImage(
            model = KINETIC_LOGO_URL,
            contentDescription = "KineticFlow AI Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
              .fillMaxSize()
              .padding(3.dp),
            error = {
              Icon(
                imageVector = Icons.Default.Hub,
                contentDescription = null,
                tint = SecondaryCyan,
                modifier = Modifier.size(22.dp)
              )
            }
          )
        }

        Column {
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
              text = "KineticFlow",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                letterSpacing = (-0.3).sp
              ),
              color = MaterialTheme.colorScheme.onSurface
            )
            // Active Engine Pill
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(SecondaryCyan.copy(alpha = 0.15f))
                .padding(horizontal = 5.dp, vertical = 1.5.dp)
            ) {
              Text(
                text = "v4.2 ACTIVE",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 8.5.sp,
                  fontWeight = FontWeight.Bold
                ),
                color = SecondaryCyan
              )
            }
          }
          Text(
            text = "AI + AUTOMATION SYSTEMS",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontFamily = FontFamily.Monospace,
              letterSpacing = 1.sp
            ),
            color = SecondaryCyan
          )
        }
      }

      // Action Button
      Button(
        onClick = onScheduleAuditClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = PrimaryContainer,
          contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        modifier = Modifier
          .height(36.dp)
          .testTag("top_bar_audit_button")
      ) {
        Icon(
          imageVector = Icons.Default.Bolt,
          contentDescription = null,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "AI Audit",
          style = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 11.5.sp
          )
        )
      }
    }
  }
}
