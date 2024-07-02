package com.redbus.bean.output;

import java.util.List;

import com.redbus.bean.pojo.BusesImage;

public class ResponseBusImages {
	String message;
	int status;
	List<BusesImage> busimages;
	
	public ResponseBusImages(String message, int status,
			List<BusesImage> busimages) {
		this.message = message;
		this.status = status;
		this.busimages = busimages;
	}		
}
