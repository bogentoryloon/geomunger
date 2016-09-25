package ie.jtc.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import ie.jtc.model.Filter;
import ie.jtc.model.GPLocation;
import ie.jtc.model.Input;
import ie.jtc.services.FileAccessor;


@Controller
@SessionAttributes(value={"docs","filter"})

public class GuiController {

	@Autowired
	FileAccessor fileReader;		
	
    @RequestMapping(value="/filter",method=RequestMethod.GET)
    public String previewGet(  Model model) {  
    	if( ! model.containsAttribute("filter") ){
    		model.addAttribute("filter",new Filter());
    	}
        return "filter";
    }

    @RequestMapping(value="/filter",method=RequestMethod.POST)
    public String previewPost(@Valid Filter filter,
    		@SessionAttribute List<GPLocation> docs,
    		Model model) {   
    	if( filter.getAddress() != null && !filter.getAddress().isEmpty() ){
    		docs = docs.stream().filter(p -> p.getAddress().indexOf(filter.getAddress().toUpperCase())!=-1)
    				.collect(Collectors.toList());
    	}
    	if( filter.getName() != null && !filter.getName().isEmpty() ){
    		docs = docs.stream().filter(p -> p.getName().indexOf(filter.getName().toUpperCase())!=-1)
    				.collect(Collectors.toList());
    	}
    	model.addAttribute("filter",filter);
    	model.addAttribute("docs",docs);
        return "filter";
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
