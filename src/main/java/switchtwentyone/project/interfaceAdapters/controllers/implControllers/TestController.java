package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/smoketest")
    public @ResponseBody String greeting() {
        return "Hello, SWitCH!";
    }

}
