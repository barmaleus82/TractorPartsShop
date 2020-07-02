package com.TractorParts.utils;

import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import java.io.IOException;
import java.io.Writer;

public final class JSON {
    public final static JSONStreamAware emptyJSON = prepare(new JSONObject());

    public static JSONStreamAware prepare(final JSONObject json) {
        return new JSONStreamAware() {
            private final char[] jsonChars = json.toJSONString().toCharArray();

            public void writeJSONString(Writer out) throws IOException {
                out.write(jsonChars);
            }
        };
    }

}
