package com.walmart.TicketService;

import java.util.TimerTask;

public class ClearHoldTimerTask extends TimerTask {

	TicketServiceImpl ticketServiceImpl;
	int seatHoldId;
	
	public ClearHoldTimerTask(TicketServiceImpl ticketServiceImpl, int seatHoldId)
	{
	 	this.ticketServiceImpl = ticketServiceImpl;
	 	this.seatHoldId = seatHoldId;
	}
	
	@Override
	public void run() {
		ticketServiceImpl.removeHoldId(seatHoldId);
	}
}
