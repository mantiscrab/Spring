package pl.mantiscrab.calculator.bmi;

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
public class BmiFullInfoConverter extends AbstractHttpMessageConverter<BmiFullInfo> {
    public BmiFullInfoConverter() {
        super(MediaType.TEXT_PLAIN);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return BmiFullInfo.class.equals(clazz);
    }

    @Override
    protected BmiFullInfo readInternal(Class<? extends BmiFullInfo> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(BmiFullInfo bmiFullInfo, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();
        String body = String.valueOf(bmiFullInfo.getBmi());
        outputStream.write(body.getBytes());
        outputStream.close();
    }
}
