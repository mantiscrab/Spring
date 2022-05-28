package pl.mantiscrab.containter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.mantiscrab.containter.coder.Coder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
class FileService {
    private final String fileName;
    private final Coder coder;

    FileService(@Value("${app.translations.filename}") String fileName, Coder coder) {
        this.fileName = fileName;
        this.coder = coder;
    }

    List<Entry> readAllFile() throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(coder::decode)
                .map(CsvEntryConverter::parse)
                .collect(Collectors.toList());
    }

    void saveEntries(List<Entry> entries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Entry entry : entries) {
            writer.write(coder.encode(entry.toString()));
            writer.newLine();
        }
        writer.close();
    }

    private static class CsvEntryConverter {
        static Entry parse(String text) {
            String[] split = text.split(";");
            return new Entry(split[0], split[1]);
        }
    }
}
