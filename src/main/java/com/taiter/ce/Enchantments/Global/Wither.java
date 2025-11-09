package com.taiter.ce.Enchantments.Global;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Wither extends CEnchantment {

	int	duration;
	int	strength;

	public Wither(Application app) {
		super(app);		
		configEntries.put("Duration", 60);
		configEntries.put("Strength", 1);
		triggers.add(Trigger.DAMAGE_GIVEN);
		triggers.add(Trigger.SHOOT_BOW);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		LivingEntity target = (LivingEntity) event.getEntity();

		target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, duration * level, strength + level));

	}

	@Override
	public void initConfigEntries() {
		duration = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Duration"));
		strength = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Strength"))-1;
	}
}
