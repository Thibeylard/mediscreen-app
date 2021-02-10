package com.mediscreen.abernathyapp.patHistory;

import com.mediscreen.abernathyapp.patHistory.models.PatHistory;
import com.mediscreen.abernathyapp.patHistory.repositories.PatHistoryRepository;
import com.mediscreen.abernathyapp.patHistory.services.PatHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class PatHistoryServiceTest {

    @MockBean
    private PatHistoryRepository patHistoryRepository;

    @Autowired
    private PatHistoryService patHistoryService;

    @Test
    public void terminologySearchGetRightCount() {

        Set<String> terminology =  Set.of(
                "MOT1",
                "MOT2",
                "MOT3",
                "MOT4",
                "MOT5",
                "MOT6");

        List<PatHistory> patHistoryList = new ArrayList<>();
        patHistoryList.add(new PatHistory(
                "1",
                "Bonjour, ceci est un texte avec MOT1 et aussi avec MOT2."
        ));
        patHistoryList.add(new PatHistory(
                "1",
                "Bonjour, ceci est un texte avec MOT2 et aussi avec MOT5."
        ));
        patHistoryList.add(new PatHistory(
                "1",
                "Bonjour, ceci est un texte avec MOT5, et aussi avec MOT6!"
        ));
        patHistoryList.add(new PatHistory(
                "1",
                "Bonjour, ceci est un texte avec MOT1 et MOT?1 aussi avec MOT5."
        ));
        patHistoryList.add(new PatHistory(
                "1",
                "Bonjour, ceci est un texte avec MOT3 et aussi avec MOT5."
        ));

        // MOT1 = 2
        // MOT2 = 2
        // MOT3 = 1
        // MOT4 = 0
        // MOT5 = 4
        // MOT6 = 1
        // TOTAL = 10

        when(patHistoryRepository.findByPatientId(any(String.class)))
                .thenReturn(patHistoryList);

        assertThat(patHistoryService.terminologySearch("1", terminology))
            .isEqualTo(10);
    }
}