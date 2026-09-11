package net.lotifo.furnacely.fuel;

import net.lotifo.furnacely.inventory.InventoryLayout;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public final class FuelReserve {

  private FuelReserve() {}

  public static boolean needsRefill(DefaultedList<ItemStack> inventory) {
    if (inventory == null || inventory.size() < 6) return false;
    if (inventory.get(InventoryLayout.fuelSlot(0)).isEmpty()) {
      return !inventory.get(InventoryLayout.fuelSlot(1)).isEmpty()
          || !inventory.get(InventoryLayout.fuelSlot(2)).isEmpty();
    }
    return false;
  }

  public static void rotate(DefaultedList<ItemStack> inventory) {
    if (inventory == null || inventory.size() < 6) return;
    inventory.set(InventoryLayout.fuelSlot(0), inventory.get(InventoryLayout.fuelSlot(1)).copy());
    inventory.set(InventoryLayout.fuelSlot(1), inventory.get(InventoryLayout.fuelSlot(2)).copy());
    inventory.set(InventoryLayout.fuelSlot(2), ItemStack.EMPTY);
  }
}
