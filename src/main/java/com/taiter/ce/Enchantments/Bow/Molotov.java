package com.taiter.ce.Enchantments.Bow;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.taiter.ce.Enchantments.CEnchantment;

public class Molotov extends CEnchantment {


	public Molotov(Application app) {
		super(app);		
		triggers.add(Trigger.SHOOT_BOW);
		triggers.add(Trigger.DAMAGE_GIVEN);
	}

	@SuppressWarnings("deprecation")
    @Override
	public void effect(Event e, ItemStack item, final int level) {
		if(e instanceof EntityDamageByEntityEvent) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		Entity target = event.getEntity();

		World world = target.getWorld();
		world.playEffect(target.getLocation(), Effect.POTION_BREAK, 10);
		double boundaries = 0.1*level;
		for(double x = boundaries; x >= -boundaries; x-=0.1)
			for(double z = boundaries; z >= -boundaries; z-=0.1) {
				FallingBlock b = world.spawnFallingBlock(target.getLocation(), Material.FIRE.getId(), (byte) 0x0);
				b.setVelocity(new Vector(x, 0.1, z));
				b.setDropItem(false);
			}
		}
	}

	@Override
	public void initConfigEntries() {
	}

}
