package com.taiter.ce.Enchantments.Tool;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.taiter.ce.Enchantments.CEnchantment;

public class Energizing extends CEnchantment {

	int Strength;
	int Duration;

	
	public Energizing(Application app) {
		super(app);		
		configEntries.put("Strength", 1);
		configEntries.put("Duration", 20);
		triggers.add(Trigger.BLOCK_BROKEN);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		BlockBreakEvent event = (BlockBreakEvent) e;
		Player player = event.getPlayer();
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Duration, Strength+level-1), false);
	}
	
	@Override
	public void initConfigEntries() {
		Strength = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Strength"))-1;
		Duration = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".Duration"));
	}
	
}
