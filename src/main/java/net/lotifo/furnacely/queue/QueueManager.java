package net.lotifo.furnacely.queue;

import net.lotifo.furnacely.inventory.InventoryLayout;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

/**
 * Pure functions that read and reshape the smelt queue inside the furnace inventory.
 *
 * <p>The queue is the contiguous input region of the backing inventory; the helpers here never look
 * at fuel or output and hold no state, so the same call is safe from a server tick, a client sync,
 * and a chunk-load path without coordination.
 */
public final class QueueManager {

  private QueueManager() {}

  public static boolean isQueueEmpty(DefaultedList<ItemStack> inventory) {
    if (inventory == null || inventory.size() < InventoryLayout.INPUT_END_EXCLUSIVE) return true;
    for (int i = InventoryLayout.INPUT_FIRST; i < InventoryLayout.INPUT_END_EXCLUSIVE; i++) {
      if (!inventory.get(i).isEmpty()) {
        return false;
      }
    }
    return true;
  }

  public static boolean promote(DefaultedList<ItemStack> inventory) {
    if (inventory == null || inventory.size() < InventoryLayout.INPUT_END_EXCLUSIVE) return false;
    // The vanilla tick only consumes slot 0; if the front is empty but later slots
    // hold work, shift the queue forward so the next item becomes the active target.
    // Skip when slot 0 is already populated - the tick will pick it up on its own.
    ItemStack front = inventory.get(InventoryLayout.INPUT_FIRST);
    if (!front.isEmpty()) {
      return false;
    }
    ItemStack second = inventory.get(InventoryLayout.INPUT_FIRST + 1);
    ItemStack third = inventory.get(InventoryLayout.INPUT_FIRST + 2);
    if (second.isEmpty() && third.isEmpty()) {
      return false;
    }
    inventory.set(InventoryLayout.INPUT_FIRST, second.copy());
    inventory.set(InventoryLayout.INPUT_FIRST + 1, third.copy());
    inventory.set(InventoryLayout.INPUT_FIRST + 2, ItemStack.EMPTY);
    return true;
  }

  public static int visibleCount(DefaultedList<ItemStack> inventory) {
    if (inventory == null || inventory.size() < InventoryLayout.INPUT_END_EXCLUSIVE) return 0;
    int count = 0;
    for (int i = InventoryLayout.INPUT_FIRST; i < InventoryLayout.INPUT_END_EXCLUSIVE; i++) {
      if (!inventory.get(i).isEmpty()) {
        count++;
      }
    }
    return count;
  }
}
