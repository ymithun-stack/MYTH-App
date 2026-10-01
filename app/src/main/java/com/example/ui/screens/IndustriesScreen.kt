package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CaseStudy
import com.example.data.IndustrySolution
import com.example.data.KineticRepository
import com.example.ui.theme.*

@Composable
fun IndustriesScreen(
  onScheduleAuditForIndustry: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(18.dp)
  ) {
    // Header
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
            text = "VERTICAL APPLICATIONS",
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
          text = "Engineered For Your Specific Industry",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
          ),
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = "Pre-validated prompt sets, HIPAA/SOC-ready pipelines, and turnkey logic maps customized to operational industry demands.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    // 6 Vertical Industry Cards
    items(KineticRepository.industries) { industry ->
      IndustryCard(
        industry = industry,
        onAuditClick = { onScheduleAuditForIndustry(industry.title) }
      )
    }

    // Divider Header for Production Case Studies
    item {
      Spacer(modifier = Modifier.height(10.dp))
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier
              .size(8.dp)
              .clip(CircleShape)
              .background(PrimaryIndigo)
          )
          Text(
            text = "PRODUCTION CASE STUDIES",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace,
              letterSpacing = 0.8.sp
            ),
            color = PrimaryIndigo
          )
        }

        Text(
          text = "Battle-Tested Implementations",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
          ),
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = "Real enterprise architectures engineered to eliminate manual workload and capture operational leverage.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    // 3 Case Studies
    items(KineticRepository.caseStudies) { caseStudy ->
      CaseStudyCard(caseStudy = caseStudy)
    }
  }
}

@Composable
private fun IndustryCard(
  industry: IndustrySolution,
  onAuditClick: () -> Unit
) {
  val iconVec: ImageVector = when (industry.icon) {
    "local_hospital" -> Icons.Default.LocalHospital
    "domain" -> Icons.Default.Domain
    "gavel" -> Icons.Default.Gavel
    "local_shipping" -> Icons.Default.LocalShipping
    "directions_car" -> Icons.Default.DirectionsCar
    else -> Icons.Default.Restaurant
  }

  val accentColor = if (industry.isAccentCyan) SecondaryCyan else PrimaryIndigo

  Card(
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
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
            .size(38.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHighest),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = iconVec,
            contentDescription = null,
            tint = accentColor,
            modifier = Modifier.size(20.dp)
          )
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Verified,
            contentDescription = null,
            tint = accentColor,
            modifier = Modifier.size(14.dp)
          )
          Text(
            text = industry.badge,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold
            ),
            color = accentColor
          )
        }
      }

      Text(
        text = industry.title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurface
      )

      Text(
        text = industry.description,
        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Button(
        onClick = onAuditClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
          contentColor = MaterialTheme.colorScheme.onSurface
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        modifier = Modifier.align(Alignment.End)
      ) {
        Text(
          text = "Configure For This Vertical",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(13.dp))
      }
    }
  }
}

@Composable
private fun CaseStudyCard(caseStudy: CaseStudy) {
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
            .clip(RoundedCornerShape(4.dp))
            .background(SecondaryCyan.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text(
            text = caseStudy.clientName,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold
            ),
            color = SecondaryCyan
          )
        }

        Text(
          text = caseStudy.industry,
          style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.5.sp),
          color = MaterialTheme.colorScheme.outline
        )
      }

      Text(
        text = caseStudy.title,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          fontSize = 14.sp
        ),
        color = MaterialTheme.colorScheme.onSurface
      )

      Text(
        text = caseStudy.problem,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      // Blueprint box
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(MaterialTheme.colorScheme.surfaceContainer)
          .padding(10.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = "ARCHITECTURE BLUEPRINT",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 8.5.sp,
              fontFamily = FontFamily.Monospace
            ),
            color = MaterialTheme.colorScheme.outline
          )
          Text(
            text = caseStudy.blueprint,
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 10.sp,
              fontFamily = FontFamily.Monospace,
              lineHeight = 15.sp
            ),
            color = PrimaryIndigo
          )
        }
      }

      // Delivered Value
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(SecondaryCyan.copy(alpha = 0.08f))
          .padding(10.dp)
      ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
          Text(
            text = "DELIVERED BUSINESS VALUE",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 8.5.sp,
              fontFamily = FontFamily.Monospace,
              fontWeight = FontWeight.Bold
            ),
            color = SecondaryCyan
          )
          Text(
            text = caseStudy.businessValue,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp),
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }
    }
  }
}
