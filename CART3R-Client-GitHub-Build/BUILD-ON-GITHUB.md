# Build CART3R Client on GitHub

This project includes a GitHub Actions workflow that builds the mod with Java 25 and Gradle 9.6.

1. Create a new, empty repository on GitHub.
2. Upload the contents of this folder to the repository (not the outer folder itself).
3. Open the repository's **Actions** tab.
4. Select **Build CART3R Client**.
5. Click **Run workflow** and wait for the job to finish.
6. Open the completed workflow run and download the artifact named **CART3R-Client-1.0.0**.
7. Extract the downloaded artifact ZIP. Put `CART3R-Client-1.0.0.jar` into your Modrinth instance's `mods` folder. Install Fabric API in that instance too.

Target: Minecraft Java 26.3, Fabric Loader 0.20.1, Java 25.

Important: This workflow has been prepared but not executed here. The Java sources have not been verified against a real Minecraft 26.3 Gradle build in this environment. In particular, the in-game HUD/GUI classes may need API adjustments before the build passes. ESP, X-ray, Freecam and Auto-clicker remain placeholders in the current source.
