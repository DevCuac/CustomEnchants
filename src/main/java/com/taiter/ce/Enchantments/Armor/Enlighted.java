package com.taiter.ce.Enchantments.Armor;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Enlighted extends CEnchantment {
	
	int duration;
	int strength;

	public Enlighted(Application app) {
		super(app);		
		configEntries.put("Duration", 60);
		configEntries.put("Strength", 1);
		triggers.add(Trigger.DAMAGE_TAKEN);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		Player owner = (Player) event.getEntity();
		owner.addPotionEffect(
				new PotionEffect(
						PotionEffectType.REGENERATION,
						duration * level, 
						strength + level));
		
	}
	
	@Override
	public void initConfigEntries() {
		duration = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Duration"));
		strength = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Strength"))-1;
	}
	
}
