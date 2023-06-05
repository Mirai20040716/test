package net.blay09.mods.waystones.compat;

import net.blay09.mods.waystones.config.WaystonesConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RepurposedStructuresIntegration {
    public static final DeferredRegister<Supplier<Boolean>> RS_CONDITIONS_REGISTRY = DeferredRegister.createOptional(
            new ResourceLocation("repurposed_structures", "json_conditions"), "waystones");

    public static final RegistryObject<Supplier<Boolean>> WAYSTONE_CONFIG_CONDITION = RS_CONDITIONS_REGISTRY.register(
            "config", () -> () -> WaystonesConfig.getActive().spawnInVillages() || WaystonesConfig.getActive().forceSpawnInVillages());

    public RepurposedStructuresIntegration() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RS_CONDITIONS_REGISTRY.register(modEventBus);
    }
}
