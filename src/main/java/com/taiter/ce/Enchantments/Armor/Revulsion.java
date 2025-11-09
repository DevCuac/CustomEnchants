package com.taiter.ce.Enchantments.Armor;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Revulsion extends CEnchantment {
	
	int duration;

	public Revulsion(Application app) {
		super(app);		
		configEntries.put("Duration", 20);
		triggers.add(Trigger.DAMAGE_TAKEN);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		LivingEntity target = (LivingEntity) event.getDamager();
			target.addPotionEffect(
				new PotionEffect(
						PotionEffectType.CONFUSION,
						duration * level, //This value is counted in ticks, 1/20 of a second
						0));
	}

	@Override
	public void initConfigEntries() {
		duration = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Duration"));
	}
}
