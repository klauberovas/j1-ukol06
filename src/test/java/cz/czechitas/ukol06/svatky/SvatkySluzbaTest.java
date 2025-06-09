package cz.czechitas.ukol06.svatky;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.MonthDay;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SvatkySluzbaTest {

    @Test
    void vyhledatSvatkyKeDni() throws IOException {
        SvatkySluzba sluzba = new SvatkySluzba();

        // Jeden svátek na daný den
        List<String> svatkyNa9Cervna = sluzba.vyhledatSvatkyKeDni(MonthDay.of(6, 9));

        // Více svátků na daný den
        List<String> svatkyNa31Ledna = sluzba.vyhledatSvatkyKeDni(MonthDay.of(1, 31));

        // Žádný svátek na daný den
        List<String> svatkyNa1Ledna = sluzba.vyhledatSvatkyKeDni(MonthDay.of(1, 1));

        // Svátek na konci roku
        List<String> svatkyNa31Prosince = sluzba.vyhledatSvatkyKeDni(MonthDay.of(12, 31));

        // Více svátků na 24. prosince (např. 4 jména)
        List<String> svatkyNa24Prosince = sluzba.vyhledatSvatkyKeDni(MonthDay.of(12, 24));

        assertAll(
                () -> assertEquals(List.of("Stanislava"), svatkyNa9Cervna, "Očekáván jeden svátek na 9.6."),
                () -> assertEquals(List.of("Marika", "Spytihněv"), svatkyNa31Ledna, "Očekávané dva svátky na 31.1."),
                () -> assertTrue(svatkyNa1Ledna.isEmpty(), "Očekáváme žádné svátky na 1.1."),
                () -> assertEquals(List.of("Silvestr"), svatkyNa31Prosince, "Očekáván svátek Silvestr na 31.12."),
                () -> assertEquals(4, svatkyNa24Prosince.size(), "Očekáváme 4 svátky na 24.12.")
        );
    }
}
