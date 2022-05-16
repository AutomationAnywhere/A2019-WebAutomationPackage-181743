
package com.automationanywhere.botcommand.webautomation;




import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.Map;

import org.openqa.selenium.WebDriver;
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
@CommandPkg(label = "Drag an Drop", name = "dragdrop",
        description = "Drag and Drop an Element",
        node_label = "element {{fromelement}} to {{toelement}}", icon = "pkg.svg", comment = true ,group_label="Click", text_color = "#2F4F4F" , background_color =  "#2F4F4F")


public class DragandDrop {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public void action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    				   @Idx(index = "2", type = AttributeType.TEXTAREA)  @Pkg(label = "From Element" , description = "Should match the type", default_value_type = STRING ) @NotEmpty String fromelement,
    				   @Idx(index = "3", type = AttributeType.TEXTAREA)  @Pkg(label = "To Element" , description = "Should match the type", default_value_type = STRING ) @NotEmpty String toelement,
    		    	   @Idx(index = "4", type = AttributeType.SELECT , options = {
      						    @Idx.Option(index = "4.1", pkg = @Pkg(label = "Search by Element XPath", value = BrowserUtils.XPATH)), 
       						    @Idx.Option(index = "4.2", pkg = @Pkg(label = "Search by Element Id", value = BrowserUtils.ID)), 
       						    @Idx.Option(index = "4.3", pkg = @Pkg(label = "Search by Tag name", value = BrowserUtils.TAG)), 
       						    @Idx.Option(index = "4.4", pkg = @Pkg(label = "Search by CSS Selector", value = BrowserUtils.CSS))}) 
    						@Pkg(label = "Search Type" , default_value = BrowserUtils.CSS , default_value_type= STRING ) @NotEmpty String type,
    	    		   @Idx(index = "5", type = AttributeType.NUMBER)  @Pkg(label = "Timeout (Seconds)" , description = "No wait if 0" ,default_value_type = DataType.NUMBER,  default_value ="0" ) @NotEmpty Number timeout,
    	    		   @Idx(index = "6", type = AttributeType.TEXT)  @Pkg(label = "Wait for Attribute Value" , default_value_type = STRING , default_value = "className" ) @NotEmpty String attribute

   
    				   ) throws Exception
     {
		
		try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			WebDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			String library = connection.getLibrary();
			utils.draganddrop(driver, fromelement, toelement, type,library,timeout.intValue(),attribute);
		}
		catch (Exception e) {
			  		throw new BotCommandException("DRAGNDROP "+fromelement+" "+toelement+" "+type+" : "+e.getMessage());
		}
	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}