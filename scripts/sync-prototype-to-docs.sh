#!/usr/bin/env bash
# Copies the static M1 prototype into docs/ for GitHub Pages
# ("Deploy from a branch" → main → /docs). Run from repo root after editing the prototype.
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
SRC="$ROOT/Child Nutrition App/m1-hifi-prototype"
DST="$ROOT/docs"
mkdir -p "$DST"
rsync -a --delete \
  --exclude='.git' \
  "$SRC/" "$DST/"
echo "Synced prototype → docs/ ($DST)"
