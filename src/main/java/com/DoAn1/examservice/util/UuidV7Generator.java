package com.DoAn1.examservice.util;

import java.util.UUID;

import com.github.f4b6a3.uuid.UuidCreator;

public final class UuidV7Generator {

    private UuidV7Generator() {
    }

    public static UUID generate() {
        return UuidCreator.getTimeOrderedEpoch();
    }
}

