package com.example.Testcards;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final List<CardSet> allSets = new ArrayList<>();

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/storage")
    public String storage(Model model) {
        model.addAttribute("sets", allSets);
        return "storage";
    }

    @GetMapping("/create")
    public String createPage() {
        return "create";
    }

    @PostMapping("/create")
    public String saveSet(@RequestParam String setName,
                          @RequestParam String[] terms,
                          @RequestParam String[] definitions) {

        CardSet newSet = new CardSet(setName);

        int count = Math.min(terms.length, definitions.length);

        for (int i = 0; i < count; i++) {
            if (terms[i] != null && !terms[i].trim().isEmpty()) {
                newSet.addCard(terms[i], definitions[i]);
            }
        }

        allSets.add(newSet);
        return "redirect:/storage";
    }

    // ✅ DELETE SET
    @PostMapping("/delete/{id}")
    public String deleteSet(@PathVariable String id) {
        allSets.removeIf(set -> set.getId().equals(id));
        return "redirect:/storage";
    }

    @GetMapping("/study/{id}")
    public String studyPage(@PathVariable String id, Model model) {

        for (CardSet set : allSets) {
            if (set.getId().equals(id)) {
                model.addAttribute("currentSet", set);
                return "study";
            }
        }

        return "redirect:/storage";
    }
}
