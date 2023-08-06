package game.utils;

import java.util.Scanner;

public class MenuManagerAdmin{
    /**
     * variable to ensure MenuManagerAdmin only has one instance at a time
     */
    private static MenuManagerAdmin menuManagerAdminInstance = null;

    /**
     * get Instance method that ensures only one instance at a time
     * @return menuManagerAdmin Instance
     */

    public static MenuManagerAdmin getInstance(){
        if(menuManagerAdminInstance == null){
            menuManagerAdminInstance = new MenuManagerAdmin();
        }
        return menuManagerAdminInstance;
    }

    /**
     * prints Menu for users to select options
     * @return choice that user makes
     */

    public int menuItem() {
        Scanner sel = new Scanner(System.in);
        System.out.println();
        System.out.println("CHOOSE YOUR STARTING CLASS!:");
        System.out.println("1) Samurai");
        System.out.println("2) Bandit");
        System.out.println("3) Wretch");
        System.out.println("4) Astrologer");
        System.out.print("Select one:");
        int choice = Integer.parseInt(sel.nextLine());
        System.out.println("Your choice:"+choice);
        return choice;
    }
}
