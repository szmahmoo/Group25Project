/**********************************************************************************************
 * 
 * PokerHandChecker_D1.cpp
 * COSC 1430 | Group 25 Project
 *
 * D1: First Draft of Program
 *      - Missing: bools for hand cases and ranking of hands, winner decision
 *      - Contains: main, variable declarations, struct for Hand and Card, load file
 *
 * The program will check a given set of poker hands in the file 'poker.txt' and will determine 
 * how many times player 1 wins. The program uses various structures and methods to evaluate the
 * player hands.
 *
 * The input format from file: 8C TS KC 9H 4S 7D 2S 5D 3S AC
 *      Note: First five cards belong to Player 1, and remaining five cards with Player 2
 *
 **********************************************************************************************/

#include <iostream>
#include <string>
#include <fstream>
#include <cctype>
#include <iomanip>
#include <sstream>

using namespace std;

// Constants for the program
const int RANK_SIZE = 15;   // Maximum size of rank array
const int HAND_SIZE = 5;    // Maximum size of hand
const int MAX_HANDS = 1000; // MAXIMUM HANDS TO load
const string FILE_NAME = "poker.txt";

// Structure to define a Card, that has a face and value
struct Card
{
    char suit;
    char face;
};

// Structure to define a Hand
struct Hand
{
    Card cards [HAND_SIZE];
};

// Function to get the value of a given card
int cardValue(Card card);

// Function to get the suit values: Spade=1, Club=2, Hearts=3, Diamond=4
int suitValue(Card card);

// Function to get the Rank of given hand. Rank is an array with counts of cards.
int * getRanks(Hand hand);

// Function to get the Suit rank of given hand
int * getSuitRank(Hand hand);

/* To Do - Poker Hand Cases
 *
 * Cases:   High Card, One Pair, Two Pairs, Three of a Kind, Straight, Flush, Full House,
 *          Four of a Kind, Straight Flush, Royal Flush
 */
bool isPair(Hand hand);             // Function to check that the hand has a Pair
bool isTwoPairs(Hand hand);         // Function to check that if there are two pairs
bool isThreeOfKind(Hand hand);      // Function to check that given hand is a Three of Kind
bool isFlush(Hand hand);            // Function to check that if a given hand is a flush
bool isFullHouse(Hand hand);        // Function to Check that a given Hand is a Full House
bool isStraight(Hand hand);         // Function to check that a given hand is a Straight
bool isFourOfKind(Hand hand);       // Function to check that a given hand is a Four of Kind

/* To Do - Poker Hand Cases dealing with Rank
 *
 * Cases:   High Card, One Pair, Two Pairs, Three of a Kind, Straight, Flush, Full House,
 *          Four of a Kind, Straight Flush, Royal Flush
 */
int getRankOfPair(Hand hand);           // Function to Get the rank of a given pair (One Pair)
int getRankOfTwoPairs(Hand hand);       // Function to Get the rank of a given pair (Two Pairs)
int getRankOfThreeOfKind(Hand hand);    // Function to get the rank of Three of Kind
int getRankOfStraight(Hand hand);       // Function to get the Rank of a Straight
int getRankOfFlush(Hand hand);          // Function to get the rank of a Flush
int getRankOfFullHouse(Hand hand);      // Function to Get the rank of a Full House
int getRankOfFourOfKind(Hand hand);     // Function to get the rank of Four of a Kind

string getFaceName(int face);       // Get the string representation of Card value
string getType(Hand hand);          // Function to get the type of hand i.e Pair of Fives

// To Do - getHandType, getHandRanks, getWinner, getFaceName, getType
int getHandType(Hand h);            // Function to get the type of hand
int getHandRanks(Hand hand)         // Function to get ranks array sorted for hand
int getWinner(Hand h1, Hand h2);    // Function to get the winner of a given hand
string getFaceName(int face);       // Get the string representation of Card value
string getType(Hand hand);          // Function to get the type of hand i.e Pair of Fives

// Function to load hands into player hands
// Receives Hand arrays for 2 players, and load all the hands into the array
void loadHands(Hand playerOne [], Hand playerTwo [], int & count);

// To Do - Function to display two hands as given in the pdf file
void dislayHand(Hand h1, Hand h2, int, int &);

//---------------------------------------- Main Method --------------------------------------//
int main()
{
    //Player hands collection
    Hand playerOne [MAX_HANDS];
    Hand playerTwo [MAX_HANDS];
    
    //Count of actual hands read from file
    int count = 0;
    int playerOneWins = 0;
    
    //Load hands
    loadHands(playerOne, playerTwo, count);
    
    cout << "Total Hands loaded: " << count << endl;
    
    cout << left << setw(5) << "Hand"
    << setw(30) << "Player 1"
    << setw(30) << "Player 2"
    << setw(15) << "Winner" << endl;
    cout <<"======================================================================" << endl;
    
    for(int i=0; i<count; i++)
    {
        int winner = 0;
        dislayHand(playerOne[i], playerTwo[i], (i+1), winner);
        
        if(winner == 1)
        {
            playerOneWins++;
        }
    }
    
    cout << endl;
    cout << "Player 1 Wins " << playerOneWins << " times." << endl;
    return 0;
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
        cout << "ERROR: opening file - " << FILE_NAME << endl;
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

// Function to get the value of a given card (Ace is highest card)
int cardValue(Card card)
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

// Function to get the suit value of a given card (Diamonds highest in rank, while Spades lowest in rank)
int suitValue(Card card)
{
    int value = 0;
    switch(card.suit)
    {
        case 'S': value = 1; break;
        case 'C': value = 2; break;
        case 'H': value = 3; break;
        case 'D': value = 4; break;
    }
    return value;
}

int * getSuitRank(Hand hand)
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

int * getRanks(Hand hand)
{
    int * ranks = new int[RANK_SIZE];
    
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
