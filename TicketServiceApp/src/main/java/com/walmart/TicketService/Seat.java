package com.walmart.TicketService;

import java.util.Timer;
import java.util.TimerTask;

import com.walmart.TicketService.TicketService.SeatStatus;

public class Seat {
 
	private SeatStatus seatStatus = SeatStatus.AVAILABLE;
	private int seatHoldTimeInSeconds = 0;
	
	public Seat(int seatHoldTimeInSeconds) {
		setStatus(SeatStatus.AVAILABLE);
		this.seatHoldTimeInSeconds = seatHoldTimeInSeconds;
	}

	public void setStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}
	
	public SeatStatus getStatus() {
		return seatStatus;
	}
	
}
