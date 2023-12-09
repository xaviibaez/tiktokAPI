package com.api.tiktok.domain;

import io.github.jwdeveloper.tiktok.live.LiveClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TiktokClientConfiguration {
    @Bean("tiktok-client")
    public LiveClient tiktokWebClient(@Value("${tiktok_host_name}") String host) {
        System.out.println("3 wc");
        var webClientFactory = new TikTokClientFactory(host);

        return webClientFactory.init();
    }
}
