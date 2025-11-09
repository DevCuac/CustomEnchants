package com.taiter.ce.Enchantments.Helmet;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Glowing extends CEnchantment {


	public Glowing(Application app) {
		super(app);		
		triggers.add(Trigger.WEAR_ITEM);
		this.resetMaxLevel();
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
	}

	@Override
	public void initConfigEntries() {
		this.potionsOnWear.put(PotionEffectType.NIGHT_VISION, 1);
	}
}
