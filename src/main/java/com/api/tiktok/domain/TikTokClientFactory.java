package com.api.tiktok.domain;

import io.github.jwdeveloper.tiktok.TikTokLive;
import io.github.jwdeveloper.tiktok.live.LiveClient;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class TikTokClientFactory {

    public LiveClient init() {
        System.out.println("tiktok test" + hostName);
        return TikTokLive.newClient(hostName)
                .onGift((liveClient, event) ->
                {
                    String message = switch (event.getGift()) {
                        case ROSE -> "ROSE!";
                        case GG -> "GOOD GAME";
                        case TIKTOK -> "Ye";
                        case CORGI -> "Nice gift";
                        default -> "Thank you for " + event.getGift().getName();
                    };
                    System.out.println(event.getUser().getProfileName() + " sends " + message);
                })
                .onGiftCombo((liveClient, event) ->
                {
                    System.out.println(event.getComboState() + " " + event.getCombo() + " " + event.getGift().getName());
                })
                .onRoomInfo((liveClient, event) ->
                {
                    var roomInfo = event.getRoomInfo();
                    System.out.println("Room Id: " + roomInfo.getRoomId());
                    System.out.println("Likes: " + roomInfo.getLikesCount());
                    System.out.println("Viewers: " + roomInfo.getViewersCount());
                })
                .onJoin((liveClient, event) ->
                {
                    System.out.println(event.getUser().getProfileName() + "Hello on my stream! ");
                })
                .onConnected((liveClient, event) ->
                {
                    System.out.println("Connected to live ");
                })
                .onError((liveClient, event) ->
                {
                    System.out.println("Error! " + event.getException().getMessage());
                })
                .buildAndConnect();
    }

    public TikTokClientFactory(String hostName) {
        this.hostName = hostName;
    }

    private final String hostName;
}
