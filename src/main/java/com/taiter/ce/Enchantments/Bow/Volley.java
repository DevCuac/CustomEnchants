package com.taiter.ce.Enchantments.Bow;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;
import com.taiter.ce.Enchantments.CEnchantment;

public class Volley extends CEnchantment {

    private static final int CONE_DEGREES = 45;

    public Volley(Application app) {
        super(app);
        triggers.add(Trigger.SHOOT_BOW);
    }

    @Override
    public void effect(Event e, ItemStack item, int level) {
        if (e instanceof EntityShootBowEvent) {
            volley((EntityShootBowEvent) e, item, level);
        }
    }

    // MODIFICADO: flecha central va justo donde mira el jugador (look vector)
    private void volley(EntityShootBowEvent e, ItemStack item, int lvl) {
        Player p = (Player) e.getEntity();
        int amount = 1 + 2 * lvl;

        Arrow oldArrow = (Arrow) e.getProjectile();
        int fireTicks = oldArrow.getFireTicks();
        int knockbackStrength = oldArrow.getKnockbackStrength();
        boolean critical = oldArrow.isCritical();
        String metadata = oldArrow.getMetadata("ce.bow.enchantment").get(0).asString();

        double angleBetweenArrows = (CONE_DEGREES / (amount - 1)) * Math.PI / 180;
        double pitch = (p.getLocation().getPitch() + 90) * Math.PI / 180;
        double yaw = (p.getLocation().getYaw() + 90 - CONE_DEGREES / 2) * Math.PI / 180;
        double sZ = Math.cos(pitch);

        for (int i = 0; i < amount; i++) {
            Vector newDir;
            if (i == amount / 2) {
                // CENTRAL: dirección exacta donde mira el jugador
                newDir = p.getLocation().getDirection();
            } else {
                double nX = Math.sin(pitch) * Math.cos(yaw + angleBetweenArrows * i);
                double nY = Math.sin(pitch) * Math.sin(yaw + angleBetweenArrows * i);
                newDir = new Vector(nX, sZ, nY);
            }

            Arrow arrow = p.launchProjectile(Arrow.class);
            arrow.setShooter(p);
            arrow.setVelocity(newDir.normalize().multiply(oldArrow.getVelocity().length()));
            arrow.setFireTicks(fireTicks);
            arrow.setKnockbackStrength(knockbackStrength);
            arrow.setCritical(critical);
            arrow.setMetadata("ce.Volley", new FixedMetadataValue(getPlugin(), null));
            arrow.setMetadata("ce.bow.enchantment", new FixedMetadataValue(getPlugin(), metadata));
        }
        oldArrow.remove();
    }

    @Override
    public void initConfigEntries() {}
}
