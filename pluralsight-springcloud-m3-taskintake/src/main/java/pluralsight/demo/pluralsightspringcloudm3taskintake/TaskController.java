package pluralsight.demo.pluralsightspringcloudm3taskintake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private TaskProcessor t;

    @RequestMapping(path="/tasks", method = RequestMethod.POST)
    public @ResponseBody String launchTask(@RequestBody String s) {
        t.publishRequest(s);

        System.out.println("request made");

        return "success";
    }
}
