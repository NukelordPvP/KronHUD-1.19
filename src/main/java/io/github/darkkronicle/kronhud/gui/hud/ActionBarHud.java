package io.github.darkkronicle.kronhud.gui.hud;

import fi.dy.masa.malilib.config.IConfigBase;
import io.github.darkkronicle.kronhud.config.KronBoolean;
import io.github.darkkronicle.kronhud.config.KronInteger;
import io.github.darkkronicle.kronhud.gui.AbstractHudEntry;
import io.github.darkkronicle.kronhud.util.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ActionBarHud extends AbstractHudEntry {

    public static final Identifier ID = new Identifier("kronhud", "actionbarhud");

    public KronInteger timeShown = new KronInteger("timeshown", ID.getPath(), 60, 40, 300);
    public KronBoolean customTextColor = new KronBoolean("customtextcolor", ID.getPath(), false);

    private Text actionBar;
    private int ticksShown;
    private int color;
    private final String placeholder = "Action Bar";

    public ActionBarHud() {
        super(115, 13);
        client = MinecraftClient.getInstance();
    }

    public void setActionBar(Text bar, int color){this.actionBar = bar; this.color = color;}

    @Override
    public void render(MatrixStack matrices) {
        if (ticksShown >= timeShown.getIntegerValue()){
            this.actionBar = null;
        }
        Color vanillaColor = new Color(color);
        if(this.actionBar != null) {

            matrices.push();
            scale(matrices);
            if (shadow.getBooleanValue()){
                client.textRenderer.drawWithShadow(matrices, actionBar,
                        (float)getPos().x() + Math.round((float) width /2) -  (float) client.textRenderer.getWidth(actionBar) /2,
                        (float)getPos().y() + 3,
                        customTextColor.getBooleanValue() ? (textColor.getColor().alpha()==255 ?
                                new Color(
                                        textColor.getColor().red(),
                                        textColor.getColor().green(),
                                        textColor.getColor().blue(),
                                        vanillaColor.alpha()).color():
                                textColor.getColor().color()) :
                                color
                );
            } else {

                client.textRenderer.draw(matrices, actionBar,
                        (float)getPos().x() + Math.round((float) width /2) - ((float) client.textRenderer.getWidth(actionBar) /2),
                        (float)getPos().y() + 3,
                        customTextColor.getBooleanValue() ? (textColor.getColor().alpha()==255 ?
                                new Color(textColor.getColor().red(),
                                        textColor.getColor().green(),
                                        textColor.getColor().blue(),
                                        vanillaColor.alpha()).color():
                                textColor.getColor().color()) :
                                color
                );
            }
            matrices.pop();
            ticksShown++;
        } else {
            ticksShown = 0;
        }
    }

    @Override
    public void renderPlaceholder(MatrixStack matrices) {
        matrices.push();
        renderPlaceholderBackground(matrices);
        scale(matrices);
        client.textRenderer.draw(matrices, placeholder,  (float)getPos().x() + Math.round((float) width /2) - (float) client.textRenderer.getWidth(placeholder) /2, (float)getPos().y() + 3, -1);
        matrices.pop();
        hovered = false;
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    public boolean movable() {
        return true;
    }

    @Override
    public void addConfigOptions(List<IConfigBase> options){
        super.addConfigOptions(options);
        options.add(shadow);
        options.add(timeShown);
        options.add(customTextColor);
        options.add(textColor);
    }
}
