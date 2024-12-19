//Folder that this file is located in
package statisticsManager;

//Stats Class That Saves The Dogs Statistics For Later Use
public class Stats{
  // Instance variables for the dog's name, age, hunger, happiness, energy, and appearance
  private String event;
  private int age;
  private int hunger;
  private int happiness;
  private int energy;

  // Default constructor to initialize instance variables with default values
  public Stats(){
    this.event = "Nothing happened today!";
    this.age = 1;
    this.hunger = 100;
    this.happiness = 100;
    this.energy = 100;
  }

   // Constructor with parameters to set instance variables with specified values
  public Stats(String event, int age, int hunger, int happiness, int energy){
    if(event.equalsIgnoreCase("The Dog Got Struck By a Car!")||event.equalsIgnoreCase("The Dog Had Kids!")||event.equalsIgnoreCase("The Dog Got To Play With The Neighbours Pet!")||event.equalsIgnoreCase("The Dog Had Food Poisoning!")||event.equalsIgnoreCase("The Dog Received a Gift!")||event.equalsIgnoreCase("The Dog Hated His Visit To The Veterinary, But Got a Rush Of Adrenaline!")||event.equalsIgnoreCase("You Made Some Amazing Dog Food!")||event.equalsIgnoreCase("Nothing happened today!")||event.equalsIgnoreCase("The Dog Died!")){
      //Event is only set if it is a valid event
      this.event = event;
    }
    else{
      //Throw exception if the event is not valid
      throw new IllegalArgumentException("Invalid Event");
    }
    
    if(age>0){
      //Age is only set if it is above 0
      this.age = age;
    }
    else{
      //Throw exception if the age is set to a number below 0
      throw new IllegalArgumentException("Age is out of bounds!");
    }
    
    this.hunger = hunger;
    
    this.happiness = happiness;
    
    this.energy = energy;
  }

  // Getters(accessor methods) and setters(mutator methods) for the instance variables
  // Gets the age of the dog
  public int getAge() {
    return age;
  }

  //Sets the age of the dog
  public void setAge(int age) {
    if(age>0){
      //Age is only set if it is above 0
      this.age = age;
    }
    else{
      //throw exception if the age is set to a number below 1
      throw new IllegalArgumentException("Age is out of bounds!");
    }
  }

  // Gets the hunger level of the dog
  public int getHunger() {
    return hunger;
  }

  // Sets the hunger level of the dog
  public void setHunger(int hunger) {
    this.hunger = hunger;
  }

  // Gets the happiness level of the dog
  public int getHappiness() {
    return happiness;
  }

  // Sets the happiness level of the dog
  public void setHappiness(int happiness) {
    this.happiness = happiness;
  }

  // Gets the energy level of the dog
  public int getEnergy() {
    return energy;
  }

  // Set the energy level of the dog
  public void setEnergy(int energy) {
    this.energy = energy;
  }

  // Gets the current event for the dog
  public String getEvent() {
    return event;
  }

  // Sets the current event for the dog
  public void setEvent(String event) {
    if(event.equalsIgnoreCase("The Dog Got Struck By a Car!")||event.equalsIgnoreCase("The Dog Had Kids!")||event.equalsIgnoreCase("The Dog Got To Play With The Neighbours Pet!")||event.equalsIgnoreCase("The Dog Had Food Poisoning!")||event.equalsIgnoreCase("The Dog Recieved a Gift!")||event.equalsIgnoreCase("The Dog Hated His Visit To The Veterinary, But Got a Rush Of Adrenaline!")||event.equalsIgnoreCase("You Made Some Amazing Dog Food!")||event.equalsIgnoreCase("Nothing happened today!")||event.equalsIgnoreCase("The Dog Died!")){
      //Event is only set if it is a valid event
      this.event = event;
    }
    else{
      //Throw exception if the event is not valid
      throw new IllegalArgumentException("Invalid Event");
    }
  }
}
