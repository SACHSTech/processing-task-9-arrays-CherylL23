import processing.core.PApplet;

public class Sketch extends PApplet {
	
  //variables for the snow
  float[] circleY = new float[30];
  float[] circleX = new float[30];
  boolean[] ballHideStatus = new boolean[30];
  
  //varaibles for the player
  float playerX = 200;
  float playerY = 200;
  int healthBar = 3;
  boolean conditionHealthy = true;

  public void settings() {

    size(400, 400);
  }

  public void setup() {
    background(49, 31, 191);

    for (int i = 0; i < circleX.length; i++) {
      circleX[i] = random(width);
    }

    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
    }
  }

   /**
   * Computes out shapes that are coded within the program. It creates a game where the player must avoid the snowballs.
   */
 
  public void draw() {
	

   background(49, 31, 191);
   
    for (int i = 0; i < circleY.length; i++) {

      if (ballHideStatus[i] == false){

       //snowballs
        circleX[i] = (float) (width * i / circleY.length);
       fill(255);
       ellipse(circleX[i], circleY[i], 25, 25);
      }
  
      circleY[i] ++;

      if (circleY[i] > height) {
        circleY[i] = 0;
        circleX[i] = random(width);
      }

      // snowfall speed
      if (keyPressed) {
        if (keyCode == UP) {
          circleY[i] += 3;
        }
         else if (keyCode == DOWN) {
         circleY[i] -= 0.7;
        }
      }

      //player's icon
      fill(79, 176, 255);
      ellipse(playerX, playerY, 30, 30);

      //detects for any player collision with the snowball  
      if (dist(playerX, playerY, circleX[i], circleY[i]) <= 25 && ballHideStatus[i] == false){
        healthBar --;
        ballHideStatus[i] = true;
      } 
    } 

    //displays the amount of health the player has left
    for (int h = 0; h < healthBar; h++) {
      fill(30, 201, 147);
      rect(300 + h * 35, 30, 25, 25);
    }

    //finds out whether the player has lost all of their health. If true, the player gets a white screen
    if (healthBar == 1) {
     conditionHealthy = false;
    }
    else if (conditionHealthy == false){
     background(255);
    }  
  }

  /**
  * the movements of the player when the keys are pressed
  */
  public void keyPressed() {

   if (key == 'w') {
      playerY -= 3;
     } 
    else if (key == 's') {
      playerY += 3;
    } 
    else if(key == 'a'){
      playerX -= 3;
    }
    else if(key == 'd'){
      playerX += 3; 
    }
    
  }

  /**
  * figures out whether the snowball is clicked on. if it is clicked snowball is gone
  */
  public void mousePressed() {
    for (int i = 0; i < circleY.length; i++) {

      if (dist(mouseX, mouseY, circleX[i], circleY[i]) <= 25){
        ballHideStatus[i] = true;
        circleY[i] = 0;
        circleX[i] = random(width);
      }
    }
  }
}