package net.lotifo.furnacely.inventory;

import net.lotifo.furnacely.Furnacely;

/**
 * Fixed layout of the extended furnace inventory.
 *
 * <p>Three logical groups share the block entity backing inventory, in order: input queue, fuel,
 * output. Index helpers exist so callers never spell raw slot numbers, which keeps the mixin code
 * aligned with Furnacely tunables.
 */
public final class InventoryLayout {

  public static final int INPUT_COUNT = Furnacely.QUEUE_SIZE;
  public static final int FUEL_COUNT = 1 + Furnacely.FUEL_RESERVE_SIZE;
  public static final int OUTPUT_COUNT = Furnacely.OUTPUT_SLOTS;

  public static final int TOTAL = INPUT_COUNT + FUEL_COUNT + OUTPUT_COUNT;

  public static final int INPUT_FIRST = 0;
  public static final int FUEL_FIRST = INPUT_COUNT;
  public static final int OUTPUT_FIRST = INPUT_COUNT + FUEL_COUNT;

  public static final int INPUT_END_EXCLUSIVE = FUEL_FIRST;
  public static final int FUEL_END_EXCLUSIVE = OUTPUT_FIRST;
  private static final int OUTPUT_END_EXCLUSIVE = TOTAL;

  private InventoryLayout() {
    // Static layout table; instantiation has no meaning.
  }

  public static int inputSlot(int i) {
    return INPUT_FIRST + i;
  }

  public static int fuelSlot(int i) {
    return FUEL_FIRST + i;
  }

  public static int outputSlot(int i) {
    return OUTPUT_FIRST + i;
  }

  public static boolean isInput(int index) {
    return index >= INPUT_FIRST && index < INPUT_END_EXCLUSIVE;
  }

  public static boolean isFuel(int index) {
    return index >= FUEL_FIRST && index < FUEL_END_EXCLUSIVE;
  }

  public static boolean isOutput(int index) {
    return index >= OUTPUT_FIRST && index < OUTPUT_END_EXCLUSIVE;
  }
}
