package ua.com.alevel;

import ua.com.alevel.view.View;

import java.io.IOException;

public class StartLibDemo {
      public static void main(String[] args) {
          try {
            View.view();
          } catch (IOException e){
            System.out.println(e.getMessage());
          }


      }
}
