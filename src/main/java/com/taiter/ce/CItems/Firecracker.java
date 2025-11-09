package com.taiter.ce.CItems;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Firecracker extends CItem {
	
	public Firecracker(String originalName, ChatColor color, String lDescription, long lCooldown, Material mat) {
		super(originalName, color, lDescription, lCooldown, mat);
		triggers.add(Trigger.PROJECTILE_HIT);
		triggers.add(Trigger.PROJECTILE_THROWN);
		triggers.add(Trigger.DAMAGE_GIVEN);

	}

	@Override
	public boolean effect(Event event, final Player player) {
		if(event instanceof ProjectileHitEvent) {
			ProjectileHitEvent e = (ProjectileHitEvent) event;
			Location l = e.getEntity().getLocation();
			World w = l.getWorld();
			w.createExplosion(l, 0f);
			final Firework fw = (Firework) w.spawnEntity(l, EntityType.FIREWORK);
			FireworkMeta fm = fw.getFireworkMeta();
			Random r = new Random();
			int type = r.nextInt(3) + 1;
			int colors = r.nextInt(3) + 1;
			Type ft = null;
			Color c1 = null;
			Color c2 = null;
			switch (type) {
				case 1:
					ft = Type.BALL;
					break;
				case 2:
					ft = Type.BALL_LARGE;
					break;
				case 3:
					ft = Type.BURST;
					break;
			}
			switch (colors) {
				case 1:
					c1 = Color.RED;
					c2 = Color.ORANGE;
					break;
				case 2:
					c1 = Color.BLUE;
					c2 = Color.TEAL;
					break;
				case 3:
					c1 = Color.GREEN;
					c2 = Color.LIME;
					break;
			}
			FireworkEffect effect =  FireworkEffect.builder().flicker(false).withColor(c1).withFade(c2).trail(true).with(ft).trail(false).build();
			fm.addEffect(effect);
			fw.setFireworkMeta(fm);
			new BukkitRunnable() {
				@Override
				public void run() {
					fw.detonate();
				}
			}.runTaskLater(getPlugin(), 1l);
		}
		return false;
	}

	@Override
	public void initConfigEntries() {
	}

}
