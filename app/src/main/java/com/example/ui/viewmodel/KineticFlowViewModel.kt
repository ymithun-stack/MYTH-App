package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

enum class AppTab(val title: String, val icon: String) {
  HOME("Overview", "home"),
  SIMULATOR("Live Demo", "play_circle"),
  EXPLORER("Process Matrix", "hub"),
  INDUSTRIES("Industries", "business_center"),
  AUDIT("AI Audit", "assignment_turned_in")
}

data class SimulatorUiState(
  val selectedTrigger: SimulationTrigger = SimulationTrigger.PHONE_CALL,
  val isRunning: Boolean = false,
  val isCompleted: Boolean = false,
  val elapsedTimeSeconds: Double = 0.0,
  val steps: List<PipelineStep> = defaultSteps(),
  val logs: List<LogEntry> = defaultLogs(),
  val activeClusterThroughput: String = "1.8k req/min",
  val activeClusterLatency: String = "42ms"
)

private fun defaultSteps(): List<PipelineStep> = listOf(
  PipelineStep(1, "Inbound Trigger", "Ready to ingest payload packet.", StepStatus.PENDING),
  PipelineStep(2, "AI Reasoning", "Intent classification & entity extraction.", StepStatus.PENDING),
  PipelineStep(3, "Calendar Query", "Executive schedule slot check.", StepStatus.PENDING),
  PipelineStep(4, "CRM Sync", "Salesforce & HubSpot record write.", StepStatus.PENDING),
  PipelineStep(5, "Multi-Channel Dispatched", "SMS, invite & Slack alert sent.", StepStatus.PENDING)
)

private fun defaultLogs(): List<LogEntry> = listOf(
  LogEntry("0.00s", "SYS", "$ System initialized. Ready to execute live pipeline simulation.", isHighlight = false)
)

data class ExplorerUiState(
  val selectedDepartmentId: String = "sales",
  val teamMembersCount: Int = 18
)

data class AuditFormState(
  val fullName: String = "",
  val email: String = "",
  val company: String = "",
  val phone: String = "",
  val selectedIndustry: String = "Healthcare & Medical Practice",
  val selectedBottleneck: String = "Unanswered inbound customer phone calls / After-hours",
  val teamSize: String = "10–50",
  val additionalNotes: String = "",
  val isSubmitting: Boolean = false,
  val isSubmitted: Boolean = false,
  val submissionConfirmationId: String = ""
)

class KineticFlowViewModel : ViewModel() {

  private val _currentTab = MutableStateFlow(AppTab.HOME)
  val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

  private val _simulatorState = MutableStateFlow(SimulatorUiState())
  val simulatorState: StateFlow<SimulatorUiState> = _simulatorState.asStateFlow()

  private val _explorerState = MutableStateFlow(ExplorerUiState())
  val explorerState: StateFlow<ExplorerUiState> = _explorerState.asStateFlow()

  private val _auditFormState = MutableStateFlow(AuditFormState())
  val auditFormState: StateFlow<AuditFormState> = _auditFormState.asStateFlow()

  private val _expandedFaqIndices = MutableStateFlow<Set<Int>>(setOf(0))
  val expandedFaqIndices: StateFlow<Set<Int>> = _expandedFaqIndices.asStateFlow()

  private var simJob: Job? = null

  fun setTab(tab: AppTab) {
    _currentTab.value = tab
  }

  fun setSimulationTrigger(trigger: SimulationTrigger) {
    if (_simulatorState.value.isRunning) return
    _simulatorState.update { current ->
      current.copy(
        selectedTrigger = trigger,
        isCompleted = false,
        elapsedTimeSeconds = 0.0,
        steps = defaultSteps(),
        logs = listOf(
          LogEntry("0.00s", "SYS", "$ Inbound trigger set to: ${trigger.title} (${trigger.subtitle}). Ready to run simulation.")
        )
      )
    }
  }

  fun runSimulation() {
    if (_simulatorState.value.isRunning) return
    simJob?.cancel()

    simJob = viewModelScope.launch {
      val trigger = _simulatorState.value.selectedTrigger

      _simulatorState.update {
        it.copy(
          isRunning = true,
          isCompleted = false,
          elapsedTimeSeconds = 0.0,
          steps = defaultSteps().map { s -> s.copy(status = StepStatus.PENDING) },
          logs = listOf(
            LogEntry("0.00s", "START", "Starting autonomous pipeline for [${trigger.title}]...", isHighlight = true)
          )
        )
      }

      val stepsData = when (trigger) {
        SimulationTrigger.PHONE_CALL -> listOf(
          Triple(1, 0.12, "[0.12s] [INGEST] VoIP SIP trunk audio stream established. TLS 1.3 encrypted connection open."),
          Triple(2, 0.44, "[0.44s] [REASONING] Audio acoustic model: \"Patient rescheduling orthopedic follow-up for knee evaluation\"."),
          Triple(3, 0.78, "[0.78s] [CALENDAR API] Checking Dr. Vance's Clinic schedule. Slot reserved: Thursday at 10:30 AM EST."),
          Triple(4, 1.15, "[1.15s] [CRM/EHR POST] Updated Epic Systems EHR & HubSpot with audio transcript & confirmed tags."),
          Triple(5, 1.48, "[1.48s] [DISPATCH] Automated SMS confirmation dispatched to patient + Clinic staff calendar updated.")
        )
        SimulationTrigger.WHATSAPP -> listOf(
          Triple(1, 0.10, "[0.10s] [INGEST] Meta Cloud WhatsApp API webhook received. JSON payload verified with SHA-256 HMAC."),
          Triple(2, 0.38, "[0.38s] [REASONING] Conversational RAG parses inquiry: \"Requesting quote for 10-unit fleet logistics telematics\"."),
          Triple(3, 0.72, "[0.72s] [DYNAMIC PDF] Rendered custom enterprise proposal PDF and queried inventory availability."),
          Triple(4, 1.08, "[1.08s] [CRM POST] Opportunity created in Salesforce (Tier 1 Enterprise). Deal score: 94/100."),
          Triple(5, 1.39, "[1.39s] [DISPATCH] PDF Quote sent directly in WhatsApp chat + Senior Account Exec alerted in Slack.")
        )
        SimulationTrigger.WEB_FORM -> listOf(
          Triple(1, 0.11, "[0.11s] [INGEST] Inbound HTTPS POST received from Enterprise Landing Portal (2.4KB JSON)."),
          Triple(2, 0.41, "[0.41s] [REASONING] Intent classifier: \"Commercial Real Estate Leasing - 12,000 sq ft Office Space\"."),
          Triple(3, 0.75, "[0.75s] [CALENDAR API] Verified Broker availability in Google Workspace. Slot confirmed: Tomorrow at 2:00 PM EST."),
          Triple(4, 1.12, "[1.12s] [CRM POST] HubSpot company domain enriched via Clearbit API & assigned to East Coast Lead Queue."),
          Triple(5, 1.45, "[1.45s] [DISPATCH] Calendar invite + property brochure dispatched via SendGrid + push notification to Broker.")
        )
      }

      for ((stepNum, timeSec, logText) in stepsData) {
        // Update current step to in progress
        _simulatorState.update { current ->
          val updatedSteps = current.steps.map { s ->
            if (s.stepNumber == stepNum) s.copy(status = StepStatus.IN_PROGRESS) else s
          }
          current.copy(steps = updatedSteps)
        }

        delay(550)

        // Mark current step completed, add log
        _simulatorState.update { current ->
          val updatedSteps = current.steps.map { s ->
            if (s.stepNumber == stepNum) s.copy(status = StepStatus.COMPLETED, latencyMs = (timeSec * 1000).toLong()) else s
          }
          val newLogs = current.logs + LogEntry(
            timestamp = String.format(Locale.US, "%.2fs", timeSec),
            tag = "STEP $stepNum",
            message = logText,
            isHighlight = stepNum == 5
          )
          current.copy(
            steps = updatedSteps,
            logs = newLogs,
            elapsedTimeSeconds = timeSec
          )
        }
      }

      // Finish simulation
      delay(300)
      _simulatorState.update { current ->
        current.copy(
          isRunning = false,
          isCompleted = true,
          logs = current.logs + LogEntry(
            timestamp = String.format(Locale.US, "%.2fs", current.elapsedTimeSeconds),
            tag = "COMPLETE",
            message = "✓ Full autonomous cycle completed in ${String.format(Locale.US, "%.2f", current.elapsedTimeSeconds)}s with 0 human bottleneck.",
            isSuccess = true
          )
        )
      }
    }
  }

  fun resetSimulation() {
    simJob?.cancel()
    _simulatorState.update {
      it.copy(
        isRunning = false,
        isCompleted = false,
        elapsedTimeSeconds = 0.0,
        steps = defaultSteps(),
        logs = defaultLogs()
      )
    }
  }

  fun selectExplorerDepartment(deptId: String) {
    _explorerState.update { it.copy(selectedDepartmentId = deptId) }
  }

  fun updateTeamMembersCount(count: Int) {
    _explorerState.update { it.copy(teamMembersCount = count) }
  }

  fun updateAuditForm(
    fullName: String? = null,
    email: String? = null,
    company: String? = null,
    phone: String? = null,
    industry: String? = null,
    bottleneck: String? = null,
    teamSize: String? = null,
    notes: String? = null
  ) {
    _auditFormState.update { current ->
      current.copy(
        fullName = fullName ?: current.fullName,
        email = email ?: current.email,
        company = company ?: current.company,
        phone = phone ?: current.phone,
        selectedIndustry = industry ?: current.selectedIndustry,
        selectedBottleneck = bottleneck ?: current.selectedBottleneck,
        teamSize = teamSize ?: current.teamSize,
        additionalNotes = notes ?: current.additionalNotes
      )
    }
  }

  fun submitAuditRequest() {
    val state = _auditFormState.value
    if (state.fullName.isBlank() || state.email.isBlank()) return

    viewModelScope.launch {
      _auditFormState.update { it.copy(isSubmitting = true) }
      delay(1200) // Realistic network submission simulation
      val randomRef = "KF-" + (1000..9999).random()
      _auditFormState.update {
        it.copy(
          isSubmitting = false,
          isSubmitted = true,
          submissionConfirmationId = randomRef
        )
      }
    }
  }

  fun resetAuditForm() {
    _auditFormState.value = AuditFormState()
  }

  fun toggleFaq(index: Int) {
    _expandedFaqIndices.update { current ->
      if (current.contains(index)) current - index else current + index
    }
  }
}
