# Furnacely - Code Formatting

This project uses Spotless with the google-java-format plugin to keep Java sources consistent without manual style debates.

## Why

Furnacely is a solo project. The goal is one enforced style, applied automatically at build time, with zero ongoing configuration churn. google-java-format produces a known, deterministic layout - no opinions, no per-author drift.

## Gradle Snippet

Spotless is applied via the `spotless` plugin in `build.gradle`; `googleJavaFormat()` runs on every build (`spotlessCheck` + `spotlessApply`). The snippet above is now live.

```gradle
plugins {
    id 'java'
    id 'maven-publish'
    id 'com.diffplug.spotless' version '6.25.0'
}

spotless {
    java {
        googleJavaFormat()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
```

## Commands

- `./gradlew spotlessCheck` - verify that all Java sources match the formatter.
- `./gradlew spotlessApply` - auto-format every Java source in place.

`spotlessCheck` is intended to run on every build (and in CI when the project grows).

## Pairing with EditorConfig

`.editorconfig` defines the baseline that human authors and editors follow:

- `indent_style = space`, `indent_size = 4` for Java.
- `charset = utf-8`.
- `end_of_line = lf`, `insert_final_newline = true`.

google-java-format runs on top of this baseline and produces the final source layout. The two are complementary: EditorConfig shapes what humans type, Spotless rewrites what the build commits.

## Pointers

- `.editorconfig` - formatting baseline for humans and editors.
- `.gitignore` - excludes build outputs and transient files.
- `.kilo/REFERENCE.md` - full project concept (queue, fuel reserve, freeze, vanilla balance).