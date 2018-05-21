# Utils
Common Files Generally Needed In Project Developement

# Usefull Files
- [KeyBoardWatcher](KeyboardWatcher.java)
- [DateParser](DateParser.java)

# KeyBoardWatcher
It helps you to track Open or Close Keypad

Just create a object with Activity or Fragment where you want to use.
```
public KeyboardStateWatcher(View activityRootView)
{
    this(activityRootView, false);
}
```

Use this to register your listner.
```
public void addSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
   mListner = listener;
}
```
    
# DateParser
Format any type of date to required type already mentioned possible output format for date still can add more.

How to use:

To get current output **Current Time** in **`yyyy-MM-dd'T'HH:mm:ss.sss`** format 
```
LocalDateParser.getInstance().getDateString("", LocalDateParser.DATE.LOCALE_CURRENT, LocalDateParser.DATE.OUTPUT_T_S);
```

You can also get in **`Date`** Object with assigned format
```
LocalDateParser.getInstance().getDate("2018-2-22 11:12:33", LocalDateParser.DATE.OUTPUT_DD_MMM_YYYY);
```
There are other usefull methods can check.
