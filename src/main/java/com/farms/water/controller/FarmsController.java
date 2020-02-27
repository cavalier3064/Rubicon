package com.farms.water.controller;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.farms.water.model.FarmsRequest;
import com.farms.water.service.FarmServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/farms")
@Api(value = "/farms", produces = "application/json")
public class FarmsController {
	private static final Logger logger = LoggerFactory.getLogger(FarmsController.class);
	@Autowired
	FarmServiceImpl farmServiceImpl;

	@ApiOperation(value = "get Farms", response = FarmsRequest.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Farms Details Retrieved", response = FarmsRequest.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Details not found") })
	@RequestMapping(value = "/getFarms", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<HashMap<Integer, FarmsRequest>> getFarms(HttpServletRequest request) {
		HashMap<Integer, FarmsRequest> farmMap = (HashMap<Integer, FarmsRequest>) request.getSession()
				.getAttribute("farmsOrder");
		logger.info("Details are getting for Farms water");
		return new ResponseEntity<HashMap<Integer, FarmsRequest>>(farmMap, HttpStatus.OK);
	}

	@ApiOperation(value = "Submit Orders", response = FarmsRequest.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Farms Details Submitted", response = String.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Details not found") })
	@PostMapping(value = "/createOrder")
	public ResponseEntity<String> createOrder(@RequestParam("farmId") final int farmId,
			@RequestParam("startDateTime") final String startDateTime, @RequestParam("duration") final int duration,
			HttpServletRequest request) {
		HashMap<Integer, FarmsRequest> farmMap = (HashMap<Integer, FarmsRequest>) request.getSession()
				.getAttribute("farmsOrder");
		
		logger.info("Farms water order going to be created");
		String status = farmServiceImpl.createOrder(farmId, startDateTime, duration, farmMap,request);
		
		if (status != null && status.equals("Invalid Format")) {
			status = "Date should be in MM/dd/yyyy-HH:mm:ss format";
			return new ResponseEntity<String>(status, HttpStatus.BAD_REQUEST);
		}
		if (status != null && status.equals("Invalid Date")) {
			status = "Date should be greater than curret date";
			return new ResponseEntity<String>(status, HttpStatus.BAD_REQUEST);
		}
	//	request.getSession().setAttribute("farmsOrder",farmMap);
		
		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Farms Details Canceled", response = String.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Details not found") })

	@DeleteMapping(value = "/cancelOrder")
	public ResponseEntity<String> cancelOrder(@RequestParam("farmId") final int farmId, HttpServletRequest request)throws Exception {
		HashMap<Integer, FarmsRequest> farmMap = (HashMap<Integer, FarmsRequest>) request.getSession()
				.getAttribute("farmsOrder");
		String status = farmServiceImpl.cancelOrder(farmId,farmMap,request);
		logger.info("Farms water order going to be cancel");
		request.getSession().setAttribute("farmsOrder",farmMap);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	
}
