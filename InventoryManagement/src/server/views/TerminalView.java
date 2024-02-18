package server.views;

import java.util.Scanner;

public class TerminalView {
    Scanner kInput = new Scanner(System.in);

    public void printString(String string){
        System.out.print(string);
    }

    public String getInput(){
        return kInput.nextLine();
    }
}
