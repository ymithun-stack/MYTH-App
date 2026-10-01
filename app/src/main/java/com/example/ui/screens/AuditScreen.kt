package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.AuditFormState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuditScreen(
  state: AuditFormState,
  onUpdateForm: (
    fullName: String?,
    email: String?,
    company: String?,
    phone: String?,
    industry: String?,
    bottleneck: String?,
    teamSize: String?,
    notes: String?
  ) -> Unit,
  onSubmit: () -> Unit,
  onReset: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var industryExpanded by remember { mutableStateOf(false) }
  var bottleneckExpanded by remember { mutableStateOf(false) }

  val industryOptions = listOf(
    "Healthcare & Medical Practice",
    "Real Estate & Property Development",
    "Professional Services & Legal",
    "Logistics & Supply Chain",
    "Automotive Dealership",
    "Financial Services",
    "Other Enterprise Domain"
  )

  val bottleneckOptions = listOf(
    "Unanswered inbound customer phone calls / After-hours",
    "Manual multi-touch follow-up sequences",
    "Disconnected data re-entry between ERP & CRM",
    "Overwhelmed customer support ticket volume",
    "Internal operational approvals & coordination delay"
  )

  val teamSizeOptions = listOf("1–10", "10–50", "50–250", "250+")

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header & Value Proposition
    item {
      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
            text = "OPERATIONAL DIAGNOSTIC",
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
          text = "Not sure what to automate first?",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
          ),
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = "Book a complimentary 30-minute operational audit. We will analyze your team's workflow and map 3 specific processes where AI agents can deliver immediate ROI within 30 days.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Trust Pillars
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          TrustItem(icon = Icons.Default.VerifiedUser, text = "100% Confidential • NDA protected before review")
          TrustItem(icon = Icons.Default.Schedule, text = "Zero sales fluff • Actionable technical architecture brief")
          TrustItem(icon = Icons.Default.Engineering, text = "Delivered directly by Senior Automation Architects")
        }
      }
    }

    // Success State or Form
    if (state.isSubmitted) {
      item {
        Card(
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, SecondaryCyan.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
        ) {
          Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            Box(
              modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(SecondaryCyan.copy(alpha = 0.2f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = SecondaryCyan,
                modifier = Modifier.size(36.dp)
              )
            }

            Text(
              text = "Audit Request Received",
              style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
              ),
              color = MaterialTheme.colorScheme.onSurface
            )

            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
              Text(
                text = "REFERENCE: ${state.submissionConfirmationId}",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontFamily = FontFamily.Monospace,
                  fontWeight = FontWeight.Bold
                ),
                color = SecondaryCyan
              )
            }

            Text(
              text = "Thank you, ${state.fullName}. Our senior architect will review your workflow inputs for ${state.company} and email your calendar briefing invite to ${state.email} within 2 business hours.",
              style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 21.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Direct Communication Channels
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              OutlinedButton(
                onClick = {
                  val sendIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://wa.me/18005550199?text=Hello%20KineticFlow,%20inquiring%20about%20audit%20${state.submissionConfirmationId}")
                  }
                  try {
                    context.startActivity(sendIntent)
                  } catch (e: Exception) {
                    Toast.makeText(context, "WhatsApp direct link opened", Toast.LENGTH_SHORT).show()
                  }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
              ) {
                Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("WhatsApp", style = MaterialTheme.typography.labelSmall)
              }

              Button(
                onClick = onReset,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryContainer)
              ) {
                Text("New Request", style = MaterialTheme.typography.labelSmall)
              }
            }
          }
        }
      }
    } else {
      // Interactive Form
      item {
        Card(
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f), RoundedCornerShape(16.dp))
        ) {
          Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            Text(
              text = "CONSULTATION DIAGNOSTIC INTAKE",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
              ),
              color = MaterialTheme.colorScheme.outline
            )

            // Full Name Field
            OutlinedTextField(
              value = state.fullName,
              onValueChange = { onUpdateForm(it, null, null, null, null, null, null, null) },
              label = { Text("Full Name *") },
              placeholder = { Text("Elena Vance") },
              singleLine = true,
              modifier = Modifier
                .fillMaxWidth()
                .testTag("audit_input_name"),
              shape = RoundedCornerShape(10.dp),
              colors = customTextFieldColors()
            )

            // Work Email Field
            OutlinedTextField(
              value = state.email,
              onValueChange = { onUpdateForm(null, it, null, null, null, null, null, null) },
              label = { Text("Business Work Email *") },
              placeholder = { Text("elena@enterprise.com") },
              singleLine = true,
              modifier = Modifier
                .fillMaxWidth()
                .testTag("audit_input_email"),
              shape = RoundedCornerShape(10.dp),
              colors = customTextFieldColors()
            )

            // Company & Phone Row
            OutlinedTextField(
              value = state.company,
              onValueChange = { onUpdateForm(null, null, it, null, null, null, null, null) },
              label = { Text("Company / Organization") },
              placeholder = { Text("Apex Health Group") },
              singleLine = true,
              modifier = Modifier
                .fillMaxWidth()
                .testTag("audit_input_company"),
              shape = RoundedCornerShape(10.dp),
              colors = customTextFieldColors()
            )

            OutlinedTextField(
              value = state.phone,
              onValueChange = { onUpdateForm(null, null, null, it, null, null, null, null) },
              label = { Text("Direct Phone Number") },
              placeholder = { Text("+1 (555) 019-2831") },
              singleLine = true,
              modifier = Modifier
                .fillMaxWidth()
                .testTag("audit_input_phone"),
              shape = RoundedCornerShape(10.dp),
              colors = customTextFieldColors()
            )

            // Select Industry Exposed Dropdown
            ExposedDropdownMenuBox(
              expanded = industryExpanded,
              onExpandedChange = { industryExpanded = !industryExpanded }
            ) {
              OutlinedTextField(
                value = state.selectedIndustry,
                onValueChange = {},
                readOnly = true,
                label = { Text("Target Industry") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = industryExpanded) },
                modifier = Modifier
                  .fillMaxWidth()
                  .menuAnchor()
                  .testTag("audit_industry_dropdown"),
                shape = RoundedCornerShape(10.dp),
                colors = customTextFieldColors()
              )
              ExposedDropdownMenu(
                expanded = industryExpanded,
                onDismissRequest = { industryExpanded = false },
                modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
              ) {
                industryOptions.forEach { opt ->
                  DropdownMenuItem(
                    text = { Text(opt, style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                      onUpdateForm(null, null, null, null, opt, null, null, null)
                      industryExpanded = false
                    }
                  )
                }
              }
            }

            // Primary Operational Bottleneck Exposed Dropdown
            ExposedDropdownMenuBox(
              expanded = bottleneckExpanded,
              onExpandedChange = { bottleneckExpanded = !bottleneckExpanded }
            ) {
              OutlinedTextField(
                value = state.selectedBottleneck,
                onValueChange = {},
                readOnly = true,
                label = { Text("Primary Operational Bottleneck") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = bottleneckExpanded) },
                modifier = Modifier
                  .fillMaxWidth()
                  .menuAnchor()
                  .testTag("audit_bottleneck_dropdown"),
                shape = RoundedCornerShape(10.dp),
                colors = customTextFieldColors()
              )
              ExposedDropdownMenu(
                expanded = bottleneckExpanded,
                onDismissRequest = { bottleneckExpanded = false },
                modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer)
              ) {
                bottleneckOptions.forEach { opt ->
                  DropdownMenuItem(
                    text = { Text(opt, style = MaterialTheme.typography.bodySmall) },
                    onClick = {
                      onUpdateForm(null, null, null, null, null, opt, null, null)
                      bottleneckExpanded = false
                    }
                  )
                }
              }
            }

            // Team Size Chips
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "Approximate Team Size",
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                color = MaterialTheme.colorScheme.outline
              )
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                teamSizeOptions.forEach { sizeOption ->
                  val isSelected = state.teamSize == sizeOption
                  Box(
                    modifier = Modifier
                      .weight(1f)
                      .clip(RoundedCornerShape(8.dp))
                      .background(if (isSelected) SecondaryCyan else MaterialTheme.colorScheme.surfaceContainer)
                      .border(
                        1.dp,
                        if (isSelected) SecondaryCyan else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                        RoundedCornerShape(8.dp)
                      )
                      .clickable { onUpdateForm(null, null, null, null, null, null, sizeOption, null) }
                      .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(
                      text = sizeOption,
                      style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) OnSecondary else MaterialTheme.colorScheme.onSurface
                      )
                    )
                  }
                }
              }
            }

            // Submit Button
            val isFormValid = state.fullName.isNotBlank() && state.email.isNotBlank()
            Button(
              onClick = onSubmit,
              enabled = isFormValid && !state.isSubmitting,
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                disabledContentColor = MaterialTheme.colorScheme.outline
              ),
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("submit_audit_form_button")
            ) {
              if (state.isSubmitting) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimaryContainer)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Submitting Audit Request...", style = MaterialTheme.typography.labelLarge)
              } else {
                Text(
                  text = "Claim My Free Operational AI Audit",
                  style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun TrustItem(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = SecondaryCyan,
      modifier = Modifier.size(16.dp)
    )
    Text(
      text = text,
      style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )
  }
}

@Composable
private fun customTextFieldColors(): TextFieldColors {
  return OutlinedTextFieldDefaults.colors(
    focusedBorderColor = PrimaryIndigo,
    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
    focusedLabelColor = PrimaryIndigo,
    unfocusedLabelColor = MaterialTheme.colorScheme.outline,
    cursorColor = SecondaryCyan,
    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest
  )
}
