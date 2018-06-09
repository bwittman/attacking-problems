public class DeadlockPhilosopher extends Thread {
	public static final int SEATS = 5; 
	private static boolean[] chopsticks = new boolean[SEATS];	
	private int seat;
	
	public DeadlockPhilosopher( int seat ) {
		this.seat = seat;
	}

	public void run() {			
		try	{ 
			getChopstick( seat );			
			Thread.sleep(50);		
			getChopstick( seat - 1 );
		}
		catch( InterruptedException e ) {
			e.printStackTrace();
		}			
		eat();
	}
	
	private void getChopstick( int location )
		throws InterruptedException {
		if( location < 0 )
			location += SEATS;
		synchronized( chopsticks ) {
			while( chopsticks[location] )
				chopsticks.wait();
			chopsticks[location] = true;
		}		
		System.out.println("Philosopher " + seat +
			" picked up chopstick " + location + ".");
	}
	
	private void eat() {
		//done eating, put back chopsticks
		synchronized( chopsticks ) {
			chopsticks[seat] = false;			
			if( seat == 0)
				chopsticks[SEATS - 1] = false;
			else
				chopsticks[seat - 1] = false;							
			chopsticks.notifyAll();
		}
	}
}	
	