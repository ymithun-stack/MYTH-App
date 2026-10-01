package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LogEntry
import com.example.data.PipelineStep
import com.example.data.SimulationTrigger
import com.example.data.StepStatus
import com.example.ui.theme.*
import com.example.ui.viewmodel.SimulatorUiState
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun SimulatorScreen(
  state: SimulatorUiState,
  onSelectTrigger: (SimulationTrigger) -> Unit,
  onRunSimulation: () -> Unit,
  onResetSimulation: () -> Unit,
  modifier: Modifier = Modifier
) {
  val listState = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()
  val context = LocalContext.current
  val clipboardManager = LocalClipboardManager.current
  var showPayloadDialog by remember { mutableStateOf(false) }

  // Auto scroll logs when new entries arrive
  LaunchedEffect(state.logs.size) {
    if (state.logs.isNotEmpty()) {
      coroutineScope.launch {
        listState.animateScrollToItem(state.logs.size - 1)
      }
    }
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header Title Card
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
            text = "LIVE ARCHITECTURAL SIMULATION",
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
          text = "See AI Automation In Action",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
          ),
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = "Trigger an autonomous event simulation to watch how KineticFlow's agent swarm coordinates inbound channels, speech reasoning, calendar APIs, and CRM databases in real-time.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    // Trigger Selector Section
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "SELECT INBOUND EVENT TRIGGER",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 8.5.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
              ),
              color = MaterialTheme.colorScheme.outline
            )

            // Inspect Raw Payload Button
            TextButton(
              onClick = { showPayloadDialog = true },
              contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(14.dp), tint = SecondaryCyan)
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = "View JSON",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = SecondaryCyan
                )
              )
            }
          }

          SimulationTrigger.values().forEach { trigger ->
            val isSelected = state.selectedTrigger == trigger
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(
                  if (isSelected) MaterialTheme.colorScheme.surfaceContainerHigh else MaterialTheme.colorScheme.surfaceContainer
                )
                .border(
                  width = 1.dp,
                  color = if (isSelected) SecondaryCyan else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f),
                  shape = RoundedCornerShape(10.dp)
                )
                .clickable(enabled = !state.isRunning) { onSelectTrigger(trigger) }
                .padding(12.dp)
                .testTag("trigger_${trigger.id}")
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                  val iconVec = when (trigger) {
                    SimulationTrigger.PHONE_CALL -> Icons.Default.PhoneInTalk
                    SimulationTrigger.WHATSAPP -> Icons.Default.Chat
                    SimulationTrigger.WEB_FORM -> Icons.Default.Web
                  }
                  Box(
                    modifier = Modifier
                      .size(36.dp)
                      .clip(RoundedCornerShape(8.dp))
                      .background(if (isSelected) SecondaryCyan.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceContainerHighest),
                    contentAlignment = Alignment.Center
                  ) {
                    Icon(
                      imageVector = iconVec,
                      contentDescription = null,
                      tint = if (isSelected) SecondaryCyan else MaterialTheme.colorScheme.outline,
                      modifier = Modifier.size(20.dp)
                    )
                  }
                  Column {
                    Text(
                      text = trigger.title,
                      style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 13.5.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold
                      ),
                      color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                      text = trigger.subtitle,
                      style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                      color = MaterialTheme.colorScheme.outline
                    )
                  }
                }

                RadioButton(
                  selected = isSelected,
                  onClick = { if (!state.isRunning) onSelectTrigger(trigger) },
                  colors = RadioButtonDefaults.colors(
                    selectedColor = SecondaryCyan,
                    unselectedColor = MaterialTheme.colorScheme.outline
                  )
                )
              }
            }
          }

          // Execution Buttons
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = onRunSimulation,
              enabled = !state.isRunning,
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = SecondaryCyan,
                contentColor = OnSecondary
              ),
              modifier = Modifier
                .weight(1.4f)
                .height(48.dp)
                .testTag("run_simulation_button")
            ) {
              if (state.isRunning) {
                CircularProgressIndicator(
                  modifier = Modifier.size(18.dp),
                  color = OnSecondary,
                  strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Simulating...",
                  style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
              } else {
                Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = if (state.isCompleted) "Re-run Simulation" else "Run Live Simulation",
                  style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
              }
            }

            OutlinedButton(
              onClick = onResetSimulation,
              enabled = !state.isRunning,
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.onSurface
              ),
              modifier = Modifier
                .weight(0.7f)
                .height(48.dp)
                .testTag("reset_simulation_button")
            ) {
              Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = "Reset",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
              )
            }
          }
        }
      }
    }

    // 5-Step Pipeline Visualizer
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
            Text(
              text = "AUTONOMOUS PIPELINE STATE MACHINE",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 8.5.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
              ),
              color = SecondaryCyan
            )
            Text(
              text = String.format(Locale.US, "Elapsed: %.2fs", state.elapsedTimeSeconds),
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              ),
              color = if (state.isCompleted) SecondaryCyan else MaterialTheme.colorScheme.outline
            )
          }

          state.steps.forEach { step ->
            PipelineStepRow(step = step)
          }
        }
      }
    }

    // Terminal Log Output Console
    item {
      Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .fillMaxWidth()
          .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          // Window title bar
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.4f))
              .padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(MaterialTheme.colorScheme.error))
              Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(SecondaryCyan))
              Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(PrimaryIndigo))
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = "kinetic_pipeline_exec.log",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 9.5.sp,
                  fontFamily = FontFamily.Monospace
                ),
                color = MaterialTheme.colorScheme.outline
              )
            }

            IconButton(
              onClick = {
                val fullText = state.logs.joinToString("\n") { it.message }
                clipboardManager.setText(AnnotatedString(fullText))
                Toast.makeText(context, "Execution log copied to clipboard", Toast.LENGTH_SHORT).show()
              },
              modifier = Modifier.size(24.dp)
            ) {
              Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = "Copy Log",
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(14.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          // Log Lines
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .heightIn(min = 120.dp, max = 220.dp)
              .padding(horizontal = 6.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            state.logs.forEach { log ->
              val textColor = when {
                log.isSuccess -> SuccessGreen
                log.isHighlight -> SecondaryCyan
                log.tag == "START" -> PrimaryIndigo
                else -> MaterialTheme.colorScheme.onSurfaceVariant
              }

              Text(
                text = log.message,
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 10.sp,
                  fontFamily = FontFamily.Monospace,
                  lineHeight = 15.sp
                ),
                color = textColor
              )
            }
          }
        }
      }
    }
  }

  // Raw JSON Payload Dialog
  if (showPayloadDialog) {
    AlertDialog(
      onDismissRequest = { showPayloadDialog = false },
      containerColor = MaterialTheme.colorScheme.surfaceContainer,
      title = {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Ingested Payload JSON",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(SecondaryCyan.copy(alpha = 0.2f))
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(
              text = state.selectedTrigger.id.uppercase(),
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              ),
              color = SecondaryCyan
            )
          }
        }
      },
      text = {
        val payloadJson = when (state.selectedTrigger) {
          SimulationTrigger.PHONE_CALL -> """
            {
              "event": "telephony.inbound_call",
              "call_sid": "CA983f4728d84a1e948a",
              "codec": "G.711u / Opus-HD",
              "caller": "+1 (555) 492-1823",
              "transcript": "Hi, I need to reschedule my consultation with Dr. Vance for Thursday morning please.",
              "detected_intent": "CLINICAL_RESCHEDULE",
              "confidence": 0.984
            }
          """.trimIndent()
          SimulationTrigger.WHATSAPP -> """
            {
              "object": "whatsapp_business_account",
              "entry": [{
                "changes": [{
                  "value": {
                    "messaging_product": "whatsapp",
                    "from": "+1 (555) 892-0041",
                    "message": "We have 10 fleet vehicles and need an automated telematics quote.",
                    "intent": "COMMERCIAL_QUOTE_REQUEST"
                  }
                }]
              }]
            }
          """.trimIndent()
          SimulationTrigger.WEB_FORM -> """
            {
              "form_id": "enterprise_landing_lead",
              "timestamp": "2026-10-01T17:08:42Z",
              "organization": "Apex Commercial Holdings",
              "contact_email": "leasing@apexholdings.com",
              "space_requirement": "12,000 sq ft",
              "priority_score": "HIGH"
            }
          """.trimIndent()
        }

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(10.dp)
        ) {
          Text(
            text = payloadJson,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.5.sp,
              fontFamily = FontFamily.Monospace,
              lineHeight = 14.sp
            ),
            color = SecondaryCyan
          )
        }
      },
      confirmButton = {
        Button(
          onClick = { showPayloadDialog = false },
          colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer)
        ) {
          Text("Close")
        }
      }
    )
  }
}

@Composable
private fun PipelineStepRow(step: PipelineStep) {
  val stepBorderColor = when (step.status) {
    StepStatus.COMPLETED -> SecondaryCyan.copy(alpha = 0.7f)
    StepStatus.IN_PROGRESS -> PrimaryIndigo
    StepStatus.PENDING -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f)
  }

  val statusBg = when (step.status) {
    StepStatus.COMPLETED -> SecondaryCyan.copy(alpha = 0.15f)
    StepStatus.IN_PROGRESS -> PrimaryContainer.copy(alpha = 0.25f)
    StepStatus.PENDING -> MaterialTheme.colorScheme.surfaceContainerHighest
  }

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(10.dp))
      .background(MaterialTheme.colorScheme.surfaceContainer)
      .border(1.dp, stepBorderColor, RoundedCornerShape(10.dp))
      .padding(10.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.weight(1f)
      ) {
        // Step number indicator
        Box(
          modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(statusBg),
          contentAlignment = Alignment.Center
        ) {
          if (step.status == StepStatus.COMPLETED) {
            Icon(Icons.Default.Check, contentDescription = null, tint = SecondaryCyan, modifier = Modifier.size(16.dp))
          } else if (step.status == StepStatus.IN_PROGRESS) {
            CircularProgressIndicator(modifier = Modifier.size(16.dp), color = PrimaryIndigo, strokeWidth = 2.dp)
          } else {
            Text(
              text = "0${step.stepNumber}",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              ),
              color = MaterialTheme.colorScheme.outline
            )
          }
        }

        Column {
          Text(
            text = step.title,
            style = MaterialTheme.typography.titleMedium.copy(
              fontSize = 12.5.sp,
              fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = step.description,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.5.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      // Latency badge if completed
      if (step.status == StepStatus.COMPLETED) {
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(SecondaryCyan.copy(alpha = 0.15f))
            .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
          Text(
            text = "${step.latencyMs}ms",
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
