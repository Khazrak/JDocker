package se.codeslasher.docker.unixsocket;


import okio.ByteString;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by gesellix
 * From https://github.com/gesellix/docker-client
 * Modified by Khazrak (Groovy > Java)
 */
class HostnameEncoder {

    /**
     * @see java.net.IDN
     */
    static final int MAX_LABEL_LENGTH = 63;
    static final int MAX_HOSTNAME_LENGTH = (MAX_LABEL_LENGTH * 4);

    static String encode(String toEncode) {
        StringJoiner joiner = new StringJoiner(".");
        final String encoded = ByteString.encodeUtf8(toEncode).hex();
        String returnValue;
        if (encoded.length() > MAX_LABEL_LENGTH && encoded.length() < MAX_HOSTNAME_LENGTH) {

            int labelCount = (int)(Math.ceil(encoded.length() / MAX_LABEL_LENGTH));

            List<String> labels = IntStream.range(0,labelCount).mapToObj(i ->
                    {int from = i * MAX_LABEL_LENGTH;
                    int to = from + MAX_LABEL_LENGTH;
                    return encoded.substring(from, Math.min(to, encoded.length()));
                    }
            ).collect(Collectors.toList());

            labels.stream().forEach(bs -> joiner.add(bs));

            returnValue = joiner.toString();
        }
        else {
            returnValue = encoded;
        }

        return returnValue;
    }

    static String decode(String toDecode) {
        StringJoiner joiner = new StringJoiner("");
        if (toDecode.contains(".")) {
            String [] parts = toDecode.split("\\.");
            Stream.of(parts).forEach(s -> joiner.add(s));
            toDecode = joiner.toString();
        }
        return ByteString.decodeHex(toDecode).utf8();
    }
}