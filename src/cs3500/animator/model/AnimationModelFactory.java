package cs3500.animator.model;

/**
 * Factory class for Animation Models
 */
public class AnimationModelFactory {
  /**
   * Constructs a new AnimationModel of the correct type.
   * @param type either AnimationModelType.HASHMAP or AniamtionModelType.SIMPLE
   * @return a freshly constructed AnimationModel
   */
  public static AnimationModel getModel(AnimationModelType type) {
    if(type == AnimationModelType.HASHMAP) {
      return new HashmapAnimationModel();
    } else if (type == AnimationModelType.SIMPLE) {
      return new SimpleAnimationModel();
    } else {
      throw new IllegalArgumentException("Type must be HASHMAP or SIMPLE");
    }
  }

  /**
   * Constructs a new AnimationModel of the correct type.
   * @param type either AnimationModelType.HASHMAP or AniamtionModelType.SIMPLE
   * @param width the width of the frame of the model to construct
   * @param height the height of the frame of the model to construct
   * @return a freshly constructed AnimationModel
   */
  public static AnimationModel getModel(AnimationModelType type, int width, int height) {
    if(type == AnimationModelType.HASHMAP) {
      return new HashmapAnimationModel(width, height);
    } else if (type == AnimationModelType.SIMPLE) {
      return new SimpleAnimationModel(width, height);
    } else {
      throw new IllegalArgumentException("Type must be HASHMAP or SIMPLE");
    }
  }
}
