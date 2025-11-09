package com.taiter.ce.Enchantments.Armor;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Berserker extends CEnchantment {
	
	int duration;
	int strength;
	int trigger;
	int cooldown;

	public Berserker(Application app) {
		super(app);
		configEntries.put("DurationPerLevel", 200);
		configEntries.put("BaseStrength", 5);
		configEntries.put("HpToTrigger", 4);
		configEntries.put("Cooldown", 6000);
		triggers.add(Trigger.DAMAGE_TAKEN);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		Player player = (Player) event.getEntity();
		if(player.getHealth() <= trigger) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration * level, strength + level - 1));
			player.sendMessage("Your bloodloss makes you stronger!");
			generateCooldown(player, cooldown);
		}
	}

	@Override
	public void initConfigEntries() {
		duration = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".DurationPerLevel"));
		strength = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".BaseStrength"))-1;
		trigger = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".HpToTrigger"));
		cooldown = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Cooldown"));
	}
}
