package com.taiter.ce.Enchantments.Bow;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.taiter.ce.EffectManager;
import com.taiter.ce.Enchantments.CEnchantment;

public class Shuffle extends CEnchantment {

	public Shuffle(Application app) {
		super(app);		
		triggers.add(Trigger.SHOOT_BOW);
		triggers.add(Trigger.DAMAGE_GIVEN);
		this.resetMaxLevel();
	}

	@Override
	public void effect(Event e, ItemStack item, final int level) {
		if(e instanceof EntityDamageByEntityEvent) {
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
		Entity target = event.getEntity();
		Player p = (Player) ((Projectile) event.getDamager()).getShooter();
		
		if(target.getEntityId() == p.getEntityId())
			return;
		
		Location pLoc = p.getLocation();
		Location tLoc = target.getLocation();
		
		target.teleport(pLoc);
		p.teleport(tLoc);
		
		EffectManager.playSound(tLoc, "ENTITY_ENDERMAN_TELEPORT", 0.4f, 2f);
		EffectManager.playSound(pLoc, "ENTITY_ENDERMAN_TELEPORT", 0.4f, 2f);

		
		for(int i = 10; i>0; i--) {
			p.getWorld().playEffect(tLoc, Effect.ENDER_SIGNAL, 10);
			p.getWorld().playEffect(pLoc, Effect.ENDER_SIGNAL, 10);
		}
		
		if(target instanceof Player) {
			p.sendMessage(ChatColor.DARK_PURPLE + "You have switched positions with " + target.getName() + "!");
			target.sendMessage(ChatColor.DARK_PURPLE + "You have switched positions with " + p.getName() + "!");
		}
		
		
		}
	}

	@Override
	public void initConfigEntries() {
	}

}
