package com.acorn;

import org.hashids.Hashids;

public class CryptoTool {

    private final Hashids hashids;

    public CryptoTool(String salt) {
        var minHashLength = 10;
        this.hashids = new Hashids(salt, minHashLength);
    }

    public String hasoOf(Long id) {
        return hashids.encode(id);
    }

    public Long idOf(String hash) {
        long[] decode = hashids.decode(hash);
        if (decode != null && decode.length > 0) {
            return decode[0];
        }
        return null;
    }
}
