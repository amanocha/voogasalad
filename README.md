# VOOGA

### Robert Steilberg, Filip Mazurek, Aninda Manocha, Teddy Franceschi, Harshil Garg, William Xiong, Nisakorn Valyasevi, Daniel Chai, Pim Chuaylua, Ryan Anders

#### Overview

VOOGA (Video Object-Oriented Gaming Architecture) is a game authoring engine with a focus on 2D RPG style games, like Pokemon, Evoland, and Zelda. The games that can be constructed are all similar in that there are set number of moveable locations defined in a grid, with only one possible element (character, landscape object, et cetera) occupying that space. Hence, for example, a user's player would not be able to stand in an obstacle cell. We have built a clickable grid in which a user can build an overworld. Certain objects have configurable attributes, such as gates, teleporters, or enemies. The user can then export their overworld to a file which can later be loaded into VOOGA's engine and played like any regular game.

VOOGA is written in Java and uses JavaFX on the front end.

##### Instructions

Clone the project down onto your machine and import it into an IDE such as IntelliJ or Eclipse. Add the `xstream-1.4.9.jar` file to your buildpath. The application can now be compiled and run.

Example editor and engine files are located in the `example-gamefiles` directory.

The “build” button on the main menu will direct you to the editor where you can build your own overworld and add characters. First select the default grid size for your project. Then, use the on-screen arrow buttons or scroll with your cursor to navigate around the map. The side tabs in the editor provide full customization tools for your game; with these side control panels, triggered by each tab, you can add objects, players, links, music, and other things to your overworld. You can place an object by selecting it and clicking on the map; you can delete objects by right-clicking them. You can test your game within the editor by navigating to the menu bar and selected Game -> Run Game. You can save your editor for further editing later via File->Save Project As. You can export your editor to a runnable game file via Game -> Export As Game. From the main menu, you can click the “play” button to load in a game file and play games that you’ve created with the editor.

##### Roles

1. Robert Steilberg: Game editor design, menu bar functionality, file I/O, side control panel menus, size chooser, main menu, engine design, transition between different application states, JavaFX builder utility, back end integration
2. Filip Mazurek: Back end model design, interactions, front end integration
3. Aninda Manocha: Back end model design, controller design, front end integration
4. Teddy Franceschi: overworld grid design, game mechanics
5. Harshil Garg: Front end editor design, overworld grid design, custom image import, control panel layout, engine front end design, engine integration with back end, animation, JavaFX builder utility
6. William Xiong: Battles
7. Nisakorn Valyasevi: Front end engine design, engine stats panel
8. Daniel Chai: XStream I/O, Battles
9. Pim Chuaylua: sounds, front end engine design, engine stats panel
10. Ryan Anders: image importing, testing and bug detection

##### Design

Our application is divided into four quadrants that overlap with each other. We have a sharp divide between the editor and engine components of our application. Essentially, the editor and engine could each function as a completely separate program, and could be mixed and matched with other editors or engines to customize the kind of game that you want users to be able to design.

Both the editor and engine each have a front end and a back end. The editor and engine communicate only through game files that are created and exported by the editor. However, resources used in the editor to build a game must be available to the engine, such as images or sounds. This is because we use XStream in a way that serializes classes but the assets that they may require such as images. Properties files are completely separate so that any user can customize either the editor or engine without affecting the other component.

The code for the engine and editor are each divided into a model, view, and controller. The model defines the basic building blocks of our games such as block types, players, or other object. The controller interfaces the model with the view and also handles I/O for communicating with the editor or engine. The view holds all of our UI code that uses JavaFX to create a functional GUI. The controller bridges the divide between the front end and back end and is largely a product of both teams.
Our main goal was to keep the editor and engine separate so that they would be easily extensible for different games types. We wanted it to be possible to create any editor that would be able to interface with our engine, and vice versa, so that our code base could be built on to allow users to build radically different games using our authoring environment.

##### User Interface Design

At the home page, the user can select between build and play mode. In the play mode, an XML file with a standard overworld design will be loaded. In build mode, the user can build the overworld from a blank canvas and can choose to add obstacles and other NPCs, then the design is saved as an XML file. The canvas is represented as a grid of cells and, in build mode, there is a side menu where objects can be added to the cells by clicking the cell, then selecting an object that appears in a pop up box. The user can also select multiple number of cells by using the mouse to drag and release across the area they desire and are then able to select the object they want to fill each of the selected cells with from a pop up box. As for the game mode user interface, there will be a grid display with the main character as the center of the section of the overworld being displayed (Pokemon display style). There is also a side panel with the current game statistics, such as player health points. Textual dialogs appear as a pop up box at the bottom of the grid display. The game play takes in keyboard input to control the character moving through the overworld.

##### Flexibility

Our final API incorporates both power and simplicity, though we learn more towards simplicity in favor of power. We sought to really stick with the MVC architecture so that it would be easy for any future developer to quickly figure out how all the components of our application work together. Our API is relatively simple: we have a model, view, and controller package, and each of those packages contain separate sub-packages that define functionality for the editor, engine, grid, battles, blocks, et cetera. Thus, it would certainly be easy for any developer to navigate to a specific feature of the game and modify it to their specifications. Furthermore, the authoring environment itself is exceedingly simple, and requires little to no time to pick up and understand. The only feature that is not self-evident is the ability to delete objects via a right-click. Other than that, it is an authoring environment similar to Minecraft. A user need only use the simple, clean side control panels for defining the objects that they want to add to the overworld in addition to any other functionality that they wish to customize within their game. Concise instructions are given throughout the editor that quickly explain how to effectively build a game and errors are thrown when proper building practices are not followed. This functionality prevents users from making mistakes and simultaneously avoids a complicated editor interface where users need to define specific values (i.e. a positional value). Most importantly, a user need not repeatedly test their game to "get it right". Because our editor allows you to visualize your game as you build it, the user is provided with an optimal environment for quickly building extensive and powerful games.

Our authoring environment is also highly flexible because it can be used to design many different kinds of games, like puzzle or battle-based games. Although our authoring environment heavily favors block-based games, users can, for example, user our battle interface to change the kind of experience that players have when playing games built through our application. In terms of the API, developers can easily add to our implementation without violating basic design practice. Because we endeavored to segregate our classes based on the MVC model, there are few features that a user need add to our application that would require a convoluted approach wherein the API would not meet the needs of the developer. However, because of our block-based-centric design, it would be more challenging to implement non-block-based games, which is why our API design leans slightly more in favor of simplicity than power.
