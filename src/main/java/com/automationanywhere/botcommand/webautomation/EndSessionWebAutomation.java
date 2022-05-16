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
@CommandPkg(label = "End session", name = "EndSessionWebAutomation", group_label="Session",description = "End session", comment = true ,  text_color = "#2F4F4F" , background_color =  "#2F4F4F" , icon = "pkg.svg", node_label = "{{sessionName}}")
public class EndSessionWebAutomation {


    @Sessions
    private Map<String, Object> sessions;
 
    @Execute
    public void end(
            @Idx(index = "1", type = TEXT) @Pkg(label = "Session name",default_value_type = STRING, default_value = "Default" ) @NotEmpty String sessionName,
            @Idx(index = "2", type = AttributeType.BOOLEAN) @Pkg(label = "Close Browser", default_value_type = DataType.BOOLEAN, default_value = "true") @NotEmpty Boolean closeBrowser) {
    	
 
    	BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
    	if (closeBrowser) {
    		connection.quitDriver();
    	}
        this.sessions.remove(sessionName);
    }
     
    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
}