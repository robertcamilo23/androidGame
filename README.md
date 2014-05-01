# Background

The goal of this project is to implement a simple interactive game as an Android application.
The java code from which this project starts can be found [here](https://bitbucket.org/loyolachicagocs_comp313/uidemo-android-java).

# Functional Requirements (R. Martinez, A. Parsa, and R. Klein)

The object of the game is to eliminate as many monsters as possible on a two-dimensional playing field within limited time. Scoring is based on eliminating more monsters in less time.

    (1) Playing field:
        (0.5) The screen is divided into n by m squares.
        (0.5) n and m are chosen based on the device's display size such that the resulting squares can be pressed accurately with the user's finger.
        (0.5) Each square has room for up to one monster. (optional extra credit)
    (2) Monsters:
        (0.5) There are k monsters initially. Monsters behave autonomously in the following way:
        (1.0) Monsters move around among adjacent squares at random (all eight neighbors are considered adjacent).
        (0.5) Over time, Monsters repeatedly alternate between a vulnerable and protected state (shown in yellow versus green); they spend a certain percentage of time in the vulnerable state but alternate in a randomized way.
    (0.5) Touch interface: Monsters can be eliminated while in the vulnerable state by pressing on the square they occupy.
    (0.5) The playing field is locked into the preferred orientation to preserve the application state.
    Levels (extra credit): At higher levels, the number of Monsters goes up, and the proportion of time in the vulnerable state goes down.
    Display pixel density (extra credit): n and m are chosen by taking the device's pixel density into account such that the resulting squares are about the same physical size on different devices.
    Rotation (extra credit): the application state is preserved during device rotation.

# Nonfunctional Requirements (R. Martinez, A. Parsa, and R. Klein)

    (0.5) Follow the design principles discussed so far.
    (1.0) Maintain a clear, responsibility-based separation among the different building blocks.
        Follow the Model-View-Controller architecture (http://en.wikipedia.org/wiki/Model-view-controller) discussed in Mednieks's text (especially chapters 6 and 8). The example from the text is here: https://github.com/bmeike/ProgrammingAndroid2Examples/tree/master/AndroidUIDemo
        You may be able to reuse some of the model classes from this example: https://github.com/concurrency-cs-luc-edu/ecosystem-java
    (1.5) Ensure your application includes comprehensive unit, integration, and functional tests using the techniques from the clickcounter and stopwatch examples where appropriate. (See also http://developer.android.com/tools/testing/testing_android.html) At least one of Robolectric tests or Android instrumentation tests (in src/androidTest) should be present.
    Use thread-based concurrency for making the Monsters autonomous, and use suitable concurrency building blocks for ensuring the capacity of squares in a thread-safe way.
        (0.5) It is usually best to represent each Monster as a background task: http://developer.android.com/reference/android/os/AsyncTask.html
        (0.5) A good way to limit cell capacity is by giving each cell a semaphore to control access: http://developer.android.com/reference/java/util/concurrent/Semaphore.html
        Generally follow the coding guidelines and good Android development practice: http://developer.android.com

# Written Part/Documentation (Sam, Cole, and Hamdan)

    (0.5) Provide a brief description of your domain model.
    (0.25) Use inline comments to document design details in the code.
    (0.25) Use javadoc comments to document how to use the abstractions (interfaces, classes) you designed.
    (1.0) Include a brief (300-500 words) report on the design tradeoffs and decisions you made in this project, including
        MVA versus MVC
        use of SOLID and other design principles
        impact on testability
        concurrency