package com.taiter.ce.Enchantments.Bow;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.inventory.ItemStack;

import com.taiter.ce.Enchantments.CEnchantment;

public class Piercing extends CEnchantment {

    private ItemStack[] emptyArmor = new ItemStack[4];

    public Piercing(Application app) {
        super(app);
        triggers.add(Trigger.SHOOT_BOW);
        triggers.add(Trigger.DAMAGE_GIVEN);
        for (int i = 0; i < 4; i++)
            emptyArmor[i] = new ItemStack(Material.AIR, 0);
    }

    @Override
    public void effect(Event e, ItemStack item, int level) {
        if (e instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
            LivingEntity target = (LivingEntity) event.getEntity();

            int armorCounter = 0;
            for (ItemStack piece : target.getEquipment().getArmorContents())
                if (!piece.getType().equals(Material.AIR))
                    armorCounter++;

            if (armorCounter == 0)
                return;
            
            event.setDamage(DamageModifier.ARMOR, 0); //Completely remove effects of Armor
            target.getWorld().playEffect(target.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 10);
        }
    }

    @Override
    public void initConfigEntries() {
    }
}
