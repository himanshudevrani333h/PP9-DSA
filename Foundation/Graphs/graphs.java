
import java.util.ArrayList;
import java.util.LinkedList;



public class graphs {
    private static boolean add;

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

    public static int printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean visited[], String ans) {

        if (src == dest) {
            ans += dest;
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                // ans += src;
                count += printAllPath(graph, e.nbr, dest, visited, ans + src);
                // ans = ans.substring(0, ans.length() -1);
            }
        }

        visited[src] = false;

        return count;
    }

    public static void preorderInGraph(ArrayList<Edge>[] graph, int src, int wt, boolean visited[], String ans) {

        System.out.println(src + " ->" + ans + " @ " + wt);
        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                preorderInGraph(graph, e.nbr, wt + e.wt, visited, ans + e.nbr);
            }
        }
        visited[src] = false;

    }

    public static void postorderInGraph(ArrayList<Edge>[] graph, int src, int wt, boolean visited[], String ans) {

        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                postorderInGraph(graph, e.nbr, wt + e.wt, visited, ans + e.nbr);
            }
        }
        System.out.println(src + " ->" + ans + " @ " + wt);
        visited[src] = false;

    }

    public static class weightpathPair {
        String psf = "";
        int wght = (int) 1e9;
    }

    public static weightpathPair LightweightPath(ArrayList<Edge>[] graph, int src, int dest, boolean visited[]) {

        if (src == dest) {
            weightpathPair base = new weightpathPair();
            base.psf += src;
            base.wght = 0;
            return base;
        }

        visited[src] = true;
        weightpathPair myAns = new weightpathPair();
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                weightpathPair recAns = LightweightPath(graph, e.nbr, dest, visited);
                if (recAns.wght != (int) 1e9 && recAns.wght + e.wt < myAns.wght) {
                    myAns.wght = recAns.wght + e.wt;
                    myAns.psf = src + recAns.psf;
                }

            }
        }

        visited[src] = false;

        return myAns;
    }

    public static void LightweightPath(ArrayList<Edge>[] graph, int src, int dest) {
        boolean visited[] = new boolean[graph.length];
        weightpathPair res = LightweightPath(graph, src, dest, visited);
        System.out.println("Lightest weight: " + res.wght + " by path " + res.psf);
    }

    public static class weightpathPair_2 {
        String psf = "";
        int wght = -1;
    }

    public static weightpathPair_2 HeavyweightPath(ArrayList<Edge>[] graph, int src, int dest, boolean visited[]) {

        if (src == dest) {
            weightpathPair_2 base = new weightpathPair_2();
            base.psf += src;
            base.wght = 0;
            return base;
        }

        visited[src] = true;
        weightpathPair_2 myAns = new weightpathPair_2();
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                weightpathPair_2 recAns = HeavyweightPath(graph, e.nbr, dest, visited);
                if (recAns.wght != -1 && recAns.wght + e.wt > myAns.wght) {
                    myAns.wght = recAns.wght + e.wt;
                    myAns.psf = src + recAns.psf;
                }

            }
        }

        visited[src] = false;

        return myAns;
    }

    public static void HeavyweightPath(ArrayList<Edge>[] graph, int src, int dest) {
        boolean visited[] = new boolean[graph.length];
        weightpathPair_2 res = HeavyweightPath(graph, src, dest, visited);
        System.out.println("Heaviest weight: " + res.wght + " by path " + res.psf);
    }

    public static class ceilAndFloorPair {
        int ceil = (int) 1e9;
        int floor = -(int) 1e9;
    }

    public static ceilAndFloorPair ceilAndFloor_(ArrayList<Edge> graph[], int data) { // for vertix
        ceilAndFloorPair res = new ceilAndFloorPair();

        for (int i = 0; i < graph.length; i++) {
            for (Edge e : graph[i]) {
                if (e.nbr < data && e.nbr > res.floor)
                    res.floor = e.nbr;
                else if (e.nbr > data && e.nbr < res.ceil)
                    res.ceil = e.nbr;
            }
        }
        return res;

    }

    public static void ceilAndFloor(ArrayList<Edge> graph[], int data) {
        ceilAndFloorPair res = ceilAndFloor_(graph, data);
        System.out.println(res.ceil + " " + res.floor);
    }

    public static void ceilAndFloor_2(ArrayList<Edge> graph[], int src, int wsf, int data, boolean vis[],
            ceilAndFloorPair ceilfloor) {
        if (wsf > data) {
            ceilfloor.ceil = Math.min(ceilfloor.ceil, wsf);
        }
        if (wsf < data) {
            ceilfloor.floor = Math.max(ceilfloor.floor, wsf);
        }

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                ceilAndFloor_2(graph, e.nbr, wsf + e.wt, data, vis, ceilfloor);
        }
        vis[src] = false;
    }

    public static void dfs_gcc(ArrayList<Edge> graph[], int src, boolean vis[]) {

        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.nbr])
                dfs_gcc(graph, e.nbr, vis);
        }
    }

    public static int getConnectedComponents(ArrayList<Edge> graph[], int src) {
        boolean vis[] = new boolean[graph.length];
        int gccCount = 0;

        for (int i = 0; i < graph.length; i++) {
            for (Edge e : graph[i]) {
                if (!vis[e.nbr]) {
                    dfs_gcc(graph, e.nbr, vis);
                    gccCount++;
                }
            }
        }
        return gccCount;
    }

    public static void hamaltonianpathAndCycle(ArrayList<Edge> graph[], int srcOrigin, int src, int edgeCount,
            boolean visited[], String ans) {

        if (edgeCount == graph.length - 1) {
            int edge = findEdge(graph, src, srcOrigin);
            if (edge == -1) {
                System.out.println(ans + ".");
            } else {
                System.out.println(ans + "*");
            }
            return;
        }

        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                hamaltonianpathAndCycle(graph, srcOrigin, e.nbr, edgeCount + 1, visited, ans + e.nbr);
            }
        }
        visited[src] = false;
    }

    public static void BFS(ArrayList<Edge>[] graph, int src, int dest) {
        LinkedList<Integer> que = new LinkedList<>();
        int N = graph.length;
        boolean[] vis = new boolean[N];

        que.addLast(src);
        int level = 0;

        boolean isCyclePresent = false;
        int shortestPath = -1;

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();

                // for cycle
                if (vis[rvtx]) {
                    isCyclePresent = true;
                    continue;
                }

                if (rvtx == dest) {
                    shortestPath = level;
                }

                vis[rvtx] = true;
                for (Edge e : graph[rvtx]) {
                    if (!vis[e.nbr]) {
                        que.addLast(e.nbr);
                    }
                }
            }
            level++;
        }
    }

    public static boolean cycleDetect(ArrayList<Edge> graph[], int src, boolean visited[]) {
        LinkedList<Integer> qu = new LinkedList<>();

        qu.addLast(src);

        while (qu.size() > 0) {
            int sz = qu.size();
            while (sz-- > 0) {
                int vtc = qu.removeFirst();

                if (visited[vtc]) {
                    return true;
                }

                visited[vtc] = true;
                for (Edge e : graph[vtc]) {
                    if (!visited[e.nbr]) {
                        qu.addLast(e.nbr);
                    }
                }
            }

        }

        return false;
    }

    public static class pairForpath {
        int vtc;
        String psf = "";
        int wsf;

        pairForpath(int vtc, String psf, int wsf) {
            this.vtc = vtc;
            this.psf = psf;
            this.wsf = wsf;
        }
    }

    public static void pathsofar(ArrayList<Edge> graph[], int src, boolean visited[]) {
        LinkedList<pairForpath> qu = new LinkedList<>();
        qu.addLast(new pairForpath(src, src + "", src));

        while (qu.size() > 0) {
            int sz = qu.size();
            while (sz-- > 0) {
                pairForpath rp = qu.removeFirst();
                
                if(visited[rp.vtc]) continue;
                System.out.println(rp.vtc + " -> " + rp.psf + " @ " + rp.wsf);
                visited[rp.vtc] = true;
                for(Edge e: graph[rp.vtc]){
                   if(!visited[e.nbr]){
                       qu.addLast(new pairForpath(e.nbr, rp.psf + e.nbr, rp.wsf + e.wt));
                   }
                }
            }
        }
       
    }

    public static int spreadOfInfection(ArrayList<Edge> graph[], int src , int nODays){
        int day = nODays;
        int inf = 0;
        LinkedList<Integer> qu = new LinkedList<>();
        qu.addLast(src);
        boolean visited[] = new boolean[graph.length];
         while(day -->0){
           int sz = qu.size();
   
           while(sz -->0){
               int rv = qu.removeFirst();
               if(visited[rv]) continue;
               inf++;
               visited[rv] = true;
   
               for(Edge e: graph[rv]){
                   if(!visited[e.nbr]){
                       qu.addLast(e.nbr);
                   }
               }
           }
         }
         return inf;

    }

     public static boolean isBipartate(ArrayList<Edge> graph[], int src, int colorMark[]){
       LinkedList<Integer> qu = new LinkedList<>();
       qu.addLast(src);
       int color =0;
    //    colorMark[src] = color;
       while(qu.size() > 0){
           int sz = qu.size();

           while(sz-->0){
               int rd = qu.removeFirst();

               if(colorMark[rd] != -1 && colorMark[rd] != color){
                   return false;
               }
               if(colorMark[rd] != -1 ){
                   continue;
               }

               colorMark[rd] = color;

               for(Edge e: graph[rd]){
                   if(colorMark[e.nbr] == -1){
                       qu.addLast(e.nbr);
                   }
               }
           }

           color = (color +1 ) %2;
       }

       return true;
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
        boolean visited[] = new boolean[N];
        // int res= printAllPath(graph, 0, 6, visited, "");
        // System.out.println(preorderInGraph(graph, 0, 6, 10, visited, ""));
        // postorderInGraph(graph, 0, 10, visited, "0");
        // LightweightPath(graph, 0, 6);
        // ceilAndFloor(graph, 5);
        // HeavyweightPath(graph, 0, 6);
        // System.out.println(res);
        ceilAndFloorPair cp = new ceilAndFloorPair();
        ceilAndFloor_2(graph, 0, 0, 19, visited, cp);
        System.out.println(cp.ceil + " " + cp.floor);

        // ----------cycle
        boolean res = false;
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i])
                res = res || cycleDetect(graph, i, visited);
        }

        System.out.println(res);
        /// -------------cyclic
    }

    public static void main(String[] args) {
        construction();
    }
}