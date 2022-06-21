package pl.mantiscrab.calculator.bmr;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class BmrFullInfoConverter extends AbstractHttpMessageConverter<BmrFullInfo> {
    public BmrFullInfoConverter() {
        super(MediaType.TEXT_PLAIN);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return BmrFullInfo.class.equals(clazz);
    }

    @Override
    protected BmrFullInfo readInternal(Class<? extends BmrFullInfo> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(BmrFullInfo bmrFullInfo, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();
        String body = String.valueOf(bmrFullInfo.getBmr() + "kcal");
        outputStream.write(body.getBytes());
        outputStream.close();
    }
}
