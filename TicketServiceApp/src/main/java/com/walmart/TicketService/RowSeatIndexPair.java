package com.walmart.TicketService;

//Convenience Class to hold a Pair of rowIndex, seatIndex
//These values are held in HashMap against the HoldId for convenience
public class RowSeatIndexPair {
	
	private Integer rowIndex;
	private Integer seatIndex ;
	
	public RowSeatIndexPair(Integer rowIndex, Integer seatIndex)
	{
		this.rowIndex = rowIndex;
		this.seatIndex= seatIndex;
	}
	
	public Integer getRowIndex()
	{
		return rowIndex;
	}
	
	public Integer getSeatIndex()
	{
		return seatIndex;
	}   
}

