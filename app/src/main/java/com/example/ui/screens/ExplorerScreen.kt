package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.KineticRepository
import com.example.ui.theme.*
import com.example.ui.viewmodel.ExplorerUiState
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ExplorerScreen(
  state: ExplorerUiState,
  onSelectDepartment: (String) -> Unit,
  onUpdateTeamCount: (Int) -> Unit,
  onScheduleAuditForDept: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val selectedSection = KineticRepository.explorerSections.find { it.id == state.selectedDepartmentId }
    ?: KineticRepository.explorerSections.first()

  // ROI calculations
  val hoursSavedWeekly = state.teamMembersCount * 17
  val monthlyDollarsSaved = hoursSavedWeekly * 4 * 42
  val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US).apply {
    maximumFractionDigits = 0
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Title Header
    item {
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier
              .size(8.dp)
              .clip(CircleShape)
              .background(SecondaryCyan)
          )
          Text(
            text = "INTERACTIVE PROCESS MATRIX",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace,
              letterSpacing = 0.8.sp
            ),
            color = SecondaryCyan
          )
        }

        Text(
          text = "What Can KineticFlow Automate?",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
          ),
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = "Select a functional department below to compare traditional human friction against the KineticFlow automated pipeline.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    // Horizontal Scrollable Department Tabs
    item {
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        items(KineticRepository.explorerSections) { section ->
          val isSelected = section.id == state.selectedDepartmentId
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(10.dp))
              .background(if (isSelected) PrimaryContainer else MaterialTheme.colorScheme.surfaceContainerHigh)
              .border(
                1.dp,
                if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                RoundedCornerShape(10.dp)
              )
              .clickable { onSelectDepartment(section.id) }
              .padding(horizontal = 14.dp, vertical = 9.dp)
              .testTag("explorer_tab_${section.id}")
          ) {
            Text(
              text = section.label,
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 12.sp
              ),
              color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }

    // Selected Department Title Banner
    item {
      Text(
        text = selectedSection.title,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 17.sp
        ),
        color = MaterialTheme.colorScheme.onSurface
      )
    }

    // Before Card (Manual Friction)
    item {
      Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .fillMaxWidth()
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.error.copy(alpha = 0.15f))
                .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
              Text(
                text = "BEFORE: MANUAL BOTTLENECK",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 8.5.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Monospace
                ),
                color = MaterialTheme.colorScheme.error
              )
            }

            Text(
              text = selectedSection.beforeTimeLost,
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
              ),
              color = MaterialTheme.colorScheme.outline
            )
          }

          Text(
            text = selectedSection.beforeTitle,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )

          selectedSection.beforePoints.forEach { point ->
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier
                  .size(16.dp)
                  .padding(top = 2.dp)
              )
              Text(
                text = point,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(MaterialTheme.colorScheme.surfaceContainer)
              .padding(8.dp)
          ) {
            Text(
              text = "High human latency • Prone to error • Unscalable unit economics",
              style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.5.sp),
              color = MaterialTheme.colorScheme.outline
            )
          }
        }
      }
    }

    // After Card (KineticFlow Autonomous Pipeline)
    item {
      Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .fillMaxWidth()
          .border(1.dp, SecondaryCyan.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(SecondaryCyan.copy(alpha = 0.15f))
                .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
              Text(
                text = "AFTER: KINETICFLOW AUTOMATION",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 8.5.sp,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Monospace
                ),
                color = SecondaryCyan
              )
            }

            Text(
              text = selectedSection.afterTimeSaved,
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              ),
              color = SecondaryCyan
            )
          }

          Text(
            text = selectedSection.afterTitle,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )

          selectedSection.afterPoints.forEach { point ->
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = SecondaryCyan,
                modifier = Modifier
                  .size(16.dp)
                  .padding(top = 2.dp)
              )
              Text(
                text = point,
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 17.sp),
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }

          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(MaterialTheme.colorScheme.surfaceContainerHigh)
              .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "24/7 Real-Time Execution",
              style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.5.sp, fontWeight = FontWeight.SemiBold),
              color = SecondaryCyan
            )
            Text(
              text = "100% Auditable JSON",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace
              ),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }

    // Interactive ROI Calculator Card
    item {
      Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .fillMaxWidth()
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(Icons.Default.Calculate, contentDescription = null, tint = SecondaryCyan, modifier = Modifier.size(20.dp))
            Text(
              text = "DEPARTMENT ROI CALCULATOR",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              ),
              color = SecondaryCyan
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Active Team Members:",
              style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.sp),
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "${state.teamMembersCount} Staff",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
              ),
              color = SecondaryCyan
            )
          }

          Slider(
            value = state.teamMembersCount.toFloat(),
            onValueChange = { onUpdateTeamCount(it.toInt()) },
            valueRange = 3f..120f,
            steps = 117,
            colors = SliderDefaults.colors(
              thumbColor = SecondaryCyan,
              activeTrackColor = SecondaryCyan,
              inactiveTrackColor = MaterialTheme.colorScheme.surfaceContainerHighest
            ),
            modifier = Modifier.testTag("team_size_slider")
          )

          // Calculated Impact Metrics
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Box(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(10.dp)
            ) {
              Column {
                Text(
                  text = "HOURS SAVED / WK",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace
                  ),
                  color = MaterialTheme.colorScheme.outline
                )
                Text(
                  text = "${hoursSavedWeekly}h",
                  style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                  ),
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = "~${hoursSavedWeekly / 40} Full-time equivalents",
                  style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.5.sp),
                  color = SecondaryCyan
                )
              }
            }

            Box(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(10.dp)
            ) {
              Column {
                Text(
                  text = "EST. MONTHLY RECAPTURE",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace
                  ),
                  color = MaterialTheme.colorScheme.outline
                )
                Text(
                  text = currencyFormat.format(monthlyDollarsSaved),
                  style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                  ),
                  color = SuccessGreen
                )
                Text(
                  text = "Loaded labor overhead",
                  style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.5.sp),
                  color = MaterialTheme.colorScheme.outline
                )
              }
            }
          }

          // Direct Button to schedule audit for this dept
          Button(
            onClick = { onScheduleAuditForDept(selectedSection.label) },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = PrimaryContainer,
              contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier
              .fillMaxWidth()
              .height(44.dp)
              .testTag("explorer_book_audit_button")
          ) {
            Icon(Icons.Default.Bolt, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Automate ${selectedSection.label}",
              style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
            )
          }
        }
      }
    }
  }
}
