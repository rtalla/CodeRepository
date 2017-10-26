package com.walmart.TicketService;

public class SeatHold {

	private static int seatHoldIdCounter = 0;
	
	private int seatHoldId;
	public SeatHold() {
	   	seatHoldId = seatHoldIdCounter++;
	}
	
	public int getSeatHoldId()
	{
		return seatHoldId;
	}
	
	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}
}