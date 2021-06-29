import java.util.ArrayList;

public class graphs {
    public static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(u, v, w));
        graph[v].add(new Edge(v, u, w));
    }

    public static void display_1(ArrayList<Edge>[] graph, int N) {

        for (int i = 0; i < N; i++) {
            int sz = graph[i].size();
            for (int j = 0; j < sz; j++) {
                System.out.print(
                        graph[i].get(j).src + "->" + "(" + graph[i].get(j).nbr + ", " + graph[i].get(j).wt + ") ");
            }
            System.out.println();
        }

    }

    public static void display(ArrayList<Edge>[] graph, int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.nbr + ", " + e.wt + ") ");
            }
            System.out.println();
        }

    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {
        ArrayList<Edge> list = graph[u];
        for (int i = 0; i < list.size(); i++) {
            Edge e = list.get(i);
            if (e.nbr == v)
                return i;
        }

        return -1;
    }

    public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
        int i1 = findEdge(graph, u, v);
        int i2 = findEdge(graph, v, u);

        graph[u].remove(i1);
        graph[v].remove(i2);
    }

    public static void removeVtx(ArrayList<Edge>[] graph, int u) {
        ArrayList<Edge> list = graph[u];
        for (int i = list.size() - 1; i >= 0; i--) {
            Edge e = list.get(i);
            removeEdge(graph, e.src, e.nbr);
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] vis) {
        if (src == dest)
            return true;

        vis[src] = true;
        boolean res = false;
        for (Edge e : graph[src])
            if (!vis[e.nbr])
                res = res || hasPath(graph, e.nbr, dest, vis);

        return res;
    }
    

    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean visited[], String ans){

        if( src == dest){
            ans += dest;
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        visited[src] = true;
        for(Edge e: graph[src]){
            if(!visited[e.nbr]){
                // ans += src;
                count += printAllPath(graph, e.nbr, dest, visited, ans + src);
                // ans = ans.substring(0, ans.length() -1);
            }
        }

        visited[src] = false;
       

        return count;
    }

    public static void preorderInGraph(ArrayList<Edge>[] graph , int src,  int wt, boolean visited[] , String ans){
       
        System.out.println(src+" ->" + ans + " @ " + wt);
        visited[src] = true;
        
        for(Edge e: graph[src]){
            if(!visited[e.nbr]){
                preorderInGraph(graph, e.nbr, wt + e.wt, visited, ans + e.nbr );
            }
        }
        visited[src] = false;
       
    }

    public static void postorderInGraph(ArrayList<Edge>[] graph , int src,  int wt, boolean visited[] , String ans){
       
        visited[src] = true;
        
        for(Edge e: graph[src]){
            if(!visited[e.nbr]){
                postorderInGraph(graph, e.nbr, wt + e.wt, visited, ans + e.nbr );
            }
        }
        System.out.println(src+" ->" + ans + " @ " + wt);
        visited[src] = false;
       
    }
    public static class weightpathPair{
        String psf ="";
        int wght = (int)1e9;
    }
    public static weightpathPair LightweightPath(ArrayList<Edge> [] graph, int src, int dest, boolean visited[]){
        
        if( src == dest){
            weightpathPair base = new weightpathPair();
            base.psf += src;
            base.wght = 0;
            return base;
        }
        
        visited[src] = true;
        weightpathPair myAns = new weightpathPair();
        for(Edge e: graph[src]){
            if(!visited[e.nbr]){
               weightpathPair recAns = LightweightPath(graph, e.nbr, dest, visited);
                if( recAns.wght != -1 &&  recAns.wght + e.wt < myAns.wght){
                    myAns.wght = recAns.wght + e.wt;
                    myAns.psf =  src + recAns.psf ;
                }

            }
        }
       
        visited[src] = false;

        return myAns;
    }

    public static void LightweightPath(ArrayList<Edge> [] graph, int src, int dest){
      boolean visited[] = new boolean[graph.length];
      weightpathPair res = LightweightPath(graph, src, dest, visited);
      System.out.println("Lightest weight: "+ res.wght +" by path " + res.psf);
    }


    public static class weightpathPair_2{
        String psf ="";
        int wght = -1;
    }
    public static weightpathPair_2 HeavyweightPath(ArrayList<Edge> [] graph, int src, int dest, boolean visited[]){
        
        if( src == dest){
            weightpathPair_2 base = new weightpathPair_2();
            base.psf += src;
            base.wght = 0;
            return base;
        }
        
        visited[src] = true;
        weightpathPair_2 myAns = new weightpathPair_2();
        for(Edge e: graph[src]){
            if(!visited[e.nbr]){
                weightpathPair_2  recAns = HeavyweightPath(graph, e.nbr, dest, visited);
                if( recAns.wght != -1 &&  recAns.wght + e.wt > myAns.wght){
                    myAns.wght = recAns.wght + e.wt;
                    myAns.psf =  src + recAns.psf ;
                }

            }
        }
       
        visited[src] = false;

        return myAns;
    }

    public static void HeavyweightPath(ArrayList<Edge> [] graph, int src, int dest){
      boolean visited[] = new boolean[graph.length];
      weightpathPair_2 res = HeavyweightPath(graph, src, dest, visited);
      System.out.println("Heaviest weight: "+ res.wght +" by path " + res.psf);
    }

    public static void construction() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        // display(graph, N);
        // boolean visited[] = new boolean[N];
        // int res= printAllPath(graph, 0, 6, visited, "");
        // System.out.println(preorderInGraph(graph, 0, 6, 10, visited, ""));
        // postorderInGraph(graph, 0, 10, visited, "0");
        LightweightPath(graph, 0, 6);
        // HeavyweightPath(graph, 0, 6);
        // System.out.println(res);
    }

    public static void main(String[] args) {
        construction();
    }
}