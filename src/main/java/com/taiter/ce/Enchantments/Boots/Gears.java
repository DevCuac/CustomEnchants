package com.taiter.ce.Enchantments.Boots;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Gears extends CEnchantment {

	int	strength;

	public Gears(Application app) {
		super(app);		
		configEntries.put("SpeedBoost", 1);
		triggers.add(Trigger.WEAR_ITEM);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
	}

	@Override
	public void initConfigEntries() {
		strength = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".SpeedBoost"))-1;
		this.getPotionEffectsOnWear().put(PotionEffectType.SPEED, strength);
	}
}
