package com.taiter.ce.Enchantments.Armor;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Hardened extends CEnchantment {
	
	int duration;
	int strength;

	public Hardened(Application app) {
		super(app);
		configEntries.put("Duration", 60);
		configEntries.put("Strength", 1);
		triggers.add(Trigger.DAMAGE_TAKEN);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		LivingEntity target = (LivingEntity) event.getDamager();
			target.addPotionEffect(
					new PotionEffect(
							PotionEffectType.WEAKNESS, 
							duration * level,
							strength + level));

	}

	@Override
	public void initConfigEntries() {
	duration = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Duration"));
	strength = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Strength"))-1;
	}
}
