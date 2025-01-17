package io.github.darkkronicle.kronhud.gui.hud;

import fi.dy.masa.malilib.config.IConfigBase;
import io.github.darkkronicle.kronhud.gui.AbstractHudEntry;
import io.github.darkkronicle.kronhud.util.DrawPosition;
import net.minecraft.client.util.math.MatrixStack;

import java.util.List;

public abstract class CleanHudEntry extends AbstractHudEntry {

    public CleanHudEntry() {
        super(53, 13);
    }

    protected CleanHudEntry(int width, int height) {
        super(width, height);
    }

    @Override
    public void render(MatrixStack matrices) {
        matrices.push();
        scale(matrices);
        DrawPosition pos = getPos();
        if (background.getBooleanValue()) {
            fillRect(matrices, getBounds(), backgroundColor.getColor());
        }
        drawCenteredString(matrices, client.textRenderer, getValue(), new DrawPosition(pos.x() + (Math.round(width) / 2),
                pos.y() + (Math.round((float) height / 2)) - 4), textColor.getColor(), shadow.getBooleanValue());
        matrices.pop();
    }

    @Override
    public void renderPlaceholder(MatrixStack matrices) {
        matrices.push();
        renderPlaceholderBackground(matrices);
        scale(matrices);
        DrawPosition pos = getPos();
        drawCenteredString(matrices, client.textRenderer, getPlaceholder(),
                new DrawPosition(pos.x() + (Math.round(width) / 2),
                pos.y() + (Math.round((float) height / 2)) - 4), textColor.getColor(), shadow.getBooleanValue());
        matrices.pop();
        hovered = false;
    }

    @Override
    public void addConfigOptions(List<IConfigBase> options) {
        super.addConfigOptions(options);
        options.add(textColor);
        options.add(shadow);
        options.add(background);
        options.add(backgroundColor);
    }

    @Override
    public boolean movable() {
        return true;
    }

    public abstract String getValue();

    public abstract String getPlaceholder();

}
