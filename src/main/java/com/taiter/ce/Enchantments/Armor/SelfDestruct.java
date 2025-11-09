package com.taiter.ce.Enchantments.Armor;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import com.taiter.ce.Main;
import com.taiter.ce.Tools;
import com.taiter.ce.Enchantments.CEnchantment;

public class SelfDestruct extends CEnchantment {

	int delay;

	public SelfDestruct(Application app) {
		super(app);		
		triggers.add(Trigger.DEATH);
		this.configEntries.put("ExplosionDelay", 40);
	}

	@Override
	public void effect(Event e, ItemStack item, int level) {
		PlayerDeathEvent event = (PlayerDeathEvent) e;
		for(int i = level; i >= 0; i--) {
		TNTPrimed tnt = (TNTPrimed) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
		tnt.setFuseTicks(delay);
		tnt.setVelocity(new Vector(Tools.random.nextDouble()*1.5 - 1, Tools.random.nextDouble() * 1.5, Tools.random.nextDouble()*1.5 - 1));
		if(!Main.createExplosions)
			tnt.setMetadata("ce.explosive", new FixedMetadataValue(getPlugin(), null));
		}
	}

	@Override
	public void initConfigEntries() {
		delay = Integer.parseInt(getConfig().getString("Enchantments." + getOriginalName() + ".ExplosionDelay"));		
	}
}
