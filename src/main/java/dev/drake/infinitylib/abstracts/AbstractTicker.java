package dev.drake.infinitylib.abstracts;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * Compatibility class for InfinityLib AbstractTicker.
 */
public abstract class AbstractTicker extends SlimefunItem {

    protected AbstractTicker(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem item, Config config) {
                BlockMenu menu = me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory(b);
                if (menu != null) {
                    AbstractTicker.this.tick(menu, b, config);
                }
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    @Override
    public void postRegister() {
        new me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset(getId(), getItemName()) {
            @Override
            public void init() {
                setupMenu(this);
            }

            @Override
            public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
                onNewInstance(menu, b);
            }

            @Override
            public boolean canOpen(@Nonnull Block b, @Nonnull org.bukkit.entity.Player p) {
                return true;
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu menu, me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow, ItemStack item) {
                return AbstractTicker.this.getTransportSlots(menu, flow, item);
            }
        };
    }

    protected abstract void tick(@Nonnull BlockMenu menu, @Nonnull Block b, @Nonnull Config config);

    public void setupMenu(@Nonnull BlockMenuPreset preset) {}

    public void onNewInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {}

    public int[] getTransportSlots(@Nonnull me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu menu, @Nonnull me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow, ItemStack item) {
        return new int[0];
    }
}
