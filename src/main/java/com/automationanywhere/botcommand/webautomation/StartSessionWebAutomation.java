/*
 * Copyright (c) 2019 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */
/**
 * 
 */
package com.automationanywhere.botcommand.webautomation;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Map;

import com.automationanywhere.bot.service.GlobalSessionContext;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.utils.BrowserConnection;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.Sessions;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;




/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Start Session", name = "StartSessionWebAutomation", description = "Start New Session",  comment = true , group_label="Session",  text_color = "#2F4F4F" , background_color =  "#2F4F4F",
icon = "pkg.svg",  node_label = "{{sessionName}}") 
public class StartSessionWebAutomation {
 
    @Sessions
    private Map<String, Object> sessions;
    
    
	  
	@com.automationanywhere.commandsdk.annotations.GlobalSessionContext
	private GlobalSessionContext globalSessionContext;

	  
	  public void setGlobalSessionContext(GlobalSessionContext globalSessionContext) {
	        this.globalSessionContext = globalSessionContext;
	    }
	  
	  
   private BrowserConnection connection;
	
    
    @Execute
    public void start(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    				  @Idx(index = "2", type = AttributeType.SELECT,  options = {
						@Idx.Option(index = "2.1", pkg = @Pkg(label = "Chrome", value = "Chrome")),
						@Idx.Option(index = "2.2", pkg = @Pkg(label = "Edge", value = "Edge"))
					//	@Idx.Option(index = "2.3", pkg = @Pkg(label = "Firefox", value = "Firefox"))
    				       }) @Pkg(label = "Browser", default_value = "Chrome", default_value_type = STRING) @NotEmpty String browser ,
    				  @Idx(index = "3", type = AttributeType.NUMBER) @Pkg(label = "Window Width",  default_value_type = DataType.NUMBER, default_value = "1920") @NotEmpty Number width,
    				  @Idx(index = "4", type = AttributeType.NUMBER) @Pkg(label = "Window Height",  default_value_type = DataType.NUMBER, default_value = "1080") @NotEmpty Number height,
    				  @Idx(index = "5", type = AttributeType.BOOLEAN) @Pkg(label = "Headless",  default_value_type = DataType.BOOLEAN, default_value = "false") @NotEmpty Boolean headless,
    				  @Idx(index = "6", type = AttributeType.FILE) @Pkg(label = "WebDriver path",  default_value_type = DataType.FILE)  String driverpath,
    				  @Idx(index = "7", type = AttributeType.TEXT) @Pkg(label = "User Profile path",  default_value_type = DataType.FILE)  String profilepath,
    				  @Idx(index = "8", type = AttributeType.NUMBER) @Pkg(label = "Existing Remote Session Port",  default_value_type = DataType.NUMBER)  Number port,
    				  @Idx(index = "9", type = AttributeType.TEXTAREA) @Pkg(label = "Function Library",  default_value_type = DataType.STRING)  String library
    		) throws Exception {
 
        // Check for existing session
      if (sessions.containsKey(sessionName))
            throw new BotCommandException("Session name in use ") ;
        
        
    //   headless = (headless == null) ? false : headless;
       profilepath = (profilepath == null) ? "" : profilepath;
       connection = new BrowserConnection(driverpath,profilepath,browser,headless,(port == null) ? null : port.intValue(),library,height.intValue(),width.intValue());
       
       this.sessions.put(sessionName, this.connection);


 
    }
 
    
    
    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    

    
    
 
    
    
}