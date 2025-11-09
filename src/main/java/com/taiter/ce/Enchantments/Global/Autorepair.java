package com.taiter.ce.Enchantments.Global;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.taiter.ce.Main;
import com.taiter.ce.Enchantments.CEnchantment;

public class Autorepair extends CEnchantment {
	int	healAmount;
	boolean healFully;
	int cooldown;
	
	public Autorepair(Application app) {
		super(app);				
		configEntries.put("HealAmount", 1);
		configEntries.put("HealFully", false);
		triggers.add(Trigger.MOVE);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		PlayerMoveEvent event = (PlayerMoveEvent) e;
		Player owner = event.getPlayer();

		
		if(owner != null && owner.isOnline() && !owner.isDead()) {
			if(healFully)
				item.setDurability((short) 0);
			else {
				int newDur = item.getDurability() - ( 1 + (healAmount*level));
				
				if(newDur > 0)
					item.setDurability((short) newDur);
				else
					item.setDurability((short) 0);
			}
		}
	}

	@Override
	public void initConfigEntries() {
		healAmount = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".HealAmount"));
		healFully  = Boolean.parseBoolean(getConfig().getString("Enchantments." + getOriginalName() + ".HealFully"));
		if(getConfig().contains("Enchantments." + getOriginalName() + ".Cooldown")) {
		    getConfig().set("Enchantments." + getOriginalName() + ".Cooldown", null);
		    new BukkitRunnable() {
		        @Override
		        public void run() {
		            Main.plugin.saveConfig();
		        }
		    }.runTaskLater(Main.plugin, 60);
		}
	}
}
