package switchtwentyone.project.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class USInfoAndStatusDTOTest {


    @Test
    void USInfoAndStatusDTO(){

        USInfoAndStatusDTO dto1 = new USInfoAndStatusDTO();
        dto1.statement = "statement";
        dto1.storyNumber = 2;
        dto1.priority = 2;
        dto1.detail = "detail";
        dto1.usID = 1.002;
        dto1.usStatus = "status";

        String statement = "statement";
        int storyNumber = 2;
        int priority = 2;
        String detail = "detail";
        double usID = 1.002;
        String usStatus = "status";

        assertEquals(dto1.statement,statement );
        assertEquals(dto1.storyNumber,storyNumber );
        assertEquals(dto1.priority,priority );
        assertEquals(dto1.detail, detail );
        assertEquals(dto1.usID,usID );
        assertEquals(dto1.usStatus,usStatus );
    }

}