package switchtwentyone.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import switchtwentyone.project.dto.NewSprintInfoDTO;

public interface ICreateSprintController {
    ResponseEntity<Object> createSprint(@PathVariable int id, @RequestBody NewSprintInfoDTO info);
    ResponseEntity<Object> getSprintById(@PathVariable(value="id") final Double id);
}
