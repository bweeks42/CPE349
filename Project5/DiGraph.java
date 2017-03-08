import java.util.LinkedList;
import java.lang.Integer;
import java.util.Arrays;

public class DiGraph {

   private LinkedList<Integer>[] directedGraph;
   private int edgeCount;

   public DiGraph(int N) {
      directedGraph = new LinkedList[N];
      for (int i = 0; i < N; i++) {
         directedGraph[i] = new LinkedList<Integer>();
      }
      edgeCount = 0;
   }

   public boolean addEdge(int from, int to) {
      if (!directedGraph[from-1].contains(new Integer(to-1))) {
         directedGraph[from-1].add(new Integer(to-1));
         edgeCount++;
         return true;
      }
      return false;
   }

   public boolean deleteEdge(int from, int to) {
      if(directedGraph[from-1].remove(new Integer(to-1))) {
         edgeCount--;
         return true;
      }
      return false;
   }

   public int edgeCount() {
      return edgeCount;
   }

   public int vertexCount() {
      return directedGraph.length;
   }

   public void print() {
      for (int i = 0; i < directedGraph.length; i++) {
         System.out.print((i + 1) + " is connected to: ");
         for (int j = 0; j < directedGraph[i].size(); j++) {
            System.out.print(directedGraph[i].get(j).intValue() + 1);
            if (j != directedGraph[i].size() - 1) {
               System.out.print(", ");
            }
         }
         System.out.println();
      }
   }

   private int[] indegrees() {
      int[] ind = new int[vertexCount()];
      Arrays.fill(ind, 0, ind.length - 1, 0);

      for (int i = 0; i < vertexCount(); i++) {
         for (int j = 0; j < directedGraph[i].size(); j++) {
            ind[directedGraph[i].get(j).intValue()] += 1;
         }
      }
      return ind;
   }

   public int[] topSort() throws IllegalArgumentException {
      int[] ind = indegrees();

      int[] result = new int[vertexCount()];
      int step = 0;

      LinkedList<Integer> queue = new LinkedList<Integer>();
      int dq;

      boolean found = false;
      for (int i = 0; i < ind.length; i++) {
         if (ind[i] == 0) {
            found = true;
            queue.addLast(new Integer(i));
            ind[i] = -1;
         }
      }

      if (!found) {
         throw new IllegalArgumentException();
      }

      while (queue.size() != 0) {

         dq = queue.removeFirst().intValue();
         result[step++] = dq;

         for (int j = 0; j < directedGraph[dq].size(); j++) {
               ind[directedGraph[dq].get(j).intValue()] -= 1;
         }

         for (int i = 0; i < ind.length; i++) {
            if (ind[i] == 0) {
               queue.addLast(new Integer(i));
               ind[i] = -1;
            }
         }
      }

      return result;
   }
}