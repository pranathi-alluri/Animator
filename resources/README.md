# OODHW4
Our animation model uses lists of keyframes to represent animations of shapes.
In the SimpleAnimationModel class, the ArrayList(ArrayList(Keyframe)) allKeyframes represents all
the animations, and each ArrayList(Keyframe) represents one shapes full movement during the full
duration of the animation. This is done using linear interpolation between Keyframes
to fill in the gaps of the movements and changes of the shapes.

For example, if there is a keyframe of a red (100, 0, 0) square (20x20) at position
(20, 20) at time 10 tics, and a second keyframe of a green (0, 100, 0) rectangle (40x10) at
position (30, 30) at time 30 tics. If you wanted to know what this shape looks like at time 20 tics,
you can use linear interpolation between the keyframes to find that it would be a green-red (50, 50, 0)
rectangle (30x15) at position (25, 25). This is the core of how our animation model know where all
of its shapes are at all times.

The AnimationModel interface is the interface that defines the functionality of the model
of our animation. 

The SimpleAnimationModel class holds different animations as ArrayList(Keyframe), and all of
these animations are held together in one ArrayList(ArrayList(Keyframe)). The model can tell you
where all the animations are at a given time, or it can tell you what one animation looks like. It
can be mutated by adding or subtracting Keyframes, which it automatically places at the correct 
location within itself.

The Keyframe interface defines the functionality of Keyframes as something that holds a shape and
the time that that shape exists at. 

The SimpleKeyframe class acts as a simple data structure that holds a shape and a time. It also can 
interpolate the shape that exists at a specific time between this Keyframe and another Keyframe. 

The Shape interface defines shapes as a name, type, location, size, and color.

The ShapeType enumeration defines that the model can currently only hold ovals and rectangles.

The SimpleShape abstract class defines simple getters for all the variables of shapes and defines 
equals and hashcode for SimpleShapes.

The SimpleOval and SimpleRectangle classes both extend SimpleShape with the only difference being
what ShapeType they are, ShapeType.OVAL and ShapeType.RECTANGLE respectively.

SimpleShapeFactory is a helper class used to use the abstract testing class SimpleShapeTest.

The interface SimpleAnimationTextView defines that the text view of the animation is a user-friendly
description of all the animations in the model.

The class SimpleAnimationView implements a text description of the animation in the model.