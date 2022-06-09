package pl.mantiscrab.converter;

import com.github.freva.asciitable.AsciiTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConverterController {
    @PostMapping("/convert")
    @ResponseBody
    String convert(@RequestParam String headers, @RequestParam String data) {
        String[] headersColumns = getHeaders(headers);
        String[][] dataCells = getDataCells(data);
        String asciiTable = AsciiTable.getTable(headersColumns, dataCells);
        return formatToHtmlSourceCode(asciiTable);
    }

    private String formatToHtmlSourceCode(String asciiTable) {
        return "<pre>" + asciiTable + "</pre>";
    }

    private String[][] getDataCells(String data) {
        String[] dataRows = data.split("\n");
        String[][] strings = new String[dataRows.length][];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = dataRows[i].split(";");
        }
        return strings;
    }

    private String[] getHeaders(String headers) {
        return headers.split(";");
    }
}
