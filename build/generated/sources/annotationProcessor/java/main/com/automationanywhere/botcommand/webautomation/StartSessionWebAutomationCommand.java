package com.automationanywhere.botcommand.webautomation;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.Boolean;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Number;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StartSessionWebAutomationCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(StartSessionWebAutomationCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    StartSessionWebAutomation command = new StartSessionWebAutomation();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("sessionName") && parameters.get("sessionName") != null && parameters.get("sessionName").get() != null) {
      convertedParameters.put("sessionName", parameters.get("sessionName").get());
      if(convertedParameters.get("sessionName") !=null && !(convertedParameters.get("sessionName") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","sessionName", "String", parameters.get("sessionName").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("sessionName") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","sessionName"));
    }

    if(parameters.containsKey("browser") && parameters.get("browser") != null && parameters.get("browser").get() != null) {
      convertedParameters.put("browser", parameters.get("browser").get());
      if(convertedParameters.get("browser") !=null && !(convertedParameters.get("browser") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","browser", "String", parameters.get("browser").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("browser") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","browser"));
    }
    if(convertedParameters.get("browser") != null) {
      switch((String)convertedParameters.get("browser")) {
        case "Chrome" : {

        } break;
        case "Edge" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","browser"));
      }
    }

    if(parameters.containsKey("width") && parameters.get("width") != null && parameters.get("width").get() != null) {
      convertedParameters.put("width", parameters.get("width").get());
      if(convertedParameters.get("width") !=null && !(convertedParameters.get("width") instanceof Number)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","width", "Number", parameters.get("width").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("width") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","width"));
    }

    if(parameters.containsKey("height") && parameters.get("height") != null && parameters.get("height").get() != null) {
      convertedParameters.put("height", parameters.get("height").get());
      if(convertedParameters.get("height") !=null && !(convertedParameters.get("height") instanceof Number)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","height", "Number", parameters.get("height").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("height") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","height"));
    }

    if(parameters.containsKey("headless") && parameters.get("headless") != null && parameters.get("headless").get() != null) {
      convertedParameters.put("headless", parameters.get("headless").get());
      if(convertedParameters.get("headless") !=null && !(convertedParameters.get("headless") instanceof Boolean)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","headless", "Boolean", parameters.get("headless").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("headless") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","headless"));
    }

    if(parameters.containsKey("driverpath") && parameters.get("driverpath") != null && parameters.get("driverpath").get() != null) {
      convertedParameters.put("driverpath", parameters.get("driverpath").get());
      if(convertedParameters.get("driverpath") !=null && !(convertedParameters.get("driverpath") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","driverpath", "String", parameters.get("driverpath").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("profilepath") && parameters.get("profilepath") != null && parameters.get("profilepath").get() != null) {
      convertedParameters.put("profilepath", parameters.get("profilepath").get());
      if(convertedParameters.get("profilepath") !=null && !(convertedParameters.get("profilepath") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","profilepath", "String", parameters.get("profilepath").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("port") && parameters.get("port") != null && parameters.get("port").get() != null) {
      convertedParameters.put("port", parameters.get("port").get());
      if(convertedParameters.get("port") !=null && !(convertedParameters.get("port") instanceof Number)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","port", "Number", parameters.get("port").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("library") && parameters.get("library") != null && parameters.get("library").get() != null) {
      convertedParameters.put("library", parameters.get("library").get());
      if(convertedParameters.get("library") !=null && !(convertedParameters.get("library") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","library", "String", parameters.get("library").get().getClass().getSimpleName()));
      }
    }

    command.setSessions(sessionMap);
    command.setGlobalSessionContext(globalSessionContext);
    try {
      command.start((String)convertedParameters.get("sessionName"),(String)convertedParameters.get("browser"),(Number)convertedParameters.get("width"),(Number)convertedParameters.get("height"),(Boolean)convertedParameters.get("headless"),(String)convertedParameters.get("driverpath"),(String)convertedParameters.get("profilepath"),(Number)convertedParameters.get("port"),(String)convertedParameters.get("library"));Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","start"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }

  public Map<String, Value> executeAndReturnMany(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return null;
  }
}
