# Prototype notes (M1 hi-fi HTML)

1. **Brand:** Final app name and landing headline are still open in the design spec; this build uses neutral “Child nutrition” / PRD-aligned copy.
2. **0–5 month Today mode:** The hi-fi spec’s milk-forward layout is not built as a separate page; the demo child is **3–5 years** with **macro snapshot**, **two** gap-hint cards, and **meal ideas** carousel (PRD §7.5).
3. **Auth:** Sign-up / log-in is static; no real validation. Duplicate-email messaging is stubbed in HTML only.
4. **Recipe detail after “Save recipe”:** Navigates with `?mine=1` to show the “Yours” pill only—ingredients/steps are still the sample library recipe.
5. **Settings:** Section rows link to `#` as placeholders until engineering wires routes.
6. **Fridge empty search:** Inline error appears when the ingredient field is cleared and **Find recipes** is clicked; default field is pre-filled for demo flow.
