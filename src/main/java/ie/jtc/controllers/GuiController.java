package ie.jtc.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import ie.jtc.model.Location;
import ie.jtc.services.FileAccessor;
import ie.jtc.services.GeoService;
import ie.jtc.services.NearbyService;

@Controller
@SessionAttributes(value={"docs","filter","input"})

public class GuiController {

	@Autowired
	FileAccessor fileService;	
	@Autowired
	GeoService geoService;
	@Autowired
	NearbyService nearbyService;
	
	Logger log = Logger.getLogger(GuiController.class );
    @RequestMapping(value="/filter",method=RequestMethod.GET)
    public String previewGet(  Model model) {  
    	if( ! model.containsAttribute("filter") ){
    		model.addAttribute("filter",new Filter());
    	}
        return "filter";
    }

    @RequestMapping(value="/filter",params="filter",method=RequestMethod.POST)
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
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String save(@SessionAttribute List<GPLocation> docs,
    		@Valid Input input,
    		Model model){
    	try {
			fileService.writePCRSFile(input.getOutputFile(), docs);
			model.addAttribute("status","Ok");
		} catch (IOException e) {
			model.addAttribute("status",e.getMessage());			
		}
    	 return "filter";
    }
    @RequestMapping(value="/connect",method=RequestMethod.GET)
    public String getConnect(@SessionAttribute List<GPLocation> docs,    		
    		Model model){    	
    	 return "connect";
    }    
    @RequestMapping(value="/connect",method=RequestMethod.POST)
    public String doConnect(@SessionAttribute List<GPLocation> docs,
    		@Valid Input input,
    		Model model){
    	Map<Double,GPLocation> results= nearbyService.findWithinDrivingDistance(input.getDoctorId(), docs, input.getDistanceKm(), input.getDegreesToSearch());
    	model.addAttribute("results",results);
    	// todo:  want to summarise these results as well
    	 return "connect";
    }      
    
    @RequestMapping(value="/geocode",method=RequestMethod.POST)
    public String geocode(@SessionAttribute List<GPLocation> docs,
    		@Valid Input input,
    		Model model){
    	int geocoded=0;
    	long start=System.currentTimeMillis();
		for(GPLocation doc:docs){
			try {
				Location.Status status = geoService.getGeoCode(doc);
				log.debug(status+" for "+doc.toString());
				switch( status ){
				case OK:
					++geocoded;
				default:
					break;				
				}
			} catch (Exception e) {
				log.warn(doc.toString()+e.getMessage());
			}
		}
		long duration = System.currentTimeMillis()-start;
		model.addAttribute("status",String.format("Geocoded %d out of %d in %d ms.",geocoded,docs.size(),duration));
    	return "filter";
    }

    
    
    @RequestMapping(value="/input",method=RequestMethod.GET)
    public String start(  Model model) {        
    	Input input = new Input();
    	input.setInputFile("UGP_GPlist_by_LHO.json");
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
			docs = fileService.readPCRSFile (input.getInputFile());
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
        	result.rejectValue("fileName","error","empty file: "+input.getInputFile());
        	return "input";
        }
        long milliSeconds = System.currentTimeMillis() - startTime;        
    	model.addAttribute("docs", docs);
    	model.addAttribute("milliSeconds",milliSeconds);
    	model.addAttribute("filter",new Filter());
    	return "filter";        
    }

}
