package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintBacklogItemDTOTest {

    @Test
    void springBacklogItemDTO() {
        SprintBacklogItemDTO dto1 = new SprintBacklogItemDTO();
        dto1.itemID = "US-1.001";
        dto1.usID = 1.001;
        dto1.category = "To do";
        dto1.effortEstimate = 3;

        String itemID ="US-1.001";
        double usID = 1.001;
        String category = "To do";
        int effortEstimate = 3;

        assertEquals(dto1.itemID,itemID );
        assertEquals(dto1.usID,usID );
        assertEquals(dto1.usID,usID );
        assertEquals(dto1.category, category );
        assertEquals(dto1.effortEstimate,effortEstimate );
    }


}