package com.jtc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jtc.model.Input;

@Controller
public class InputController {

    @RequestMapping(value="/input",method=RequestMethod.GET)
    public String start(  Model model) {        
    	Input input = new Input();
    	input.setFileName("UGP_GPlist_by_LHO.txt");
    	model.addAttribute("input",input);
        return "input";
    }
    @RequestMapping(value="/input",method=RequestMethod.POST)
    public String getInput( Model model) {
        
        model.addAttribute("input",new Input());
        return "input";
    }

}
