# Simple Animator
Our animation model uses lists of keyframes to represent animations of shapes.
In the SimpleAnimationModel class, the ArrayList(ArrayList(Keyframe)) allKeyframes represents all the animations, and each ArrayList(Keyframe) represents one shapes full movement during the fullduration of the animation. This is done using linear interpolation between Keyframes
to fill in the gaps of the movements and changes of the shapes.
