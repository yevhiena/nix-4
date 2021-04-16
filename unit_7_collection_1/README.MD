### Demo of orderedList, implementation of collection List<E>

In the app only functions without annotation @Deprecated are shown

Some functions are annotated with @Deprecated in order to warn the user that it is better to use another functions. 

It happens because not all the functions from interface List are suitable for OrderedList implementation.

For example, methods which require index for element insertion are pointless in terms of sorted list.(add(int i, E e), set(int i, E e), addAll(int i, Collection<? extends E> collection)).

The same thing is related to add and set methods of ListIterator.

### The demonstration allows to choose the next type parameters to see the lib abilities: String, Integer, class User(String name)

To use the whole library, please add dependency to your module.

Also in the demonstration toArrays methods are not presented, because it hard to show these functions in console, but they are existing in lib and can be used. 