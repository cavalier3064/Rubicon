package com.farms.water.service;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.farms.water.model.FarmsRequest;
import com.farms.water.util.FarmsUtil;
@Service
public class FarmServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(FarmServiceImpl.class);
	@Autowired
	FarmsUtil farmUtil;
	HashMap<Integer, FarmsRequest> farmMap = null;
	/*
	 * Create NEW order for Farm water deliver 
	 */
	public String createOrder(int farmId, String startDateTime, int duration,HashMap<Integer, FarmsRequest> farmMap,HttpServletRequest request) {
		FarmsRequest farmsRequest = null;
		String status = null;
		
		boolean validDateFormat = farmUtil.isValidDate(startDateTime);
		boolean validDate=farmUtil.getDate(startDateTime);
		if (!validDateFormat) {
			status = "Invalid Format";
			
			return status;
		}
		if (!validDate) {
			status = "Invalid Date";
			
			return status;
		}
		if (farmMap == null) {
			farmMap = new HashMap<Integer, FarmsRequest>();
			farmsRequest = new FarmsRequest();
			farmsRequest.setDuration(duration);
			farmsRequest.setFarmId(farmId);
			farmsRequest.setStatus("Requested");
			farmsRequest.setStartDateTime(startDateTime);
			farmMap.put(farmId, farmsRequest);
			request.getSession().setAttribute("farmsOrder", farmMap);
			status = "Order has been placed but not yet delivered.";
			logger.info(status+" For "+farmsRequest.getFarmId()+" created");
			this.farmMap=farmMap;
			return status;
		} else {
			if (null != (FarmsRequest) farmMap.get(farmId)) {
				farmsRequest = (FarmsRequest) farmMap.get(farmId);
				boolean delivered = farmUtil.diffrenceDate(farmsRequest.getStartDateTime(),startDateTime, farmsRequest.getDuration());
				if (!delivered) {
					farmMap.remove(farmId);
					farmsRequest = new FarmsRequest();
					farmsRequest.setDuration(duration);
					farmsRequest.setFarmId(farmId);
					farmsRequest.setStatus("Requested");
					farmsRequest.setStartDateTime(startDateTime);
					farmMap.put(farmId, farmsRequest);
					status = "Order has been placed but not yet delivered.";
					logger.info(status+" For "+farmsRequest.getFarmId());
					this.farmMap=farmMap;
					request.getSession().setAttribute("farmsOrder", farmMap);
					return status;
				} else {
					status = "Order has been already created";
					logger.info(status+" For "+farmsRequest.getFarmId());
					this.farmMap=farmMap;
					return status;
				}

			} else {
				farmsRequest = new FarmsRequest();
				farmsRequest.setDuration(duration);
				farmsRequest.setFarmId(farmId);
				farmsRequest.setStatus("Requested");
				farmsRequest.setStartDateTime(startDateTime);
				farmMap.put(farmId, farmsRequest);
				request.getSession().setAttribute("farmsOrder", farmMap);
				status = "Order has been placed but not yet delivered.";
				this.farmMap=farmMap;
				return status;
			}
			
		}
		
	}
	
	/*
	 * Cancel order when order is not delivered
	 */
	public String cancelOrder(int farmId, HashMap<Integer, FarmsRequest> farmMap,HttpServletRequest request)throws Exception {

		String status = null;
		
		this.farmMap=farmMap;
		if (farmMap != null) {
			FarmsRequest farmsRequest = (FarmsRequest) farmMap.get(farmId);
			boolean isCanceled = farmUtil.cancelDate(farmsRequest.getStartDateTime(), farmsRequest.getDuration());
			if (isCanceled) {
				farmsRequest.setStatus("Cancelled");
				status = "Order was cancelled before delivery.";
				logger.info(status+" For "+farmsRequest.getFarmId());
				farmMap.put(farmId, farmsRequest);
				request.getSession().setAttribute("farmsOrder", farmMap);
			} else {
				status = "Order has been delivered.";
				logger.info(status+" For "+farmsRequest.getFarmId());
			}
		}

		return status;
	}
	
	/*
	 * Schedule order  for diffrent state
	 */
	
	@Scheduled(fixedRate = 20000)
	public void updateStatus() {
		if(this.farmMap!=null) {
			Iterator<Map.Entry<Integer, FarmsRequest>> iterator = farmMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, FarmsRequest> entry = iterator.next();
				FarmsRequest farmRequest =  entry.getValue();
				
				String status = farmUtil.equalsDateTime(farmRequest.getStartDateTime(), farmRequest.getDuration());
				if(farmRequest.getStatus()!=null && !farmRequest.getStatus().equals("Cancelled")) {
					
				
				if(status !=null && status.equals("Requested")) {
					logger.info("New Water order for farm "+farmRequest.getFarmId()+" created");
				}
				else if(status !=null && status.equals("Started")) {
					logger.info("Water delivery to farm " +farmRequest.getFarmId()+" started");
				}
				else if(status !=null && status.equals("Stopped")) {
					logger.info("Water delivery to farm" +farmRequest.getFarmId()+" stopped");
				}
			}
				
			}
			
		}else {
			logger.info("There is no water delivery order");
		}
		
	}

}
