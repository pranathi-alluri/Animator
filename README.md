# Simple Animator
The animator was developed in Java using MVC architecture to easily create and play simple 2D animations from shapes. Specifically, the animation model uses lists of keyframes to represent the movement of each shape during the full duration of the animation, done using linear interpolation. 

For more details on the design process and the functionality of the model, controller, and views visit the "Design" page. 


### Running Application
The JAR files (OODHW4.jar) under the "resources" folder are used to run the program, while the main(String[] args) method in cs3500.animator package acts as the entry point and takes in inputs as command line arguments. For example:

```bash
java -jar OODHW4.jar -in buildings.txt -view interactive -speed 50  -out out.svg
```
<ul>
<li> -jar "name-of-jar-file": JAR file to run the program
<li> -in "input-file": Name of animation file to be run 
<li> -view "type-of-view": One of the four types of view to display the animation in
<ul>
<li> Text view: Describes the animation in a readable text
<li> Visual view: Draws and displays the animation
<li> SVG view: XML based format to describe images and animations 
<ul>
<li> Allows for existing animation frameworks to be used to display the animation (ex: Flash)
</ul>
<li> Interactive view: Includes playback controls 
<ul>
<li> Start
<li> Pause
<li> Restart
<li> Loop
<li> Change speed 
</ul>
</ul>
<li> -out "output-file": Where the output should be displayed
<ul>
<li> If not specified the default is System.out
</ul>
<li> -speed "rate": The speed at which the animation should be displayed in ticks-per-second
<ul>
<li> Default is 1 tick per second
<li> Must be an integer 
</ul>
</ul>


### Acknowledgments  
Developed alongside Joel Willick for CS3500, Object Oriented Design, at Northeastern University. 







