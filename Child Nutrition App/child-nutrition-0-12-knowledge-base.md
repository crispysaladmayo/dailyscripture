# Child nutrition (ages 0–12) — Knowledge base for product targets

**Research role framing:** [`agents/researcher.md`](../agents/researcher.md) — credible sources, separated **facts vs. inference**, explicit **limitations**.  
**Not medical advice:** This document supports **product planning and approximate age-based targets** in the app. Individual needs vary; caregivers should consult **pediatricians or registered dietitians** for medical or therapeutic nutrition.

**Geography note:** Numbers below lean on **U.S. Dietary Reference Intakes (DRI)** and **U.S. Dietary Guidelines** where cited. Household **city/country** in the product should eventually map to **national guidelines** (e.g. Indonesia: Ministry of Health / PERGIZI materials) — treat localization as a **follow-on research task**.

---

## Executive summary (for PM / design)

1. **0–~6 months:** For most infants, **exclusive breast milk** or **infant formula** is the core nutrition story; **complementary foods** are not recommended before developmental readiness (commonly **~6 months** per CDC/DGA/AAP alignment). **Vitamin D** supplementation is commonly recommended for **exclusively breastfed** infants (U.S. guidance **400 IU/day**); formula-fed infants meeting formula volume may not need extra vitamin D—**wording in app must defer to local clinical guidance.**
2. **~6–24 months (complementary feeding / toddler):** **Dietary diversity**, **iron-rich** and **zinc-rich** foods, **texture progression**, and **responsive feeding** are central (WHO 2023 complementary feeding guideline). **Cow’s milk as main drink** is generally **not recommended before 12 months** (CDC). **Choking risk** and **allergen introduction** have specific behavioral guidance (CDC).
3. **2–12 years:** USDA **Dietary Guidelines** emphasize **patterns** across **vegetables, fruits, grains (half whole grain), protein, dairy (or fortified soy alternatives)**; **amounts vary by age, sex, height, weight, and activity**—apps should use **bands** and **ranges**, not false precision.
4. **Nutrients often emphasized for young children in public health messaging:** **Iron**, **vitamin D**, **calcium** (as diets shift off breast/formula), **fiber**, **potassium**; limit **added sugars** and **sodium** (DGA themes).
5. **Product implication (M1):** Map **child nutrition row** → **reference energy (EER-style midpoint) + Acceptable Macronutrient Distribution Range (AMDR)** to set **default macro targets** in UI; **logging** stays **food-group + portion** with a **fixed estimation table**—shown numbers are **approximate**, not intake from a weighed food record. Avoid diagnosing deficiency; cite **educational** framing. See **§AMDR & energy reference (M1 macro UI)** below.
6. **M1 expansion (nutrient focus, sodium/sugar awareness, growth):** A **curated nutrient-focus module** (not full micronutrient tracking from food-group logs) should foreground **vitamin D** and **iron** plus **pattern** language for **calcium** where stage-appropriate—see **§Nutrient focus goals**. **Sodium** and **added sugars** are best shown as **reference “stay mindful” bands** tied to age + approximate energy defaults, with heavy **unknown** states when logs/recipes lack nutrient data—see **§Sodium & added sugars**. **Growth curves** for caregiver-entered measurements should use **published LMS-based standards** (WHO vs CDC split by age) and **never** imply diagnosis—see **§Growth charts (in-app)**.

---

## Age stages & what authoritative guidance emphasizes

### 0–~6 months (milk feeding)

| Theme | Guidance (synthesized) | Notes for product |
|--------|-------------------------|-------------------|
| Primary feed | Human milk and/or infant formula appropriate for age | Log could be “milk volume / session count” if ever added—**M1 may skip** or use simple “breast/formula day” flag. |
| Solids | Not before readiness; **not before 4 months**; introduce around **6 months** when ready (CDC, DGA) | Today view for **under 6 months** should not pretend micronutrient “gaps” from mixed meals. |
| Vitamin D | U.S.: **400 IU (10 µg)/day** for infants; breastfed infants typically need supplement; formula-fed infants consuming sufficient fortified formula may not (CDC consumer guidance) | Any supplement UI is **high risk**—prefer **“ask your pediatrician”** copy. |

**Inference (labeled):** For **0–5 mo**, a consumer app’s best “target” is **adequate milk** + caregiver education links—not scored meal diversity.

---

### ~6–12 months (complementary foods begin)

| Theme | Guidance (synthesized) | Notes for product |
|--------|-------------------------|-------------------|
| Readiness | Sit with support, head control, swallowing skills, interest (CDC) | Optional checklist UX—not a nutrient target. |
| Diversity | Offer foods from **multiple food groups** over time; WHO stresses **minimum meal frequency** and **diversity** in complementary feeding | Map to **food groups touched this week** as soft goal, not daily perfection. |
| Iron | Complementary diets often need **iron-rich** foods (meats, iron-fortified cereals, beans); U.S. **AI iron 7–12 mo = 11 mg/day** (Institute of Medicine / DRI, summarized via NIH ODS) | Hints: “iron-rich” foods, not “you are deficient.” |
| Allergens | Major allergens **can be introduced** when other complementary foods are introduced; eczema/egg allergy may change peanut introduction planning—**clinician-directed** (CDC) | App: never auto-prescribe introduction order. |
| Cow’s milk beverage | Not recommended **as main drink before 12 months** (CDC) | Copy only. |
| Texture / safety | Progress textures; **choking hazard** avoidance (CDC) | Recipe module: link safety, not just nutrition. |

---

### 1–2 years (12–24 months)

| Theme | Guidance (synthesized) | Notes for product |
|--------|-------------------------|-------------------|
| Milk transition | Whole milk or alternatives per clinician/country guidance after 12 months; DGA includes low-fat dairy patterns for older toddlers—**follow local guidance** | Keep copy generic. |
| Dietary pattern | Continue vegetables, fruits, whole grains, proteins, dairy/soy beverages | Food-group checklist remains appropriate. |
| Vitamin D | U.S. **RDA 600 IU (15 µg)/day** beginning at **1 year** (NIH ODS consumer fact sheet summary) | Hint toward fortified dairy/cereal + sun discussion **outside** app or generic edu. |
| Iron | U.S. **RDA ~7 mg/day (1–3 y)** (NIH ODS) | Useful for “iron-rich snack” hints. |

---

### 2–5 years (preschool)

- **DGA patterns:** Build plates with **variety**; **vegetables** (including legumes), **fruits**, **grains** (**half whole grain**), **protein** (seafood, lean meat, eggs, beans, nuts as appropriate), **dairy** (or fortified soy).  
- **Portion sizes** scale with **calorie needs**; USDA provides **MyPlate** resources by age ranges.  
- **Limit added sugars** (~**&lt;10% calories** is a common DGA theme for healthy dietary patterns).  
- **Sodium** moderation; **hydration** primarily water.

**Product mapping:** **Food-group servings as ranges** by age **and** optional sex later; start with **coarse buckets** (e.g. “veg today: none / some / plenty”) to match low-fidelity logging.

---

### 6–8 years & 9–12 years (school age)

- Same **pattern** emphasis; **energy needs** rise with growth and activity.  
- **Calcium** and **vitamin D** remain important for bone (DRI increases with age; see NIH fact sheets).  
- **Fiber** increases with DRI age bands (institute of Medicine tables).  
- **Protein** RDA increases by weight/age band—for **macro UI**, M1 may show **approximate protein g** vs **AMDR-derived defaults** with the same **non-precision** disclaimers as logging estimates.

**Inference:** For **8–12**, peer social and school meals add **logging noise**—UX research needed on who logs and when.

---

## Reference nutrient snapshots (U.S. DRI — illustrative)

Use as **backend reference only**; **do not** show gram-for-gram precision to users without a validated food database and clinician context.

| Nutrient | ~7–12 mo (example) | ~1–3 y | ~4–8 y | Primary NIH / DRI pathway |
|----------|-------------------|--------|--------|---------------------------|
| Iron | AI **11 mg/day** | RDA **7 mg/day** | RDA **10 mg/day** | [NIH ODS Iron fact sheet](https://ods.od.nih.gov/factsheets/Iron-Consumer/) |
| Vitamin D | **400 IU** infants; **600 IU** from **1 y** (U.S.) | **600 IU** | **600 IU** | [NIH ODS Vitamin D fact sheet](https://ods.od.nih.gov/factsheets/VitaminD-Consumer/); [CDC vitamin D (infant)](https://www.cdc.gov/infant-toddler-nutrition/vitamins-minerals/vitamin-d.html) |

**Calcium, fiber, protein:** See **DRI summary tables** — [NCBI Bookshelf DRI collection](https://www.ncbi.nlm.nih.gov/books/NBK222881/) — for age/sex specifics when engineering needs numeric defaults.

---

## AMDR & energy reference (M1 macro UI)

**Purpose:** Ground PRD [`prd-milestone-1.md`](./prd-milestone-1.md) §7.2 **default kcal and gram targets** in published **ranges**, while keeping the app **non-clinical**.

**Acceptable Macronutrient Distribution Ranges (AMDR)** — U.S. **Institute of Medicine**, *Dietary Reference Intakes: Macronutrients* (summary via NCBI Bookshelf): for **children 1–3 years**, **protein 5–20%**, **carbohydrate 45–65%**, **fat 30–40%** of total energy; for **children 4–18 years**, **protein 10–30%**, **carbohydrate 45–65%**, **fat 25–35%** of total energy. [NCBI Bookshelf — DRI Macronutrients](https://www.ncbi.nlm.nih.gov/books/NBK56068/table/ch1.t16/).

**Estimated Energy Requirement (EER)** varies by **age, sex, height, weight, and activity**; M1 uses a **single illustrative daily kcal midpoint per nutrition row** (see PRD §7.2 table) **until** localized guidelines or sex-split UX ships. Engineers derive **gram targets** as: **grams = (kcal × %AMDR_mid) ÷ kcal_per_g** (4 for protein and carbohydrate, 9 for fat), or use the **PRD table directly** as product defaults—both must stay **versioned** and **disclaimed** in UI.

**Infants 6–12 months:** AMDR bands for older children do not map cleanly to complementary feeding; PRD uses an **illustrative** complementary-day energy and balanced macro split—tune with **optional RD review** and **country-specific** guidance (e.g. WHO complementary feeding themes in §WHO below).

**Localization:** Indonesia and other countries may publish different **energy** or **pattern** guidance; treat U.S.-leaning defaults as **placeholder** for global marketing until a **locale pack** replaces §7.2 defaults ([`child-nutrition-app-context.md`](./child-nutrition-app-context.md) geo story).

**User-facing honesty:** The KB still **does not** endorse showing numbers as **true intake** without a validated food database; M1 explicitly frames figures as **estimates from quick logging**.

---

## Nutrient focus goals (M1 — “vitamins” & minerals in the UI)

**Purpose:** The product asked for **vitamin-related goals** for the child. Food-group logging **cannot** estimate full vitamin intake. The defensible M1 approach is a **small, stage-aware set of “nutrient focus” cards**—educational and **pattern-based**—grounded in the same authorities as the rest of this KB.

### Curated focus set (recommended for product defaults)

| Focus ID | Nutrient / theme | Why include (public-health emphasis) | Product stance |
|----------|------------------|--------------------------------------|----------------|
| `vitamin_d` | **Vitamin D** | Breastfed infants: common **400 IU/day** supplement theme (U.S.); **600 IU/day** from **1 y** (NIH ODS / CDC). | **Education + “talk with pediatrician”** for supplements; **no** dosing UI. **0–5 mo milk-day:** optional short line only, not a scored “gap from logs.” |
| `iron` | **Iron** | Complementary feeding: **iron-rich foods**; DRI **AI 11 mg/day (7–12 mo)**, **RDA ~7 mg (1–3 y)** (NIH ODS summaries). | **“Iron-rich meal ideas”** / pattern hints—not “you are deficient.” |
| `calcium_pattern` | **Calcium (pattern)** | Bone health narrative for toddlers and older children (DGA food groups, dairy/fortified soy). | **Food-group alignment** (“dairy/fortified soy today”) rather than mg tallies unless a future DB ships. |

**Explicit non-goals for M1:** Do **not** present a **complete multivitamin checklist** or imply the app **measures all vitamins** from logs. Optional future: single **“vitamin A / C / folate”** pattern cards only after RD review and locale pack.

**Inference:** Ordering of cards can follow **stage** (e.g. iron heavier for 6–12 mo; vitamin D persistent across bands with different copy).

---

## Sodium & added sugars (awareness targets, not prescriptions)

**Purpose:** Support **per-child “awareness” limits** in the app (sodium / **natrium** are the same nutrient). Logs **without** gram-level sodium or added sugar cannot support accurate tracking; the product should combine **reference daily bands** (defaults from guidelines) with **optional recipe metadata** and **explicit unknowns**.

### Added sugars

- **Dietary Guidelines for Americans (2020–2025)** theme: **limit added sugars to less than 10% of calories per day** for those **2 years and older** who consume a healthy dietary pattern. For **under 2 years**, guidance emphasizes **avoiding foods and beverages with added sugars** (qualitative; see DGA chapter and consumer materials).  
- **Product mapping:** For ages **2+**, an **illustrative daily added-sugar cap (g)** may be derived as **~10% × reference kcal ÷ 4 kcal/g** using the **same approximate daily kcal midpoints** as PRD §7.2 (e.g. at **1600 kcal**, ~**40 g** added sugar as a **ceiling for awareness**, not individualized prescription). **Under 2:** prefer **qualitative** copy (“little or no added sugar”) rather than a numeric goal unless localized guidance dictates otherwise.

### Sodium

- **DGA** consistently recommends **reducing sodium** and choosing **low-sodium** options; exact mg targets for children are **less consumer-simple** than for adults.  
- **DRI / IOM (summary via NCBI Bookshelf):** **Adequate Intakes (AI)** for sodium exist by young age band (e.g. **1–3 y**, **4–8 y**) and **Tolerable Upper Intake Levels (UL)** for sodium apply for older children—these are **reference nutrition planning** tools, **not** a license to “diagnose” excess from incomplete logs.  
- **Product mapping:** Ship **age-band default “stay mindful” sodium caps (mg/day)** as **soft awareness lines** in Settings/Today, **versioned** in the technical spec from DRI tables + DGA narrative. When **recipe sodium per serving** or **day rollup** is unknown, show **“not enough data”** rather than false precision.

**Risk:** Caregivers may interpret any number as medical fact—pair with **disclaimers** and **pediatrician/RD** referral copy ([`prd-milestone-1.md`](./prd-milestone-1.md) §8.4).

---

## Growth charts (in-app) — WHO, CDC, and guardrails

**Purpose:** When caregivers **manually enter** weight and length/height (with **date** and child **sex**), the app may plot the point against **growth standards** and show whether the point falls **near typical percentile bands**.

### Which standards (U.S.-aligned product default)

| Age span | Common U.S. practice | Notes for engineering |
|----------|----------------------|------------------------|
| **Birth – 24 months** | **WHO Child Growth Standards** (length/height-for-age, weight-for-age, weight-for-length; sex-specific). | LMS parameters and percentile computation are **documented by WHO**; implementations often use official tables or validated libraries—**verify license and version** in tech spec. |
| **2 – 20 years** | **CDC growth charts** (BMI-for-age; stature-for-age; weight-for-age; sex-specific)—for this product, use the **2–12** portion only unless extended later. | CDC provides **data files** and guidance; align **units** (kg/lb, cm/in) and **measurement type** (recumbent length vs standing height) with UX. |

**Facts vs inference**

- **Fact:** WHO and CDC publish **population growth standards / reference charts** for monitoring growth in **clinical and public health** contexts.  
- **Inference:** A **consumer app** showing a percentile band is **not** equivalent to a **clinical growth assessment** (no correction for prematurity, genetics, or acute illness unless explicitly modeled—**out of M1** unless PO adds a simple “was baby born early?” flag later).

### What the app must **not** do

- **No** diagnostic labels (“failure to thrive,” “obese,” “stunted”) in M1 default copy.  
- **No** automated treatment or feeding changes based on chart position.  
- Prefer phrases like **“within a typical range on this chart”** vs **“normal/abnormal.”**  
- **Always** show **disclaimer**: growth interpretation belongs with **the child’s clinician**; measurements entered by user may be **wrong**.

### Open implementation choices (PO / Architect)

- **Length vs height:** For **24–36 mo**, some workflows use **standing height**; chart selection must match **CDC vs WHO** transition rules used in pediatric practice in your market.  
- **Prematurity:** M1 may **defer** corrected-age plotting; if included later, requires **gestational age** capture and research sign-off.

---

## Recipe metadata: total cook time (product-only)

**Not a medical claim.** **Total active + passive time** (or a single **total minutes**) is **authoritative content** on each recipe (seeded data entry + optional user recipe field). Used for **sorting, filters, and meal-prep planning** only.

---

## WHO complementary feeding (6–23 months) — themes for product

The **WHO guideline** (2023) is structured for **policymakers and programs** but informs **caregiver-facing principles**:

- Complementary feeding **starts when breast milk alone is insufficient**, with **developmental readiness** (aligned with ~6 months in many settings).  
- Promote **diverse, nutrient-adequate** diets; address **unhealthy foods and beverages**.  
- **Responsive feeding** (recognize hunger/satiety cues).  
- Context-specific guidance on **fortification** and **supplements**—**not** something for a consumer app to automate without local public-health alignment.

**Source:** [WHO publication page](https://www.who.int/publications/i/item/9789240081864); [NCBI Bookshelf summary](https://www.ncbi.nlm.nih.gov/books/NBK596423/).

---

## Source list (title, publisher, date accessed)

| # | Title / page | Publisher | Approx. date | URL |
|---|----------------|-----------|----------------|-----|
| 1 | When, What, and How to Introduce Solid Foods | CDC | Content updated **2025-03-20** (per page) | https://www.cdc.gov/infant-toddler-nutrition/foods-and-drinks/when-what-and-how-to-introduce-solid-foods.html |
| 2 | Infant and Toddler Nutrition (hub) | CDC | — | https://www.cdc.gov/infant-toddler-nutrition/index.html |
| 3 | Vitamin D (infants & children consumer topic) | CDC | — | https://www.cdc.gov/infant-toddler-nutrition/vitamins-minerals/vitamin-d.html |
| 4 | Guideline for complementary feeding… 6–23 months | WHO | **2023** | https://www.who.int/publications/i/item/9789240081864 |
| 5 | Recommendations (WHO complementary feeding guideline) | NCBI Bookshelf | **2023** | https://www.ncbi.nlm.nih.gov/books/NBK596423/ |
| 6 | Dietary Guidelines for Americans | USDA / HHS | **2020–2025** edition current; **2025–2030** in national process | https://www.dietaryguidelines.gov/ |
| 7 | Kids & teens fact sheet (PDF) | USDA DGAs | **2021** (PDF on site) | https://www.dietaryguidelines.gov/sites/default/files/2021-12/DGA_KidsTeens_FactSheet-508c.pdf |
| 8 | Iron — Consumer fact sheet | NIH Office of Dietary Supplements | Updated periodically | https://ods.od.nih.gov/factsheets/Iron-Consumer/ |
| 9 | Vitamin D — Consumer fact sheet | NIH ODS | Updated periodically | https://ods.od.nih.gov/factsheets/VitaminD-Consumer/ |
| 10 | DRI summary tables | NCBI Bookshelf | — | https://www.ncbi.nlm.nih.gov/books/NBK222881/ |
| 11 | WHO Child Growth Standards (methods, charts) | WHO | — | https://www.who.int/tools/child-growth-standards |
| 12 | CDC Growth Charts | CDC | — | https://www.cdc.gov/growthcharts/ |
| 13 | Sodium — Consumer fact sheet | NIH ODS | Updated periodically | https://ods.od.nih.gov/factsheets/Sodium-Consumer/ |

*Accessed for synthesis: **2026-04-05** (agent session); **2026-04-05** M1 expansion pass (nutrient focus, sodium/sugar awareness, growth). Recheck sources before clinical or legal claims.*

---

## Assumptions & risks

1. **Assumption:** Early users may be **Indonesia-based** (from product geo feature) but citations above are **U.S.-heavy** — **national guidelines may differ** for breastfeeding promotion, iron fortification, and default milk type.  
2. **Assumption:** Parents will **tolerate imprecise** logging — coarse models may **mis-rank** “gaps.”  
3. **Risk:** Micronutrient **hinting** without lab data can **increase anxiety** — mitigate with **pattern** language and **professional referral** copy.  
4. **Risk:** **Allergy** and **introduction** guidance is **individual** — wrong generic text can cause harm.  
5. **Single-source sensitivity:** Any one guideline PDF may **update**; track **version** in app `References` screen later.  
6. **Risk:** **Growth chart UX** can increase anxiety or drive **avoidance of care** if copy feels judgmental—use **neutral** language and **clinician referral**.  
7. **Risk:** **Sodium/added sugar “limits”** without label-grade data may **conflict** with user perception of “healthy” home cooking—**unknown** states are required.  
8. **Assumption:** M1 **nutrient focus** stays limited to **vitamin D + iron (+ optional calcium pattern)** unless RD review expands the set.

---

## Recommendations (hypotheses for product)

1. **If** primary ICP includes **0–12 mo**, **then** ship **stage-aware Today**: “milk-focused” vs “complementary feeding” vs “family food” modes — don’t one-size UI.  
2. **If** logging stays **low-fidelity**, **then** keep **food groups + portion** as the **only** logging inputs and treat **macro bars** as **directional** (contribution table), not true gram intake.  
3. **If** geo is **non-U.S.**, **then** fund a **short localization pass** on default EER/AMDR application and edu links **before** marketing those locales.  
4. **If** recipe suggestions are central, **then** maintain **per-serving macro fields** + **`macro_emphasis`** (protein / carbs / fat / balanced) for **gap-driven meal ideas**, plus optional nutrient tags (e.g. iron-rich) for future edu cards.  
5. **If** the app shows **sodium/added sugar awareness**, **then** pair **recipe-level optional fields** (`sodium_mg_per_serving`, `added_sugar_g_per_serving`) with **rollup rules** that respect **honest imprecision** when data is missing.  
6. **If** the app plots **growth**, **then** pin **WHO vs CDC** chart sources **by age**, store **sex** and **measurement metadata**, and ship **legal/clinical disclaimers** reviewed before marketing.

---

## Open questions — next research

1. **Indonesia-specific** complementary feeding and **school-age** guidelines — official PDFs and **Bahasa** consumer copy.  
2. **Evidence** on caregiver willingness to log for **6–12 year olds** (school lunch, snacks).  
3. **Competitor scan** of how **Baby-led weaning** apps vs **clinical** apps phrase **iron** and **allergen** content (market + liability).  
4. **Validation** with **1–2 pediatric dietitians** on **M1 hint templates** (paid consult recommended).  
5. **Legal review** of **growth chart** and **shareable recipe URL** copy (privacy, not medical device claims).  
6. **Version lock** for **WHO LMS** and **CDC chart** data files used in engineering.

---

## Linked product artifacts

- [`child-nutrition-app-context.md`](./child-nutrition-app-context.md)  
- [`milestone-1-spec.md`](./milestone-1-spec.md)  
- [`prd-milestone-1.md`](./prd-milestone-1.md) — M1 macro model §7
