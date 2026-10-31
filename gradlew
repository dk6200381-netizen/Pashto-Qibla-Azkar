#!/usr/bin/env sh
set -eu

GRADLE_VERSION=8.9
GRADLE_DIR="$HOME/.gradle/manual/gradle-$GRADLE_VERSION"

if [ ! -d "$GRADLE_DIR" ]; then
  mkdir -p "$HOME/.gradle/manual"
  curl -L "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip" -o "$HOME/.gradle/manual/gradle.zip"
  unzip -q "$HOME/.gradle/manual/gradle.zip" -d "$HOME/.gradle/manual"
fi

exec "$GRADLE_DIR/bin/gradle" "$@" 
