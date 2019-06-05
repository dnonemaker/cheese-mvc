package org.launchcode.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping("cheese")
class CheeseController {

    // static  ArrayList<String> cheeses = new ArrayList<>();
    private static HashMap<String, String>cheesesExtended = new HashMap<>();

    // request path: /cheese
    @RequestMapping(value="")
    public String index(Model model){

        //model.addAttribute("cheeses",cheeses);
        model.addAttribute("cheesesExtended", cheesesExtended);
        model.addAttribute("title", "My Cheeses");

        return"cheese/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model){

        model.addAttribute("title", "Add Cheese");
        return "cheese/add";

    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName,
                                       @RequestParam String description){

        //cheeses.add(cheeseName);
        cheesesExtended.put(cheeseName,description);
        // redirect to /cheese
        return "redirect:";

    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemovwCheeseForm(Model model){

        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheesesExtended", cheesesExtended);

        return "cheese/remove";

    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam(required = false)  ArrayList<String> cheese){

        if(cheese != null) {

            for (int i = 0; i < cheese.size(); i++) {

                if (cheesesExtended.containsKey(cheese.get(i))) {
                    cheesesExtended.remove(cheese.get(i));
                }
            }
         }


        // redirect to /cheese
        return "redirect:";

    }
}