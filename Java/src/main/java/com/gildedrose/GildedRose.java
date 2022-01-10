package com.gildedrose;

class GildedRose {
    public enum ITEM_TYPE {
        BRIE,
        LEGENDARY,
        CONJURED,
        TICKET,
        NORMAL;
    }

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public static ITEM_TYPE getTypeOfItem(Item item) {
        switch (item.name) {
            case "Aged Brie": return ITEM_TYPE.BRIE;
            case "Sulfuras, Hand of Ragnaros": return ITEM_TYPE.LEGENDARY;
            case "Backstage passes to a TAFKAL80ETC concert": return ITEM_TYPE.TICKET;
            case "Conjured Mana Cake": return ITEM_TYPE.CONJURED;
            default: return ITEM_TYPE.NORMAL;
        }
    }

    public void updateQuality() {
        for (Item item : items) {
            final ITEM_TYPE type = getTypeOfItem(item);

            if (type == ITEM_TYPE.CONJURED) {
                item.quality = item.quality - 2;

                if (item.sellIn <= 0)
                    item.quality = item.quality - 1;

            } else if (type != ITEM_TYPE.BRIE && type != ITEM_TYPE.TICKET) {
                if (item.quality > 0) {
                    if (type != ITEM_TYPE.LEGENDARY) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (type == ITEM_TYPE.TICKET) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (type != ITEM_TYPE.LEGENDARY) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (type != ITEM_TYPE.BRIE) {
                    if (type != ITEM_TYPE.TICKET) {
                        if (item.quality > 0) {
                            if (type != ITEM_TYPE.LEGENDARY) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = item.quality - item.quality;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}