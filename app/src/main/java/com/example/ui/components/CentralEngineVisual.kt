package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.PrimaryContainer
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.SecondaryCyan

@Composable
fun CentralEngineVisual(
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.95f,
    targetValue = 1.05f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "scale"
  )
  val pulseAlpha by infiniteTransition.animateFloat(
    initialValue = 0.3f,
    targetValue = 0.7f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "alpha"
  )

  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .background(MaterialTheme.colorScheme.surfaceContainerLowest)
      .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
      .padding(14.dp)
  ) {
    // Canvas background with animated streams
    Canvas(modifier = Modifier.matchParentSize()) {
      val w = size.width
      val h = size.height
      val midY = h * 0.5f

      // Connector streams from left side to center (0.5w, midY)
      val pathLeftTop = Path().apply {
        moveTo(w * 0.28f, h * 0.28f)
        cubicTo(w * 0.36f, h * 0.28f, w * 0.40f, midY, w * 0.5f, midY)
      }
      val pathLeftMid = Path().apply {
        moveTo(w * 0.28f, midY)
        lineTo(w * 0.5f, midY)
      }
      val pathLeftBottom = Path().apply {
        moveTo(w * 0.28f, h * 0.72f)
        cubicTo(w * 0.36f, h * 0.72f, w * 0.40f, midY, w * 0.5f, midY)
      }

      // Connector streams from center to right side
      val pathRightTop = Path().apply {
        moveTo(w * 0.5f, midY)
        cubicTo(w * 0.60f, midY, w * 0.64f, h * 0.28f, w * 0.72f, h * 0.28f)
      }
      val pathRightMid = Path().apply {
        moveTo(w * 0.5f, midY)
        lineTo(w * 0.72f, midY)
      }
      val pathRightBottom = Path().apply {
        moveTo(w * 0.5f, midY)
        cubicTo(w * 0.60f, midY, w * 0.64f, h * 0.72f, w * 0.72f, h * 0.72f)
      }

      val strokeDashed = Stroke(
        width = 2.dp.toPx(),
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
      )
      val strokeSolid = Stroke(width = 2.dp.toPx())

      drawPath(pathLeftTop, PrimaryIndigo.copy(alpha = 0.4f), style = strokeDashed)
      drawPath(pathLeftMid, SecondaryCyan.copy(alpha = 0.6f), style = strokeSolid)
      drawPath(pathLeftBottom, PrimaryIndigo.copy(alpha = 0.4f), style = strokeDashed)

      drawPath(pathRightTop, SecondaryCyan.copy(alpha = 0.4f), style = strokeDashed)
      drawPath(pathRightMid, PrimaryIndigo.copy(alpha = 0.6f), style = strokeSolid)
      drawPath(pathRightBottom, SecondaryCyan.copy(alpha = 0.4f), style = strokeDashed)
    }

    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      // Top Telemetry Header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          Box(
            modifier = Modifier
              .size(8.dp)
              .clip(CircleShape)
              .background(SecondaryCyan)
          )
          Text(
            text = "KINETIC ENGINE v4.2 • ACTIVE CLUSTER",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            ),
            color = SecondaryCyan
          )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(MaterialTheme.colorScheme.surfaceContainerHigh)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(
              text = "1.8k req/m",
              style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(MaterialTheme.colorScheme.surfaceContainerHigh)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(
              text = "42ms Latency",
              style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold),
              color = SecondaryCyan
            )
          }
        }
      }

      // Three-Column Hub Layout: Inbound Nodes | Center Swarm Engine | Outbound Nodes
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Left Column: Inbound Channels
        Column(
          modifier = Modifier.weight(1f),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          EngineMiniNode(icon = Icons.Default.Language, title = "Website Portal", sub = "Lead Ingestion", isCyan = true)
          EngineMiniNode(icon = Icons.Default.Chat, title = "WhatsApp Biz", sub = "Omnichannel API", isCyan = true)
          EngineMiniNode(icon = Icons.Default.PhoneInTalk, title = "VoIP / SIP", sub = "Voice AI Intake", isCyan = true)
        }

        // Center: Glowing Autonomous Swarm Engine Core
        Box(
          modifier = Modifier
            .weight(1.15f)
            .padding(horizontal = 6.dp),
          contentAlignment = Alignment.Center
        ) {
          // Glow background
          Box(
            modifier = Modifier
              .size((80 * pulseScale).dp)
              .clip(CircleShape)
              .background(
                Brush.radialGradient(
                  colors = listOf(
                    PrimaryContainer.copy(alpha = pulseAlpha * 0.8f),
                    SecondaryCyan.copy(alpha = pulseAlpha * 0.4f),
                    Color.Transparent
                  )
                )
              )
          )

          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .clip(RoundedCornerShape(14.dp))
              .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.95f))
              .border(1.dp, SecondaryCyan.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
              .padding(horizontal = 10.dp, vertical = 10.dp)
          ) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(PrimaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Psychology,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(24.dp)
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "Autonomous",
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
              ),
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "SWARM ENGINE",
              style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontSize = 8.5.sp,
                letterSpacing = 0.8.sp
              ),
              color = SecondaryCyan
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(horizontal = 5.dp, vertical = 2.dp)
            ) {
              Text(
                text = "Zero Bottleneck",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }

        // Right Column: Destination Systems
        Column(
          modifier = Modifier.weight(1f),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          EngineMiniNode(icon = Icons.Default.Storage, title = "Salesforce & CRM", sub = "Instant Sync", isCyan = false)
          EngineMiniNode(icon = Icons.Default.Event, title = "Google / Outlook", sub = "Auto Booking", isCyan = false)
          EngineMiniNode(icon = Icons.Default.AccountBalance, title = "SAP & ERP Core", sub = "Financial Post", isCyan = false)
        }
      }

      // Bottom Event Ticker
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(MaterialTheme.colorScheme.surfaceContainerLow)
          .padding(horizontal = 10.dp, vertical = 6.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
          ) {
            Icon(
              imageVector = Icons.Default.SyncAlt,
              contentDescription = null,
              tint = SecondaryCyan,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = "Live Stream: Inbound → AI Qualification → CRM Sync → Action Dispatched",
              style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.5.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              maxLines = 1
            )
          }
          Text(
            text = "100% Autonomous",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 8.5.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            ),
            color = SecondaryCyan
          )
        }
      }
    }
  }
}

@Composable
private fun EngineMiniNode(
  icon: ImageVector,
  title: String,
  sub: String,
  isCyan: Boolean
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(8.dp))
      .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.85f))
      .border(
        width = 1.dp,
        color = if (isCyan) SecondaryCyan.copy(alpha = 0.25f) else PrimaryIndigo.copy(alpha = 0.25f),
        shape = RoundedCornerShape(8.dp)
      )
      .padding(horizontal = 7.dp, vertical = 6.dp)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = if (isCyan) SecondaryCyan else PrimaryIndigo,
        modifier = Modifier.size(15.dp)
      )
      Column {
        Text(
          text = title,
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 9.sp
          ),
          color = MaterialTheme.colorScheme.onSurface,
          maxLines = 1
        )
        Text(
          text = sub,
          style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 7.5.sp
          ),
          color = MaterialTheme.colorScheme.outline,
          maxLines = 1
        )
      }
    }
  }
}
