
package com.automationanywhere.botcommand.webautomation;




import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.data.impl.ListValue;
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
@CommandPkg(label = "Get Values", name = "getvalueselement",
        description = "Get Values of an element list",
        node_label = "in session {{sessionName}}", icon = "pkg.svg", comment = true , group_label="Get", text_color = "#2F4F4F" , background_color =  "#2F4F4F",
		return_type=DataType.DICTIONARY, return_sub_type = STRING, return_description = "key = <search>",return_label = "Values", return_required=true)


public class GetAllValues {
	
    @Sessions
    private Map<String, Object> sessions;
	
	@Execute
    public DictionaryValue action(@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
    						  @Idx(index = "2", type = AttributeType.LIST )  @Pkg(label = "Searches" , description = "Should match the type", default_value_type = STRING ) @NotEmpty List<StringValue> searchList,
    	    		    	  @Idx(index = "3", type = AttributeType.SELECT , options = {
    	      						    @Idx.Option(index = "3.1", pkg = @Pkg(label = "Search by Element XPath", value = BrowserUtils.XPATH)), 
    	      						    @Idx.Option(index = "3.2", pkg = @Pkg(label = "Search by Element Id", value = BrowserUtils.ID)), 
    	      						    @Idx.Option(index = "3.3", pkg = @Pkg(label = "Search by Tag name", value = BrowserUtils.TAG)), 
    	      						    @Idx.Option(index = "3.4", pkg = @Pkg(label = "Search by CSS Selector", value = BrowserUtils.CSS)),
    	      						    @Idx.Option(index = "3.5", pkg = @Pkg(label = "JavaScript", value = BrowserUtils.JS))}) 
    	       						@Pkg(label = "Search Type" , default_value = BrowserUtils.CSS , default_value_type= STRING ) @NotEmpty String type    	 
    				   ) throws Exception
     {
		LinkedHashMap<String,Value> values= new LinkedHashMap<String,Value>();
		try {
			BrowserConnection connection = (BrowserConnection) this.sessions.get(sessionName); 
			WebDriver driver = connection.getDriver();
			BrowserUtils utils =  new BrowserUtils();
			String library = connection.getLibrary();
			
			for (int i = 0; i < searchList.size(); i++) {
				String search =  searchList.get(i).get();
				values.put(search,new StringValue(utils.getValue(driver,search,type,library,0,"")));
			}

		}
	    catch (Exception e) {
	   		throw new BotCommandException("GETVALUES "+sessionName+" : "+e.getMessage());
	   	}
		
		DictionaryValue dictValue = new DictionaryValue();
		dictValue.set(values);
		return dictValue;
	    

	}

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }
    
	
}