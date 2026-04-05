# BMad review — M1 expansion (AR + Edge Case Hunter)

**Scope:** [`prd-milestone-1.md`](./prd-milestone-1.md) v0.4, [`design-m1-hifi-pages.md`](./design-m1-hifi-pages.md) v0.4, [`milestone-1-spec.md`](./milestone-1-spec.md) (updated), [`child-nutrition-0-12-knowledge-base.md`](./child-nutrition-0-12-knowledge-base.md) (KB expansion).  
**Date:** 2026-04-05  
**Method:** Adversarial Review (general) + Edge Case Hunter paths.

---

## Part A — Adversarial Review (findings)

1. **Sodium/sugar rollup** is underspecified for how **logs** bind to **recipes**—without a “cooked this recipe” link, PRD §7.8 may be **unimplementable** as written; tech spec must define **minimum** data path or **always-unknown** default.
2. **Growth chart X-axis** without DOB invites **wrong age** on the curve—design flags this (open issue 10) but PRD should **force** a decision: **months since birth** field vs **DOB** vs **band midpoint** disclaimer in UI.
3. **WHO vs CDC at 24 months** transition is **discontinuous** in real data—users may see a **jump**; product copy and eng should address **chart switch** explicitly (open issue 11).
4. **Share URL** without **revoke** increases **stale recipe** risk after user edits—PRD lists as open question; cynical view: users will **blame the app** when grandma cooks the old version.
5. **Public recipe page** may be **indexed** unless `noindex` is enforced (NFR5)—conflicts with “feels private” mental model; **marketing vs privacy** needs an explicit owner decision.
6. **Meal prep free-text notes** with allergy warning may still drive **harm** if users skim—consider **stronger friction** (checkbox “I verified allergens”) if legal review asks.
7. **Trending** as **editorial only** without **freshness** may feel **stale** after months—content ops burden not captured in milestone exit criteria.
8. **Nutrient focus + sodium row + macro hints + meal ideas** on Today may **violate** “one primary action” principle (§5)—cognitive load regression; consider **progressive disclosure** or **collapsible** sections in a follow-up design pass.
9. **Sex for growth** copy (“sex at birth”) may **alienate** some users—**Inclusive UX** review suggested; align wording with **CPO** and legal.
10. **Prefer not to say** on sex **disables growth** but **does not** explain whether **data was previously entered** if user later changes sex—**migration** of chart state unspecified.
11. **Added sugar ~10% kcal** derived from **single kcal midpoint** (e.g. 1600) is **rough** for small toddlers on **1000 kcal** row—PRD should tie **sugar cap derivation** to **same nutrition row** as macros, not a single example.
12. **Five bottom tabs** on **320px** width may **crowd** labels—design should specify **icon-only** mode or **abbreviated** labels for `xs`.

---

## Part B — Edge Case Hunter (unhandled paths)

Structured per Edge Case Hunter skill. Full machine-readable list: [`m1-bmad-review-ech-m1-expansion.json`](./m1-bmad-review-ech-m1-expansion.json).

| Location | Trigger (short) | Gap |
|----------|-----------------|-----|
| PRD §7.8 | Log references recipe without portion | No rollup rule |
| PRD §7.8 | User overrides sodium line to zero / extreme | No validation bounds |
| PRD Epic H | Share link for **deleted** user recipe | Orphan URL behavior undefined |
| PRD Epic H | Clipboard API denied (Safari) | Fallback for “copy text” |
| PRD Epic G | Timezone boundary **week** Mon vs locale | Week start not specified |
| PRD Epic I | Measurement **future date** | Validation not in PRD |
| PRD Epic I | Weight **0** or **negative** | Validation not in PRD |
| Design §5.18 | Two measurements **same day** different values | Which plots? |
| Design §5.16 | Very **long** ingredient list print | Page break / overflow |
| Design §5.17 | **Duplicate** same recipe 7× | Summary cook time double-count |

---

## Recommended next actions

1. **Technical Writer / Architect:** Close **log↔recipe** binding for sodium/sugar or ship **awareness-only** without rollup in M1.  
2. **PO:** Tie **added sugar cap** to **nutrition row** table (§7.2) in PRD v0.4.1.  
3. **Design:** `xs` nav pattern for **5 tabs**; Today **collapsible** secondary modules.  
4. **Legal:** Growth + share copy review before beta.

---

## Change log

| Version | Date | Notes |
|---------|------|--------|
| 1.0 | 2026-04-05 | Initial AR + ECH summary for M1 expansion docs. |
