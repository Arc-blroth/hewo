package ai.arcblroth.fabric.hewo;

import ai.arcblroth.fabric.hewo.api.HewoAPI;
import ai.arcblroth.fabric.hewo.command.OwOCommand;
import ai.arcblroth.fabric.hewo.config.HewoConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hewo implements ModInitializer {
    private static final Logger LOGGER = LogManager.getLogger("Hewo");

    public static final String MODID = "hewo";
    public static HewoConfig CONFIG;

    @Override
    public void onInitialize() {
        LOGGER.info(HewoAPI.getInstance().translate("Loading furry speak..."));

        AutoConfig.register(HewoConfig.class, GsonConfigSerializer::new);
        ConfigHolder<HewoConfig> configHolder = AutoConfig.getConfigHolder(HewoConfig.class);
        CONFIG = configHolder.getConfig();
        if (CONFIG.hewoChance != 0) HewoAPI.getInstance().setProbability(CONFIG.hewoChance);
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, dedicated) ->
                OwOCommand.register(commandDispatcher)));
    }
}