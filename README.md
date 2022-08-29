# Simple Animator
Our animation model uses lists of keyframes to represent animations of shapes.
In the SimpleAnimationModel class, the ArrayList(ArrayList(Keyframe)) allKeyframes represents all the animations, and each ArrayList(Keyframe) represents one shapes full movement during the fullduration of the animation. This is done using linear interpolation between Keyframes
to fill in the gaps of the movements and changes of the shapes.

Java Swing 


Application that can easily create and play simple 2D animations from shapes 
created using mvc architecture 

Displays animation in textual view 
visual view
svg animation view 
- using existing animation frameworks (ex: Flash) 
- xml based format to descirbe image and animations 
interactive view = playback controls
  - start
  - pause
  - resume
  - restart 
  - looping
  - increase or decrease speed 


main(String[] args) method in cs3500.animator package = entry point for the program 
take in inputs as command-line arguements 

- "name of animation" "type of view" "where-output-sjow-go" "interger-ticks-per-second"
- in and view are mandatory 
- in any order 
- run program through provided jar file 


Example aniamtions you can run 






