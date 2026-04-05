# BMad Adversarial Review — Macro-anchored M1 (PRD 0.3 + design 0.3)

**Scope:** [`prd-milestone-1.md`](./prd-milestone-1.md) §6–§7, §9–§11 deltas; [`design-m1-hifi-pages.md`](./design-m1-hifi-pages.md) §5.8–5.15, components, open issues.  
**Persona:** Cynical reviewer. **Date:** 2026-04-05.

## Findings

1. **Contribution table is unspecified in PRD** — FR-D1 references a table that lives only in “technical spec”; without a stub appendix or link, engineering can ship arbitrary coefficients that contradict PO intent.

2. **Gap “policy threshold” is delegated entirely to tech spec** — AC-C2 requires protein “below reference by policy threshold” but PRD never gives a default (e.g. 15% relative gap); risk of never firing hints or firing constantly.

3. **6–12 month row mixes “complementary only” with milk not modeled** — PRD §7.2 says milk not fully modeled “unless extended”; caregivers may see misleading totals if breast/formula dominates the day.

4. **child_3_12 uses single 1600 kcal for 3–12** — Adversarial read: a 12-year-old and a 3-year-old share defaults; UX promises age personalization on the pill but numeric targets flatten the span—trust erosion when parents notice.

5. **FR-C5 empty copy vs design C8** — Wording differs slightly (“add macros to your recipes in Settings” vs design “add per-serving nutrition”); localization and Settings placement may confuse (macros edited on recipe, not Settings).

6. **Recipe `macro_emphasis` vs energy gap** — PRD allows `balanced` when gap is energy; no rule prevents every “balanced” recipe from flooding energy-gap ideas, diluting usefulness.

7. **Optional FR-E2 gap boost** — “Optional M1” without a hard default invites shipping inconsistency between fridge and Today meal ideas.

8. **User recipe partial macros** — If user enters only kcal but not grams, eligibility for meal ideas is unclear (exclude entirely vs treat as unknown).

9. **Analytics `meal_idea_tap` new** — Funnel definitions in §2.2 still describe “hint shown” generically; activation metric may need updating for meal-idea engagement.

10. **Design §5.8 “scroll to §5 below”** — Section reference is self-referential error (“§5” vs internal subsection); implementers will not anchor in-app scroll targets from this text.

11. **Accessibility:** Macro bars rely on progress visuals; design §8 calls for SR—“Tip” pattern may need explicit `aria-label` including both fraction and that values are estimated (prototype partially does this).

12. **Indonesia / non-U.S. users** — PRD and KB repeat localization risk; shipping numeric U.S.-leaning defaults without prominent “reference region” copy may increase complaint volume.

13. **§7.6 optional edu card “PO toggle”** — Unspecified config owner; dead feature path or surprise content drift.

14. **Seeded recipe count still “TBD”** — With mandatory macro fields for meal ideas, content workload just spiked; no owner or deadline in PRD.

15. **Edge case “all macros near target”** — PRD §9.2 suggests positive framing but no AC asserts behavior; easy to ship empty awkward Today.

---

**Suggested next pass:** Tighten PRD with **default gap threshold**, **partial user macro rules**, and either **split 3–12 defaults** or explicit “wide band” disclaimer in UI; add **contribution table v0** appendix or link.
