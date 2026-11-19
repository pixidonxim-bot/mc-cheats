package dev.cart3r.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public final class CART3RClient implements ClientModInitializer {
    public static final String MOD_ID = "cart3r";

    private static KeyMapping openGuiKey;
    private static KeyMapping toggleSprintKey;
    private static KeyMapping toggleFullbrightKey;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath(MOD_ID, "main")
        );

        openGuiKey = KeyBindingHelper.registerKeyMapping(new KeyMapping(
                "key.cart3r.open_gui", InputConstants.Type.KEYBOARD,
                GLFW.GLFW_KEY_RIGHT_SHIFT, category
        ));
        toggleSprintKey = KeyBindingHelper.registerKeyMapping(new KeyMapping(
                "key.cart3r.toggle_sprint", InputConstants.Type.KEYBOARD,
                GLFW.GLFW_KEY_UNKNOWN, category
        ));
        toggleFullbrightKey = KeyBindingHelper.registerKeyMapping(new KeyMapping(
                "key.cart3r.toggle_fullbright", InputConstants.Type.KEYBOARD,
                GLFW.GLFW_KEY_UNKNOWN, category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.consumeClick()) {
                client.gui.setScreen(new CART3RClickGui());
            }
            while (toggleSprintKey.consumeClick()) {
                CART3RModules.SPRINT.toggle();
            }
            while (toggleFullbrightKey.consumeClick()) {
                CART3RModules.FULLBRIGHT.toggle();
            }

            if (client.player != null && client.level != null) {
                if (CART3RModules.SPRINT.enabled()) {
                    client.player.setSprinting(true);
                }
                CART3RModules.applyFullbright(client);
            }
        });

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(MOD_ID, "hud"),
                (graphics, deltaTracker) -> CART3RHud.render(Minecraft.getInstance(), graphics)
        );
    }
}
