package sec.project.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class EventController {

    @Autowired
    private HttpSession session;

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String loadEventSession(Model model, @RequestParam(required = false) boolean admin) {
        List<Signup> members = signupRepository.findAll();
        model.addAttribute("signups", members);
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("address", session.getAttribute("address"));
        if (admin == true) {
            model.addAttribute("admin", true);
        }
        return "event";
    }

    @RequestMapping(value = "/event/login", method = RequestMethod.GET)
    public String adminLogin(Model model, @RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return "redirect:/event?admin=true";
        }
        return "redirect:/event?admin=false";
    }
}
