package org.blahajenjoyer.compat.vanillabackport.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.blahajenjoyer.compat.vanillabackport.entity.ThrownVariantChickenEgg;

public class VariantChickenEggItem extends Item {

    private final String variantKey;

    public VariantChickenEggItem(String variantKey, Properties properties) {
        super(properties);
        this.variantKey = variantKey;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F,
            0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClientSide) {
            ThrownVariantChickenEgg egg = new ThrownVariantChickenEgg(world, player, variantKey);
            egg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(egg);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }
}
