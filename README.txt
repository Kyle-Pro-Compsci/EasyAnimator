These files represent how the model of the Animator is made.

The IAnimationModel interface describes what the model should be able to do.
The SimpleAnimationModel class implements IAnimationModel and represents a model to animate shapes. This should store data relating to the shapes and their animations as well as be able to run the animation.

The IShape interface describes what a shape is able to do.
The Shape abstract class implements some of the methods that a shape is able to do from IShape and serves as a baseline as to what a shape should store and how it should perform certain methods.
The classes Oval and Rectangle extend Shape and represent the shapes oval and rectangle, respectively, and implements any remaining methods from the interface that are specific to the individual shapes.
The Coordinate class represents a field that a shape will have, being the x and y coordinates that a shape is within an animation.
The enums ShapeType and Position are designed to ensure integrity of the types of shapes and the position that a coordinate is relative to a shape, respectively.

The IAnimation interface describes what an animation should be able to do.
The Animation abstract class, similar to the abstract Shape class, implements some of the methods that an animation is able to do from IAnimation and serves as a baseline as to what an animation should store and how it should perform certain methods.
The Move, Scale, and ChangeColor classes are subclasses of Animation and implement the rest of the methods necessary. These represent the currently available animations that can be created and used.
The AnimationName enum is designed to ensure integrity of the types of animations that can be performed.

There are three main views to EasyAnimator:

TextView - Outputs a text description of the animation in the console. It describes the shapes that make up the model and the transformations that they go through.

SVGView - Outputs a .svg-formatted representation of the animation. It creates tags for each of the shapes as well as inner tags for their respective animations. We use the animate tag to transform the attributes of the shapes based off of their IAnimations, with their startTime set to the 'begin' value and the total duration from startTime to endTime set to 'dur'.

VisualView - Pops up a JFrame window that directly and visually displays the animation using Graphics2D to draw each frame of the animation. A timer is used to set the intervals between each tick, upon which the shapes are transformed to their state in the next tick and are redrawn, creating the illusion of motion.

These can be initialized using the static factory class AnimatorViewCreator and its method create().