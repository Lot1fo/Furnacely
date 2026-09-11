package net.lotifo.furnacely;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point and shared constants for Furnacely.
 *
 * <p>The mod does not add a new block. It rewires the vanilla furnace through a mixin, so this
 * class only carries the tunables every subsystem agrees on (queue depth, fuel reserve, output slot
 * count) plus the mod id used to register and log.
 */
public final class Furnacely implements ModInitializer {

  public static final String MOD_ID = "furnacely";

  // Sequential smelt queue length: 1 active slot + N reserve slots, fed in order.
  public static final int QUEUE_SIZE = 3;

  // Fuel held behind the active slot. Two reserves let a player top up the
  // furnace once and walk away without the burn dying between visits.
  public static final int FUEL_RESERVE_SIZE = 2;

  // Vanilla furnace has 3 output slots and we keep that contract intact:
  // changing it would break item insertion math and the stop-if-full rule.
  public static final int OUTPUT_SLOTS = 3;

  private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    LOGGER.info("Furnacely initialised");
  }
}
