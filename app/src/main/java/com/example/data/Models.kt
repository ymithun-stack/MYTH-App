package com.example.data

enum class SimulationTrigger(
  val id: String,
  val title: String,
  val subtitle: String,
  val iconName: String
) {
  PHONE_CALL(
    id = "call",
    title = "Incoming Phone Call",
    subtitle = "VoIP / SIP Trunk Audio Packet",
    iconName = "phone_in_talk"
  ),
  WHATSAPP(
    id = "whatsapp",
    title = "WhatsApp Quote Request",
    subtitle = "Meta Cloud Webhook JSON",
    iconName = "chat"
  ),
  WEB_FORM(
    id = "form",
    title = "Inbound Web Form",
    subtitle = "HTTPS Lead Ingestion API",
    iconName = "web"
  )
}

enum class StepStatus {
  PENDING,
  IN_PROGRESS,
  COMPLETED
}

data class PipelineStep(
  val stepNumber: Int,
  val title: String,
  val description: String,
  val status: StepStatus = StepStatus.PENDING,
  val latencyMs: Long = 0
)

data class LogEntry(
  val timestamp: String,
  val tag: String,
  val message: String,
  val isHighlight: Boolean = false,
  val isSuccess: Boolean = false
)

data class ProblemAudit(
  val id: String,
  val frictionNumber: String,
  val title: String,
  val problemDescription: String,
  val solutionHeadline: String,
  val solutionDescription: String,
  val icon: String
)

data class SolutionMatrixItem(
  val id: String,
  val title: String,
  val description: String,
  val icon: String,
  val capabilities: List<String>,
  val isAccentCyan: Boolean = false
)

data class ExplorerSection(
  val id: String,
  val label: String,
  val title: String,
  val beforeTitle: String,
  val beforeTimeLost: String,
  val beforePoints: List<String>,
  val afterTitle: String,
  val afterTimeSaved: String,
  val afterPoints: List<String>
)

data class IndustrySolution(
  val id: String,
  val title: String,
  val description: String,
  val badge: String,
  val icon: String,
  val isAccentCyan: Boolean = false
)

data class CaseStudy(
  val id: String,
  val clientName: String,
  val industry: String,
  val title: String,
  val problem: String,
  val blueprint: String,
  val businessValue: String
)

data class DeploymentStep(
  val number: String,
  val title: String,
  val description: String,
  val timeline: String
)

data class FaqItem(
  val question: String,
  val answer: String
)

data class AuditSubmission(
  val fullName: String,
  val email: String,
  val company: String,
  val phone: String,
  val industry: String,
  val bottleneck: String,
  val teamSize: String = "10-50",
  val additionalNotes: String = ""
)
