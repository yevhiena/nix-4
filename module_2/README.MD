#### TASK 2 ALGORITHM COMPLEXITY
In this algorithm we have only one foreach loop through ArrayList with names, it's complexity is O(n)

Inside these loop we use containsKey(), remove(), put() methods of LinkedHashMap, time complexity of these methods is O(1) according to this https://www.baeldung.com/java-collections-complexity

Finally, time complexity of algorithm calculates as following:

O(n) * (O(1)+O(1)+O(1)) = O(n)

Then we take first unique element from LinkedHashMap, which also takes O(1).

#### TASK 3 ALGORITHM EXPLANATION

This task was solved with the use of JGraphT lib, the shortest path(minimum weight of path) is found with A* Algorithm. 