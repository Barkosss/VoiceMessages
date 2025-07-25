package ru.dimaskama.voicemessages.spigot.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.dimaskama.voicemessages.api.VoiceMessagesApi;
import ru.dimaskama.voicemessages.spigot.networking.VoiceMessagesSpigotNetworking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VoiceMessagesApiImpl implements VoiceMessagesApi {

    @Override
    public boolean isPlayerHasCompatibleModVersion(UUID playerUuid) {
        return VoiceMessagesSpigotNetworking.hasCompatibleVersion(playerUuid);
    }

    @Override
    public boolean updateAvailableTargets(UUID playerUuid) {
        Player player = Bukkit.getPlayer(playerUuid);
        if (player != null) {
            VoiceMessagesSpigotNetworking.updateTargets(player);
            return true;
        }
        return false;
    }

    @Override
    public void sendVoiceMessage(UUID senderUuid, Iterable<UUID> playerUuids, List<byte[]> message, String displayTarget) {
        List<Player> players = new ArrayList<>();
        for (UUID playerUuid : playerUuids) {
            Player player = Bukkit.getPlayer(playerUuid);
            if (player != null) {
                players.add(player);
            }
        }
        VoiceMessagesSpigotNetworking.sendVoiceMessage(senderUuid, players, message, displayTarget);
    }

}
