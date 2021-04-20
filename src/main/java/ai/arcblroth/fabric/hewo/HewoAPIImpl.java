package ai.arcblroth.fabric.hewo;

import ai.arcblroth.fabric.hewo.api.HewoAPI;
import dev.maow.owo.api.OwO;
import dev.maow.owo.api.OwOProvider;
import dev.maow.owo.util.Options;
import dev.maow.owo.util.OwOFactory;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class HewoAPIImpl implements HewoAPI, OwO {
    public static final HewoAPIImpl INSTANCE = new HewoAPIImpl();

    private final OwO owo = OwOFactory.INSTANCE.create(
            Options.defaults().setMaxLength(256));
    private double probability = 0D;

    private HewoAPIImpl() {}

    @Override
    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Override
    public boolean shouldTranslate() {
        double v = ThreadLocalRandom.current().nextDouble();
        System.out.println(v);
        System.out.println(probability);
        return v <= probability;
    }

    @Override
    public String translate(String s) {
        return owo.translate(TranslateMode.ALL, s);
    }

    @Override
    public String translate(TranslateMode mode, String s) {
        return owo.translate(mode, s);
    }

    @Override
    public Optional<Options> getOptions() {
        return owo.getOptions();
    }

    @Override
    public OwOProvider getProvider() {
        return owo.getProvider();
    }
}