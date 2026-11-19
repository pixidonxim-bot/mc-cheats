package dev.cart3r.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public final class CART3RHud {
    private CART3RHud() {}

    public static void render(Minecraft client, GuiGraphicsExtractor graphics) {
        if (client.player == null || !CART3RModules.HUD.enabled()) return;

        int red = 0xFFFF3445;
        int white = 0xFFF2F2F2;
        int dark = 0xB509090D;

        graphics.fill(7, 7, 126, 24, dark);
        graphics.fill(7, 7, 10, 24, red);
        graphics.text(client.font, "CART3R CLIENT", 15, 12, red, true);

        int y = 30;
        if (CART3RModules.COORDINATES.enabled()) {
            String coords = String.format("XYZ: %.1f / %.1f / %.1f",
                    client.player.getX(), client.player.getY(), client.player.getZ());
            graphics.fill(7, y - 3, 160, y + 12, dark);
            graphics.text(client.font, coords, 12, y, white, true);
            y += 17;
        }

        if (CART3RModules.KEYSTROKES.enabled()) {
            String keys = "W " + key(client.options.keyUp.isDown())
                    + "  A " + key(client.options.keyLeft.isDown())
                    + "  S " + key(client.options.keyDown.isDown())
                    + "  D " + key(client.options.keyRight.isDown())
                    + "  SPACE " + key(client.options.keyJump.isDown());
            graphics.fill(7, y - 3, 190, y + 12, dark);
            graphics.text(client.font, keys, 12, y, white, true);
        }
    }

    private static String key(boolean down) {
        return down ? "§c■" : "§7□";
    }
}
