package ai.arcblroth.fabric.hewo.mixin;

import ai.arcblroth.fabric.hewo.api.HewoAPI;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinServerPlayNetworkHandler {
    @ModifyArg(method = "method_31286", at = @At(value = "INVOKE", target = "net/minecraft/server/PlayerManager.broadcast(Lnet/minecraft/text/Text;Ljava/util/function/Function;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"), index = 0)
    private Text translateChatMessage(Text text) {
        if (HewoAPI.getInstance().shouldTranslate()) {
            if (text instanceof TranslatableText) {
                TranslatableText translatableText = (TranslatableText) text;
                if(translatableText.getKey().equals("chat.type.text")) {
                    Object[] args = translatableText.getArgs();
                    if (args.length >= 2) {
                        args[1] = HewoAPI.getInstance()
                                .translate(args[1].toString());
                    }
                }
            }
        }
        return text;
    }
}