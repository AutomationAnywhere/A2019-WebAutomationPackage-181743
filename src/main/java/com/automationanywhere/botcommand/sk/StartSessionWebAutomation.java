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
package com.automationanywhere.botcommand.sk;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Map;

import com.automationanywhere.bot.service.GlobalSessionContext;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.Sessions;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;




/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Start session", name = "StartSessionWebAutomation", description = "Start new session",  comment = true ,  text_color = "#777777" , background_color =  "#777777",
icon = "pkg.svg",  node_label = "start session {{sessionName}}|") 
public class StartSessionWebAutomation {
 
    @Sessions
    private Map<String, Object> sessions;
    
    private static final Messages MESSAGES = MessagesFactory
			.getMessages("com.automationanywhere.botcommand.demo.messages");
    
	  
	@com.automationanywhere.commandsdk.annotations.GlobalSessionContext
	private GlobalSessionContext globalSessionContext;

	  
	  public void setGlobalSessionContext(GlobalSessionContext globalSessionContext) {
	        this.globalSessionContext = globalSessionContext;
	    }
	  
	  
   private BrowserConnection connection;
	
    
    @Execute
    public void start(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    				  @Idx(index = "2", type = AttributeType.BOOLEAN) @Pkg(label = "Headless",  default_value_type = DataType.BOOLEAN, default_value = "False") @NotEmpty Boolean headless,
    				  @Idx(index = "3", type = AttributeType.FILE) @Pkg(label = "ChromeDriver path",  default_value_type = DataType.FILE)  String driverpath,
    				  @Idx(index = "4", type = AttributeType.NUMBER) @Pkg(label = "Existing Remote Session Port",  default_value_type = DataType.NUMBER)  Number port,
    				  @Idx(index = "5", type = AttributeType.TEXTAREA) @Pkg(label = "Function Library",  default_value_type = DataType.STRING)  String library
    		) throws Exception {
 
        // Check for existing session
        if (sessions.containsKey(sessionName))
            throw new BotCommandException(MESSAGES.getString("Session name in use ")) ;
        
        
       headless = (headless == null) ? false : headless;
       connection = new BrowserConnection(driverpath,headless,(port == null) ? null : port.intValue(),library);
       
       this.sessions.put(sessionName, this.connection);


 
    }
 
    
    
    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    

    
    
 
    
    
}