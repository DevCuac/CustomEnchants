package com.taiter.ce.CItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.taiter.ce.EffectManager;

public class HealingShovel extends CItem {

    int Heal;

    public HealingShovel(String originalName, ChatColor color, String lDescription, long lCooldown, Material mat) {
        super(originalName, color, lDescription, lCooldown, mat);
        this.configEntries.put("Heal", 4);
        triggers.add(Trigger.DAMAGE_GIVEN);
    }

    @Override
    public boolean effect(Event event, Player player) {
        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
        if (e.getDamager() == player && e.getEntity() instanceof Player) {
            Player damaged = (Player) e.getEntity();
            e.setDamage(0);
            EffectManager.playSound(damaged.getLocation(), "ENTITY_GENERIC_DRINK", 0.5f, 1f);
            EffectManager.playSound(damaged.getLocation(), "BLOCK_ANVIL_LAND", 0.5f, 2f);
            short currentDur = player.getItemInHand().getDurability();

            if (((Damageable) damaged).getHealth() + Heal <= ((Damageable) damaged).getMaxHealth()) {
                damaged.setHealth(((Damageable) damaged).getHealth() + Heal);
            } else {
                damaged.setHealth(((Damageable) damaged).getMaxHealth());
            }

            if (currentDur + Heal < player.getItemInHand().getType().getMaxDurability()) {
                player.getItemInHand().setDurability((short) (currentDur + Heal));
            } else {
                player.setItemInHand(new ItemStack(Material.AIR, 1));
                EffectManager.playSound(player.getLocation(), "ENTITY_ITEM_BREAK", 0.1f, 0.3f);
            }
            return true;
        }
        return false;
    }

    @Override
    public void initConfigEntries() {

        Heal = Integer.parseInt(getConfig().getString("Items." + getOriginalName() + ".Heal"));

    }

}
