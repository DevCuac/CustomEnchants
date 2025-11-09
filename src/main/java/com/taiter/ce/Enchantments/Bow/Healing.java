package com.taiter.ce.Enchantments.Bow;

import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.taiter.ce.Enchantments.CEnchantment;

public class Healing extends CEnchantment {

	public Healing(Application app) {
		super(app);		
		triggers.add(Trigger.SHOOT_BOW);
		triggers.add(Trigger.DAMAGE_GIVEN);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		if(e instanceof EntityDamageByEntityEvent) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		LivingEntity target = (LivingEntity) event.getEntity();
		

		double newHealth = target.getHealth()+event.getDamage() + level;
		
		if(newHealth >= target.getMaxHealth())
			newHealth = target.getMaxHealth();
		target.setHealth(newHealth);
		event.setDamage(0);
		target.getWorld().playEffect(target.getLocation(), Effect.POTION_BREAK, 10);
		}
	}

	@Override
	public void initConfigEntries() {
	}
}
