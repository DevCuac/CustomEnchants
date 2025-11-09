package com.taiter.ce.Enchantments.Global;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.taiter.ce.Enchantments.CEnchantment;


public class Thunderingblow extends CEnchantment {

	int	chance;

	public Thunderingblow(Application app) {
		super(app);		
		configEntries.put("LightningChance", 20);
		triggers.add(Trigger.DAMAGE_GIVEN);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		Player damager = (Player) event.getDamager();
		Random random = new Random();
		int i = level;
		while(i != 0) {
			if(random.nextInt(100) < chance) {
				damager.getWorld().strikeLightning(
						event.getEntity().getLocation());
			}
			--i;
		}
	}

	@Override
	public void initConfigEntries() {
		chance = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".LightningChance"));
	}
}
