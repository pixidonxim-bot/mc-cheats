package dev.cart3r.client;

import net.minecraft.client.Minecraft;

import java.util.EnumMap;
import java.util.Map;

public final class CART3RModules {
    private CART3RModules() {}

    public enum Module {
        HUD("HUD", true),
        COORDINATES("Coordinates", true),
        KEYSTROKES("Keystrokes", true),
        SPRINT("Sprint", false),
        FULLBRIGHT("Fullbright", false),
        ESP("ESP (local test worlds)", false),
        XRAY("X-ray (not implemented)", false),
        FREECAM("Freecam (not implemented)", false),
        AUTOCLICKER("Autoclicker (not implemented)", false);

        private final String label;
        private boolean enabled;

        Module(String label, boolean initial) {
            this.label = label;
            this.enabled = initial;
        }

        public String label() { return label; }
        public boolean enabled() { return enabled; }
        public void toggle() { enabled = !enabled; }
        public void setEnabled(boolean value) { enabled = value; }
    }

    private static final Map<Module, Double> originalGamma = new EnumMap<>(Module.class);
    private static boolean gammaCaptured = false;

    public static void applyFullbright(Minecraft client) {
        if (client.options == null) return;

        if (FULLBRIGHT.enabled()) {
            if (!gammaCaptured) {
                originalGamma.put(Module.FULLBRIGHT, client.options.gamma().get());
                gammaCaptured = true;
            }
            client.options.gamma().set(16.0);
        } else if (gammaCaptured) {
            Double old = originalGamma.get(Module.FULLBRIGHT);
            if (old != null) client.options.gamma().set(old);
            gammaCaptured = false;
            originalGamma.clear();
        }
    }

    public static final Module HUD = Module.HUD;
    public static final Module COORDINATES = Module.COORDINATES;
    public static final Module KEYSTROKES = Module.KEYSTROKES;
    public static final Module SPRINT = Module.SPRINT;
    public static final Module FULLBRIGHT = Module.FULLBRIGHT;
    public static final Module ESP = Module.ESP;
    public static final Module XRAY = Module.XRAY;
    public static final Module FREECAM = Module.FREECAM;
    public static final Module AUTOCLICKER = Module.AUTOCLICKER;
}
