package ua.com.alevel.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListIteratorController<E extends Comparable<E>>{
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private ListIterator<E> it;

    ListIteratorController(ListIterator<E> it){
        this.it = it;
    }

    public void run() throws IOException {
        System.out.println("Select operation to perform by entering a number");
        System.out.println("0 - exit program");
        System.out.println("100 - return to the previous step(before calling an iterator)");
        System.out.println("1 - hasNext()");
        System.out.println("2 - E next()");
        System.out.println("3 - remove()");
        System.out.println("4 - hasPrevious()");
        System.out.println("5 - E previous()");
        System.out.println("6 - nextIndex()");
        System.out.println("7 - previousIndex()");
        System.out.println("The list of iterator functions is not full, only functions without @Deprecated annotation are mentioned");


        String position;
        while ((position = bufferedReader.readLine()) != null && !position.equals("100")) {
            switch (position) {
                case "0" : {
                    System.exit(0);
                }
                case "1" : hasNext(); break;
                case "2" : Next(); break;
                case "3" : remove(); break;
                case "4" : hasPrevious(); break;
                case "5" : previous(); break;
                case "6" : nextIndex(); break;
                case "7" : previousIndex(); break;

                default:
                    System.out.println("Invalid operation, try again");
            }
            System.out.println("Your variant: if you want exit, please input 0, else, repeat logic");
        }
        OperationsController.printOperations();
    }


    private void hasNext(){
        try {
            System.out.println("has next element? " + it.hasNext());
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("No more elements");
        }

    }
    private void Next(){
        try {
            Object o = it.next();
            System.out.println("next element is " + o);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
    private void remove(){
        try {
            it.remove();
            System.out.println("element was removed");
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }
    }

    private void hasPrevious(){
        System.out.println("has previous element? " + it.hasPrevious());

    }
    private void previous(){
        try {
            Object o = it.previous();
            System.out.println("previous is " + o);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    private void nextIndex(){
        System.out.println("next index is " + it.nextIndex());

    }
    private void previousIndex(){
        System.out.println("previous index is " + it.previousIndex());

    }



}
