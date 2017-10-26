package com.walmart.TicketService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TicketServiceImplTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TicketServiceImplTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TicketServiceImplTest.class );
    }

    
	/**
	 * Test Case Description:
	 * Initialize TicketServiceImpl
	 * Validate that total number of seats is equal to the Available Number of Seats.
	 */
	public void testNumSeatsAvailable1() {
		
		System.out.println("===============BEGIN testNumSeatsAvailable1===========================");
		
		//initialize TicketServiceImpl
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
		int rows = ticketServiceImpl.getRows();
		int seatsInRow = ticketServiceImpl.getSeatsInEachRow();
		
		// test numSeatsAvailable
		assertEquals(rows*seatsInRow,ticketServiceImpl.numSeatsAvailable());		
		System.out.println("===============END testNumSeatsAvailable1===========================");
	}
	
	/**
	 * Test Case Description:
	 * Initialize TicketServiceImpl
	 * Put Some Seats on Hold - H1
	 * Validate That Available Seats are reduced by number of Seats Placed on Hold
	 */
	public void testFindAndHoldSeats2() {
		System.out.println("===============BEGIN testFindAndHoldSeats2===========================");

		//initialize TicketServiceImpl
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
		int totalNumSeats = ticketServiceImpl.numSeatsAvailable();
		String customerEmail = "abc@xyz.com";

		// put some seats on hold		
		int numHoldSeats = 1 + (int)(Math.random() * ((totalNumSeats - 1) + 1));
	    SeatHold seatHold = ticketServiceImpl.findAndHoldSeats(numHoldSeats, customerEmail);
	    
		// validate that number of available Seats are reduced by the held seats
	    assertEquals(totalNumSeats - numHoldSeats, ticketServiceImpl.numSeatsAvailable());
		System.out.println("===============END testFindAndHoldSeats2===========================");
	}
	
	/**
	 * Test Case Description:
	 * Initialize TicketServiceImpl
	 * Put Some Seats on Hold - H1
	 * Put Some Seats on Hold - H2
	 * Validate That Available Seats are reduced by Reserved Seats
	 */
	public void testFindAndHoldSeats3() {
		System.out.println("===============BEGIN testFindAndHoldSeats3===========================");

		//initialize TicketServiceImpl
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
		int totalNumSeats = ticketServiceImpl.numSeatsAvailable();
		String customerEmail1 = "abc@xyz.com";
		String customerEmail2 = "def@xyz.com";

		// put some seats on hold		
		int numHoldSeats1 = 1 + (int)(Math.random() * ((totalNumSeats - 1) + 1));
	    SeatHold seatHold1 = ticketServiceImpl.findAndHoldSeats(numHoldSeats1, customerEmail1);

	    // put some seats on hold		
		int numHoldSeats2 = 1 + (int)(Math.random() * ((totalNumSeats - numHoldSeats1 - 1) + 1));
	    SeatHold seatHold2 = ticketServiceImpl.findAndHoldSeats(numHoldSeats2, customerEmail2);

	    // validate that number of available Seats are reduced by the held seats
	    assertEquals(totalNumSeats - numHoldSeats1 - numHoldSeats2, ticketServiceImpl.numSeatsAvailable());
		System.out.println("===============END testFindAndHoldSeats3===========================");
	}
	
	
	/**
	 * Test Case Description:
	 * Initialize TicketServiceImpl
	 * Put Some Seats on Hold - H1
	 * Validate That Available Seats are reduced by number of Seats Put on Hold
	 * Wait until Hold Interval expires.
	 * Validate That Available Seats are equal to the total number of seats
	 */
	public void testFindAndHoldSeats4() {

		System.out.println("===============BEGIN testFindAndHoldSeats4===========================");
		
		//initialize TicketServiceImpl
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
		int totalNumSeats = ticketServiceImpl.numSeatsAvailable();
		String customerEmail = "abc@xyz.com";

		// put some seats on hold		
		int numHoldSeats = 1 + (int)(Math.random() * ((totalNumSeats - 1) + 1));
	    SeatHold seatHold = ticketServiceImpl.findAndHoldSeats(numHoldSeats, customerEmail);

	    assertEquals(totalNumSeats - numHoldSeats, ticketServiceImpl.numSeatsAvailable());

	    // wait until hold time expires
		try        
		{
		    Thread.sleep((ticketServiceImpl.getSeatHoldTimeInSecs() +1)*1000);
		} 
		catch(InterruptedException ex) 
		{
		    //Thread.currentThread().interrupt();
		}

		// validate that seats are released and are available (not held)
	    assertEquals(totalNumSeats, ticketServiceImpl.numSeatsAvailable());
		System.out.println("===============END testFindAndHoldSeats4===========================");
	}
	
	
	/**
	 * Test Case Description:
	 * Initialize TicketServiceImpl
	 * Put Some Seats on Hold - H1
	 * Reserve Held Seats - Reserve H1
	 * Validate That Available Seats are reduced by Reserved Seats
	 */
	public void testReserveSeats5() {
		System.out.println("===============BEGIN testReserveSeats5===========================");
		//initialize TicketServiceImpl
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
		int totalNumSeats = ticketServiceImpl.numSeatsAvailable();
		String customerEmail = "abc@xyz.com";

		// put some seats on hold
		int numHoldSeats = 1 + (int)(Math.random() * ((totalNumSeats - 1) + 1));
	    SeatHold seatHold = ticketServiceImpl.findAndHoldSeats(numHoldSeats, customerEmail);

	    // reserve held seats (should succeed)
	    String confNum = ticketServiceImpl.reserveSeats(seatHold.getSeatHoldId(), customerEmail);
	    
		// validate that seats are reserved
	    assertEquals(totalNumSeats - numHoldSeats, ticketServiceImpl.numSeatsAvailable());
		System.out.println("===============END testReserveSeats5===========================");
	}

	/**
	 * Test Case Description:
	 * Initialize TicketServiceImpl
	 * Put Some Seats on Hold - H1
	 * Wait Until Hold Time Expires
	 * Attempt to Reserve Held Seats - Reserve H1
	 * Validate that Reservation-Confirmation-Number is null (since reservation is not succesful) and that all seats are still Available 
	 */
	public void testReserveSeats6() {
		System.out.println("===============BEGIN testReserveSeats6===========================");
		//initialize TicketServiceImpl
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
		int totalNumSeats = ticketServiceImpl.numSeatsAvailable();
		String customerEmail = "abc@xyz.com";

		// put some seats on hold
		int numHoldSeats = 1 + (int)(Math.random() * ((totalNumSeats - 1) + 1));
	    SeatHold seatHold = ticketServiceImpl.findAndHoldSeats(numHoldSeats, customerEmail);

	    // wait until hold time expires
		try        
		{
		    Thread.sleep((ticketServiceImpl.getSeatHoldTimeInSecs() +1)*1000);
		} 
		catch(InterruptedException ex) 
		{
		    //Thread.currentThread().interrupt();
		}

		// reserve held seats (this should fail)		
	    String confNum = ticketServiceImpl.reserveSeats(seatHold.getSeatHoldId(), customerEmail);

	    //Validate that reservation confirmation number is null, since reservation fails
	    assertNull(confNum);
	    
	    // validate that seats are not reserved
	    assertEquals(totalNumSeats, ticketServiceImpl.numSeatsAvailable());
		System.out.println("===============END testReserveSeats6===========================");
	}

	/**
	 * Test Case Description:
	 * Initialize TicketServiceImpl
	 * Put Some Seats on Hold - H1
	 * Put Some Seats on Hold - H2
	 * Reserve Held Seats - Reserve H1
	 * Reserve Held Seats - Reserve H2
	 * Validate That Available Seats are reduced by Reserved Seats
	 */
	public void testReserveSeats7() {
		System.out.println("===============BEGIN testReserveSeats7===========================");
		//initialize TicketServiceImpl
		TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
		int totalNumSeats = ticketServiceImpl.numSeatsAvailable();
		String customerEmail1 = "abc@xyz.com";
		String customerEmail2 = "def@xyz.com";

		// put some seats on hold - H1
		int numHoldSeats1 = 1 + (int)(Math.random() * ((totalNumSeats - 1) + 1));
	    SeatHold seatHold1 = ticketServiceImpl.findAndHoldSeats(numHoldSeats1, customerEmail1);

	    // put some seats on hold - H2		
		int numHoldSeats2 = 1 + (int)(Math.random() * ((totalNumSeats - numHoldSeats1 - 1) + 1));
	    SeatHold seatHold2 = ticketServiceImpl.findAndHoldSeats(numHoldSeats2, customerEmail2);

	    // reserve held seats - Reserve H1 
	    String confNum1 = ticketServiceImpl.reserveSeats(seatHold1.getSeatHoldId(), customerEmail1);
		// reserve held seats - Reserve H2
	    String confNum2 = ticketServiceImpl.reserveSeats(seatHold2.getSeatHoldId(), customerEmail2);
		
		// validate that Available Seats are reduced by Reserved Seats
	    assertEquals(totalNumSeats - numHoldSeats1 - numHoldSeats2, ticketServiceImpl.numSeatsAvailable());
		System.out.println("===============END testReserveSeats7===========================");
	    
	}
}
