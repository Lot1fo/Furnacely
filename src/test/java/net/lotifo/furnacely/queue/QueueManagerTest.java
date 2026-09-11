/* Author: Lotifo */
package net.lotifo.furnacely.queue;

import static org.junit.jupiter.api.Assertions.*;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.junit.jupiter.api.Test;

public class QueueManagerTest {

  @Test
  public void testIsQueueEmptyStatic() {
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(9, ItemStack.EMPTY);
    assertTrue(QueueManager.isQueueEmpty(inv));
  }

  @Test
  public void testPromoteStatic() {
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(9, ItemStack.EMPTY);
    inv.set(1, new net.minecraft.item.ItemStack(net.minecraft.item.Items.COAL, 1));
    assertTrue(QueueManager.promote(inv));
    assertFalse(QueueManager.isQueueEmpty(inv));
  }

  @Test
  public void testVisibleCountStatic() {
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(9, ItemStack.EMPTY);
    assertEquals(0, QueueManager.visibleCount(inv));
  }

  @Test
  public void testPromoteShiftsCorrectly() {
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(9, ItemStack.EMPTY);
    inv.set(1, new net.minecraft.item.ItemStack(net.minecraft.item.Items.COAL, 1));
    inv.set(2, new net.minecraft.item.ItemStack(net.minecraft.item.Items.COAL, 1));
    assertTrue(QueueManager.promote(inv));
    assertFalse(inv.get(0).isEmpty());
    assertTrue(inv.get(2).isEmpty());
  }

  @Test
  public void testVisibleCountWithItems() {
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(9, ItemStack.EMPTY);
    inv.set(0, new net.minecraft.item.ItemStack(net.minecraft.item.Items.COAL, 1));
    inv.set(2, new net.minecraft.item.ItemStack(net.minecraft.item.Items.COAL, 1));
    assertEquals(2, QueueManager.visibleCount(inv));
  }
}
