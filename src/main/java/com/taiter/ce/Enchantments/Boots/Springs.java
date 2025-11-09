package com.taiter.ce.Enchantments.Boots;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Springs extends CEnchantment {

	int	strength;

	public Springs(Application app) {
		super(app);		
		configEntries.put("Strength", 4);
		triggers.add(Trigger.WEAR_ITEM);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
	}

	@Override
	public void initConfigEntries() {
		strength = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Strength"))-1;
		this.potionsOnWear.put(PotionEffectType.JUMP, strength);
	}
}
