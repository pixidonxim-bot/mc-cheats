package dev.cart3r.client;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class CART3RClickGui extends Screen {
    private static final int RED = 0xFFFF3445;
    private static final int PANEL = 0xF00B0B10;
    private static final int MUTED = 0xFFB7B7C0;

    public CART3RClickGui() {
        super(Component.literal("CART3R CLIENT"));
    }

    @Override
    protected void init() {
        int panelWidth = Math.min(360, this.width - 32);
        int left = (this.width - panelWidth) / 2;
        int top = Math.max(24, (this.height - 300) / 2);
        int buttonWidth = panelWidth - 32;
        int y = top + 52;

        addModuleButton("HUD", CART3RModules.HUD, left, y, buttonWidth); y += 25;
        addModuleButton("Coordinates", CART3RModules.COORDINATES, left, y, buttonWidth); y += 25;
        addModuleButton("Keystrokes", CART3RModules.KEYSTROKES, left, y, buttonWidth); y += 25;
        addModuleButton("Sprint", CART3RModules.SPRINT, left, y, buttonWidth); y += 25;
        addModuleButton("Fullbright", CART3RModules.FULLBRIGHT, left, y, buttonWidth); y += 25;

        addRenderableWidget(Button.builder(Component.literal("Close  [Right Shift]"),
                button -> onClose()).bounds(left + 16, y + 8, buttonWidth, 20).build());
    }

    private void addModuleButton(String title, CART3RModules.Module module,
                                 int left, int y, int width) {
        String label = title + ": " + (module.enabled() ? "ON" : "OFF");
        addRenderableWidget(Button.builder(Component.literal(label), button -> {
            module.toggle();
            button.setMessage(Component.literal(title + ": " + (module.enabled() ? "ON" : "OFF")));
        }).bounds(left + 16, y, width, 20).build());
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX,
                                   int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        int panelWidth = Math.min(360, this.width - 32);
        int left = (this.width - panelWidth) / 2;
        int top = Math.max(24, (this.height - 300) / 2);

        graphics.fill(left - 2, top - 2, left + panelWidth + 2, top + 310, RED);
        graphics.fill(left, top, left + panelWidth, top + 306, PANEL);
        graphics.fill(left, top, left + panelWidth, top + 34, 0xFF19090D);
        graphics.text(this.font, "CART3R CLIENT", left + 16, top + 12, RED, true);
        graphics.text(this.font, "CLIENT MODULES  /  LOCAL PROFILE",
                left + 16, top + 39, MUTED, true);
        graphics.text(this.font, "Right Shift  •  Toggle menu",
                left + 16, top + 286, MUTED, true);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
