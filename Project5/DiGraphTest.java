import java.util.Scanner;

public class DiGraphTest {

   public static void main(String []args) {

      Scanner s = new Scanner(System.in);
      int n,from,to;

      System.out.print("Enter number of vertices: ");
      n = s.nextInt();

      DiGraph graph = new DiGraph(n);
      printMenu();

      char choice = s.next().charAt(0);


      while(choice != 'q') {

         switch(choice){
            case 'a':
               System.out.print("Enter from: ");
               from = s.nextInt();
               System.out.print("Enter to: ");
               to = s.nextInt();
               if(graph.addEdge(from, to)) {
                  System.out.println("Edge from " + from + " to " + to + " was added.");
               }
               else {
                  System.out.println("Edge from " + from + " to " + to + " already exists.");
               }
               break;
            case 'd':
               System.out.print("Enter from: ");
               from = s.nextInt();
               System.out.print("Enter to: ");
               to = s.nextInt();
               if(graph.deleteEdge(from, to)) {
                  System.out.println("Edge from " + from + " to " + to + " was deleted.");
               }
               else {
                  System.out.println("Edge from " + from + " to " + to + " does not exist.");
               }
               break;
            case 'e':
               System.out.println("Edge count is: " + graph.edgeCount());
               break;
            case 'v':
               System.out.println("Vertex count is: " + graph.vertexCount());
               break;
            case 'p':
               System.out.println("The graph is the following:");
               graph.print();
               break;
            case 't':
               System.out.println("The topological ordering is:");
               int []sol = graph.topSort();
               for (int i = 0; i < sol.length; i++) {
                  System.out.print(sol[i] + 1);
                  if (i != sol.length - 1) {
                     System.out.print(", ");
                  }
               }
               System.out.println();
               break;

            default:
               System.out.println("Invalid command.");
         }

         System.out.print("Enter menu choice: ");
         choice = s.next().charAt(0);
      }
   }


   private static void printMenu() {
      System.out.println("Choose one of the following operations: ");
      System.out.println(" - add edge (enter a)");
      System.out.println(" - delete edge (enter d)");
      System.out.println(" - edge count (enter e)");
      System.out.println(" - topological sort (enter t)");
      System.out.println(" - vertex count (enter v)");
      System.out.println(" - print graph (enter p)");
      System.out.println(" - Quit (enter q)");
      System.out.print("Enter menu choice: ");
   }
}