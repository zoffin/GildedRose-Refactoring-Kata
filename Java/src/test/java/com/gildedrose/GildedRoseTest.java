package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gildedrose.GildedRose.ITEM_TYPE;

class GildedRoseTest {
    private static Item[] getItems() {
        return new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };
    }

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void checkTickets() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality);
        assertEquals(14, app.items[0].sellIn);

        items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 8, 20) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(22, app.items[0].quality);
        assertEquals(7, app.items[0].sellIn);

        items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 4, 20) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);

        items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);

        items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 4, 49) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);

        items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void checkBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);

        items = new Item[] { new Item("Aged Brie", 5, 50) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void checkQualityValuesOfItems() {
        GildedRose app = new GildedRose(getItems());

        for (int i=0; i<100; i++)
            app.updateQuality();
      
        for (Item item : app.items) {
            if (item.name == "Sulfuras, Hand of Ragnaros")
                assertTrue(item.quality == 80);
            else
                assertTrue(item.quality <= 50);
        }
    }

    @Test
    void checkNormalItems() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 1, 21)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(20, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);

        app.updateQuality();
        assertEquals(18, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);

        app.updateQuality();
        assertEquals(16, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void checkConjuredItems() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 1, 22)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(20, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);

        app.updateQuality();
        assertEquals(16, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);

        app.updateQuality();
        assertEquals(12, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }


    @Test
    void checkGetTypeOfItem() {
        assertEquals(ITEM_TYPE.NORMAL, GildedRose.getTypeOfItem(new Item("+5 Dexterity Vest", 10, 20)));
        assertEquals(ITEM_TYPE.NORMAL, GildedRose.getTypeOfItem(new Item("Elixir of the Mongoose", 5, 7)));
        assertEquals(ITEM_TYPE.BRIE, GildedRose.getTypeOfItem(new Item("Aged Brie", 2, 0)));
        assertEquals(ITEM_TYPE.LEGENDARY, GildedRose.getTypeOfItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80)));
        assertEquals(ITEM_TYPE.TICKET, GildedRose.getTypeOfItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)));
        assertEquals(ITEM_TYPE.CONJURED, GildedRose.getTypeOfItem(new Item("Conjured Mana Cake", 3, 6)));
    }
}
