package cz.czechitas.ukol06.svatky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.time.MonthDay;
import java.util.List;

public class SvatkySluzba {

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    private final Path cestaKDatum = Path.of("data/svatky.json");
    private final SeznamSvatku seznamSvatku;

    /**
     * Načte seznam svátků ze souboru svatky.json
     * @throws IOException
     */
    public SvatkySluzba() throws IOException {
        seznamSvatku = objectMapper.readValue(cestaKDatum.toFile(), SeznamSvatku.class);
    }

    public List<String> vyhledatSvatkyDnes() {
        return vyhledatSvatkyKeDni(MonthDay.now());
    }

    /**
     * @param day
     * @return vyhledá svátky k danému dni
     *
     */
    public List<String> vyhledatSvatkyKeDni(MonthDay day) {
        return seznamSvatku.getSvatky()
                .stream()
                .filter(svatek -> svatek.getDen().equals(day))
                .map(Svatek::getJmeno)
                .toList();
    }
}
