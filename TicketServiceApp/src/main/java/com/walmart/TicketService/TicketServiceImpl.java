package com.walmart.TicketService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

public class TicketServiceImpl implements TicketService {
	
	//private static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

	//constants to represent the property names in config files
	private final static String CONFIG_FILE_NAME = "config";
	private final static String NUM_OF_ROWS = "Rows";
	private final static String SEATS_IN_EACH_ROW = "SeatsInEachRow";
	private final static String SEAT_HOLD_TIME_IN_SECS = "SeatHoldTimeInSecs";
	
	private static ResourceBundle rb = ResourceBundle.getBundle(CONFIG_FILE_NAME);
	
	//Following properties read from config file
	private int rows = 0; // number of rows 
	private int seatsInEachRow = 0; // number of seats in each row
	private int seatHoldTimeInSecs = 0; // duration of time in seconds after which a placed hold would expire
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSeatsInEachRow() {
		return seatsInEachRow;
	}

	public void setSeatsInEachRow(int seatsInEachRow) {
		this.seatsInEachRow = seatsInEachRow;
	}

	public int getSeatHoldTimeInSecs() {
		return seatHoldTimeInSecs;
	}

	public void setSeatHoldTimeInSecs(int seatHoldTimeInSecs) {
		this.seatHoldTimeInSecs = seatHoldTimeInSecs;
	}

	//Following three variables represent DataStructure to represent seats and reservation status(AVAILABLE, HOLD, RESERVED)
	//Matrix (Two Dimensional Array) of Seats in the stadium
	private List<List<Seat>> stadiumSeats = new ArrayList<List<Seat>>();
	//HashMap to hold list of HoldIds, and their corresponding seat numbers placed on hold
	private Map<Integer, List<RowSeatIndexPair>> seatHoldMap = new HashMap<Integer, List<RowSeatIndexPair>>();
	//HashMap to hold list of ReservationIds, and their corresponding seat numbers placed on hold
	private Map<String, List<RowSeatIndexPair>> reservationMap = new HashMap<String, List<RowSeatIndexPair>>();
	
	//Constructor
	public TicketServiceImpl () {
		readConfigVals();
		initStadiumSeatConfiguration();            
	}
	
	//Initialize the Seating DataStructure
	private void initStadiumSeatConfiguration() {
		
		//initialize Matrix of Seats
		stadiumSeats = new ArrayList<List<Seat>>();
		for(int rowIndex = 0; rowIndex < rows; rowIndex++) {
			
			//initialize an entire row of seats to be AVAILABLE
			List<Seat> rowSeats = new ArrayList<Seat>(seatsInEachRow);
			for(int seatIndex =0; seatIndex < seatsInEachRow; seatIndex++) {
				rowSeats.add(new Seat(seatHoldTimeInSecs));
			}
			
			//Assign the row of seats above to stadiumSeats matrix
			stadiumSeats.add(rowSeats);
		}
	}
	
	//Read # of rows, # of seats in each row, seatHoldTimeInSecs from config.properties using ResourceBundle
	private void readConfigVals() {
        rows = Integer.parseInt(rb.getString(NUM_OF_ROWS));
        seatsInEachRow = Integer.parseInt(rb.getString(SEATS_IN_EACH_ROW));
        seatHoldTimeInSecs = Integer.parseInt(rb.getString(SEAT_HOLD_TIME_IN_SECS));				
	}
	
	/**
	 * The number of seats in the venue that are neither held nor reserved
	 *
	 * @return the number of tickets available in the venue
	 */
	public int numSeatsAvailable() {
		int countAvailable = 0;
		for(List<Seat> rowSeats : stadiumSeats) {	
			for(Seat seat: rowSeats) {
				if (seat.getStatus() == SeatStatus.AVAILABLE) {
					countAvailable = countAvailable + 1;	
				}
			}
		}
		return countAvailable;
	}

	/**
	 * Find and hold the best available seats for a customer
	 *
	 * @param numSeats the number of seats to find and hold
	 * @param customerEmail unique identifier for the customer
	 * @return a SeatHold object identifying the specific seats and related information
	 */
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		int seatsPlacedOnHold=0;
		int rowIndex=0;
		//SeatIndex within each row
		int seatIndex=0;
		
		SeatHold seatHold = new SeatHold();
	    List<RowSeatIndexPair> rowSetIndexPairList = new ArrayList<RowSeatIndexPair>();
		
		// Start from middle seats in last row
		// Once all seats in last row are exhausted, move to the next to last row
		for(rowIndex=rows-1; rowIndex >=0; rowIndex--) {
			int counter=0;
			if (seatsPlacedOnHold == numSeats) break;						
			for(counter = 0; counter <=(seatsInEachRow/2+1); counter++) {
			    seatIndex = seatsInEachRow/2 - counter;
				if ((seatIndex >=0) && (seatIndex < (seatsInEachRow))) {
                   Seat seat = stadiumSeats.get(rowIndex).get(seatIndex);
                   if (seat.getStatus() == SeatStatus.AVAILABLE){
   					int row_print = rowIndex + 1;
   					int seat_print = seatIndex + 1;
   					System.out.println("Setting to HOLD [ROW-COL] =[" + row_print + "-" + seat_print + "]");
                	   seat.setStatus(SeatStatus.HOLD);
                	   seatsPlacedOnHold++;
                	   RowSeatIndexPair r1 = new RowSeatIndexPair(rowIndex, seatIndex);
                	   rowSetIndexPairList.add(r1);
           			   if (seatsPlacedOnHold == numSeats) break;                	   
                   }
				}
			    seatIndex = seatsInEachRow/2 + counter;
				if ((seatIndex >=0) && (seatIndex < (seatsInEachRow))) {
	                   Seat seat = stadiumSeats.get(rowIndex).get(seatIndex);
	                   if (seat.getStatus() == SeatStatus.AVAILABLE){
	      					int row_print = rowIndex + 1;
	       					int seat_print = seatIndex + 1;
	       					System.out.println("Setting to HOLD [ROW-COL] =[" + row_print + "-" + seat_print + "]");
	                	   seat.setStatus(SeatStatus.HOLD);
	                	   seatsPlacedOnHold++;
	                	   RowSeatIndexPair r2 = new RowSeatIndexPair(rowIndex, seatIndex);
	                	   rowSetIndexPairList.add(r2);
	           			   if (seatsPlacedOnHold == numSeats) break;
	                   }
				}				
			}
		}

		//System.out.println("Set Hold ID = " + seatHold.getSeatHoldId());
        seatHoldMap.put(new Integer(seatHold.getSeatHoldId()),rowSetIndexPairList);
        
        //BEGIN-Set Timer to Release Hold After seatHoldTimeInSeconds
        Timer timer = new Timer();
		timer.schedule(new ClearHoldTimerTask(this,seatHold.getSeatHoldId()), seatHoldTimeInSecs*1000);
        //END-Set Timer to Release Hold After seatHoldTimeInSeconds

		return seatHold;
	}
	
	/**
	 * Commit seats held for a specific customer
	 *
	 * @param seatHoldId the seat hold identifier
 	 * @param customerEmail the email address of the customer to which the seat hold is assigned
	 * @return a reservation confirmation code
	 */
	public String reserveSeats(int seatHoldId, String customerEmail) {

		//Get List of Seats which are on hold
		List<RowSeatIndexPair> rowSeatIndexPairList = seatHoldMap.get(seatHoldId);
		if (rowSeatIndexPairList != null) {
			for(RowSeatIndexPair rowSeatIndexPair : rowSeatIndexPairList)
			{
				Integer rowIndexObj = rowSeatIndexPair.getRowIndex();
				Integer seatIndexObj = rowSeatIndexPair.getSeatIndex();
				Seat seat = stadiumSeats.get(rowIndexObj.intValue()).get(seatIndexObj.intValue());
				if (seat.getStatus() == SeatStatus.HOLD)
				{
					int row_print = rowIndexObj.intValue() + 1;
					int seat_print = seatIndexObj.intValue() + 1;
					System.out.println("Setting to Reserved [ROW-COL] =[" + row_print + "-" + seat_print + "]");
					seat.setStatus(SeatStatus.RESERVED);
				}
			}
			seatHoldMap.remove(seatHoldId);
			reservationMap.put(customerEmail, rowSeatIndexPairList);
			return customerEmail; // return customerEmail as confirmation number
		}
		return null;
	}

	//Print the seating arrangement and status of reservation[AVAILABLE, RESERVED, HOLD] of each seat in matrix format
	private void printSeats()
	{
		System.out.println();
		System.out.println("A=AVAIABLE, H=HOLD, R=RESERVED");
		int rowIndex=0;
		for(List<Seat> rowSeats : stadiumSeats)
		{
			rowIndex++;
			System.out.print("[ROW-" + rowIndex + "]   ");
			for(Seat seat: rowSeats )
			{
				if (seat.getStatus() == SeatStatus.AVAILABLE)
				{
					System.out.print("A ");
				}
				else if (seat.getStatus() == SeatStatus.HOLD)
				{
					System.out.print( "H ");
				}
				else if (seat.getStatus() == SeatStatus.RESERVED)
				{
					System.out.print("R ");
				}
			}
			System.out.println();
		}
	}
	
	public void removeHoldId(int seatHoldId) {
		//Get List of Seats which are on hold
		//System.out.println("get Hold ID = " + seatHold.getSeatHoldId());
		List<RowSeatIndexPair> rowSeatIndexPairList = seatHoldMap.get(seatHoldId);
		if (rowSeatIndexPairList != null) {
		for(RowSeatIndexPair rowSeatIndexPair : rowSeatIndexPairList)
		{
			Integer rowIndexObj = rowSeatIndexPair.getRowIndex();
			Integer seatIndexObj = rowSeatIndexPair.getSeatIndex();
			Seat seat = stadiumSeats.get(rowIndexObj.intValue()).get(seatIndexObj.intValue());
			if (seat.getStatus() == SeatStatus.HOLD)
			{
				int row_print = rowIndexObj.intValue() + 1;
				int seat_print = seatIndexObj.intValue() + 1;
				System.out.println("Releasing Hold On [ROW-COL] =[" + row_print + "-" + seat_print + "] Seats are now marked as AVAILABLE since HoldTime has expired");
			    seat.setStatus(SeatStatus.AVAILABLE);
			}
		}
		seatHoldMap.remove(seatHoldId);
		}
	}
	// Main Routine
	public static void main(String [] args) {
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
		  
		System.out.println("======================numSeatsAvailable===============================================");
		int availSeats = ticketServiceImpl.numSeatsAvailable();
		System.out.println("TotalAvailableSeats = " + availSeats);
		System.out.println("TotalNumRows = " + ticketServiceImpl.getRows());
		System.out.println("TotalSeatsInEachRow = " + ticketServiceImpl.getSeatsInEachRow());
		System.out.println("======================================================================================");
		System.out.println();
		System.out.println();
	 
		System.out.println("===================findAndHoldSeats==================================================");
		int numSeatsToPlaceOnHold = 10;
		System.out.println("Placing Seats on Hold = " + numSeatsToPlaceOnHold);
		SeatHold seatHold = ticketServiceImpl.findAndHoldSeats(numSeatsToPlaceOnHold, "abc@xyz.com");	
	    availSeats = ticketServiceImpl.numSeatsAvailable();
		ticketServiceImpl.printSeats();
		System.out.println("TotalAvailableSeats After Hold= " + availSeats);
		System.out.println("======================================================================================");
		System.out.println();
		
		/**
		 * Uncomment to test Hold on Seats Expiring, where Held Sets are Released
		 * When application has a sleep/wait for duration > ticketServiceImpl.getSeatHoldTimeInSecs()
		 * 
		try        
		{
		    Thread.sleep((ticketServiceImpl.getSeatHoldTimeInSecs() +1)*1000);
		} 
		catch(InterruptedException ex) 
		{
		    //Thread.currentThread().interrupt();
		}
		*/
		
	
		System.out.println("====================ReserveSeats======================================================");
		String confNum = ticketServiceImpl.reserveSeats(seatHold.getSeatHoldId(), "abc@xyz.com");		
	    availSeats = ticketServiceImpl.numSeatsAvailable();
		ticketServiceImpl.printSeats();
		System.out.println("TotalAvailableSeats After Reservation = " + availSeats);
		//System.out.println("Reservation Confirmation # is = " + confNum);
		System.out.println("======================================================================================");
		System.out.println();
	}

	
}
  