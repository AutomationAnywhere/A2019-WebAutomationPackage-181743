
package com.automationanywhere.botcommand.webautomation;




import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.utils.BrowserConnection;
import com.automationanywhere.botcommand.utils.BrowserUtils;
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
@CommandPkg(label = "Get Value", name = "getvalueelement",
        description = "Get Value of an element",
        node_label = "of element {{search}}", icon = "pkg.svg", comment = true , group_label="Get", text_color = "#2F4F4F" , background_color =  "#2F4F4F",
		return_type=STRING, return_label="Value", return_required=true)


public class GetValue {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public StringValue action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    				   		  @Idx(index = "2", type = AttributeType.TEXTAREA)  @Pkg(label = "Search" , description = "Should match the type", default_value_type = STRING ) @NotEmpty String search,
    	    		    	  @Idx(index = "3", type = AttributeType.SELECT , options = {
    	      						    @Idx.Option(index = "3.1", pkg = @Pkg(label = "Search by Element XPath", value = BrowserUtils.XPATH)), 
    	      						    @Idx.Option(index = "3.2", pkg = @Pkg(label = "Search by Element Id", value = BrowserUtils.ID)), 
    	      						    @Idx.Option(index = "3.3", pkg = @Pkg(label = "Search by Tag name", value = BrowserUtils.TAG)), 
    	      						    @Idx.Option(index = "3.4", pkg = @Pkg(label = "Search by CSS Selector", value = BrowserUtils.CSS)),
    	      						    @Idx.Option(index = "3.5", pkg = @Pkg(label = "JavaScript", value = BrowserUtils.JS))}) 
    	       						@Pkg(label = "Search Type" , default_value = BrowserUtils.CSS , default_value_type= STRING ) @NotEmpty String type,
    						  @Idx(index = "4", type = AttributeType.NUMBER)  @Pkg(label = "Timeout (Seconds)" , description = "No wait if 0" ,default_value_type = DataType.NUMBER,  default_value ="0" ) @NotEmpty Number timeout,
    						  @Idx(index = "5" ,type = AttributeType.TEXT)  @Pkg(label = "Wait for Attribute Value" , default_value_type = STRING , default_value = "className" ) @NotEmpty String attribute
    	 
    				   ) throws Exception
     {
		String value="";
		try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			WebDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			String library = connection.getLibrary();
			value = utils.getValue(driver,search,type,library,timeout.intValue(),attribute	);
		}
	    catch (Exception e) {
	   		throw new BotCommandException("GETVALUE "+search+" "+type+" : "+e.getMessage());
	   	}
		
		return new StringValue(value);
	    

	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}