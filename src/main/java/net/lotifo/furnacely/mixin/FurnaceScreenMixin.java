package net.lotifo.furnacely.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.FurnaceScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FurnaceScreen.class)
public abstract class FurnaceScreenMixin {

  @Inject(method = "render", at = @At("TAIL"))
  private void furnacely$renderLabels(
      DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
    int screenX = ((FurnaceScreen) (Object) this).x;
    int screenY = ((FurnaceScreen) (Object) this).y;
    int baseX = screenX + 56;
    int baseY = screenY + 12;
    for (int i = 0; i < 3; i++) {
      context.drawTextWithShadow(
          ((FurnaceScreen) (Object) this).getTextRenderer(),
          Text.literal(String.valueOf(i + 1)).formatted(Formatting.WHITE),
          baseX + i * 18,
          baseY,
          0xFFFFFF);
    }
    int fuelX = screenX + 116;
    for (int i = 0; i < 3; i++) {
      context.drawTextWithShadow(
          ((FurnaceScreen) (Object) this).getTextRenderer(),
          Text.literal("F" + (i + 1)).formatted(Formatting.YELLOW),
          fuelX + i * 18,
          baseY,
          0xFFFFFF);
    }
  }
}
