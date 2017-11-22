# SlidingPanel
Use this sliding panel in your android projects with pleasure!

# WHY to add?
First. If you need a view or a panel for your project/android-app for showing some details like "Settings", "Popup menu" or "Popup bar" or other information panel (like AlertDialog/DialogFragment in Android framework) for details that you want to show and you don't want to add difficult dialogs (was described previously) you simply can implement this tiny custom library with simple logic.

Second. The sliding panel works like custom container for your views and after it appears on the screen with animation you can see that the panel doesn't change screen hierarchy, it's just additional view container for you with appearing animation.

Third. When the sliding panel appears, it takes the view-focus on yourself and you can interact either it or "background" without conflicts. As it mentioned before: instead of dialogs, this custom view DOESN'T CHANGE your activity/fragment lifecycle.

# HOW to add?
Add it in your build.gradle:

  in the root build.gradle:

      allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }
  
  in the local build.gradle:
  
      dependencies {
        compile 'com.github.RELEX-Group:SlidingPanel:0.1'
      }

# HOW to use?
  # 1 
  Just put the element from this library inside your layout.xml file and add the property (android:visibility="invisible") for      keep container "out" from the screen:
  
      <ru.relex.slidingpanel.view.SlidingPanelLinear
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        ...
        android:visibility="invisible">
        
          ...
        
      </ru.relex.slidingpanel.view.SlidingPanelLinear>

  # 2 
  Initialize view in your activity/fragment. To do that I recommend you to use ButterKnife lib from Jake Wharton and I'll show you example using this Wharton's lib:

      @BindView(<your-view-id>)
      SlidingPanelConstraint myPanel;

      ...

      @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...
        ButterKnife.bind(this);
        ...
      }

  # 3 
  Just use method slide() with instance:

      ...
      myPanel.slide();
      ...
      
# Library details
In this section you can see the list of valid custom properties of the sliding panel view and list of valid properties' values:

    app:speed="500" - appearing/disappearing speed
    app:targetHeight="350dp | wrap_content | match_parent" - view's height (used for animation calculations and I recommend you set value for this attribute from android:height attribute)
    app:interpolator="accelerate" - interpolator for animation (default is linear interpolator - you can remove this property if you want to apply linear interpolator to the animation)
      accelerate
      decelerate
      accelerateDecelerate
      anticipate
      anticipateOvershoot
      bounce
      cycle
      overshoot
    app:direction="topToBottom" - direction of the current animation (you should understand that like "view will appear from top to bottom"; default is "topToBottom" animation direction)
      topToBottom
      bottomToTop
      leftToRight
      rightToLeft

 # Plans and updates
 I have a couple features for this library (e.g. listeners, etc.) and I'll add this in the near future! Use my tiny customs with pleasure!
 
 # Conclusion
 Thank you for using if you're doing it! Thanks commonsguy for fundamental materials about Android development. From his book I've got basics for this tiny custom lib.
