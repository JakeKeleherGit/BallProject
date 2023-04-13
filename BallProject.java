import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
import javax.swing.*;

public class BallProject extends Application
{
   //instantiate all of the variables i will need for my BallProject class
   GraphicsContext gc;
   private GridPane gp;
   private GamePane [][]panes = new GamePane[4][4];
   private GamePane theGame;
   private int ballsLeft = 15;
   private int possibleMoves = 2;
   private Label ballMoveLabel;
   
   //make the start method
   public void start(Stage stage)
   {
   //creates a border pane and gridpane
   BorderPane root = new BorderPane();
   gp = new GridPane();
      
   //creates the label
   ballMoveLabel = new Label("Balls left: " +ballsLeft+ "   Possible moves: "+possibleMoves);
   
   //sets the alignment of the label
   BorderPane.setAlignment(ballMoveLabel, Pos.TOP_CENTER);
   root.setTop(ballMoveLabel);
   
      //create the double for loop
      for(int i = 0; i < 4; i++)
      {
         for(int j = 0; j < 4; j++)
         {
         
         theGame = new GamePane();
         gp.add(theGame, i, j);
         panes[i][j] = theGame;
      
         if(i == 0 && j == 2)
            {
            panes[i][j].setBallVisible(false);
            }
         }   
      }
         //check if you can move here and then do the draw method
         checkIfCanMove();      
         for(int i = 0; i<4; i++)
         {
            for(int j = 0; j < 4; j++)
            {
            panes[i][j].draw();
            }
         }
   //center the gridpane
   root.setCenter(gp);
   gp.setAlignment(Pos.CENTER);
   
   //creates the scene of the label
   Scene scene = new Scene(root, 600, 600); 
   stage.setScene(scene);
   stage.setTitle("Ball Game");
   stage.show();

   }
   
   //make the counter method so i will know the label and they will change
   private void counter()
   {
   //instantiate the balls and possible moves
   int ballsLeftTemporary = 0;
   int possibleMovesTemporary = 0;
   for(int i = 0; i < 4; i++)
   {
      for(int j = 0; j < 4; j++)
      {
         //print out the new numbers every time
         ballMoveLabel.setText("Balls left: " +ballsLeftTemporary+ "   Possible moves: "+possibleMovesTemporary);
         //these 4 below will see if the buttons are visible, and if so, they will add one to the possibleMovesTemporary
         if(panes[i][j].top.isVisible())
         {
         possibleMovesTemporary++;
         }
         if(panes[i][j].bottom.isVisible())
         {
         possibleMovesTemporary++;
         }
         if(panes[i][j].left.isVisible())
         {
         possibleMovesTemporary++;
         }
         if(panes[i][j].right.isVisible())
         {
         possibleMovesTemporary++;
         }
         //if the balls are visible, it will add that to how many balls are left
         if(panes[i][j].isBallVisible())
         {
         ballsLeftTemporary++;
         }
         //make the if statements so if you win or lose
         if(possibleMovesTemporary == 0)
         {
         ballMoveLabel.setText("You LOSE!");
         }
         if(ballsLeftTemporary == 1)
         {
         ballMoveLabel.setText("You WIN!");
         }
         
      }
   }
   }
   
   //this is the check if you can move 
   private void checkIfCanMove()
   {
   for(int i = 0; i < 4; i++)
   {
      for(int j = 0; j < 4; j++)
      {
      //bottom
      if(j >= 2 && panes[i][j-1].isBallVisible() && !panes[i][j-2].isBallVisible())
         {
         panes[i][j].setBottomButtonVisible(true);
         }
         else
         {
         panes[i][j].setBottomButtonVisible(false);
         }
      //top
      if(j <= 1 && panes[i][j+1].isBallVisible() && !panes[i][j+2].isBallVisible())
         {
         panes[i][j].setTopButtonVisible(true);
         }
         else
         {
         panes[i][j].setTopButtonVisible(false);
         }
      //right
      if(i >= 2 && panes[i-1][j].isBallVisible() && !panes[i-2][j].isBallVisible())
         {
         panes[i][j].setRightButtonVisible(true);
         }
         else
         {
         panes[i][j].setRightButtonVisible(false);
         }
         //left
      if(i <= 1 && panes[i+1][j].isBallVisible() && !panes[i+2][j].isBallVisible())
         {
         panes[i][j].setLeftButtonVisible(true);
         }
         else
         {
         panes[i][j].setLeftButtonVisible(false);
         } 
       } 
     } 
   }
   
   //create the button listener 
   public class ButtonListener implements EventHandler<ActionEvent>  
   {
      public void handle(ActionEvent e) 
      {
      for(int i = 0; i < 4; i++)
      {
         for(int j = 0; j < 4; j++)
         {
         //for all of these, connect the click method
         if (panes[i][j].top == e.getSource()) 
         {
            click(i, j, "top");
         } 
         else if (panes[i][j].bottom == e.getSource()) 
         {
            click(i, j, "bottom");
         } 
         else if (panes[i][j].left == e.getSource()) 
         {
            click(i, j, "left");
         } 
         else if (panes[i][j].right == e.getSource()) 
         {
            click(i, j, "right");
         }
         
         }  
      }
      
      }
   }
   
   //this is the click method
   public void click(int i, int j, String direction)
   {
    GamePane currentPane = panes[i][j];
    GamePane nextPane = null;
    //a switch case statement that makes sure it works for each top, bottom, left, right
    switch (direction) 
    {
        case "top":
            nextPane = panes[i][j+2];
            panes[i][j+1].setBallVisible(false);
            nextPane.setBallVisible(true);
            break;
            
        case "bottom":
            nextPane = panes[i][j-2];
            panes[i][j-1].setBallVisible(false);
            nextPane.setBallVisible(true);
            break;
            
        case "left":
            nextPane = panes[i+2][j];
            panes[i+1][j].setBallVisible(false);
            nextPane.setBallVisible(true);
            break;
            
        case "right":
            nextPane = panes[i-2][j];
            panes[i-1][j].setBallVisible(false);
            nextPane.setBallVisible(true);
            break;
    }
    currentPane.setBallVisible(false);
    checkIfCanMove();
    counter();
   } 

public class GamePane extends GridPane
{
   
   GraphicsContext gc;
   Canvas theCanvas = new Canvas();
   
   //set all of the buttons and balls to a true or false
   private boolean ballVisible = true;
   private boolean leftButtonVisible = false;
   private boolean rightButtonVisible = false;
   private boolean topButtonVisible = false;
   private boolean bottomButtonVisible = false;
   
   //creates the buttons for the top left right and bottom
   Button left;
   Button right;
   Button top;
   Button bottom;

   //the constructor
   public GamePane()
   {
   left = new Button();
   right = new Button();   
   top = new Button();
   bottom = new Button();
   
   //set all of the buttons on action
   top.setOnAction(new ButtonListener());
   bottom.setOnAction(new ButtonListener());
   right.setOnAction(new ButtonListener());
   left.setOnAction(new ButtonListener());
   
   left.setPrefSize(20,80);
   right.setPrefSize(20,80);
   top.setPrefSize(80,20);
   bottom.setPrefSize(80,20);
   
   add(top,1,0);
   add(bottom,1,2);
   add(left,0,1);
   add(right,2,1);
   
   theCanvas.setWidth(80);
   theCanvas.setHeight(80);
   updateButtonsVisibility();
   
   add(theCanvas, 1, 1);
   }
    //create the getter for is ball visible
    public boolean isBallVisible()
    {
    return ballVisible;
    }
    
    //set ball visible setter
    public void setBallVisible(boolean ballVisible)
    {
    this.ballVisible = ballVisible;
    draw();
    updateButtonsVisibility();
    }
    
    //getter for the left button
    public boolean isLeftButtonVisible()
    {
    return leftButtonVisible;
    }
    
    //setter for the left button
    public void setLeftButtonVisible(boolean leftButtonVisible)
    {
    this.leftButtonVisible = leftButtonVisible;
    updateButtonsVisibility();
    }
    
    //getter for the right button
    public boolean isRightButtonVisible()
    {
    return rightButtonVisible;
    }
    
    //setter for the right button
    public void setRightButtonVisible(boolean rightButtonVisible)
    {
    this.rightButtonVisible = rightButtonVisible;
    updateButtonsVisibility();
    }
    
    //getter for the top button
    public boolean isTopButtonVisible()
    {
    return topButtonVisible;
    }
    
    //setter for the top button
    public void setTopButtonVisible(boolean topButtonVisible)
    {
    this.topButtonVisible = topButtonVisible;
    updateButtonsVisibility();
    }
    
    //getter for the bottom button
    public boolean isBottomButtonVisible()
    {
    return bottomButtonVisible;
    }
    
    //setter for the bottom button
    public void setBottomButtonVisible(boolean bottomButtonVisible)
    {
    this.bottomButtonVisible = bottomButtonVisible;
    updateButtonsVisibility();
    }
      //make a method to update the buttons viisbility
       private void updateButtonsVisibility()
       {
        if(ballVisible)
          {
          left.setVisible(isLeftButtonVisible());
          right.setVisible(isRightButtonVisible());
          top.setVisible(isTopButtonVisible());
          bottom.setVisible(isBottomButtonVisible());
          } 
        else 
          {
          left.setVisible(false);
          right.setVisible(false);
          top.setVisible(false);
          bottom.setVisible(false);
         }
     }
    
    //the draw method to draw the circle
    public void draw()
    {
    gc = theCanvas.getGraphicsContext2D();
    gc.clearRect(0,0,theCanvas.getWidth(),theCanvas.getHeight());
    
    if(ballVisible)
       {
       gc.setFill(Color.LIMEGREEN);
       gc.fillOval(0,0,80,80);
       gc.setFill(Color.GREEN);
       gc.fillOval(10,10,60,60);
       gc.setFill(Color.CYAN);
       gc.fillOval(20,20,40,40);
       gc.setFill(Color.BLUE);
       gc.fillOval(30,30,20,20);
       }
      }
}

 public static void main(String[] args)
   {
      launch(args);
   }


}
