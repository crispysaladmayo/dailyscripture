# M1 BMad review — Adversarial Review + Edge Case Hunter

**Scope:** [`prd-milestone-1.md`](./prd-milestone-1.md), [`design-m1-hifi-pages.md`](./design-m1-hifi-pages.md)  
**Date:** 2026-04-05  
**Purpose:** Optional pre-lock QA per BMad Core `[AR]` and `[ECH]`. Findings below were merged into the PRD and hi-fi spec where noted in §4.

---

## 1. [AR] Adversarial Review — findings

Cynical review; descriptions only.

1. **Activation vs allergies UX:** PRD §2.2 requires onboarding completion including “≥1 allergy field **or** explicit none known,” while the hi-fi allergy step centers chips + “No known allergies.” If engineering wires activation to “any chip touched” only, users who skip both could falsely activate—needs one explicit funnel rule in analytics + implementation.
2. **Log edit/delete is still a fork:** §8.5 presents add-only vs 7-day delete as **open** with no default. That leaves compliance with “honest imprecision” and support burden undefined for mistaken logs driving Today hints.
3. **0–5 month logging path is a product hole:** PRD and hi-fi both defer “milk check-in vs meal log” to PO. Until closed, eng will guess and Today/Log ACs (e.g. AC-D1 exception) stay untestable.
4. **“Primary caregiver” greeting without a name source:** Today shows “Good afternoon, [Caregiver]” but household members are optional and there is no FR for **account display name**—either add a field or change copy to generic “Good afternoon.”
5. **Seeded content licensing:** §14 open question on count **and** licensing owner is a release risk; without it, demo and distribution could violate third-party recipe rights.
6. **Age-band granularity mismatch:** PRD §7.2 uses four coarse bands for nutrition logic; hi-fi onboarding splits **3–12** into five selectable bands. Nutrient “rotate” lists must map explicitly per selected UX enum or designers/eng will misalign hints.
7. **“Other” allergen conservative exclusion:** PRD §8.2 prefers false negatives, but there is no **user-visible** explanation when many recipes disappear—caregivers may blame the app or assume a bug.
8. **Dislike semantics vs copy:** PRD §8.3 says dislikes are soft and may still appear with a badge; recipe list copy must not imply hard safety—any overstatement erodes trust relative to allergies.
9. **Country required vs recommended:** PRD FR-A3 says country is **recommended**; hi-fi Step 1 validation says **country required if dropdown exists.** That is a direct conflict until PO picks one rule.
10. **WCAG NFR without level:** NFR2 defers WCAG level to “tech/design spec”; hi-fi §8 is “WCAG-minded” but still no **claimed** level (A/AA), so procurement and QA lack a gate.
11. **Child data / analytics privacy:** Analytics events carry `age_band`, `has_allergy`, etc. There is no PRD-level statement on retention, COPPA/GDPR posture, or minimization—risk for a child-focused product.
12. **Multi-session onboarding:** “Logout mid-onboarding” is named; **session expiry** or **second device** mid-flow is not—resume vs discard rules stay fuzzy.
13. **Fridge input ambiguity:** Comma-separated vs chips is an open design issue; without a parsing spec, “chicken, cream cheese” vs multi-line edge cases will behave inconsistently across locales.
14. **Direct navigation to recipe:** If a future route allows opening a recipe by ID without passing through filter pipeline, §8.2’s guarantee for “suggestions” does not automatically extend—scope of “always safe list” should be explicit in tech spec.

---

## 2. [ECH] Edge Case Hunter — unhandled paths (JSON)

Structured per `bmad-review-edge-case-hunter` output format. Use for QA matrices and tech-spec guards.

```json
[
  {
    "location": "design-m1-hifi-pages.md:5.11",
    "trigger_condition": "Find recipes with zero ingredients entered",
    "guard_snippet": "Disable Find until input non-empty or show inline validation.",
    "potential_consequence": "Confusing empty results and wasted API calls."
  },
  {
    "location": "design-m1-hifi-pages.md:5.9",
    "trigger_condition": "Save meal fails network or 5xx",
    "guard_snippet": "Error toast plus retain form state; offer Retry.",
    "potential_consequence": "Lost log entry and user distrust."
  },
  {
    "location": "design-m1-hifi-pages.md:5.12",
    "trigger_condition": "Fridge suggest API times out or errors",
    "guard_snippet": "Full-page or inline error with Retry; preserve ingredients.",
    "potential_consequence": "Dead end with no recovery path."
  },
  {
    "location": "design-m1-hifi-pages.md:5.5",
    "trigger_condition": "Client sends both no-allergies flag and chips",
    "guard_snippet": "Server rule: last write wins or reject 400 with clear message.",
    "potential_consequence": "Undefined allergy state and unsafe suggestions."
  },
  {
    "location": "design-m1-hifi-pages.md:5.7",
    "trigger_condition": "User navigates back from disclaimer with I understand checked",
    "guard_snippet": "Reset checkbox on back or persist step state explicitly.",
    "potential_consequence": "Skipped legal acknowledgement or confusing re-entry."
  },
  {
    "location": "prd-milestone-1.md:8.5",
    "trigger_condition": "User requests log change after delete window",
    "guard_snippet": "Define policy in PRD and match Settings or support flow.",
    "potential_consequence": "Contradictory support answers and bad Today data."
  },
  {
    "location": "design-m1-hifi-pages.md:5.13",
    "trigger_condition": "Allergens edited while recipe detail open",
    "guard_snippet": "Invalidate detail safety callout or force refresh banner.",
    "potential_consequence": "Stale safe badge contradicts new profile."
  },
  {
    "location": "design-m1-hifi-pages.md:5.15",
    "trigger_condition": "User saves recipe with empty ingredients",
    "guard_snippet": "Validation block save until min one ingredient line.",
    "potential_consequence": "Broken fridge ranking and useless cards."
  },
  {
    "location": "design-m1-hifi-pages.md:5.4",
    "trigger_condition": "Child name whitespace-only passes client",
    "guard_snippet": "Trim and reject empty after trim server-side.",
    "potential_consequence": "Blank names in UI and fridge copy."
  },
  {
    "location": "design-m1-hifi-pages.md:5.11",
    "trigger_condition": "Extremely long comma-separated ingredient string",
    "guard_snippet": "Max length token count and char limit with user message.",
    "potential_consequence": "Parser blowups and poor latency."
  },
  {
    "location": "prd-milestone-1.md:9.2",
    "trigger_condition": "Second device opens app mid-onboarding",
    "guard_snippet": "Define resume token or restart onboarding rule.",
    "potential_consequence": "Duplicate households or stuck gate."
  },
  {
    "location": "design-m1-hifi-pages.md:5.2",
    "trigger_condition": "Auth identifier already registered",
    "guard_snippet": "Inline error mapping duplicate email to clear next step.",
    "potential_consequence": "Support churn and abandoned signup."
  }
]
```

---

## 3. Integrations applied (traceability)

| Finding area | Action |
|--------------|--------|
| AR: country required conflict | PRD FR-A3 + hi-fi Step 1 aligned to **required country when country control shown**; free-text-only variant called out. |
| AR: caregiver greeting | Hi-fi Today copy fallback when no display name; PRD open question for account display name. |
| AR: age band vs hint mapping | PRD §7.2 note + hi-fi cross-reference to same band groupings. |
| AR: other allergen transparency | PRD §9.2 + Fridge hi-fi footnote direction. |
| ECH: empty fridge submit | Hi-fi Fridge validation state. |
| ECH: save/network, suggest failure | Hi-fi Log + Fridge results error states. |
| ECH: allergy chip + no-known conflict | Hi-fi allergies explicit mutual-exclusivity behavior. |
| ECH: stale recipe safety | Hi-fi recipe detail refresh note. |
| ECH: empty user recipe | Hi-fi add-recipe validation. |
| AR: privacy | PRD §13 NFR4 stub for child-related data minimization. |
| ECH: duplicate signup | Hi-fi §5.2 explicit duplicate-account error mapping. |

---

## Document control

| Version | Date | Notes |
|---------|------|-------|
| 0.1 | 2026-04-05 | Initial AR + ECH artifact; integrations applied to PRD and hi-fi. |
