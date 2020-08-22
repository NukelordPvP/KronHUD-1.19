package io.github.darkkronicle.kronhud.gui.screen;

import io.github.darkkronicle.kronhud.KronHUD;
import io.github.darkkronicle.kronhud.gui.AbstractHudEntry;
import io.github.darkkronicle.kronhud.gui.hud.ArmorHud;
import io.github.darkkronicle.polish.api.EntryBuilder;
import io.github.darkkronicle.polish.gui.complexwidgets.EntryButtonList;
import io.github.darkkronicle.polish.util.WidgetManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class ConfigScreen extends Screen {
    private WidgetManager widgetManager = new WidgetManager(this, children);
    private MinecraftClient client;

    private ConfigScreen() {
        super(new LiteralText("Configuration"));
        client = MinecraftClient.getInstance();
        Window window = client.getWindow();
        EntryButtonList list = new EntryButtonList((window.getScaledWidth() / 2) - 300, (window.getScaledHeight() / 2) - 200, 600, 400, 2);
        EntryBuilder builder = EntryBuilder.create();
        for (AbstractHudEntry entry : KronHUD.hudManager.getEntries()) {
            list.addEntry(builder.startCleanButtonEntry(new LiteralText(entry.getName()), new LiteralText("Config"), cleanButton -> client.openScreen(entry.getConfigScreen())).setDimensions(50, 13).build(list));
        }
        widgetManager.add(list);
    }

    public static Screen getScreen() {
        return new ConfigScreen();
    }

    @Override
    public void tick() {
        widgetManager.tick();
        super.tick();
    }

    @Override
    public boolean charTyped(char chr, int keyCode) {
        boolean yes = super.charTyped(chr, keyCode);
        widgetManager.charTyped(chr, keyCode);
        return yes;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        widgetManager.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        widgetManager.mouseScrolled(mouseX, mouseY, amount);
        return false;
    }


    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean yes = super.keyPressed(keyCode, scanCode, modifiers);
        widgetManager.keyPressed(keyCode, scanCode, modifiers);
        return yes;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean yes = super.mouseClicked(mouseX, mouseY, button);
        widgetManager.mouseClicked(mouseX, mouseY, button);
        return yes;
    }
}