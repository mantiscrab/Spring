package pl.mantiscrab.containter.coder;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DummyCoder implements Coder{
    @Override
    public String encode(String textToEncode) {
        String encodedText = textToEncode;
        return encodedText;
    }

    @Override
    public String decode(String textToDecode) {
        String decodedText = textToDecode;
        return decodedText;
    }
}
