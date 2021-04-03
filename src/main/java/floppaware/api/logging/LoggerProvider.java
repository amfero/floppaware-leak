package floppaware.api.logging;

import floppaware.client.Floppa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.ReflectionUtil;

public class LoggerProvider
{

  public static final Logger ROOT_LOGGER = LogManager.getFormatterLogger(Floppa.NAME);

  public static Logger logger()
  {
    return LogManager.getFormatterLogger(Floppa.NAME + "|" + ReflectionUtil.getCallerClass(2).getSimpleName());
  }

}
