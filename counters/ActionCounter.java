//Folder that this file is located in
package counters;

//ActionCounter Class That Counts How Many Times An Action Is Done
public class ActionCounter{
  // Instance variables for the number of times fed, played, rested, and gambled
  private int feedCount;
  private int playCount;
  private int restCount;
  private int gameCount;

  // Default constructor to initialize instance variables with default values
  public ActionCounter(){
    this.feedCount = 0;
    this.playCount = 0;
    this.restCount = 0;
    this.gameCount = 0;
  }

  // Getters(accessor methods) and setters(mutator methods) for the instance variables
  // Gets the number of times the dog was fed
  public int getFeedCount() {
    return feedCount;
  }

  //Sets the number of times the dog was fed
  public void setFeedCount(int feedCount) {
    if(feedCount>0){
      //feedCount is only set if it is above 0
      this.feedCount = feedCount;
    }
    else{
      //throw exception if the feedCount is set to a number below 0
      throw new IllegalArgumentException("feedCount is out of bounds!");
    }
  }

  // Gets the number of times the dog was played with
  public int getPlayCount() {
    return playCount;
  }

  //Sets the number of times the dog was played with
  public void setPlayCount(int playCount) {
    if(playCount>0){
      //playCount is only set if it is above 0
      this.playCount = playCount;
    }
    else{
      //throw exception if the playCount is set to a number below 0
      throw new IllegalArgumentException("playCount is out of bounds!");
    }
  }

  // Gets the number of times the dog was rested
  public int getRestCount() {
    return restCount;
  }

  //Sets the number of times the dog was rested
  public void setRestCount(int restCount) {
    if(restCount>0){
      //restCount is only set if it is above 0
      this.restCount = restCount;
    }
    else{
      //throw exception if the restCount is set to a number below 0
      throw new IllegalArgumentException("restCount is out of bounds!");
    }
  }

  // Gets the number of times the dog was rested
  public int getGameCount() {
    return gameCount;
  }

  //Sets the number of times the dog was rested
  public void setGameCount(int gameCount) {
    if(gameCount>0){
      //gameCount is only set if it is above 0
      this.gameCount = gameCount;
    }
    else{
      //throw exception if the gameCount is set to a number below 0
      throw new IllegalArgumentException("gameCount is out of bounds!");
    }
  }
}
