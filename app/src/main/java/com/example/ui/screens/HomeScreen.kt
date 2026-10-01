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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.*
import com.example.ui.components.CentralEngineVisual
import com.example.ui.theme.*

@Composable
fun HomeScreen(
  onNavigateToSimulator: () -> Unit,
  onNavigateToExplorer: () -> Unit,
  onNavigateToAudit: () -> Unit,
  expandedFaqs: Set<Int>,
  onToggleFaq: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(bottom = 32.dp)
  ) {
    // 1. HERO SECTION
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                PrimaryContainer.copy(alpha = 0.15f),
                SecondaryCyan.copy(alpha = 0.05f),
                Color.Transparent
              )
            )
          )
          .padding(horizontal = 16.dp, vertical = 20.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
          // Eyebrow Badge
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(MaterialTheme.colorScheme.surfaceContainerHigh)
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
              .padding(horizontal = 12.dp, vertical = 6.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(7.dp)
                  .clip(CircleShape)
                  .background(SecondaryCyan)
              )
              Text(
                text = "AI + AUTOMATION SOLUTIONS • ENTERPRISE SYSTEMS",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 9.5.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 0.8.sp
                ),
                color = SecondaryCyan
              )
            }
          }

          // Main Headline with Gradient
          Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
              text = "Turn Repetitive Work Into",
              style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 34.sp,
                letterSpacing = (-0.5).sp
              ),
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "Intelligent Automation.",
              style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 34.sp,
                letterSpacing = (-0.5).sp,
                brush = Brush.linearGradient(
                  colors = listOf(PrimaryIndigo, SecondaryCyan, TertiaryBlue)
                )
              )
            )
          }

          Text(
            text = "Build AI-powered systems that answer, analyze, automate and operate — so your executive team and staff can focus on high-leverage strategic growth.",
            style = MaterialTheme.typography.bodyMedium.copy(
              lineHeight = 20.sp,
              fontSize = 13.5.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          // CTA Action Group
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = onNavigateToAudit,
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
              ),
              modifier = Modifier
                .weight(1f)
                .height(46.dp)
                .testTag("hero_book_audit_button")
            ) {
              Icon(Icons.Default.Bolt, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Book AI Audit",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
              )
            }

            OutlinedButton(
              onClick = onNavigateToSimulator,
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.outlinedButtonColors(
                contentColor = SecondaryCyan
              ),
              border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = Brush.horizontalGradient(listOf(SecondaryCyan, PrimaryIndigo))
              ),
              modifier = Modifier
                .weight(1f)
                .height(46.dp)
                .testTag("hero_demo_button")
            ) {
              Icon(Icons.Default.PlayCircle, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Live Demo",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
              )
            }
          }

          // Scope pills
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.6f))
              .padding(10.dp)
          ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "ARCHITECTURE SCOPE",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 8.5.sp,
                  fontFamily = FontFamily.Monospace,
                  letterSpacing = 1.sp
                ),
                color = MaterialTheme.colorScheme.outline
              )
              Text(
                text = "AI Agents • Workflow Automation • Voice AI • Call Analytics • ERP & CRM Integrations",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          // Central Visual Engine Component
          CentralEngineVisual(
            modifier = Modifier.clickable { onNavigateToSimulator() }
          )
        }
      }
    }

    // 2. OPERATIONAL INEFFICIENCY AUDIT (PROBLEMS)
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surfaceContainerLowest)
          .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(PrimaryIndigo)
            )
            Text(
              text = "OPERATIONAL INEFFICIENCY AUDIT",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              ),
              color = PrimaryIndigo
            )
          }

          Text(
            text = "Your team shouldn't spend hours doing work AI can handle.",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
          )

          Text(
            text = "High-growth organizations leak up to 34% of revenue through delayed responses, fragmented handoffs, and human manual bottlenecks.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        // 6 Problem cards
        KineticRepository.problemAudits.forEach { problem ->
          ProblemAuditCard(problem = problem)
        }
      }
    }

    // 3. CORE SOLUTIONS MATRIX
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "SYSTEMS & ENGINEERING MATRIX",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            ),
            color = SecondaryCyan
          )
          Text(
            text = "KineticFlow Automation Architecture",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Engineered for operational durability. Runs on fault-tolerant infrastructure with enterprise SOC-2 security posture.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        KineticRepository.solutions.forEach { solution ->
          SolutionMatrixCard(solution = solution)
        }
      }
    }

    // 4. EXECUTIVE IMPACT COMPARISON TABLE
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surfaceContainerLowest)
          .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "EXECUTIVE IMPACT ANALYSIS",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            ),
            color = PrimaryIndigo
          )
          Text(
            text = "Manual Friction vs Kinetic Infrastructure",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
          )
        }

        ComparisonTableCard()
      }
    }

    // 5. 4-STEP METHODOLOGY FRAMEWORK
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "EXECUTION METHODOLOGY",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            ),
            color = SecondaryCyan
          )
          Text(
            text = "4-Step Predictable Deployment Framework",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "From initial operational audit to self-sustaining production deployment in under 3 weeks.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        KineticRepository.deploymentSteps.forEach { step ->
          DeploymentStepCard(step = step)
        }
      }
    }

    // 6. TECH ECOSYSTEM CHIPS
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surfaceContainerLowest)
          .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Text(
          text = "ENTERPRISE TOOLING & INTEGRATION STACK",
          style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 9.sp,
            fontFamily = FontFamily.Monospace
          ),
          color = MaterialTheme.colorScheme.outline
        )
        LazyRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          contentPadding = PaddingValues(end = 16.dp)
        ) {
          items(KineticRepository.techEcosystem) { tech ->
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
              Text(
                text = tech,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 11.5.sp),
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        }
      }
    }

    // 7. FREQUENTLY ASKED QUESTIONS
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "ENGINEERING CLARITY",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            ),
            color = SecondaryCyan
          )
          Text(
            text = "Frequently Asked Questions",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
          )
        }

        KineticRepository.faqs.forEachIndexed { index, faq ->
          val isExpanded = expandedFaqs.contains(index)
          Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .clickable { onToggleFaq(index) }
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = faq.question,
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.SemiBold
                  ),
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.weight(1f)
                )
                Icon(
                  imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }

              AnimatedVisibility(visible = isExpanded) {
                Column {
                  Spacer(modifier = Modifier.height(10.dp))
                  Text(
                    text = faq.answer,
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun ProblemAuditCard(problem: ProblemAudit) {
  Card(
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    shape = RoundedCornerShape(14.dp),
    modifier = Modifier
      .fillMaxWidth()
      .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
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
        val iconVec: ImageVector = when (problem.icon) {
          "phone_missed" -> Icons.Default.PhoneMissed
          "forward_to_inbox" -> Icons.Default.ForwardToInbox
          "sync_problem" -> Icons.Default.SyncProblem
          "hourglass_bottom" -> Icons.Default.HourglassBottom
          "mic_off" -> Icons.Default.MicOff
          else -> Icons.Default.Rule
        }

        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHighest),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = iconVec,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(20.dp)
          )
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text(
            text = problem.frictionNumber,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.error
          )
        }
      }

      Text(
        text = problem.title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )

      Text(
        text = problem.problemDescription,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      // Automated solution box
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(10.dp))
          .background(MaterialTheme.colorScheme.surfaceContainer)
          .padding(10.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
          ) {
            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = null,
              tint = SecondaryCyan,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = problem.solutionHeadline,
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
              ),
              color = SecondaryCyan
            )
          }
          Text(
            text = problem.solutionDescription,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }
    }
  }
}

@Composable
private fun SolutionMatrixCard(solution: SolutionMatrixItem) {
  val iconVec: ImageVector = when (solution.icon) {
    "record_voice_over" -> Icons.Default.RecordVoiceOver
    "mark_chat_unread" -> Icons.Default.MarkChatUnread
    "hub" -> Icons.Default.Hub
    "analytics" -> Icons.Default.Analytics
    "diversity_3" -> Icons.Default.Diversity3
    else -> Icons.Default.Lan
  }
  val accentColor = if (solution.isAccentCyan) SecondaryCyan else PrimaryIndigo

  Card(
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    shape = RoundedCornerShape(14.dp),
    modifier = Modifier
      .fillMaxWidth()
      .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
  ) {
    Column {
      Column(
        modifier = Modifier.padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(accentColor.copy(alpha = 0.15f)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = iconVec,
            contentDescription = null,
            tint = accentColor,
            modifier = Modifier.size(24.dp)
          )
        }

        Text(
          text = solution.title,
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = solution.description,
          style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      // Capabilities footer
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.7f))
          .padding(horizontal = 14.dp, vertical = 10.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "CAPABILITIES",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 8.5.sp,
              fontFamily = FontFamily.Monospace
            ),
            color = SecondaryCyan
          )
          solution.capabilities.forEach { cap ->
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(4.dp)
                  .clip(CircleShape)
                  .background(SecondaryCyan)
              )
              Text(
                text = cap,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun ComparisonTableCard() {
  val metrics = listOf(
    Triple("Lead Response Velocity", "3 to 5 hours (often drops after-hours)", "< 30 seconds 24/7 across all channels"),
    Triple("Call Ingestion Capacity", "1 call per human staff; busy holds", "Infinite concurrency; 0 callers held"),
    Triple("Data Entry Accuracy", "Fragmented notes, typos, missing fields", "100% structured JSON & auto tagging"),
    Triple("Operating Availability", "8 hrs/day, 5 days/week (shifts)", "24 hrs/day, 365 days/year"),
    Triple("Staff Utilization", "Trapped in repetitive scheduling & triage", "Elevated to strategic client closing"),
    Triple("Cost Per Ticket", "$18.50 - $34.00 (loaded labor)", "$0.45 - $1.20 per resolved ticket")
  )

  Card(
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    shape = RoundedCornerShape(14.dp),
    modifier = Modifier
      .fillMaxWidth()
      .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f), RoundedCornerShape(14.dp))
  ) {
    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      metrics.forEach { (metric, manual, kinetic) ->
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f))
            .padding(10.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(
            text = metric,
            style = MaterialTheme.typography.titleMedium.copy(
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface
          )
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "TRADITIONAL",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 8.sp,
                  color = MaterialTheme.colorScheme.error,
                  fontFamily = FontFamily.Monospace
                )
              )
              Text(
                text = manual,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1.2f)) {
              Text(
                text = "KINETICFLOW",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 8.sp,
                  color = SecondaryCyan,
                  fontWeight = FontWeight.Bold,
                  fontFamily = FontFamily.Monospace
                )
              )
              Text(
                text = kinetic,
                style = MaterialTheme.typography.bodySmall.copy(
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Medium
                ),
                color = SecondaryCyan
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun DeploymentStepCard(step: DeploymentStep) {
  Card(
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    shape = RoundedCornerShape(12.dp),
    modifier = Modifier
      .fillMaxWidth()
      .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
  ) {
    Column(
      modifier = Modifier.padding(14.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = step.number,
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
          ),
          color = PrimaryIndigo.copy(alpha = 0.4f)
        )
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(SecondaryCyan.copy(alpha = 0.15f))
            .padding(horizontal = 7.dp, vertical = 2.dp)
        ) {
          Text(
            text = step.timeline,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace
            ),
            color = SecondaryCyan
          )
        }
      }

      Text(
        text = step.title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )

      Text(
        text = step.description,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}
