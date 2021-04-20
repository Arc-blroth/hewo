package ai.arcblroth.fabric.hewo.api;

import ai.arcblroth.fabric.hewo.HewoAPIImpl;
import dev.maow.owo.api.OwO;

public interface HewoAPI extends OwO {
    static HewoAPI getInstance() {
        return HewoAPIImpl.INSTANCE;
    }

    /**
     * Adds a modifier that specifies the probability
     * that a message will be translated. A message will
     * be translated based on the highest probability
     * from all registered modifiers. For example, if
     * one modifier returns 0.4 and another returns 0.6,
     * than the overall probability for translation
     * will be 0.6.
     */
    void setProbability(Double probability);

    boolean shouldTranslate();
}