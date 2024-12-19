//Folder that this file is located in
package pet; 

//Dog Class That Continuously Saves and Updates The Dogs Statistics
public class Dog {
  // Instance variables for the dog's name, age, hunger, happiness, energy, and appearance
  private String name;
  private String appearance;
  private int age;
  private int hunger;
  private int happiness;
  private int energy;

  //Default constructor that initializes the instance variables with default values.
  public Dog(){
    this.name = "Dog";
    this.age = 1;
    this.hunger = 100;
    this.happiness = 100;
    this.energy = 100;
    this.appearance="     |\\_/|\n     | @ @   Woof!\n     |   <>              _\n     |  _\\/------____ ((| |))\n     |               `--' |\n ____|_       ___|   |___.'\n/_/_____/____/_______|\n";
  }

  // Constructor that initializes the instance variables with specified values.
  public Dog(String name, String appearance) {
    this.name = name;
    
    this.age = 1;
    
    this.hunger = 100 - ((int)(12*Math.random())+1);
    
    this.happiness = 100 - ((int)(12*Math.random())+1);
    
    this.energy = 100 - ((int)(12*Math.random())+1);
    
    if(appearance.equalsIgnoreCase("     |\\_/|\n     | @ @   Woof!\n     |   <>              _\n     |  _\\/------____ ((| |))\n     |               `--' |\n ____|_       ___|   |___.'\n/_/_____/____/_______|\n")||appearance.equalsIgnoreCase("     |\\_/|\n     | @ @   *Ahem*\n     |   <>              _\n     |  _\\-/-----____ ((| |))\n     |               `--' |\n ____|_       ___|   |___.'\n/_/_____/____/_______|\n")||appearance.equalsIgnoreCase("     |\\_/|\n     | @ @   *Whimper*\n     |   <>              _\n     |  _/\\------____ ((| |))\n     |               `--' |\n ____|_       ___|   |___.'\n/_/_____/____/_______|\n")){
      //Setting the dog's appearance to one of the three specified types
      this.appearance = appearance;
    }
    else{
      //throwing exception if the dog has an invalid look
      throw new IllegalArgumentException("Invalid Appearance!");
    }
  }

  // Getter (accessor methods) and setters(mutator methods) for the instance variables
  // Gets the name of the dog
  public String getName() {
    return name;
  }

  //Sets the name of the dog
  public void setName(String name) {
    this.name = name;
  }

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
      //throw exception if the age is set to a number below 0
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

  // Sets the energy level of the dog
  public void setEnergy(int energy) {
    this.energy = energy;
  }

  // Gets the appearance of the dog
  public String getAppearance() {
    return appearance;
  }

  // Sets the appearance level of the dog
  public void setAppearance(String appearance) {
    if(appearance.equalsIgnoreCase("     |\\_/|\n     | @ @   Woof!\n     |   <>              _\n     |  _\\/------____ ((| |))\n     |               `--' |\n ____|_       ___|   |___.'\n/_/_____/____/_______|\n")||appearance.equalsIgnoreCase("     |\\_/|\n     | @ @   *Ahem*\n     |   <>              _\n     |  _\\-/-----____ ((| |))\n     |               `--' |\n ____|_       ___|   |___.'\n/_/_____/____/_______|\n")||appearance.equalsIgnoreCase("     |\\_/|\n     | @ @   *Whimper*\n     |   <>              _\n     |  _/\\------____ ((| |))\n     |               `--' |\n ____|_       ___|   |___.'\n/_/_____/____/_______|\n")){
      //Setting the dog's appearance to one of the three specified types
      this.appearance = appearance;
    }
    else{
      //throwing exception if the dog has an invalid look
      throw new IllegalArgumentException("Invalid Appearance!");
    }
  }

  //Instance Methods
  //Generates a graphical representation of the dog's hunger level.
  public String getHungerGraphic(){
    //start with a vertical bar
    String hungerGraphic = "|";

    //Add a dash for every 10 hunger points
    for(int x = 10; x<hunger; x+=10){
      hungerGraphic += "-";
    }

    //mark an X for the final point
    hungerGraphic+="X";

    //Add a space for every remaining 10 hunger points
    for(int x = 10; x<=(100-hunger); x+=10){
      hungerGraphic += " ";
    }

    //final vertical bar
    hungerGraphic += "|\n";

    //return the graphic
    return hungerGraphic;
  }

  //Generates a graphical representation of the dog's happiness level.
  public String getHappinessGraphic(){
    //Start with a vertical bar
    String happinessGraphic = "|";

    //Add a dash for every 10 happiness points
    for(int x = 10; x<happiness; x+=10){
      happinessGraphic += "-";
    }

    //mark an X for the final point
    happinessGraphic+="X";

    //Add a space for every remaining 10 happiness points
    for(int x = 10; x<=(100-happiness); x+=10){
      happinessGraphic += " ";
    }

    //Final vertical bar
    happinessGraphic += "|\n";

    //return the graphic
    return happinessGraphic;
  }

  //Generates a graphical representation of the dog's energy level.
  public String getEnergyGraphic(){
    //Start with a vertical bar
    String energyGraphic = "|";

    //Add a dash for every 10 energy points
    for(int x = 10; x<energy; x+=10){
      energyGraphic += "-";
    }

    //mark an X for the final point
    energyGraphic+="X";

    //Add a space for every remaining 10 energy points
    for(int x = 10; x<=(100-energy); x+=10){
      energyGraphic += " ";
    }

    //final vertical bar
    energyGraphic += "|\n";

    //return the graphic
    return energyGraphic;
  }

  //Returns a string representation of the dog's attributes.
  public String toString(){
    return "\nThis is " + getName() + ", your virtual dog.\n" + getAppearance() + "\nHunger: " + getHunger() + "/100\n"+getHungerGraphic()+"\nHappiness: " + getHappiness() + "/100\n" + getHappinessGraphic() + "\nEnergy: " + getEnergy() + "/100\n"+getEnergyGraphic();
  }
}
