package com.taiter.ce.Enchantments.Armor;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class ObsidianShield extends CEnchantment {


	public ObsidianShield(Application app) {
		super(app);		
		triggers.add(Trigger.WEAR_ITEM);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
	}

	@Override
	public void initConfigEntries() {
		this.potionsOnWear.put(PotionEffectType.FIRE_RESISTANCE, 1);
	}
}
