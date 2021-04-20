package ai.arcblroth.fabric.hewo.config;

import ai.arcblroth.fabric.hewo.Hewo;
import ai.arcblroth.fabric.hewo.api.HewoAPI;
import dev.maow.owo.util.Options;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.ArrayList;
import java.util.List;

@Config(name = Hewo.MODID)
public class HewoConfig implements ConfigData {
    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 1)
    public double hewoChance = 0.05D;

    public List<String> owoPrefixes = new ArrayList<>(options().getPrefixes());
    public List<String> owoSuffixes = new ArrayList<>(options().getSuffixes());

    private Options options() {
        return HewoAPI.getInstance()
                .getOptions()
                .orElse(Options.defaults());
    }
}