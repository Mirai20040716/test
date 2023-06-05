package net.blay09.mods.waystones.item;

import net.blay09.mods.waystones.compat.Compat;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ScrollItemBase extends Item {

    private static final Random random = new Random();

    public ScrollItemBase(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        if (getUseDuration(itemStack) <= 0 || Compat.isVivecraftInstalled) {
            return UseAnim.NONE;
        }

        return UseAnim.BOW;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack itemStack, int remainingTicks) {
        if (level.isClientSide) {
            int duration = getUseDuration(itemStack);
            float progress = (duration - remainingTicks) / (float) duration;
            int maxParticles = Math.max(4, (int) (progress * 48));
            if (remainingTicks % 5 == 0) {
                for (int i = 0; i < maxParticles; i++) {
                    level.addParticle(ParticleTypes.REVERSE_PORTAL,
                            entity.getX() + (random.nextDouble() - 0.5) * 1.5f,
                            entity.getY() + random.nextDouble(),
                            entity.getZ() + (random.nextDouble() - 0.5) * 1.5f,
                            0,
                            random.nextDouble(),
                            0);
                }
                if (progress >= 0.25f) {
                    for (int i = 0; i < maxParticles; i++) {
                        level.addParticle(ParticleTypes.CRIMSON_SPORE,
                                entity.getX() + (random.nextDouble() - 0.5) * 1.5f,
                                entity.getY() + random.nextDouble(),
                                entity.getZ() + (random.nextDouble() - 0.5) * 1.5f,
                                0,
                                random.nextDouble(),
                                0);
                    }
                }
                if (progress >= 0.5f) {
                    for (int i = 0; i < maxParticles / 3; i++) {
                        level.addParticle(ParticleTypes.WITCH,
                                entity.getX() + (random.nextDouble() - 0.5) * 1.5f,
                                entity.getY() + 0.5f + random.nextDouble(),
                                entity.getZ() + (random.nextDouble() - 0.5) * 1.5f,
                                0,
                                random.nextDouble(),
                                0);
                    }
                }
            }

            if (remainingTicks == 1) {
                for (int i = 0; i < maxParticles; i++) {
                    level.addParticle(ParticleTypes.PORTAL,
                            entity.getX() + (random.nextDouble() - 0.5) * 1.5f,
                            entity.getY() + random.nextDouble() + 1,
                            entity.getZ() + (random.nextDouble() - 0.5) * 1.5f,
                            (random.nextDouble() - 0.5) * 0,
                            random.nextDouble(),
                            (random.nextDouble() - 0.5) * 0);
                }
            }
        }
    }
}
