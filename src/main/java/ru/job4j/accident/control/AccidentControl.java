package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.findAllAccidentTypes());
        model.addAttribute("rules", service.findAllRules());
        return "/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.findAccidentById(id));
        model.addAttribute("types", service.findAllAccidentTypes());
        model.addAttribute("rules", service.findAllRules());
        return "/update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        String[] ids = request.getParameterValues("rIds");
        Arrays.stream(ids)
                .mapToInt(Integer::parseInt)
                .forEach(id -> accident.addRule(service.findRuleById(id)));
        service.saveAccident(accident);
        return "redirect:/";
    }
}
