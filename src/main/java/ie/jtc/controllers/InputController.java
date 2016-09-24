package ie.jtc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ie.jtc.model.GPLocation;
import ie.jtc.model.Input;
import ie.jtc.services.FileReader;

@Controller
public class InputController {

	@Autowired
	FileReader fileReader;
    @RequestMapping(value="/input",method=RequestMethod.GET)
    public String start(  Model model) {        
    	Input input = new Input();
    	input.setFileName("UGP_GPlist_by_LHO.txt");
    	model.addAttribute("input",input);
        return "input";
    }
    @RequestMapping(value="/input",method=RequestMethod.POST)
    public String input(  @Valid Input input,BindingResult result) {
        if (result.hasErrors()) {
            return "input";
        }
        List<GPLocation> docs = fileReader.readPCRSFile (input.getFileName());
        if( docs == null){
        	result.rejectValue("fileName","error","failed to parse "+input.getFileName());
        }
        	
        return "input";
    }

}
