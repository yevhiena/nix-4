package ua.com.alevel.controller;

import ua.com.alevel.data.UserTest;
import ua.com.alevel.service.impl.OrderedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class OperationsController<E extends Comparable<E>> {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    List<E> list = new OrderedList<>();
    private final String type;

    OperationsController(String type){
        this.type = type;
    }

        public void run() throws IOException {

            printOperations();

            String position;
            while ((position = bufferedReader.readLine()) != null && !position.equals("100")) {
                switch (position) {
                    case "0" : {
                        System.exit(0);
                    }
                    case "1" : size(); break;
                    case "2" : contains(); break;
                    case "3" : isEmpty(); break;
                    case "4" : add(); break;
                    case "5" : addAll(); break;
                    case "6" : removeIndex(); break;
                    case "7" : removeObject(); break;
                    case "8" : containsAll(); break;
                    case "9" : removeAll(); break;
                    case "10" : retainAll(); break;
                    case "11" : clear(); break;
                    case "12" : get(); break;
                    case "13" : indexOf(); break;
                    case "14" : lastIndexOf(); break;
                    case "15" : subList(); break;
                    case "16" : listIterator(); break;

                    default:
                        System.out.println("Invalid operation, try again");
                }
                System.out.println("Your variant: if you want exit, please input 0, else, repeat logic");
            }
            BasicController.printOperations();
        }


       private void  size(){
           System.out.println("List: " + list);
           System.out.println("Size of list: " + list.size());

       }

       private void  contains() throws IOException {
        Object o = getData();
           System.out.println("List: " + list);
           System.out.println("Does the list contain " + o);
           System.out.println(list.contains(o));

       }

       private void isEmpty(){
           System.out.println("List: " + list);
           System.out.println("Is the list empty? " + list.isEmpty());
       }

        private void add() throws IOException {
            E o = getData();
            list.add(o);
            System.out.println("Modified list: " + list);
        }

        private void addAll() throws IOException {
            System.out.println("Input a collection to add all of the elements");
            List<E> el = getAllData();
            list.addAll(el);
            System.out.println("Elements to be added " + el);
            System.out.println("Modified list: " + list);
        }

        private void removeIndex() throws IOException {
            System.out.println("Input index to remove object");
            try {
                int index = Integer.parseInt(bufferedReader.readLine());
                Object removed = list.remove(index);
                System.out.println("Removed object is " + removed);
                System.out.println("Modified list: " + list);
            } catch (NumberFormatException e){
                System.out.println("Index should be a number");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        private void removeObject() throws IOException {
            System.out.println("Input object to remove ");
            Object o = getData();
            list.remove(o);
            System.out.println("Modified list: " + list);
        }

        private void containsAll() throws IOException {
            System.out.println("Input a collection to check if the list contains all of the elements");
            List<E> el = getAllData();
            boolean cont = list.containsAll(el);
            System.out.println("Elements to be checked " + el);
            System.out.println("List: " + list);
            System.out.println("Does the list contain all of the elements? " + cont);
        }

        private void removeAll() throws IOException {
            System.out.println("Input a collection to remove all of the elements");
            List<E> el = getAllData();
            list.removeAll(el);
            System.out.println("Elements to be removed " + el);
            System.out.println("Modified list: " + list);
        }

        private void retainAll() throws IOException {
            System.out.println("Input a collection to retain all of the elements");
            List<E> el = getAllData();
            list.retainAll(el);
            System.out.println("Elements to be retained " + el);
            System.out.println("Modified list: " + list);
        }

        private void clear(){
            list.clear();
            System.out.println("List was cleared. List is empty.");
            System.out.println("List: " + list);
        }

        private void get() throws IOException {
            System.out.println("Input index to get object");
            try {
                int index = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Object is " + list.get(index));
                System.out.println("List: " + list);
            }catch (NumberFormatException e){
                System.out.println("Index should be a number");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        private void indexOf() throws IOException {
            System.out.println("Input object to find index of it's first occurrence ");
            Object o = getData();
            int index = list.indexOf(o);
            System.out.println("Index of object is " + index);
            System.out.println("List: " + list);
        }

        private void lastIndexOf() throws IOException {
            System.out.println("Input object to find index of it's last occurrence ");
            Object o = getData();
            int index = list.lastIndexOf(o);
            System.out.println("Last index of object is " + index);
            System.out.println("List: " + list);
        }

        private void subList() throws IOException {
            System.out.println("Input start index(will be included)");
            try {
                int startIndex = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Input end index(will not be included)");
                int endIndex = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Sublist is " + list.subList(startIndex, endIndex));
                System.out.println("List: " + list);
            } catch (NumberFormatException e){
                System.out.println("Index should be a number");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        private void listIterator() throws IOException {
            System.out.println("ListIterator was called");
            ListIteratorController<E> listIteratorController = new ListIteratorController<>(list.listIterator());
            listIteratorController.run();
        }



        private E getData() throws IOException {
        Object o ;
            if(this.type.equals("UserTest")){
                System.out.println("enter name of user");
                String name = bufferedReader.readLine();
                o = (E) new UserTest(name);
                return (E)o;
            }
            else if(this.type.equals("String")){
                System.out.println("enter string ");
                o = (E) bufferedReader.readLine();
                return (E)o;
            }
            else {
                while (true) {
                    try {
                        System.out.println("enter Integer");
                        o = Integer.parseInt(bufferedReader.readLine());
                        return (E)o;
                    } catch (Exception e) {
                        System.out.println("Invalid input for Integer");
                        System.out.println("Try again");
                    }

                }

            }

        }


        private List<E> getAllData() throws IOException {
        List<E> o = new OrderedList<>();
            System.out.println("Input amount of elements of collection");
            int amount = Integer.parseInt(bufferedReader.readLine());
            if(this.type.equals("UserTest")){
                for (int i = 0; i < amount; i++) {
                    System.out.println("enter name of user to add user to the collection");
                    String name = bufferedReader.readLine();
                    E u = (E) new UserTest(name);
                    o.add(u);
                }
                return o;
            }
            else if(this.type.equals("String")){
                for (int i = 0; i < amount; i++) {
                    System.out.println("enter String to add to collection");
                    String name = bufferedReader.readLine();
                    o.add((E) name);
                }
                return o;
            }
            else {
                while (true) {
                    try {
                        for (int i = 0; i < amount; i++) {
                            System.out.println("enter Integer to add to collection");
                            Integer num =Integer.parseInt(bufferedReader.readLine());
                            o.add((E) num);
                        }
                        return o;
                    } catch (Exception e) {
                        System.out.println("Invalid input for Integer");
                        System.out.println("Try again");
                    }
                }

            }
        }
    static void printOperations(){
        System.out.println("Select operation to perform by entering a number");
        System.out.println("0 - exit program");
        System.out.println("100 - start from the beginning(select new type parameter for list)");
        System.out.println("1 - size()");
        System.out.println("2 - boolean contains(Object o)");
        System.out.println("3 - boolean isEmpty()");
        System.out.println("4 - boolean add(E e)");
        System.out.println("5 - boolean addAll(Collection<? extends E> collection)");
        System.out.println("6 - E remove(int i)");
        System.out.println("7 - boolean remove(Object o)");
        System.out.println("8 - boolean containsAll(Collection<?> collection)");
        System.out.println("9 - boolean removeAll(Collection<?> collection)");
        System.out.println("10 - boolean retainAll(Collection<?> collection)");
        System.out.println("11 - void clear()");
        System.out.println("12 - E get(int i)");
        System.out.println("13 - int indexOf(Object o)");
        System.out.println("14 - lastIndexOf(Object o)");
        System.out.println("15 - List<E> subList(int i, int i1)");
        System.out.println("16 - ListIterator<E> listIterator()");
        System.out.println("The list of functions is not full, only functions without @Deprecated annotation are mentioned");
        System.out.println("To use library put maven dependency in pom, for additional info go to readme file ");
    }

}

