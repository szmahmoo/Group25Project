import java.awt.*;


// Constants for the program

public class Poker {
   public static final int RANK_SIZE = 15;   // Maximum size of rank array
	public static final int HAND_SIZE = 5;    // Maximum size of hand
	public static final int MAX_HANDS = 1000; // MAXIMUM HANDS TO load
	public static final string FILE_NAME = "poker.txt";
// Structure to define a Card, that has a face and value

struct Card()
{
    char suit;
    char face;
};

// Structure to define a Hand

struct Hand()
{
    Card cards [HAND_SIZE];
};

public int cardValue(Card card);           // Function to get the value of a given card
public int suitValue(Card card);           // Function to get the suit values: Spade=1, Club=2, Diamond=3, Hearts=4
public int * getRanks(Hand hand);          // Function to get the Rank of given hand.
                                    	   // Rank is an array with counts of cards.
public int * getSuitRank(Hand hand);       // Function to get the Suit rank of given hand
public int * getHandRanks(Hand hand);      // Function to get ranks array sorted for hand

public public boolean isPair(Hand hand);             // Function to check that the hand has a Pair
public public boolean isTwoPairs(Hand hand);         // Function to check that if there are two pairs
public public boolean isThreeOfKind(Hand hand);      // Function to check that given hand is a Three of Kind
public public boolean isFlush(Hand hand);            // Function to check that if a given hand is a flush
public public boolean isFullHouse(Hand hand);        // Function to Check that a given Hand is a Full House
public public boolean isStraight(Hand hand);         // Function to check that a given hand is a Straight
public public boolean isFourOfKind(Hand hand);       // Function to check that a given hand is a Four of Kind

public int getRankOfHighCard(Hand hand);       // Function to rank of highest card
public int getRankOfPair(Hand hand);           // Function to Get the rank of a given pair (One Pair)
public int getRankOfTwoPairs(Hand hand);       // Function to Get the rank of a given pair (Two Pairs)
public int getRankOfThreeOfKind(Hand hand);    // Function to get the rank of Three of Kind
public int getRankOfStraight(Hand hand);       // Function to get the Rank of a Straight
public int getRankOfFlush(Hand hand);          // Function to get the rank of a Flush
public int getRankOfFullHouse(Hand hand);      // Function to Get the rank of a Full House
public int getRankOfFourOfKind(Hand hand);     // Function to get the rank of Four of a Kind

public int getHandType(Hand h);            // Function to get the type of hand
public int getWinner(Hand h1, Hand h2);    // Function to get the winner of a given hand

public string getFaceName(int face);       // Get the string representation of Card value
public string getType(Hand hand);          // Function to get the type of hand i.e Pair of Fives

// Function to load hands into player hands
// Receives Hand arrays for 2 players, and load all the hands into the array
public void loadHands(Hand playerOne [], Hand playerTwo [], int & count);

// Function to display two hands as given in the pdf file
public void dislayHand(Hand h1, Hand h2, int, int &);


//---------------------------------------- Main Method --------------------------------------//
int main()
{
    // Player hands collection
    Hand playerOne [MAX_HANDS];
    Hand playerTwo [MAX_HANDS];
    
    // Count of actual hands read from file
    int count = 0;
    int playerOneWins = 0;
    
    // Load hands
    loadHands(playerOne, playerTwo, count);
    
    System.out.println (("Total Hands loaded: " ) + count);
    
    System.out.println (left + setw(5) + ("Hand"));
          (setw(30) + ("Player 1"));
          (setw(30) + ("Player 2"));
          (setw(15) + ("Winner"));
    System.out.println("===========================================================================");
    
    for(int i=0; i<count; i++)
    {
        int winner = 0;
        dislayHand(playerOne[i], playerTwo[i], (i+1), winner);
        
        if(winner == 1)
        {
            playerOneWins++;
        }
    }
    
    System.out.println endl;
    System.out.println (("Player 1 Wins ") + playerOneWins + (" times.")); 
    return 0;

// Function to get the value of a given card (Ace is highest card)
public int cardValue(Card card)
{
    int value = 0;
    
    switch(card.face)
    {
        case '2': value = 2; break;
        case '3': value = 3; break;
        case '4': value = 4; break;
        case '5': value = 5; break;
        case '6': value = 6; break;
        case '7': value = 7; break;
        case '8': value = 8; break;
        case '9': value = 9; break;
        case 'T': value = 10; break;
        case 'J': value = 11; break;
        case 'Q': value = 12; break;
        case 'K': value = 13; break;
        case 'A': value = 14; break;
    }
    
    return value;
}

// Function to get the suit value of a given card (Hearts highest in rank, while Spades lowest in rank)
int suitValue (Card card)
{
    int value = 0;
    switch(card.suit)
    {
        case 'S': value = 1; break;
        case 'C': value = 2; break;
        case 'D': value = 3; break;
        case 'H': value = 4; break;
    }
    return value;
}

// Function to get the Rank of given hand. Rank is an array with counts of cards.
int getRanks (Hand hand)
{
    int ranks = new int[RANK_SIZE];
    
    // Initialize ranks array with 0
    for(int i=0; i<RANK_SIZE; i++)
    {
        ranks[i] = 0;
    }
    
    // Iterate hand and get the card value and count
    // Initialize ranks array with 0
    for(int i=0; i<HAND_SIZE; i++)
    {
        ranks[cardValue(hand.cards[i])] += 1;
    }
    
    return ranks;
}

// Function to get the Suit rank of given hand
int * getSuitRank(Hand hand)[]
{
    int * ranks = new int[HAND_SIZE];
    
    // Initialize to zero
    for(int i=0; i<HAND_SIZE; i++)
    {
        ranks[i] = 0;
    }
    
    // Count occurrances
    for(int i=0; i<HAND_SIZE; i++)
    {
        ranks[suitValue(hand.cards[i])]++;
    }
    
    return ranks;
}

// Function to get ranks array sorted for hand
int * getHandRanks(Hand hand)
{
    int * ranks = getRanks(hand);
    int * r = new int[HAND_SIZE];
    int j = 0;
    
    for(int i=2; i<RANK_SIZE; i++)
    {
        if(ranks[i] == 1)
        {
            r[j++] = i;
        }
    }
    
    return r;
}


//------------------------------------- Poker Hand Cases -------------------------------------------//
// Function to check that the hand has a Pair
public boolean isPair(Hand hand)
{
    // Get the rank array
    int * ranks = getRanks(hand);
    
    int counts = 0;
    for(int i=2; i<RANK_SIZE; i++)
    {
        if(ranks[i] == 2)
        {
            counts++;
        }
    }
    
    return counts == 1;
}

// Function to check that if there are two pairs
public boolean isTwoPairs(Hand hand)
{
    // Get the rank array
    int * ranks = getRanks(hand);
    
    int counts = 0;
    for(int i=2; i<RANK_SIZE; i++)
    {
        if(ranks[i] == 2)
        {
            counts++;
        }
    }
    
    return counts == 2;
}

// Function to check that given hand is a Three of Kind
public boolean isThreeOfKind(Hand hand)
{
    // Get the rank array
    int * ranks = getRanks(hand);
    
    int counts = 0;
    for(int i=2; i<RANK_SIZE; i++)
    {
        if(ranks[i] == 3)
        {
            counts++;
        }
    }
    
    return counts == 1;
}

// Function to check that if a given hand is a flush
public boolean isFlush(Hand hand)
{
    // Get the rank array
    int * ranks = getSuitRank(hand);
    
    for(int i=1; i<HAND_SIZE; i++)
    {
        if(ranks[i] == 5)
        {
            return true;
        }
    }
    
    return false;
}

// Function to Check that a given Hand is a Full House
public boolean isFullHouse(Hand hand)
{
    // Get the rank array
    int * ranks = getRanks(hand);
    
    int twos = 0;
    int threes = 0;
    
    for(int i=2; i<RANK_SIZE; i++)
    {
        if(ranks[i] == 2)
        {
            twos = i;
        }
        
        if(ranks[i] == 3)
        {
            threes = i;
        }
    }
    
    return (twos > 0 && threes > 0);
}

// Function to check that a given hand is a Straight
public boolean isStraight(Hand hand)
{
    // Get the rank array
    int * ranks = getRanks(hand);
    
    for(int i=2; i<RANK_SIZE-4; i++)
    {
        if(ranks[i] == 1 && ranks[i+1] == 1 && ranks[i+2] == 1 && ranks[i+3] == 1 &&
           ranks[i+4] == 1)
        {
            return true;
        }
    }
    
    // Check if Straight is: A2345
    if(ranks[RANK_SIZE-1] == 1 && ranks[2] == 1 && ranks[3] == 1 && ranks[4] == 1 &&
       ranks[5] == 1)
    {
        return true;
    }
    
    return false;
}

// Function to check that a given hand is a Four of Kind
public boolean isFourOfKind(Hand hand)
{
    /// Get the rank array
    int * ranks = getRanks(hand);
    
    for(int i=2; i<RANK_SIZE; i++)
    {
        if(ranks[i] == 4)
        {
            return true;
        }
    }
    
    return false;
}

//-------------------------------------- Poker Hand Ranks ------------------------------------------//
// Function to rank of highest card
public int getRankOfHighCard(Hand hand)
{
    int * ranks = getRanks(hand);
    
    int rank = 0;
    for(int i=2; i<RANK_SIZE; i++)
    {
        if(ranks[i] == 1)
        {
            rank = i;
        }
    }
    
    return rank;
}

// Function to Get the rank of a given pair (One Pair)
public int getRankOfPair(Hand hand)
{
    if(isPair(hand))
    {
        //Get the rank array
        int * ranks = getRanks(hand);
        
        int rank = 0;
        
        for(int i=2; i<RANK_SIZE; i++)
        {
            if(ranks[i] == 2)
            {
                rank = i;
            }
        }
        
        return rank;
    }
    return 0;
}

// Function to Get the rank of a given pair (Two Pairs)
public int getRankOfTwoPairs(Hand hand)
{
    if(isTwoPairs(hand))
    {
        // Get the rank array
        int * ranks = getRanks(hand);
        
        int rank = 0;
        for(int i=2; i<RANK_SIZE; i++)
        {
            if(ranks[i] == 2)
            {
                rank = i;
            }
        }
        
        return rank;
    }
    return 0;
}

// Function to get the rank of Three of Kind
public int getRankOfThreeOfKind(Hand hand)
{
    if(isThreeOfKind(hand))
    {
        // Get the rank array
        int * ranks = getRanks(hand);
        
        int rank = 0;
        for(int i=2; i<RANK_SIZE; i++)
        {
            if(ranks[i] == 3)
            {
                rank = i;
            }
        }
        
        return rank;
    }
    
    return 0;
}

// Function to get the Rank of a Straight
int getRankOfStraight(Hand hand)
{
    if(isStraight(hand))
    {
        int * ranks = getRanks(hand);
        
        for(int i=2; i<RANK_SIZE-4; i++)
        {
            if(ranks[i] == 1 && ranks[i+1] == 1 && ranks[i+2] == 1 && ranks[i+3] == 1 &&
               ranks[i+4] == 1)
            {
                return i+4;
            }
        }
        
        // Check if Straight is: A2345, etc.
        if(ranks[RANK_SIZE-1] == 1 && ranks[2] == 1 && ranks[3] == 1 && ranks[4] == 1 &&
           ranks[5] == 1)
        {
            return 5;
        }
    }
    
    return 0;
}

// Function to get the rank of a Flush
int getRankOfFlush(Hand hand)
{
    if(isFlush(hand))
    {
        int * ranks = getRanks(hand);
        int rank = 0;
        
        for(int i=2; i<RANK_SIZE; i++)
        {
            if(ranks[i] == 1)
            {
                rank = i;
            }
        }
        
        return rank;
    }
    return 0;
}

// Function to Get the rank of a Full House
int getRankOfFullHouse(Hand hand)
{
    return getRankOfThreeOfKind(hand);
}

// Function to get the rank of Four of a Kind
int getRankOfFourOfKind(Hand hand)
{
    //Get ranks
    int * ranks = getRanks(hand);
    
    for(int i=2; i<RANK_SIZE; i++)
    {
        if(ranks[i] == 4)
        {
            return i;
        }
    }
    
    return 0;
}

// Function to get the type of hand
int getHandType(Hand h1)
{
    if(isPair(h1) && !isTwoPairs(h1) && !isFullHouse(h1))
    {
        return 1;
    }
    
    else if(isTwoPairs(h1))
    {
        return 2;
    }
    
    else if(isThreeOfKind(h1) && !isFullHouse(h1))
    {
        return 3;
    }
    
    else if(!isFlush(h1) && isStraight(h1))
    {
        return 4;
    }
    
    else if(isFlush(h1) && !isStraight(h1))
    {
        return 5;
    }
    
    else if(isFullHouse(h1) && isPair(h1) && isThreeOfKind(h1))
    {
        return 6;
    }
    
    else if(isFourOfKind(h1))
    {
        return 7;
    }
    
    else if(isFlush(h1) && isStraight(h1))
    {
        return 8;
    }
    
    else
    {
        return 0;
    }
}

int getWinner(Hand h1, Hand h2)
{
    int t1 = getHandType(h1);
    int t2 = getHandType(h2);
    
    if(t1 > t2) return 1;
    
    else if(t1 < t2) return 2;
    
    else // Same category
    {
        // Case: Both have high cards
        if(t1 == 0)
        {
            int r1 = getRankOfHighCard(h1);
            int r2 = getRankOfHighCard(h2);
            
            if(r1 > r2) return 1;
            else if(r1 < r2) return 2;
            else // Equal rank, check other cards
            {
                int * rh1 = getHandRanks(h1);
                int * rh2 = getHandRanks(h2);
                for(int i=HAND_SIZE-1; i>=0; i--)
                {
                    if(rh1[i] > rh2[i]) return 1;
                    else if(rh1[i] < rh2[i]) return 2;
                }
            }
        }
        
        else if(t1 == 1) // Case: Both Have One Pair
        {
            int r1 = getRankOfPair(h1);
            int r2 = getRankOfPair(h2);
            
            if(r1 > r2) return 1;
            else if(r1 < r2) return 2;
            else // Same pair
            {
                int * rh1 = getHandRanks(h1);
                int * rh2 = getHandRanks(h2);
                for(int i=HAND_SIZE-1; i>=0; i--)
                {
                    if(rh1[i] > rh2[i]) return 1;
                    else if(rh1[i] < rh2[i]) return 2;
                }
            }
        }
        
        else if(t1 == 2) // Case: Both Have Two pairs
        {
            int r1 = getRankOfTwoPairs(h1);
            int r2 = getRankOfTwoPairs(h2);
            
            if(r1 > r2) return 1;
            
            else if(r1 < r2) return 2;
            
            else // Same pair
            {
                int * rh1 = getRanks(h1);
                int * rh2 = getRanks(h2);
                int xx[3], yy[3];
                int y = 0;
                int x = 0;
                
                for(int i=0; i<RANK_SIZE; i++)
                {
                    if(rh1[i] == 2)
                    {
                        xx[x++] = i;
                    }
                    
                    if(rh1[i] == 1) xx[2] = i;
                    
                    if(rh2[i] == 2)
                    {
                        yy[y++] = i;
                    }
                    
                    if(rh2[i] == 1) yy[2] = i;
                }
                
                if(xx[1] > yy[1]) return 1;
                
                else if(xx[1] < yy[1]) return 2;
                
                else
                {
                    if(xx[0] > yy[0]) return 0;
                    else if(xx[0] < yy[0]) return 0;
                    else
                    {
                        if(xx[2] > yy[2]) return 1;
                        else return 2;
                    }
                }
            }
        }
        
        else if(t1 == 3) // Case: Both Have Three of a Kind
        {
            int r1 = getRankOfThreeOfKind(h1);
            int r2 = getRankOfThreeOfKind(h2);
            
            if(r1 > r2) return 1;
            else return r2;
        }
        
        else if(t1 == 4) // Case: Both Have a Straight
        {
            int r1 = getRankOfStraight(h1);
            int r2 = getRankOfStraight(h2);
            
            if(r1 > r2) return 1;
            else return 2;
        }
        
        else if(t1 == 5) /// Case: Both Have a Flush
        {
            int r1 = getRankOfFlush(h1);
            int r2 = getRankOfFlush(h2);
            
            if(r1 > r2) return 1;
            else return 2;
        }
        
        else if(t1 == 6) //Case: Both Have a Full House
        {
            int r1 = getRankOfFullHouse(h1);
            int r2 = getRankOfFullHouse(h2);
            
            if(r1 > r2) return 1;
            
            else if(r1 < r2) return 2;
            
            else
            {
                // Same 3 of kind, check pair
                r1 = getRankOfPair(h1);
                r2 = getRankOfPair(h2);
                
                if(r1 > r2) return 1;
                else return 2;
            }
        }
        
        else if(t1 == 7) //Case: Both Have Four of a kind
        {
            int r1 = getRankOfFourOfKind(h1);
            int r2 = getRankOfFourOfKind(h2);
            
            if(r1 > r2) return 1;
            else return 2;
        }
        
        else if(t1 == 8) //Case: Both Have a Straight flush
        {
            int r1 = getRankOfStraight(h1);
            int r2 = getRankOfStraight(h2);
            
            if(r1 > r2) return 1;
            else return 2;
        }
    }
    return 1;
}

// Get the string representation of Card value
string getFaceName(int face)
{
    string name = "";
    switch(face)
    {
        case 2: name = "Two"; break;
        case 3: name = "Three"; break;
        case 4: name = "Four"; break;
        case 5: name = "Five"; break;
        case 6: name = "Six"; break;
        case 7: name = "Seven"; break;
        case 8: name = "Eight"; break;
        case 9: name = "Nine"; break;
        case 10: name = "Ten"; break;
        case 11: name = "Jack"; break;
        case 12: name = "Queen"; break;
        case 13: name = "King"; break;
        case 14: name = "Ace"; break;
    }
    return name;
}

// Function to get the type of hand i.e Pair of Fives
string getType(Hand hand)
{
    stringstream ss;
    
    if(isPair(hand) && !isTwoPairs(hand) && !isFullHouse(hand))
    {
        int rank = getRankOfPair(hand);
        ss << "Pair of " << getFaceName(rank) << "s";
    }
    
    else if(isTwoPairs(hand))
    {
        int * ranks = getRanks(hand);
        int vals[2];
        int x = 0;
        for(int i=2; i<RANK_SIZE; i++)
        {
            if(ranks[i] == 2)
            {
                vals[x++] = i;
            }
        }
        ss << "Pairs (" << getFaceName(vals[1]) << ", " << getFaceName(vals[0]) << ")";
    }
    
    else if(isThreeOfKind(hand) && !isFullHouse(hand))
    {
        int rank = getRankOfThreeOfKind(hand);
        
        ss << "Three of a Kind (" << getFaceName(rank) << "s)";
    }
    
    else if(isStraight(hand) && !isFlush(hand))
    {
        ss << "Straight";
    }
    
    else if(isFlush(hand) && !isStraight(hand))
    {
        ss << "Flush";
    }
    
    else if(isFullHouse(hand))
    {
        int rank = getRankOfFullHouse(hand);
        ss << "Full House with 3 " << getFaceName(rank) << "s";
    }
    
    else if(isFourOfKind(hand))
    {
        int rank = getRankOfFourOfKind(hand);
        ss << "Four of a Kind (" << getFaceName(rank) << "s)";
    }
    
    else if(isFlush(hand) && isStraight(hand))
    {
        int rank = getRankOfStraight(hand);
        
        if(rank == 14)
            ss << "Royal Flush";
        
        else
            ss << "Straight Flush";
    }
    
    else
    {
        int rank = getRankOfHighCard(hand);
        ss << "High Card - " << getFaceName(rank);
    }
    
    return ss.str();
}

// Opens poker.txt file, returns error if not opened, and reads line by line.
void loadHands(Hand playerOne [], Hand playerTwo [], int & count)
{
    // Open file to read
    ifstream in(FILE_NAME.c_str(), ios::in);
    
    count =0 ;
    
    // Checks if file open
    if(!in.is_open())
    {
        System.out.println "ERROR: opening file - " << FILE_NAME << endl;
        return;
    }
    
    string card;
    
    // Loads file
    while(!in.eof())
    {
        int x = 0;
        int y = 0;
        
        for(int i=0; i<10; i++)
        {
            // Five cards for first player, five cards for second player
            in >> card;
            
            if(in)
            {
                if(i < 5)
                {
                    playerOne[count].cards[x].face = card[0];
                    playerOne[count].cards[x].suit = card[1];
                    x++;
                }
                else
                {
                    playerTwo[count].cards[y].face = card[0];
                    playerTwo[count].cards[y].suit = card[1];
                    y++;
                }
            }
        }
        count++;
    }
    
    // Close file
    in.close();
}

// Function to display two hands as given in the pdf file
void dislayHand(Hand h1, Hand h2, int cnt, int & winner)
{
    stringstream ss;
    stringstream ss1;
    string p1, p2;
    
    for(int i=0; i<HAND_SIZE; i++)
    {
        ss << h1.cards[i].face << h1.cards[i].suit << " ";
        ss1 << h2.cards[i].face << h2.cards[i].suit << " ";
    }
    
    p1 = ss.str();
    p2 = ss1.str();
    
    winner = getWinner(h1, h2);
    
    System.out.println left << setw(5) << cnt
         << setw(30) << p1
         << setw(30) << p2
         << endl;
    
    System.out.println left << setw(5) << " "
         << setw(30) << getType(h1)
         << setw(30) << getType(h2);
    
    if(winner == 1)
        System.out.println "Player 1";
    
    else
        System.out.println "Player 2";
    System.out.println endl;
}