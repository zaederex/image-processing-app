# An Image Processing Application
This project is an image processing application developed in Java that provides functionality to load an image, perform various operations on the image and also save the image as a file on disk. It also allows you generate certain images like checkboard, flags and rainbow stripes pattern.

In Part 1 the functionality provided were filtering (blur and sharpen), color transformation (sepia and greyscale), generation of checkerboard, rainbow stripes (both horizontal and vertical) and generation of flags of Greece, France and Switzerland with the correct proportions.

In Part 2, we built on this and provided new functionality in the form of Dithering and Image Mosaicing. Also, a command-driven controller was created.

In Part 3, we have created a view for our image processing application, featuring a graphical user interface.
The application now supports 2 modes for performing the operations:

	1) script mode to run the application by passing a script consisting of commands to be executed.

	2) interactive mode to allow user interactions for loading/creating, modifying and saving images through the Graphical User Interface (GUI).

---
### 1. View

#### 1.1 Graphical User Interface (GUI)
A view is the part of the program that interfaces with the user. Our user interface exposes all the features of the Image Processing application through menus and sub-menus and the user will be able to see the image that is being processed on the screen. 
Alternatively, shortcuts have been defined for the user to perform certain operations quickly without navigating through the menu items.

<p align="center">  <img src="/res/mainframe.jpg"> <br> <sub>Screenshot of the GUI.</sub></p>


In some cases, certain operations are not permitted.

E.g. The image transformation and Edit options are greyed out and disabled in our GUI if no image has been loaded on the screen.
<p align="center">  <img src="/res/GUI_grey.jpg"> <br> <sub>Screenshot of the GUI with greyed out options.</sub></p>

#### 1.2 View and Controller

The Controller acts as the listener for all the GUI events and signals the model to perform the appropriate actions. The resultant image is returned by the model to the controller, which then renders it on the screen. This way, the user is able to see the effects of the operations they perform and perform further operations accordingly.

_____

### 2. Changes 

#### 2.1 GUI
Several view classes have been created in order to support the interactive mode of the application.

#### 2.2 Controller
Based on the feedback received on the previous iteration, we had to change the controller (ImageProcessingControllerImpl) to do the IO as the IO operations were previously being achieved through a model class (ImageIOHandlerImpl).

A new controller (controller/InteractiveAppControllerImpl) was added to support the interactive mode. However, the controller created as part of the previous version of the application is still in used in case of the script mode. 

Both these controllers implement the same interface ImageProcessingControllerImpl.

An abstract class has been added that contains functionality common to both the controllers (associated with Script and Interactive mode)

#### 2.3 Model changes
In order to render the images on the view, it became essential to return the model Image object as a BufferedImage object. To do this, the Image interface was extended by the ExtendedImage interface to add another method called getAsBufferedImage() {see interface model.ExtendedImage}. This extension has been done through composition.

Since the model is not supposed to perform IO, we have removed the ImageIOHandler interface and its implementation.
_____
### 3. Capabilities
Our application currently supports the following operations either interactively through the GUI or through a script passed as a command line argument to the application:

- Load and save images.
- Apply color transformations on the loaded image such as Sepia and Greyscale.
- Apply convolution filters on the loaded images such as Blur and Sharpen.
- Apply Mosaic transformation on an image.
- Apply a Dithering transformation on an image.
- Generate a checkerboard based on the user specified size for the size of each square in the checkerboard.
- Generate either a vertical or horizontal rainbow stripes based on the input provided by the user regarding the size of the rainbow, dimension of each stripe and the orientation of the stripes. 
- Generate flags of Switzerland, Greece and France depending on the size of the image provided by the user and choice of country flag.
_____

### 4. How to use the application?
   
#### 4.1 Interactive mode
  
   - The application executable jar has been provided in the res/ directory. Run it as follows:
      
           $ java -jar assignment9.jar -interactive
      
      When invoked in this manner the program will open the graphical user interface.

      <p align="center">  <img src="/res/mainframe.jpg"> <br> <sub>Screenshot of the GUI.</sub></p>
      
##### 4.1.1 Load an image

From the menu bar, click on File > Load File (or use the shortcut CTRL + o for Windows / CMD + o for Mac).

<p align="center">  <img src="/res/fileloadermenu.jpg"> <br> <sub>Screenshot of the Load File option in File menu.</sub></p>

This opens a file chooser. 

<p align="center">  <img src="/res/fileloader.jpg"> <br> <sub>Screenshot of the File chooser when Loading an image.</sub></p>

Navigate to the image file that needs to be loaded and click on Load button. This loads and renders the image on the view.  

<p align="center">  <img src="/res/loaded.jpg"> <br> <sub>Screenshot of the rendered image.</sub></p>

##### 4.1.2 Save an image

From the menu bar, click on File > Save File (or use the shortcut CTRL + s for Windows / CMD + s for Mac).

<p align="center">  <img src="/res/filesavermenu.jpg"> <br> <sub>Screenshot of the Save File option in File menu.</sub></p>

This opens a file chooser. 

<p align="center">  <img src="/res/filesaver.jpg"> <br> <sub>Screenshot of the File chooser when Saving an image.</sub></p>

Navigate to the target directory and type the name of the file. Specify the format (defaults to png format when no format is specified in the file name) and click on the Save button.

##### 4.1.3 Generate Images (checkerboard/flag/rainbow)

From the menu bar, click on Generated Images > Checkerboard.

<p align="center">  <img src="/res/checkerboardmenu.jpg"> <br> <sub>Screenshot of the Checkerboard option in Generate images menu.</sub></p>

This opens Dialog to enter further details. 

<p align="center">  <img src="/res/checkerboarddialog.jpg"> <br> <sub>Screenshot of dialog for entering checkerboard dimension details.</sub></p>

 Enter the dimension details as prompted and click on OK.

<p align="center">  <img src="/res/checkerboard.jpg"> <br> <sub>Screenshot of the generated checkerboard</sub></p>

Similarly, rainbow stripe patterns and flags can be generated.

##### 4.1.4 Apply filter (sharpen/blur)

From the menu bar, click on Image Effects > Apply filter > Sharpen.

<p align="center">  <img src="/res/sharpen.jpg"> <br> <sub>Screenshot of the Sharpen option in Apply filter menu.</sub></p>

This sharpens the image.

Similarly, the blur filter can be applied on the image.

##### 4.1.5 Apply Transformation (mosaic/greyscale/sepia/dither)

From the menu bar, click on Image Effects > Apply Transformer > Mosaic.

<p align="center">  <img src="/res/mosaic.jpg"> <br> <sub>Screenshot of the Mosaic option in Apply Transformer menu.</sub></p>

This opens Dialog to enter further details. 

<p align="center">  <img src="/res/mosaicdialog.jpg"> <br> <sub>Screenshot of dialog for entering number of seeds for mosaic.</sub></p>

Enter the number of seeds for the mosaic and click on OK.

This renders the mosaiced image on the view.

Similarly, the greyscale, sepia and dither transformers can be applied on the image.

##### 4.1.5 Edit Menu (Undo/Redo)

The application allows you to undo application of an effect or redo the application.

To undo, from the menu bar, click on Edit > Undo (or use the shortcut CTRL + z for Windows / CMD + z for Mac).

To redo, from the menu bar, click on Edit > Redo (or use the shortcut CTRL + y for Windows / CMD + y for Mac).

<p align="center">  <img src="/res/editmenu.jpg"> <br> <sub>Screenshot of Edit Menu with Undo and Redo options.</sub></p>


#### 4.2 Script mode
   - Create a text file containing the commands with proper syntax as described in section 3.2 of Part 2 or use the existing script res/commands.txt. Ensure the the commands in the script conform to the requirement that each complete command should be in one line. 
     
   - The application executable jar has been provided in the res/ directory. Run it as follows:
      
         $ java -jar assignment9.jar -script <command_script_name>
      
      Example: 
	  
	     $ java -jar assignment9.jar -script res/commands.txt

Note: If you want to try out your own command script, use the existing res/commands.txt and/or res/commands2.txt as a reference to create your own custom script.
Also ensure that you own the images that you use in order to test the application.

_________
### 5. Citation
All pictures used in this project are photographs taken by Zoheb Nawaz, or results generated by a program written by Nikhil Bolar and Zoheb Nawaz. Nikhil Bolar and Zoheb Nawaz retain the ownership of these photographs and prohibit any use or modification by others and authorized their use as part of this project.
