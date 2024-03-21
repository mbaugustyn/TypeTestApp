Classes:

1. TypeRacer
Main class that composes all of the Game components like:

- HeaderLabel
- InputField
- TimeLabel
- Reset button

Additionally as a Run method that starts the game and restart method.

2. RandomWords

Class for generating new text for user to input.
In order to do so it takes text from a text file, splits the text into seperate words and randomises them and collects them together. 
Has  public getNextLine to feed GameInputField class new text.
And ResetText to reset the generated text.

3. GameInputField

Class that extends JComponent.
In this field user inputs text. This class handles user input and updates its state accordingly via KeyListener. 

4. GameResults

Once game is finished it takes users word and mistakes count and calculates his WPM and Accuracy with calculateWPM and calculateAcc methods.

5. TimeLabel
Class that extends JLabel.
This component keeps track of the remaining gametime and presents it to user. Moreover on remaining time reaching 0 it calls the GameResults class' methods.
