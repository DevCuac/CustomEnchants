package com.taiter.ce.Enchantments.Global;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.taiter.ce.Tools;
import com.taiter.ce.Enchantments.CEnchantment;



public class Deepwounds extends CEnchantment {

	int				duration;
	int				rand;

	public Deepwounds(Application app) {
		super(app);		
		configEntries.put("Duration", 20);
		configEntries.put("BleedChance", 20);
		triggers.add(Trigger.DAMAGE_GIVEN);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		final Player damaged = (Player) event.getEntity();
		final Player damager = (Player) event.getDamager();
		if(!getHasCooldown(damager) && !damaged.hasMetadata("ce.bleed")) {

		Random random = new Random();
		if(random.nextInt(100) < rand) {
			generateCooldown(damager, 140);
			Tools.applyBleed(damaged, duration*level);
		}
		}
	}

	@Override
	public void initConfigEntries() {
		duration = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Duration"));
		rand = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".BleedChance"));
	}
}
