/*
Name: Mayan S
Date: January 8, 2023
*/

//import packages and folders
import pet.Dog;
import statisticsManager.Stats;
import gamble.SlotMachine;
import counters.ActionCounter;
import java.io.*;
import java.util.*;


//Main Class For Input and Output
public class Main {
    // main method
    public static void main(String[] args) {
        // Variable Declaration
        int choice, slotDifficulty;
        String event, proceed, dogName;


        // Constant Strings
        final String HAPPYDOG = "     |\\_/|\n" + "     | @ @   Woof!\n" + "     |   <>              _\n"
                + "     |  _\\/------____ ((| |))\n" + "     |               `--' |\n" + " ____|_       ___|   |___.'\n"
                + "/_/_____/____/_______|\n",
                APATHETICDOG = "     |\\_/|\n" + "     | @ @   *Ahem*\n" + "     |   <>              _\n"
                        + "     |  _\\-/-----____ ((| |))\n" + "     |               `--' |\n"
                        + " ____|_       ___|   |___.'\n" + "/_/_____/____/_______|\n",
                SADDOG = "     |\\_/|\n" + "     | @ @   *Whimper*\n" + "     |   <>              _\n"
                        + "     |  _/\\------____ ((| |))\n" + "     |               `--' |\n"
                        + " ____|_       ___|   |___.'\n" + "/_/_____/____/_______|\n";


        // Scanner for user input
        Scanner input = new Scanner(System.in);


        do {
            // Instructions Output
            instructions();


            // User inputs the dog's name
            dogName = input.next();


            // Create an ActionCounter object to keep track of user actions
            ActionCounter actionCounter = new ActionCounter();


            // Create a new Dog object with initial parameters
            Dog dog = new Dog(dogName, HAPPYDOG);


            // Create a new ArrayList to store the dog's events and statistics over a long
            // period of time
            ArrayList<Stats> statsSaver = new ArrayList<Stats>();
            statsSaver.add(
                    new Stats(randomEvent(dog), dog.getAge(), dog.getHunger(), dog.getHappiness(), dog.getEnergy()));


            // Main simulation loop
            do {
                // Display main menu and get user's choice
                do {
                    try {
                        // clear screen
                        System.out.print("\033\143");


                        // main menu output method
                        mainMenu(dog);


                        // user input
                        choice = input.nextInt();
                    } catch (InputMismatchException e) {
                        // Clear The Scanner
                        input.next();


                        // reset the choice value
                        choice = 0;
                    } // in case user inputs a non-integer value
                } while (choice > 6 || choice < 1);// loop if value is invalid


                // Performing the selected action
                if (choice == 1) {
                    // increasing hunger method
                    increasingHunger(dog);


                    // incrementing feedCount
                    actionCounter.setFeedCount(actionCounter.getFeedCount() + 1);
                } else if (choice == 2) {
                    // increasing happiness method
                    increasingHappiness(dog);


                    // incrementing playCount
                    actionCounter.setPlayCount(actionCounter.getPlayCount() + 1);
                } else if (choice == 3) {
                    // increasing energy method
                    increasingEnergy(dog);


                    // incrementing restCount
                    actionCounter.setRestCount(actionCounter.getRestCount() + 1);
                } else if (choice == 4) {
                    do {
                        // clear screen
                        System.out.print("\033\143");


                        try {
                            // slot instructions output method
                            slotInstructions();


                            // user input
                            System.out.print("Would you like to play easy, medium, or hard? (1, 2, or 3): ");
                            slotDifficulty = input.nextInt();
                        } catch (InputMismatchException e) {
                            // Clear The Scanner
                            input.next();


                            // reset the slotDifficulty value
                            slotDifficulty = 0;
                        }
                    } while (slotDifficulty > 3 || slotDifficulty < 1);


                    // creating slotMachine object
                    SlotMachine slotMachine = new SlotMachine(slotDifficulty);


                    // outputting slot machine results
                    System.out.println(slotMachine);


                    // increasing attributes if the user wins
                    if (slotMachine.checkWin().equalsIgnoreCase("You Win!")) {
                        prizeWinnings(dog, slotDifficulty);
                    }


                    // incrementing gameCount
                    actionCounter.setGameCount(actionCounter.getGameCount() + 1);


                    // User input to continue
                    System.out.print("\nEnter Any Key to Continue!: ");
                    proceed = input.next();
                } else if (choice == 5) {
                    // display current stats and events
                    System.out.println(dog);
                    System.out.println("Today's Events: " + statsSaver.get(statsSaver.size() - 1).getEvent());


                    // User input to continue
                    System.out.print("\nEnter Any Key to Continue!: ");
                    proceed = input.next();
                } else {
                    dog.setHunger(0);
                    dog.setEnergy(0);
                    dog.setHappiness(0);
                }


                // adjust and record attributes after each action
                if (choice != 5 && choice != 6) {
                    // method for naturally decreasing attributes
                    naturalAging(dog);


                    // acquiring today's event
                    event = randomEvent(dog);


                    // Method for ensuring values don't surpass 100
                    valueAdjustment(dog);


                    // recording the stats and events for the current day into an array list
                    statsSaver
                            .add(new Stats(event, dog.getAge(), dog.getHunger(), dog.getHappiness(), dog.getEnergy()));


                    // method for updating the appearance of the dog according to the new attributes
                    setAppearance(dog, HAPPYDOG, APATHETICDOG, SADDOG);


                    // method for delaying the time between input, and going back to the main menu
                    delayTime();
                }


            } while (dog.getHunger() > 0 && dog.getEnergy() > 0 && dog.getHappiness() > 0);// end loop if any of the
                                                                                           // values are below 0


            // Setting the last event to "The Dog Died!"
            statsSaver.get(statsSaver.size() - 1).setEvent("The Dog Died!");


            // clear screen
            System.out.print("\033\143");


            // method for displaying the user's score, and the high score
            scoreResults(dog);


            // Writing the final score to a text file
            writeToFile(dog.getAge());


            // User input to continue
            System.out.print("\nEnter Any Key to Continue!: ");
            proceed = input.next();


            // clear screen
            System.out.print("\033\143");


            // method for displaying how many times each action was performed
            actionResults(actionCounter);


            // User input to continue
            System.out.print("\nEnter Any Key to Continue!: ");
            proceed = input.next();


            // clear screen
            System.out.print("\033\143");


            // method for displaying all the events that happened to the dog
            eventResults(statsSaver);


            // User input to continue
            System.out.print("\nEnter Any Key to Continue!: ");
            proceed = input.next();


            // clear screen
            System.out.print("\033\143");


            // method for displaying the dog's hunger attribute over time, on a scatter plot
            hungerScatterPlot(statsSaver);


            // User input to continue
            System.out.print("\nEnter Any Key to Continue!: ");
            proceed = input.next();


            // clear screen
            System.out.print("\033\143");


            // method for displaying the dog's happiness attribute over time, on a scatter
            // plot
            happinessScatterPlot(statsSaver);


            // user input to continue
            System.out.print("\nEnter Any Key to Continue!: ");
            proceed = input.next();


            // clear screen
            System.out.print("\033\143");


            // method for displaying the dog's energy attribute over time, on a scatter plot
            energyScatterPlot(statsSaver);


            // user input to continue
            System.out.print("\nEnter Any Key to Continue!: ");
            proceed = input.next();


            // clear screen
            System.out.print("\033\143");


            // User input to play again
            do {
                System.out.print("Play again? (yes/no): ");
                proceed = input.next();
                // clear screen
                if (!proceed.equalsIgnoreCase("yes") && !proceed.equalsIgnoreCase("no")) {
                    System.out.print("\033\143");
                }
            } while (!proceed.equalsIgnoreCase("yes") && !proceed.equalsIgnoreCase("no"));// Loop In Case Of Invalid
                                                                                          // Input


            // clear the saved data for the current dog
            statsSaver.clear();


            // clear screen
            System.out.print("\033\143");
        } while (proceed.equalsIgnoreCase("yes"));// end loop if the user chooses to quit


        // close the scanner
        input.close();
    }


    // Method that displays a scatter plot showing the relationship between the
    // dog's age and its hunger level over time.
    public static void hungerScatterPlot(ArrayList<Stats> statsSaver) {
        // Title output
        System.out.print("Hunger Scatter Plot: (x-axis: Days Passed) (y-axis: Hunger Level)\n");


        // 2-d array declaration and instantiation based on a y-axis that goes down by 5
        // points, and an x-axis that goes up by 1 day
        String[][] scatterPlot = new String[20][statsSaver.size()];


        // Variable constants to store the size of the array
        final int ROW = scatterPlot.length;
        final int COL = scatterPlot[0].length;


        // filling each element of the array with a space
        for (int y = 0; y < ROW; y++) {
            for (int x = 0; x < COL; x++) {
                scatterPlot[y][x] = " ";
            }
        }


        // filling the array with dots based on the data saved in the array list
        for (Stats s : statsSaver) {
            scatterPlot[Math.min((20 - (int) (s.getHunger() / 5)), 19)][s.getAge() - 1] = "•";
        }


        // Scatter Plot Output
        for (int y = 0, scale = 100; y < ROW; y++, scale -= 5) {
            // outputting y-axis scale
            System.out.print("\n" + scale + "\t⎹");


            // Output Results Of The Grid For The Corresponding Column
            for (int x = 0; x < COL; x++) {
                System.out.print(scatterPlot[y][x] + "  ");
            }
        }


        // spacing output
        System.out.print("\n\t ");


        // x-axis output
        for (int x = 0; x < statsSaver.size() * 3; x++) {
            System.out.print("⎺");
        }


        // spacing output
        System.out.print("\n\t ");


        // x-axis scale output
        for (int x = 1; x <= statsSaver.size(); x++) {
            System.out.printf("%-3d", x);
        }


        // spacing output
        System.out.print("\n");
    }


    // Method that displays a scatter plot showing the relationship between the
    // dog's age and its happiness level over time.
    public static void happinessScatterPlot(ArrayList<Stats> statsSaver) {
        // Title output
        System.out.print("Happiness Scatter Plot: (x-axis: Days Passed) (y-axis: Happiness Level)\n");


        // 2-d array declaration and instantiation based on a y-axis that goes down by 5
        // points, and an x-axis that goes up by 1 day
        String[][] scatterPlot = new String[20][statsSaver.size()];


        // Variable constants to store the size of the array
        final int ROW = scatterPlot.length;
        final int COL = scatterPlot[0].length;


        // filling each element of the array with a space
        for (int y = 0; y < ROW; y++) {
            for (int x = 0; x < COL; x++) {
                scatterPlot[y][x] = " ";
            }
        }


        // filling the array with dots based on the data saved in the array list
        for (Stats s : statsSaver) {
            scatterPlot[Math.min((20 - (int) (s.getHappiness() / 5)), 19)][s.getAge() - 1] = "•";
        }


        // Scatter Plot Output
        for (int y = 0, scale = 100; y < ROW; y++, scale -= 5) {
            // outputting y-axis scale
            System.out.print("\n" + scale + "\t⎹");


            // Output Results Of The Grid For The Corresponding Column
            for (int x = 0; x < COL; x++) {
                System.out.print(scatterPlot[y][x] + "  ");
            }
        }


        // spacing output
        System.out.print("\n\t ");


        // x-axis output
        for (int x = 0; x < statsSaver.size() * 3; x++) {
            System.out.print("⎺");
        }


        // spacing output
        System.out.print("\n\t ");


        // x-axis scale output
        for (int x = 1; x <= statsSaver.size(); x++) {
            System.out.printf("%-3d", x);
        }


        // spacing output
        System.out.print("\n");
    }


    // Method that displays a scatter plot showing the relationship between the
    // dog's age and its energy level over time.
    public static void energyScatterPlot(ArrayList<Stats> statsSaver) {
        // Title output
        System.out.print("Energy Scatter Plot: (x-axis: Days Passed) (y-axis: Energy Level)\n");


        // 2-d array declaration and instantiation based on a y-axis that goes down by 5
        // points, and an x-axis that goes up by 1 day
        String[][] scatterPlot = new String[20][statsSaver.size()];


        // Variable constants to store the size of the array
        final int ROW = scatterPlot.length;
        final int COL = scatterPlot[0].length;


        // filling each element of the array with a space
        for (int y = 0; y < ROW; y++) {
            for (int x = 0; x < COL; x++) {
                scatterPlot[y][x] = " ";
            }
        }


        // filling the array with dots based on the data saved in the array list
        for (Stats s : statsSaver) {
            scatterPlot[Math.min((20 - (int) (s.getEnergy() / 5)), 19)][s.getAge() - 1] = "•";
        }


        // Scatter Plot Output
        for (int y = 0, scale = 100; y < ROW; y++, scale -= 5) {
            // outputting y-axis scale
            System.out.print("\n" + scale + "\t⎹");


            // Output Results Of The Grid For The Corresponding Column
            for (int x = 0; x < COL; x++) {
                System.out.print(scatterPlot[y][x] + "  ");
            }
        }


        // spacing output
        System.out.print("\n\t ");


        // x-axis output
        for (int x = 0; x < statsSaver.size() * 3; x++) {
            System.out.print("⎺");
        }


        // spacing output
        System.out.print("\n\t ");


        // x-axis scale output
        for (int x = 1; x <= statsSaver.size(); x++) {
            System.out.printf("%-3d", x);
        }


        // spacing output
        System.out.print("\n");
    }


    // Method that displays the events that occurred to the dog over time
    public static void eventResults(ArrayList<Stats> statsSaver) {
        // Context Output
        System.out.println("Here are all the things that happened during the dog's life:");


        // Displaying each day and it's corresponding event
        for (Stats s : statsSaver) {
            System.out.println("\nDay " + s.getAge() + ": " + s.getEvent());
        }
    }


    // Method that displays how many times you conducted each action
    public static void actionResults(ActionCounter actionCounter) {
        System.out.println("You Fed The Dog " + actionCounter.getFeedCount() + " Time(s)!");
        System.out.println("You Played With The Dog " + actionCounter.getPlayCount() + " Time(s)!");
        System.out.println("You Rested The Dog " + actionCounter.getRestCount() + " Time(s)!");
        System.out.println("You Played The Slot Machine " + actionCounter.getGameCount() + " Time(s)!");
    }


    // Method that displays your current score, and the highest score anyone has
    // ever achieved
    public static void scoreResults(Dog dog) {
        // Outputting the user's score
        System.out.println("Your Score: " + dog.getAge());


        // Outputting the highscore
        System.out.println("High Score: " + readFromFile());


        if (dog.getAge() >= readFromFile()) {
            // Output Congratulations statement if the user achieves the highscore
            System.out.println("Congratulations! You beat the high score!");
        } else {
            // Output Better luck next time statement if the user does not achieve the
            // highscore
            System.out.println("Better luck next time!");
        }
    }


    // Method that writes the user's final score to a text file
    public static void writeToFile(int age) {
        try {
            // FileWriter and PrintWriter Object
            FileWriter fw = new FileWriter("scoreTracker.txt", true);
            PrintWriter pw = new PrintWriter(fw);


            // Writing Results To File
            pw.println(age);


            // Closing Print Writer
            pw.close();
        } catch (IOException err) {
            System.out.println("Error Writing To File!");
        } // Catch Input Output Exception In Case Of Error
    }


    // Method that reads the text file and returns the highest score achieved
    public static int readFromFile() {
        // variables to store the line being read, as well as the highscore
        String line;
        int highScore = 0, scoreNum = 0;


        try {
            // File Reader and Buffered Reader Object
            FileReader fr = new FileReader("scoreTracker.txt");
            BufferedReader br = new BufferedReader(fr);


            // Read first line
            line = br.readLine();


            // looping through the text file
            while (line != null) {
                // Converting the current line to a number
                try {
                    scoreNum = Integer.parseInt(line);
                } catch (NumberFormatException err) {
                    // Error message if the line is not a number
                    System.out.println("Error Converting To Number!");
                }


                // Setting the highscore to a new number if the current score being read if
                // higher than the previous highscore number
                if (scoreNum > highScore) {
                    highScore = scoreNum;
                }


                // read next line
                line = br.readLine();
            }


            // Closing Buffered Reader
            br.close();
        } catch (IOException err) {
            // Error Message if file can't be found
            System.out.println("Could not read from the file");
        }


        // returning the highest score in the text file
        return highScore;
    }


    // Method for Instructions Output
    public static void instructions() {
        System.out.print(
                "Welcome to the Dog Simulator!\nYou just rescued a senior dog! It is now your job to prolong this dog's life by taking care of them.\n\nFor each day, you will have the opportunity to conduct one of the following options...\n1. Feed the dog [Increases hunger level; may decrease happiness level]\n2. Play with the dog [Increases happiness level; may decrease energy level]\n3. Rest the dog [Increases energy level; may decrease hunger level]\n4. Gamble For a Prize [Win the slot machine to increase all attribute levels; may win nothing]\n\nHunger, happiness, and energy levels are ranked from 0-100!\nThese attribute levels are dictated by natural ageing, random events, and the user options provided above!\nKeep these values above 0 as long as possible to achieve the high score!\n\nEnter your dog's name: ");
    }


    // Method for Main Menu Output
    public static void mainMenu(Dog dog) {
        System.out.print("What do you want to do with your dog today?\nDay #" + dog.getAge()
                + "\n1. Feed\n2. Play\n3. Rest\n4. Gamble For a Prize\n5. View Current Stats & Events\n6. Sell Your Dog (Quit)\n\nEnter your choice (1, 2, 3, 4, 5, or 6): ");
    }


    // Method for slot instructions output
    public static void slotInstructions() {
        System.out.print(
                "~-~SLOTS~-~\n\nRules:\n------------------------------\nThree wheels will spin!\nIf all of the wheels land on the same symbol, you win the jackpot for your pet!\nLose, and you forfeit a day!\n\nLevels:\n------------------------------\nEasy[1] - Two symbols (+10 For All Attributes)\nMedium[2] - Three symbols (+20 For All Attributes)\nHard[3] - Four symbols (+30 For All Attributes)\n\n");
    }


    // Method delaying time between each action
    public static void delayTime() {
        // Output statement for moving to the next day
        System.out.print("Moving To The Next Day");


        // Output statement for the following dots
        for (int x = 0; x < 3; x++) {
            System.out.print(".");
            try {
                // Pause 450 milliseconds between each output
                Thread.sleep(450);
            } catch (InterruptedException e) {
                // Error message output if the delay fails
                System.out.println("Thread is interrupted");
            }
        }
    }


    // Method for naturally decreasing the dogs attributes, and increasing the dogs
    // age
    public static void naturalAging(Dog dog) {
        // Incrementing the dog's age by 1
        dog.setAge(dog.getAge() + 1);


        // decreasing the dog's hunger, happiness, and energy by a number between 1 and
        // 12
        dog.setHunger(dog.getHunger() - ((int) (12 * Math.random()) + 1));
        dog.setHappiness(dog.getHappiness() - ((int) (12 * Math.random()) + 1));
        dog.setEnergy(dog.getEnergy() - ((int) (12 * Math.random()) + 1));
    }


    // Method for increasing the dogs hunger
    public static void increasingHunger(Dog dog) {
        // Incrementing the dog's hunger by 15
        dog.setHunger(dog.getHunger() + 15);


        // Randomising either the number 0 or 1
        int random = (int) (Math.random() * 2);


        // If the random number is 0, the dog will lose happiness by 10
        if (random == 0) {
            dog.setHappiness(dog.getHappiness() - 10);
        }
    }


    // Method for increasing the dogs happiness
    public static void increasingHappiness(Dog dog) {
        // Incrementing the dog's happiness by 15
        dog.setHappiness(dog.getHappiness() + 15);


        // Randomising either the number 0 or 1
        int random = (int) (Math.random() * 2);


        // If the random number is 0, the dog will lose energy by 10
        if (random == 0) {
            dog.setEnergy(dog.getEnergy() - 10);
        }
    }


    // Method for increasing the dogs energy
    public static void increasingEnergy(Dog dog) {
        // Incrementing the dog's energy by 15
        dog.setEnergy(dog.getEnergy() + 15);


        // Randomising either the number 0 or 1
        int random = (int) (Math.random() * 2);


        // If the random number is 0, the dog will lose hunger by 10
        if (random == 0) {
            dog.setHunger(dog.getHunger() - 10);
        }
    }


    // Method for increasing the dogs hunger, energy, and happiness if the user wins
    // the slot
    public static void prizeWinnings(Dog dog, int slotDifficulty) {
        // calculating the amount of happiness, hunger, and energy gained based on the
        // difficulty the user chose
        int prize = slotDifficulty * 10;


        // Incrementing the dog's hunger, energy, and happiness by a specific amount
        dog.setHappiness(dog.getHappiness() + prize);
        dog.setEnergy(dog.getEnergy() + prize);
        dog.setHunger(dog.getHunger() + prize);
    }


    // Method for randomising which event will occur on a given day
    public static String randomEvent(Dog dog) {
        // randomly generating a number between 0 and the dog's age (age-1); this allows
        // specific events to be unlocked later in life
        int random = (int) (Math.random() * dog.getAge());


        if (random >= 30) {
            // if the random number is greater than 30, divide the happiness by 10, and
            // divide the energy by 10
            dog.setEnergy((int) (dog.getEnergy() / 10));
            dog.setHappiness((int) (dog.getHappiness() / 10));


            // return statement for the event
            return "The Dog Got Struck By a Car!";
        } else if (random == 20 || random == 22 || random == 26 || random == 28) {
            // if the random number is 20, 22, 26, or 28, divide the energy by 4
            dog.setEnergy((int) (dog.getEnergy() / 4));


            // return statement for the event
            return "The Dog Had Kids!";
        } else if (random == 2 || random == 12) {
            // if the random number is 2 or 12, multiply the happiness by 2, and subtract
            // the energy by 20
            dog.setHappiness(dog.getHappiness() * 2);
            dog.setEnergy(dog.getEnergy() - 20);


            // return statement for the event
            return "The Dog Got To Play With The Neighbours Pet!";
        } else if (random == 3 || random == 13) {
            // if the random number is 3 or 13, subtract the hunger by 20
            dog.setHunger(dog.getHunger() - 20);


            // return statement for the event
            return "The Dog Had Food Poisoning!";
        } else if (random == 4 || random == 14) {
            // if the random number is 4 or 14, multiply the happiness by 2
            dog.setHappiness(dog.getHappiness() * 2);


            // return statement for the event
            return "The Dog Received a Gift!";
        } else if (random == 5 || random == 15) {
            // if the random number is 5 or 15, multiply the energy by 2, and subtract the
            // happiness by 20
            dog.setHappiness(dog.getHappiness() - 20);
            dog.setEnergy(dog.getEnergy() * 2);


            // return statement for the event
            return "The Dog Hated His Visit To The Veterinary, But Got a Rush Of Adrenaline!";
        } else if (random == 6 || random == 16) {
            // if the random number is 6 or 16, multiply the hunger by 2
            dog.setHunger(dog.getHunger() * 2);


            // return statement for the event
            return "You Made Some Amazing Dog Food!";
        } else {
            // return statement for the event
            return "Nothing happened today!";
        }
    }


    // Method for adjusting the dogs attributes and ensuring they are 100 or below
    public static void valueAdjustment(Dog dog) {
        dog.setHappiness(Math.min(dog.getHappiness(), 100));
        dog.setEnergy(Math.min(dog.getEnergy(), 100));
        dog.setHunger(Math.min(dog.getHunger(), 100));
    }


    // Method for setting the dog's appearance based on its attributes
    public static void setAppearance(Dog dog, String happyDog, String apatheticDog, String sadDog) {
        if ((dog.getHunger() + dog.getHappiness() + dog.getEnergy()) > 200) {
            // if the 3 attributes add to a number greater than 200, set the dog's
            // appearance to the happy dog
            dog.setAppearance(happyDog);
        } else if ((dog.getHunger() + dog.getHappiness() + dog.getEnergy()) > 100) {
            // if the 3 attributes add to a number greater than 100 and less than 200, set
            // the dog's appearance to the apathetic dog
            dog.setAppearance(apatheticDog);
        } else {
            // if the 3 attributes add to a number less than 100, set the dog's appearance
            // to the sad dog
            dog.setAppearance(sadDog);
        }
    }
}
