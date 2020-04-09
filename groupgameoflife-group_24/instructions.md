[ptr'For your final group assignment before the project, you will build up the game of life!
I encourage you to spend some time playing around with the game here: https://bitstorm.org/gameoflife/

The rules of the game of life are as follows:
- A cell is either alive, or empty.
- If a living cell has 1 or 0 living neighbours, it dies.
- If a living cell has 4 or more neighbours, it dies.
- If a living cell has 2 or 3 neighbours, it remains alive.
- If an empty cell has exactly 3 living neighbours, it becomes alive.

In the assignment you will have to manage complex, but separate pieces of code:
- The GameOfLifeModel package will include the classes that implement the rules of the game
- The GameOfLifeTests package will include tests for the classes above.
- The GameOfLifeUI package will include the javafx application and the logic behind it.

Make sure to organize well as a team around these 3 objectives.

---
**What to submit:**
-------------------
- Update the Cell and CellGrid classes to implement the rules of the gameOfLife
- Write tests **for each method in Cell and CellGrid**, this includes constructors!
- Once you are confident that your logic is correct, start working on the UI:
    - The UICell class needs to define the behavior of the clickable cells 
    - Our Main class will organize our UI. As a requirement you must have:
        - at least 40 x 40 clickable cells for the game of life
        - A 'Next' button, which moves the simulation forward 1 step
        - A 'Reset' button, which removes all living cells from the grid.
- As usual, each team member should submit their individual readme on the work they've contributed and not breaking the 
honor code.

---

**Requirements and Hints: Game of Life model**

In the Cell class, notice that there are 2 fields: The currentState, and the futureState
This is because the current state of all cells should not be updated until you have determined the future state of all 
cells

In other words, if you see that a living cell should die, don't kill it immediately! it could still be the living 
neighbour of another cell!

In the grid class, you will want a constructor that takes a width and height, and create the right amount of cells.

One of the trickiest part of managing the rules of the game is checking how many living neighbours a given cell has.
If a cell is in position (i, j), it has at most 8 neighbours, which you can find by some basic math on i and j.

Note that I say **at most** Cells on the top row, or left column, or the corner have fewer neighbours, make sure to take 
such scenarios into consideration and test them accordingly.

Each method you write in both those classes should have thorough tests in the corresponding test classes.   
    
    
**Requirements and hints: Game of Life UI**
This is where you will get the most help, let's start with our Main class. 

You may use as many Panes or other resources from javafx as you want, but you **must** use javafx.

Your App should show the user the following:
    - At least a 40 x 40 grid of clickable cells. Clicking on a dead cell makes it alive, and vice versa.
    - A button for 'Next', which moves the simulation forward 1 step.
    - A button for 'Reset', which clears the grid of all alive cells.

You can lay them out whichever way you want, but make sure it looks nice!

To manipulate the cells, you should have a CellGrid object, and use methods of that object to update the state of cells.

Recall though that the CellGrid class know nothing about javafx or drawing. To connect the logical part of the game to 
the graphics, we will use the UICell class.

The UICell class extends the javafx class Rectangle, for which you can find the documentation here: https://docs.oracle.com/javafx/2/api/javafx/scene/shape/Rectangle.html 

This means that an object of this class will behave just the same as a Rectangle object, meaning you can set its dimensions,
set it's fill color (using the setFill method), or set up some code to execute whenever it is clicked. You can achieve that by following this sample code:

```
// When dealing with colors, make sure you import javafx.scene.paint.Color
Rectangle r = new Rectangle(50, 50, Color.RED) 
r.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent event) {
        // This will execute whenever this rectangle is pressed.
    }
});
```

Most importantly, this UICell object will have a cell object as one of its field. This will not be a new Cell, but rather
one of the cells from the cellGrid, it is in that object that you can keep track of the current and future state of your cell.

Most of the logic around the UICell will have to do with updating colors, the state of its inner cell, and dealing with clicks.

**Readmes** 

For your individual readmes, I expect the following:
- What role did you play in the team? This can be features you coded, research you did, ideas you contributed.
- What was challenging about your first peer assignment? Do not only think about what you did, but also how you did 
	it
	-	What did you do about this challenge?

Grading
------------
Each team will share these grades. The only exception is for each member not submitting their individual reflection,
 this will drop your individual final grade by 1 point.

Correctness:
    - Does the code run? Can I play a game without it crashing? This is graded without looking at your code,
    just by running your program.
    
Coding proficiency:
    - You correctly use functions to organize the code's logic
    - Any logic in the code is handled clearly and elegantly. If statements are used appropriately.

Style:
    -   Clear design and organization.
    -   Good variable names, function names, and comments.
    -   Functions where appropriate.

## Honor Code

Please make sure that you fully understand the Academic Honor System, and reach out if you need any clarifications. 
As a reminder: You can discuss the assignment with your peers, but you can only do so in English or Kinyarwanda or some 
other human language. Do not show, share, read, or write any code to your peers. Figuring it out the code is their 
responsibility.

## Extra Credit:

There are many options for extra credit to consider:
- You can customize the game using a ColorPicker object
- You can have a set of preset grids, that the user can pick from like the website example.
- You can attempt to animate the grid, and set up a Start/Stop button. This will require you to learn about the concept 
of Threads, and would be a good stretch goal. The idea with the animation is that you should show the state, wait for a 
moment, then update the state and show the next one, etc. The issue is that if you make your code wait, that's all it 
is doing, waiting! If the code is busy waiting it can not do anything else, so your button presses are meaningless since 
no code can act on them. How can we solve this? Using Threads! A Thread is a way to run code *in parallel*, meaning that 
making 1 thread wait does not prevent another thread from doing work. A tutorial on how to achieve this in javafx can be 
found here: https://docs.oracle.com/javase/8/javafx/interoperability-tutorial/concurrency.htm  