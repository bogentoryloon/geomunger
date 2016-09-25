package ie.jtc.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ie.jtc.model.GPLocation;
import ie.jtc.model.Input;
import ie.jtc.services.FileAccessor;


@Controller
@SessionAttributes("docs")
public class InputController {

	@Autowired
	FileAccessor fileReader;	

    @RequestMapping(value="/preview",method=RequestMethod.GET)
    public String preview(  Model model) {        
        return "preview";
    }

    @RequestMapping(value="/input",method=RequestMethod.GET)
    public String start(  Model model) {        
    	Input input = new Input();
    	input.setFileName("UGP_GPlist_by_LHO.json");
    	model.addAttribute("input",input);
        return "input";
    }
    @RequestMapping(value="/input",method=RequestMethod.POST)
    public String input(  @Valid Input input,BindingResult result,Model model) {
        if (result.hasErrors()) {
            return "input";
        }
        List<GPLocation> docs=null;
        long startTime = System.currentTimeMillis();
		try {
			docs = fileReader.readPCRSFile (input.getFileName());
		} catch (IOException e) {
        	result.rejectValue("fileName","error","failed to read: "+e.getMessage());
        	return "input";        	
		}catch(Exception e){
			/* 
			 * 	I have no idea why jackson isn't throwing a jsonparserexception as
			 * advertised. will investigate if time allows.  
			 */
        	result.rejectValue("fileName","error","failed to read: "+e.getMessage());
        	return "input";			
			
		}
        if( docs == null){
        	result.rejectValue("fileName","error","empty file: "+input.getFileName());
        	return "input";
        }
        long milliSeconds = System.currentTimeMillis() - startTime;
    	model.addAttribute("docs", docs);
    	model.addAttribute("milliSeconds",milliSeconds);
    	return "loadresult";        
    }

}
