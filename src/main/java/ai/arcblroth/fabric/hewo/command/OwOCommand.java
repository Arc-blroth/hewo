package ai.arcblroth.fabric.hewo.command;

import ai.arcblroth.fabric.hewo.api.HewoAPI;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.SharedConstants;
import net.minecraft.entity.Entity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.apache.commons.lang3.StringUtils;

public class OwOCommand {
    private static final SimpleCommandExceptionType INVALID_MESSAGE_EXCEPTION =
            new SimpleCommandExceptionType(new TranslatableText(
                    "multiplayer.disconnect.illegal_characters"));

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("owo").then(
                CommandManager.argument(
                        "message",
                        StringArgumentType.greedyString()
                ).executes((ctx) -> {
                    String message = StringUtils.normalizeSpace(StringArgumentType.getString(ctx, "message"));

                    for (int i = 0; i < message.length(); ++i)
                        if (!SharedConstants.isValidChar(message.charAt(i)))
                            throw INVALID_MESSAGE_EXCEPTION.create();

                    message = HewoAPI.getInstance().translate(message);

                    Entity entity = ctx.getSource().getEntity();
                    if (entity == null) return 0;

                    Text text = new TranslatableText("chat.type.text", entity.getDisplayName(), message);
                    ctx.getSource().getMinecraftServer().getPlayerManager()
                            .broadcastChatMessage(text, MessageType.CHAT, entity.getUuid());
                    return 1;
                }))
        );
    }
}