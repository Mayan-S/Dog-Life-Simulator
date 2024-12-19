//Folder that this file is located in
package gamble;

//SlotMachine Class That Represents a Simple Slot Machine With Three Slots and Various Symbols.
public class SlotMachine{
  // Static Array of symbols for the slot machine
  private static String[] symbols = {"🦴", "🐶", "🐾", "🏀"};

  // Instance variables for the three slots
  private String slotOne, slotTwo, slotThree;

  //Default constructor that initializes the slots with default symbols
  public SlotMachine(){
    slotOne = symbols[0];
    slotTwo = symbols[1];
    slotThree = symbols[2];
  }

  //Constructor that initializes the slots with random symbols based on the given parameter
  public SlotMachine(int d){
    slotOne = symbols[((int)((d+1)*Math.random()))];
    slotTwo = symbols[((int)((d+1)*Math.random()))];
    slotThree = symbols[((int)((d+1)*Math.random()))];
  }

  // Getters(accessor methods) and setters(mutator methods) for the instance variables
  //Gets the value for slot machine one (accessor method)
  public String getSlotOne() {
    return slotOne;
  }

  //Gets the value for slot machine two (accessor method)
  public String getSlotTwo() {
    return slotTwo;
  }

  //Gets the value for slot machine three (accessor method)
  public String getSlotThree() {
    return slotThree;
  }

  //Sets the value for slot machine one (mutator method)
  public void setSlotOne(String slotOne) {
    if (slotOne.equalsIgnoreCase(symbols[0]) || slotOne.equalsIgnoreCase(symbols[1]) || slotOne.equalsIgnoreCase(symbols[2]) || slotOne.equalsIgnoreCase(symbols[3])){
      //set the slot only if it is a valid symbol
      this.slotOne = slotOne;
    }
    else{
      //throw exception if the symbol is not valid
      throw new IllegalArgumentException("Invalid Symbol!");
    }
  }

  //Sets the value for slot machine two (mutator method)
  public void setSlotTwo(String slotTwo) {
    if (slotTwo.equalsIgnoreCase(symbols[0]) || slotTwo.equalsIgnoreCase(symbols[1]) || slotTwo.equalsIgnoreCase(symbols[2]) || slotTwo.equalsIgnoreCase(symbols[3])){
      //set the slot only if it is a valid symbol
      this.slotTwo = slotTwo;
    }
    else{
      //throw exception if the symbol is not valid
      throw new IllegalArgumentException("Invalid Symbol!");
    }
  }

  //Sets the value for slot machine three (mutator method)
  public void setSlotThree(String slotThree) {
    if (slotThree.equalsIgnoreCase(symbols[0]) || slotThree.equalsIgnoreCase(symbols[1]) || slotThree.equalsIgnoreCase(symbols[2]) || slotThree.equalsIgnoreCase(symbols[3])){
      //set the slot only if it is a valid symbol
      this.slotThree = slotThree;
    }
    else{
      //throw exception if the symbol is not valid
      throw new IllegalArgumentException("Invalid Symbol!");
    }
  }

  //Checks if all three slots have the same symbol and returns a corresponding result message.
  public String checkWin() {
    if (slotOne.equals(slotTwo) && slotTwo.equals(slotThree)) {
      //If all three slots have the same symbol, return a win message
      return "You Win!";
    }
    else{
      //return a lose message if all three slots do not have the same symbol
      return "You Lose!";
    }
  }

  //Generates a string representation of the slot machine with the symbols in each slot and the result message
  public String toString(){
    return "|" + slotOne + "|      |" + slotTwo + "|      |" + slotThree + "|\n\n"+checkWin();
  }
}
