# CART3R Client

A client-side Fabric mod starter for Minecraft 26.3, styled in crimson and black.

## Included and implemented
- Right Shift opens the custom Click GUI.
- HUD brand panel.
- Coordinates display.
- WASD + Space keystrokes display.
- Sprint toggle.
- Fullbright via the local gamma option, restoring the previous gamma when disabled.
- HUD, coordinates and keystrokes can be toggled in the GUI.

## Not implemented in this starter
ESP, X-ray, Freecam and Auto-clicker are shown as planned modules in the module registry, but are deliberately not wired to game behavior. They need dedicated rendering/input hooks and careful local-world gating; they are not represented as working features.

## Toolchain
This project tracks the current Fabric develop page values at project creation:
- Minecraft 26.3
- Fabric Loader 0.20.1
- Fabric API 0.166.0+26.3
- Fabric Loom 1.19-SNAPSHOT
- Gradle 9.6
- Java 25

Fabric's versions move quickly. If the develop page has advanced, update `gradle.properties` to its current compatible values.

## Build
Install JDK 25 and Gradle 9.6, then run from this directory:

```bash
gradle build
```

The remapped mod JAR should be in `build/libs/`.

Copy the resulting JAR into your Minecraft instance's `mods` folder alongside Fabric API.

## Controls
- Right Shift: open/close Click GUI
- Sprint and Fullbright: can be toggled from the GUI or configured under Options > Controls.

## Important
This archive is a source project, not a precompiled JAR. The build has not been executed in this environment. The Minecraft 26.3 APIs are recent; if Gradle reports an API mismatch, use the current official Fabric template and documentation as the reference.
