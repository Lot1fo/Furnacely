package net.lotifo.furnacely.mixin;

import net.lotifo.furnacely.fuel.FuelReserve;
import net.lotifo.furnacely.inventory.InventoryLayout;
import net.lotifo.furnacely.queue.QueueManager;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin entry point for the vanilla furnace. */
@Mixin(FurnaceBlockEntity.class)
public abstract class FurnaceMixin {

  @Shadow protected DefaultedList<ItemStack> inventory;

  @Inject(method = "<init>", at = @At("TAIL"))
  private void furnacely$init(CallbackInfo info) {
    this.inventory = DefaultedList.ofSize(InventoryLayout.TOTAL, ItemStack.EMPTY);
  }

  @Inject(method = "readNbt", at = @At("TAIL"))
  private void furnacely$readNbt(
      NbtCompound nbt, RegistryWrapper.WrapperLookup registries, CallbackInfo ci) {
    if (this.inventory != null && this.inventory.size() == InventoryLayout.INPUT_COUNT) {
      DefaultedList<ItemStack> expanded =
          DefaultedList.ofSize(InventoryLayout.TOTAL, ItemStack.EMPTY);
      for (int i = 0; i < InventoryLayout.INPUT_COUNT; i++) {
        expanded.set(i, this.inventory.get(i).copy());
      }
      this.inventory = expanded;
    }
  }

  @Inject(method = "tick", at = @At("HEAD"))
  private void furnacely$beforeTick(CallbackInfo callbackInfo) {
    if (inventory == null || inventory.size() < InventoryLayout.TOTAL) return;
    QueueManager.promote(inventory);
    if (FuelReserve.needsRefill(inventory)) {
      FuelReserve.rotate(inventory);
    }
  }
}
