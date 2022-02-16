package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Ivan Ivanov");
        List<String> cities = List.of("Москва", "Санкт-Петербург", "Нижний Новгород", "Казань");
        model.addAttribute("cities", cities);
        return "index";
    }
}
