
package com.automationanywhere.botcommand.sk;




import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.Sessions;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Select", name = "selectelement",
        description = "Select an Element Option",
        node_label = "Select Element", icon = "pkg.svg", comment = true ,  text_color ="#777777" , background_color = "#777777" )



public class DoSelect {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public void action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    				   @Idx(index = "2", type = AttributeType.TEXTAREA)  @Pkg(label = "JS Script" , default_value_type = STRING ) @NotEmpty String jspath,
    				   @Idx(index = "3", type = AttributeType.TEXT)  @Pkg(label = "Value" , default_value_type = STRING ) @NotEmpty String newvalue,
					   @Idx(index = "4", type = AttributeType.NUMBER)  @Pkg(label = "Timeout (Seconds)" , description = "No wait if 0" ,default_value_type = DataType.NUMBER,  default_value ="0" ) @NotEmpty Number timeout,
					   @Idx(index = "5", type = AttributeType.TEXT)  @Pkg(label = "Wait for Attribute Value" , default_value_type = STRING , default_value = "className" ) @NotEmpty String attribute
    				    ) throws Exception
     {

    	try {
    		BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName);
        	ChromeDriver driver = connection.getDriver();
    		BrowserUtils utils =  new BrowserUtils();
			String library = connection.getLibrary();
    		utils.doSelect(driver, jspath,library, newvalue,timeout.intValue(),attribute);
    	}
    	catch (Exception e) {
    		throw new BotCommandException("SELECT "+jspath+" : "+e.getMessage());
    	}
	    

	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}