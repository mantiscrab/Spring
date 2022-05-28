package pl.mantiscrab.containter.coder;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class CaesarCipherCoder implements Coder {

    public static final int CAESAR_CIPHER_SHIFT = 4;

    @Override
    public String encode(String textToEncode) {
        return textToEncode.chars()
                .map(i -> i + CAESAR_CIPHER_SHIFT)
                .collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append)
                .toString();
    }

    @Override
    public String decode(String textToDecode) {
        return textToDecode.chars()
                .map(i -> i - CAESAR_CIPHER_SHIFT)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}
