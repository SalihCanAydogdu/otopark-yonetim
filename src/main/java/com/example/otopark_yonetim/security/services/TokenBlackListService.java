package com.example.otopark_yonetim.security.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService {
    private Set<String> blacklist = new HashSet<>();

    public void add(String token) {
        blacklist.add(token);
    }

    public boolean isBlackListed(String token) {
        return blacklist.contains(token);
    }
}
