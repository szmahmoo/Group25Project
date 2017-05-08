import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class OpenFile {


			public OpenFile(String[] args) {

				// will store the cards read from the file
				 ArrayList<String> Hands = new ArrayList<String>();
				 
				 BufferedReader br = null;
				 
				 try 
				 {
					// attempt to open the Poker Hands file
					 br = new BufferedReader( new FileReader( "poker.txt" ) );
					 
					 String card;

					// loop and read a line from the file as long as we do not get null
					 while( ( card = br.readLine() ) != null )

					// add the read card to the Hands
					 Hands.add( card );
				 }
				 
				 catch( IOException e ) 
				 {
					 e.printStackTrace();
				 }
				 
				 finally
				 {
					 try 
					 {
						// close the file
						 br.close();
					 }
					 catch( IOException ex ) 
					 {
						 ex.printStackTrace();
					 }
				 }

				// initialize a new string array equal to the size of the hand
				 String[] cards = new String[ Hands.size() ];

				// call the Hands's toArray method to and transfer items from
				// Hands to our string array cards
				 Hands.toArray( cards );

				// loop and display each card from the cards array
				 for( int i = 0; i < cards.length; i++ )
					 System.out.println( cards[ i ] );
		}




	public OpenFile() {
		
	}

}
