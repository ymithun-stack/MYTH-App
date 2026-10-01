package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.KineticBottomBar
import com.example.ui.components.KineticTopBar
import com.example.ui.screens.*
import com.example.ui.theme.KineticFlowTheme
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.KineticFlowViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      KineticFlowTheme {
        KineticFlowApp()
      }
    }
  }
}

@Composable
fun KineticFlowApp(
  viewModel: KineticFlowViewModel = viewModel()
) {
  val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
  val simulatorState by viewModel.simulatorState.collectAsStateWithLifecycle()
  val explorerState by viewModel.explorerState.collectAsStateWithLifecycle()
  val auditFormState by viewModel.auditFormState.collectAsStateWithLifecycle()
  val expandedFaqs by viewModel.expandedFaqIndices.collectAsStateWithLifecycle()

  // Handle Android physical/gesture back button to return to Home tab if on sub-tab
  BackHandler(enabled = currentTab != AppTab.HOME) {
    viewModel.setTab(AppTab.HOME)
  }

  Scaffold(
    topBar = {
      KineticTopBar(
        onScheduleAuditClick = { viewModel.setTab(AppTab.AUDIT) }
      )
    },
    bottomBar = {
      KineticBottomBar(
        currentTab = currentTab,
        onTabSelected = { viewModel.setTab(it) }
      )
    },
    containerColor = MaterialTheme.colorScheme.background,
    modifier = Modifier.fillMaxSize()
  ) { innerPadding ->
    Crossfade(
      targetState = currentTab,
      animationSpec = tween(250),
      label = "tab_transition",
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(MaterialTheme.colorScheme.background)
    ) { tab ->
      when (tab) {
        AppTab.HOME -> {
          HomeScreen(
            onNavigateToSimulator = { viewModel.setTab(AppTab.SIMULATOR) },
            onNavigateToExplorer = { viewModel.setTab(AppTab.EXPLORER) },
            onNavigateToAudit = { viewModel.setTab(AppTab.AUDIT) },
            expandedFaqs = expandedFaqs,
            onToggleFaq = { viewModel.toggleFaq(it) }
          )
        }
        AppTab.SIMULATOR -> {
          SimulatorScreen(
            state = simulatorState,
            onSelectTrigger = { viewModel.setSimulationTrigger(it) },
            onRunSimulation = { viewModel.runSimulation() },
            onResetSimulation = { viewModel.resetSimulation() }
          )
        }
        AppTab.EXPLORER -> {
          ExplorerScreen(
            state = explorerState,
            onSelectDepartment = { viewModel.selectExplorerDepartment(it) },
            onUpdateTeamCount = { viewModel.updateTeamMembersCount(it) },
            onScheduleAuditForDept = { deptName ->
              viewModel.updateAuditForm(notes = "Inquiring about automation for $deptName department.")
              viewModel.setTab(AppTab.AUDIT)
            }
          )
        }
        AppTab.INDUSTRIES -> {
          IndustriesScreen(
            onScheduleAuditForIndustry = { industryName ->
              viewModel.updateAuditForm(industry = industryName)
              viewModel.setTab(AppTab.AUDIT)
            }
          )
        }
        AppTab.AUDIT -> {
          AuditScreen(
            state = auditFormState,
            onUpdateForm = { fn, em, comp, ph, ind, bot, ts, n ->
              viewModel.updateAuditForm(
                fullName = fn,
                email = em,
                company = comp,
                phone = ph,
                industry = ind,
                bottleneck = bot,
                teamSize = ts,
                notes = n
              )
            },
            onSubmit = { viewModel.submitAuditRequest() },
            onReset = { viewModel.resetAuditForm() }
          )
        }
      }
    }
  }
}
