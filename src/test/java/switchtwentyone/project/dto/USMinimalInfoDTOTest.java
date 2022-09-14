package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class USMinimalInfoDTOTest {

    @Test
    void USMinimalInfoDTO() {

        USMinimalInfoDTO dto1 = new USMinimalInfoDTO();
        dto1.usID = 1.001;
        dto1.storyNumber = 100;
        dto1.statement = "This is US Statement";

        double usID = 1.001;
        int storyNumber = 100;
        String statement = "This is US Statement";

        assertEquals(dto1.storyNumber, storyNumber);
        assertEquals(dto1.usID, usID);
        assertEquals(dto1.statement, statement);

    }

}