/*
Date: 3/14/17
Project 4: DiGraph.java
Audrey Chan: achan65
Blain Weeks: bjweeks
*/

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
            if (ind[directedGraph[dq].get(j).intValue()] == 0) {
               queue.addLast(new Integer(directedGraph[dq].get(j).intValue()));
            }
         }
      }

      return result;
   }

   private class VertexInfo {
      int distance = -1;
      int parent = -1;
   }

   private VertexInfo[] BFS(int s) {
      int dq, currentVal;
      VertexInfo[] vi = new VertexInfo[vertexCount()];
      for (int j = 0; j < vi.length; j++) {
         vi[j] = new VertexInfo();
      }
      LinkedList<Integer> queue = new LinkedList<Integer>();
      vi[s].distance = 0;
      queue.addLast(new Integer(s));

      while (queue.size() != 0) {
         dq = queue.removeFirst().intValue();
         for (int i = 0; i < directedGraph[dq].size(); i++) {
            currentVal = directedGraph[dq].get(i).intValue();
            if (vi[currentVal].distance == -1) {
               vi[currentVal].parent = dq;
               vi[currentVal].distance = vi[dq].distance + 1;
               queue.addLast(new Integer(currentVal));
            }
         }
      }

      return vi;
   }

   public boolean isTherePath(int from, int to) {
      VertexInfo[] vi = BFS(from);
      return (vi[to].distance != -1);
   }

   public int  lengthOfPath(int from, int to) {
      VertexInfo[] vi = BFS(from);
      return (vi[to].distance);
   }

   public void printPath(int from, int to) {
      VertexInfo[] vi = BFS(from);
      int[] toPrint = new int[vi[to].distance + 1];
      int next = to;
      if (isTherePath(from, to)) {
         toPrint[vi[to].distance] = next;
         for (int i = vi[to].distance - 1; i >= 0; i--) {
            toPrint[i] = vi[next].parent;
            next = vi[next].parent;
         }
         System.out.print("Shortest path from " + (from + 1) + " to " + (to + 1) + " is: ");
         for (int j = 0; j < toPrint.length; j++) {
            if (j < toPrint.length - 1) {
               System.out.print((toPrint[j] + 1) + " -> ");
            }
            else {
               System.out.print(toPrint[j] + 1);
            }
         }
         System.out.println();
      } 
      else {
         System.out.println("There is no path.");
      }
   }

   private class TreeNode {
      int vertexNumber;
      LinkedList<TreeNode> children;
   }

   private TreeNode buildTree(int s) {
      VertexInfo[] vi = BFS(s);
      TreeNode[] nodes = new TreeNode[vertexCount()];
      for (int i = 0; i < nodes.length; i++) {
         nodes[i] = new TreeNode();
         nodes[i].vertexNumber = i;
         nodes[i].children = new LinkedList<TreeNode>();
      }

      for (int j = 0; j < nodes.length; j++) {
         if (vi[j].parent != -1) {
            nodes[vi[j].parent].children.add(nodes[j]);
         }
      }

      return nodes[s];
   }

   public void printTree(int s) {
      TreeNode root = buildTree(s);
      System.out.println("The breadth-first-tree is:");
      printTree(root, 0);
   }

   private void printTree(TreeNode root, int depth) {
      for (int i = 0; i < depth; i++) {
         System.out.print("    ");
      }
      System.out.println(root.vertexNumber + 1);
      for (int j = 0; j < root.children.size(); j++) {
         printTree(root.children.get(j), depth + 1);
      }
   }
}