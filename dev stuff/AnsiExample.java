public class AnsiExample {
    public static void main(String[] args) {
        // Set text color to red and bold
        // System.out.print("\u001B[1;31m");
        System.out.println("\u001B[1;31m" + "This text is red and bold.");

        // Set text color to green
        // System.out.print("\u001B[38;5;202m");
        System.out.println("\u001B[38;5;202m" + "This text is green.");

        // Reset all attributes to default
        // System.out.print("\u001B[0m");
        System.out.println("\u001B[0m" + "This text is back to default.");

        // Clear the screen and move cursor to top-left
        // System.out.print("\u001B[2J\u001B[10;70f");
        System.out.println("\u001B[2J\u001B[10;70f" + "Screen cleared and cursor reset.");

    }
}