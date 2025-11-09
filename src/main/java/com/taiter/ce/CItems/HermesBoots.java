package com.taiter.ce.CItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffectType;

public class HermesBoots extends CItem {
	
	int SpeedLevel;

	public HermesBoots(String originalName, ChatColor color, String lDescription, long lCooldown, Material mat) {
		super(originalName, color, lDescription, lCooldown, mat);
		this.configEntries.put("SpeedLevel", 5);
		triggers.add(Trigger.WEAR_ITEM);
	}

	@Override
	public boolean effect(Event event, Player player) {
		return false;
	}

	@Override
	public void initConfigEntries() {
		SpeedLevel = Integer.parseInt(getConfig().getString("Items." + getOriginalName() + ".SpeedLevel")) - 1;
		this.potionsOnWear.put(PotionEffectType.SPEED, SpeedLevel);
	}

}
