/*
 * package com.farms.water.util;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.scheduling.annotation.Scheduled; import
 * org.springframework.stereotype.Component; import java.time.LocalDateTime;
 * import java.time.format.DateTimeFormatter;
 * 
 * @Component public class SchedulerTask { private static final Logger logger =
 * LoggerFactory.getLogger(SchedulerTask.class); private static final
 * DateTimeFormatter dateTimeFormatter =
 * DateTimeFormatter.ofPattern("HH:mm:ss");
 * 
 * HttpServletRequest request=null;
 * 
 * @Scheduled(fixedRate = 2000) public void scheduleTaskWithFixedRate() {
 * getRequest(request);
 * 
 * logger.info("Fixed Rate Task :: Execution Time - {}",
 * dateTimeFormatter.format(LocalDateTime.now()) ); } }
 */