package com.example.data

object KineticRepository {

  val problemAudits = listOf(
    ProblemAudit(
      id = "p1",
      frictionNumber = "Friction Point #01",
      title = "Missed Calls & Inquiries",
      problemDescription = "Prospects reach out after business hours or wait on hold during surges. Leads turn cold within 15 minutes of non-response.",
      solutionHeadline = "AI AUTOMATES IN REAL-TIME",
      solutionDescription = "AI Voice Agents answer in 2 rings, qualify prospect intent, answer FAQs, and book calendar invites 24/7.",
      icon = "phone_missed"
    ),
    ProblemAudit(
      id = "p2",
      frictionNumber = "Friction Point #02",
      title = "Manual Multi-Touch Follow-ups",
      problemDescription = "Sales reps spend 3+ hours daily copy-pasting template emails and reminders across WhatsApp, SMS, and email inbox queues.",
      solutionHeadline = "AI AUTOMATES IN REAL-TIME",
      solutionDescription = "Autonomous agents execute personalized multi-channel cadences triggered immediately by prospect engagement signals.",
      icon = "forward_to_inbox"
    ),
    ProblemAudit(
      id = "p3",
      frictionNumber = "Friction Point #03",
      title = "Disconnected Data Entry",
      problemDescription = "Operations personnel re-keying customer addresses, invoices, and intake tickets between disparate ERPs, CRMs, and spreadsheets.",
      solutionHeadline = "AI AUTOMATES IN REAL-TIME",
      solutionDescription = "Zero-loss data synchronization pipelines with autonomous parsing, fuzzy matching, and validation in sub-seconds.",
      icon = "sync_problem"
    ),
    ProblemAudit(
      id = "p4",
      frictionNumber = "Friction Point #04",
      title = "Slow Support Triage",
      problemDescription = "Tier 1 repetitive inquiries take an average of 4.2 hours to resolve, causing customer frustration and employee burn-out.",
      solutionHeadline = "AI AUTOMATES IN REAL-TIME",
      solutionDescription = "Instant autonomous resolution for 75%+ routine queries with graceful, context-rich escalation to specialists.",
      icon = "hourglass_bottom"
    ),
    ProblemAudit(
      id = "p5",
      frictionNumber = "Friction Point #05",
      title = "Unanalyzed Sales Calls",
      problemDescription = "Hundreds of audio hours accumulate weekly with zero actionable intelligence extracted on objection trends or competitor churn.",
      solutionHeadline = "AI AUTOMATES IN REAL-TIME",
      solutionDescription = "Instant acoustic and semantic extraction of buying signals, competitor mentions, objection tags, and deal risk scores.",
      icon = "mic_off"
    ),
    ProblemAudit(
      id = "p6",
      frictionNumber = "Friction Point #06",
      title = "Operational Approval Delays",
      problemDescription = "Purchase orders, travel requests, and client onboarding authorizations trapped for days in static email reply loops.",
      solutionHeadline = "AI AUTOMATES IN REAL-TIME",
      solutionDescription = "Interactive Slack/Teams bots dispatch one-click authorization cards with instant contextual financial summaries.",
      icon = "rule"
    )
  )

  val solutions = listOf(
    SolutionMatrixItem(
      id = "s1",
      title = "AI Voice Agents",
      description = "Human-grade natural conversational voice agents for inbound phone reception, appointment scheduling, after-hours emergency triage, and warm human handoffs.",
      icon = "record_voice_over",
      capabilities = listOf(
        "Sub-450ms voice latency",
        "Direct SIP & PBX Phone Trunk Integration",
        "Dynamic calendar availability matching"
      ),
      isAccentCyan = false
    ),
    SolutionMatrixItem(
      id = "s2",
      title = "Autonomous Chat Agents",
      description = "Multi-channel conversational intelligence deployed across WhatsApp Business API, web portals, SMS, and Zendesk with multi-language parsing and automated lead qualification.",
      icon = "mark_chat_unread",
      capabilities = listOf(
        "Verified Meta Cloud WhatsApp API",
        "Knowledge Graph RAG Retrieval",
        "Dynamic PDF & Quote Generation"
      ),
      isAccentCyan = true
    ),
    SolutionMatrixItem(
      id = "s3",
      title = "End-to-End Workflow Automation",
      description = "Industrial orchestration linking Make, n8n, Zapier, custom Python microservices, webhooks, and REST APIs to automate complete administrative cycles without human touch.",
      icon = "hub",
      capabilities = listOf(
        "Self-healing error fallback triggers",
        "Full JSON payload audit trailing",
        "Zero data-leak protocol"
      ),
      isAccentCyan = false
    ),
    SolutionMatrixItem(
      id = "s4",
      title = "Sales Call Intelligence",
      description = "Automated acoustic & transcription pipeline processing sales conversations. Identifies objection patterns, executive buying signals, and synchronizes actionable coaching briefs into CRM.",
      icon = "analytics",
      capabilities = listOf(
        "Instant meeting synthesis in CRM notes",
        "Competitor sentiment matrix",
        "Automated deal-slippage alert pings"
      ),
      isAccentCyan = true
    ),
    SolutionMatrixItem(
      id = "s5",
      title = "Autonomous Multi-Agent Swarms",
      description = "Coordinated agent networks where specialized bots collaborate: Lead Scraper Agent passes data to Qualification Agent, then to Meeting Scheduler, then to CRM Sync Agent.",
      icon = "diversity_3",
      capabilities = listOf(
        "Stateful execution with retry gates",
        "Autonomous peer validation protocols",
        "Human-in-the-loop override toggle"
      ),
      isAccentCyan = false
    ),
    SolutionMatrixItem(
      id = "s6",
      title = "Enterprise Systems Integration",
      description = "Direct bridges between modern AI models and legacy on-premises SQL databases, SAP, NetSuite, proprietary ERPs, and secure banking portals.",
      icon = "lan",
      capabilities = listOf(
        "Encrypted hybrid-cloud connectors",
        "Bidirectional schema validation",
        "SOC-2 Type II aligned data handling"
      ),
      isAccentCyan = true
    )
  )

  val explorerSections = listOf(
    ExplorerSection(
      id = "sales",
      label = "Sales & Pipeline",
      title = "Sales & Pipeline Automation",
      beforeTitle = "Manual Friction Pipeline",
      beforeTimeLost = "~19 hours/week lost per rep",
      beforePoints = listOf(
        "Inbound web forms sit in inbox for 3+ hours before rep review.",
        "Manual back-and-forth emails to confirm timezones and calendar availability.",
        "Notes copied manually from call scribbles into CRM with missing contact data.",
        "Cold follow-ups delayed or forgotten during busy closing weeks."
      ),
      afterTitle = "KineticFlow Autonomous Pipeline",
      afterTimeSaved = "95% time reduction (< 30s response)",
      afterPoints = listOf(
        "Lead submitted → AI Voice or WhatsApp bot contacts prospect within 30 seconds.",
        "Intent verified and calendar slot reserved via bidirectional Google/Outlook sync.",
        "Full transcript, qualification score, and budget recorded into CRM instantly.",
        "Autonomous behavioral follow-up cadence executed if prospect reschedules."
      )
    ),
    ExplorerSection(
      id = "customer-ops",
      label = "Customer Operations",
      title = "Customer Operations & Support",
      beforeTitle = "Manual Support Queues",
      beforeTimeLost = "4.5 hr average ticket resolution",
      beforePoints = listOf(
        "Tier-1 repetitive questions flood desk email queues.",
        "Staff constantly lookup order statuses across disconnected ERP databases.",
        "Weekend and after-hours tickets completely blocked until Monday morning.",
        "Inconsistent answers given by rotating temporary support staff."
      ),
      afterTitle = "Autonomous Support Swarm",
      afterTimeSaved = "78% First Contact Resolution in < 5s",
      afterPoints = listOf(
        "AI Agent securely retrieves live tracking & policy records from internal databases.",
        "Instant, contextual resolution 24/7 across WhatsApp, SMS, and Web Widget.",
        "Complex edge cases automatically summarized and routed to human specialists.",
        "Zero backlog on Mondays with 100% audit logging for compliance."
      )
    ),
    ExplorerSection(
      id = "internal-ops",
      label = "Internal Ops & Logistics",
      title = "Internal Ops & Logistics",
      beforeTitle = "Manual Dispatch & Routing",
      beforeTimeLost = "~24 hours/week lost coordination",
      beforePoints = listOf(
        "Dispatchers calling drivers individually for status and delay updates.",
        "Delivery confirmation slips manually photographed and uploaded next day.",
        "Warehouse inventory discrepancies identified only during weekly tallies.",
        "Repeated spreadsheet copy-pasting for vendor proof of delivery."
      ),
      afterTitle = "Autonomous Dispatch Engine",
      afterTimeSaved = "Real-time telemetry & zero manual check-ins",
      afterPoints = listOf(
        "Automated SMS/WhatsApp driver check-in bot logging transit checkpoints.",
        "Computer vision OCR instantly extracts invoice waybills into Postgres database.",
        "Exception alerts triggered in Slack when transit delays exceed thresholds.",
        "Real-time customer status tracking pages updated with zero staff intervention."
      )
    ),
    ExplorerSection(
      id = "marketing",
      label = "Marketing & Retention",
      title = "Marketing & Retention",
      beforeTitle = "Static Broadcast Sequences",
      beforeTimeLost = "Generic, unsegmented outreach",
      beforePoints = listOf(
        "Blunt email blasts sent with low open and engagement rates.",
        "Churn signals missed until customer actually cancels contract.",
        "Review request emails sent weeks late after transaction completes.",
        "Manual collation of customer feedback for leadership review."
      ),
      afterTitle = "Behavior-Driven Autonomous Cadence",
      afterTimeSaved = "3.4x higher engagement & proactive retention",
      afterPoints = listOf(
        "Usage dips automatically trigger personalized proactive check-in bots.",
        "Positive support resolutions trigger automated WhatsApp review prompts.",
        "Dynamic content tailoring based on industry and prospect role.",
        "Weekly semantic sentiment digest generated directly for executive team."
      )
    ),
    ExplorerSection(
      id = "finance",
      label = "Finance & Procurement",
      title = "Finance & Procurement",
      beforeTitle = "Manual Invoice Reconciliation",
      beforeTimeLost = "~18 hours/week bookkeeping friction",
      beforePoints = listOf(
        "Accountants manually comparing vendor PDF invoices with PO receipts.",
        "Approval requests stuck in internal email loops without visibility.",
        "Payment receipts re-keyed into accounting software individually.",
        "Late fees incurred due to unapproved approval bottlenecks."
      ),
      afterTitle = "Autonomous Ledger Reconciliation",
      afterTimeSaved = "Zero-touch PO matching & instant audit",
      afterPoints = listOf(
        "OCR models parse incoming invoices, extract line items, and verify PO totals.",
        "One-click interactive Slack/Teams approval cards sent to department heads.",
        "Automatic posting to QuickBooks/NetSuite once approved.",
        "Discrepancies highlighted in red and routed directly to vendor with memo."
      )
    ),
    ExplorerSection(
      id = "hr",
      label = "HR & People Ops",
      title = "HR & People Operations",
      beforeTitle = "Manual Candidate Screening & Onboarding",
      beforeTimeLost = "~15 hours/week administrative drag",
      beforePoints = listOf(
        "Recruiters manually screening hundreds of identical resumes.",
        "Candidate scheduling ping-pong across multiple interviewer timezones.",
        "New hire document collection followed up manually over several weeks.",
        "Repetitive employee handbook questions asked repeatedly to HR staff."
      ),
      afterTitle = "Autonomous Talent & Onboarding Agent",
      afterTimeSaved = "80% acceleration in hiring pipeline",
      afterPoints = listOf(
        "AI screening agent verifies qualification prerequisites and schedules interviews.",
        "Automated onboarding workflow dispenses NDAs, creates accounts, and logs keys.",
        "Internal Slack bot answers 90%+ of benefits and holiday policy questions.",
        "Zero friction document signing with automated reminder nudges."
      )
    )
  )

  val industries = listOf(
    IndustrySolution(
      id = "ind1",
      title = "Healthcare & Medical Clinics",
      description = "HIPAA-conscious appointment booking, patient triage, rescheduling management, and automated patient recall notifications without phone tag.",
      badge = "EHR / EMR Direct Integration",
      icon = "local_hospital",
      isAccentCyan = true
    ),
    IndustrySolution(
      id = "ind2",
      title = "Real Estate & Development",
      description = "Instant buyer pre-qualification, private viewing schedule coordination, automated PDF brochure delivery, and agent assignment within 60 seconds.",
      badge = "MLS & Brokerage CRM Sync",
      icon = "domain",
      isAccentCyan = false
    ),
    IndustrySolution(
      id = "ind3",
      title = "Professional Services & Legal",
      description = "Autonomous client intake questionnaires, compliance document collection, engagement letter triggering, and retainer billing pipelines.",
      badge = "Clio & PracticePanther Ready",
      icon = "gavel",
      isAccentCyan = true
    ),
    IndustrySolution(
      id = "ind4",
      title = "Logistics & Field Services",
      description = "Automated route dispatch confirmations, real-time driver status messaging, exception alerts, and automated proof-of-delivery archival.",
      badge = "Fleet Telematics & TMS Bridge",
      icon = "local_shipping",
      isAccentCyan = false
    ),
    IndustrySolution(
      id = "ind5",
      title = "Automotive Dealerships",
      description = "Service lane reminder calls, instant test drive bookings, digital trade-in value estimators, and automated warranty expiration outreach.",
      badge = "DealerSocket & CDK Global Sync",
      icon = "directions_car",
      isAccentCyan = true
    ),
    IndustrySolution(
      id = "ind6",
      title = "Hospitality & Dining",
      description = "VIP table reservations via conversational voice, catering inquiry intake, automatic booking deposits, and multi-channel review follow-ups.",
      badge = "SevenRooms & OpenTable Sync",
      icon = "restaurant",
      isAccentCyan = false
    )
  )

  val caseStudies = listOf(
    CaseStudy(
      id = "cs1",
      clientName = "Specialty Orthopedic Group",
      industry = "Healthcare",
      title = "Automated Patient Intake & 24/7 Voice Reception",
      problem = "High volume of after-hours appointment calls leading to lost patient consultations and severe morning receptionist backlog.",
      blueprint = "Telephony SIP Trunk → Real-time AI Voice Engine → EHR Calendar Sync → Twilio SMS Confirmation",
      businessValue = "Designed to eliminate unanswered after-hours appointments, completely handling 100% of routine reschedule requests without front-desk burden."
    ),
    CaseStudy(
      id = "cs2",
      clientName = "Apex Commercial Holdings",
      industry = "Real Estate",
      title = "Multi-Agent Commercial Deal Pipeline",
      problem = "Commercial real estate leads were sitting unattended for up to 4.5 hours while brokers were in transit or site inspections.",
      blueprint = "Inbound Portal Leads → Intent Agent → KYC Verification → WhatsApp Cadence → Slack Notification",
      businessValue = "Designed to compress average lead response time from 4.5 hours to under 30 seconds, accelerating qualified viewing rates significantly."
    ),
    CaseStudy(
      id = "cs3",
      clientName = "Fintech Logistics Co",
      industry = "Finance Ops",
      title = "Global Cross-System Invoice Reconciliation",
      problem = "Accounting teams spent 22 manual hours every week reconciling carrier invoices, PDF waybills, and Stripe transactional logs.",
      blueprint = "Stripe & QuickBooks Webhooks → n8n Orchestrator → OCR Validation → Postgres Audit Log",
      businessValue = "Designed to replace 22 weekly hours of manual spreadsheet reconciliation with real-time exception flags and zero data entry errors."
    )
  )

  val deploymentSteps = listOf(
    DeploymentStep(
      number = "01",
      title = "Discovery Audit",
      description = "In-depth operational audit to map bottlenecks, examine existing software APIs, and quantify high-probability ROI potential.",
      timeline = "Days 1–3"
    ),
    DeploymentStep(
      number = "02",
      title = "Architecture Design",
      description = "Complete technical blueprint, conversational prompt conditioning, failover branch trees, and security compliance verification.",
      timeline = "Days 4–7"
    ),
    DeploymentStep(
      number = "03",
      title = "Build & Integrate",
      description = "Secure webhook and API pipeline build, multi-agent logic configuration, synthetic stress tests, and staging validation.",
      timeline = "Days 8–16"
    ),
    DeploymentStep(
      number = "04",
      title = "Optimize & Scale",
      description = "Production rollout with 24/7 uptime monitoring, latency telemetry optimization, and active fine-tuning of reasoning prompts.",
      timeline = "Ongoing Support"
    )
  )

  val faqs = listOf(
    FaqItem(
      question = "Can AI Voice Agents truly handle complex accents, interruptions, and angry callers?",
      answer = "Yes. We build using ultra-low latency acoustic models combined with conversational interruption detection. If a caller cuts in mid-sentence, the AI instantly pauses speaking and adapts to the caller's updated prompt. For distressed or high-risk callers, pre-programmed sentiment guardrails immediately execute a smooth transfer to a human specialist with full context briefing."
    ),
    FaqItem(
      question = "Do we need to replace our current CRM, phone system, or ERP?",
      answer = "No. We do not replace your tech stack; we enhance it. KineticFlow acts as an intelligent automation overlay connecting to your current tools (Salesforce, HubSpot, SAP, VoIP trunks, Twilio, Google Workspace) via standard REST APIs, webhooks, or database gateways."
    ),
    FaqItem(
      question = "How do we ensure customer data privacy and regulatory compliance?",
      answer = "We adhere strictly to zero-data retention policies where customer proprietary data is never used to train public foundational models. All API traffic is TLS 1.3 encrypted, and pipelines can be deployed into your dedicated private AWS/GCP VPC if required by HIPAA or GDPR mandates."
    ),
    FaqItem(
      question = "What happens when an AI agent encounters a situation it cannot solve?",
      answer = "Our workflows include deterministic \"fallback gates\". When confidence scores fall below threshold or out-of-scope policies are detected, the agent seamlessly routes the session to a designated team member via Slack alert, SMS, or live phone handover, alongside a full transcript summary."
    ),
    FaqItem(
      question = "How quickly can a typical automation workflow be deployed?",
      answer = "Turnkey AI Voice agents and standard CRM synchronization workflows are typically deployed into staging within 7 to 10 days, with full production activation following sandbox testing inside 2 to 3 weeks."
    ),
    FaqItem(
      question = "What does an AI automation engagement typically cost?",
      answer = "Engagements are structured into a fixed-scope architecture & deployment phase, followed by a predictable monthly infrastructure and optimization tier. We calculate clear ROI milestones prior to work commencing, typically achieving positive return within 60 days."
    )
  )

  val techEcosystem = listOf(
    "OpenAI GPT-4o",
    "Anthropic Claude 3.5",
    "Google Gemini 1.5 Pro",
    "Vapi Voice Engine",
    "ElevenLabs Conversational",
    "n8n Industrial Core",
    "Make.com Enterprise",
    "Salesforce CRM",
    "HubSpot Enterprise",
    "Twilio SIP Telephony",
    "PostgreSQL",
    "AWS Serverless VPC",
    "Zapier Enterprise"
  )
}
