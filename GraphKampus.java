import java.util.*;

class VertexNode {
    String nama;
    EdgeNode edgeHead;
    VertexNode next;

    public VertexNode(String nama) {
        this.nama = nama;
        this.edgeHead = null;
        this.next = null;
    }
}

class EdgeNode {
    String dest;
    int weight;
    EdgeNode next;

    public EdgeNode(String dest, int weight) {
        this.dest = dest;
        this.weight = weight;
        this.next = null;
    }
}

class WeightedGraph {
    VertexNode head;

    public WeightedGraph() {
        head = null;
    }

    public void addVertex(String nama) {
        VertexNode newNode = new VertexNode(nama);
        if (head == null) {
            head = newNode;
        } else {
            VertexNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    private VertexNode findVertex(String nama) {
        VertexNode current = head;
        while (current != null) {
            if (current.nama.equals(nama)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void addEdgeToList(VertexNode vertex, String dest, int weight) {
        EdgeNode newNode = new EdgeNode(dest, weight);

        if (vertex.edgeHead == null) {
            vertex.edgeHead = newNode;
        } else {
            EdgeNode current = vertex.edgeHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void addEdge(String src, String dest, int weight) {
        VertexNode srcNode = findVertex(src);
        VertexNode destNode = findVertex(dest);

        if (srcNode == null || destNode == null) {
            System.out.println("Lokasi tidak ditemukan!");
            return;
        }

        addEdgeToList(srcNode, dest, weight);
        addEdgeToList(destNode, src, weight);
    }

    public void dfs(String start) {
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> hasil = new ArrayList<>();

        dfsRecursive(start, visited, hasil);

        System.out.println("DFS dari " + start + ":");
        System.out.println(String.join(" -> ", hasil));
    }

    private void dfsRecursive(String lokasi, HashSet<String> visited, ArrayList<String> hasil) {
        visited.add(lokasi);
        hasil.add(lokasi);

        VertexNode vertex = findVertex(lokasi);
        EdgeNode edge = vertex.edgeHead;

        while (edge != null) {
            if (!visited.contains(edge.dest)) {
                dfsRecursive(edge.dest, visited, hasil);
            }
            edge = edge.next;
        }
    }

    public void bfs(String start) {
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.print("BFS dari " + start + ": ");

        while (!queue.isEmpty()) {
            String lokasi = queue.poll();
            System.out.print(lokasi + " ");

            VertexNode vertex = findVertex(lokasi);
            EdgeNode edge = vertex.edgeHead;

            while (edge != null) {
                if (!visited.contains(edge.dest)) {
                    visited.add(edge.dest);
                    queue.add(edge.dest);
                }
                edge = edge.next;
            }
        }
        System.out.println();
    }

    public void dijkstra(String start, String end) {

        if (findVertex(start) == null || findVertex(end) == null) {
            System.out.println("Lokasi tidak ditemukan!");
            return;
        }

        HashMap<String, Integer> distance = new HashMap<>();
        HashMap<String, String> previous = new HashMap<>();
        HashSet<String> visited = new HashSet<>();

        VertexNode current = head;
        while (current != null) {
            distance.put(current.nama, Integer.MAX_VALUE);
            current = current.next;
        }
        distance.put(start, 0);

        while (true) {
            String currentLoc = getMinDistanceVertex(distance, visited);
            if (currentLoc == null || currentLoc.equals(end)) {
                break;
            }

            visited.add(currentLoc);

            VertexNode vertex = findVertex(currentLoc);
            EdgeNode edge = vertex.edgeHead;

            while (edge != null) {
                if (!visited.contains(edge.dest)) {
                    int newDist = distance.get(currentLoc) + edge.weight;
                    if (newDist < distance.get(edge.dest)) {
                        distance.put(edge.dest, newDist);
                        previous.put(edge.dest, currentLoc);
                    }
                }
                edge = edge.next;
            }
        }

        printShortestPath(start, end, distance, previous);
    }

    private String getMinDistanceVertex(HashMap<String, Integer> distance,
                                        HashSet<String> visited) {

        int min = Integer.MAX_VALUE;
        String minVertex = null;

        for (String lokasi : distance.keySet()) {
            if (!visited.contains(lokasi) && distance.get(lokasi) < min) {
                min = distance.get(lokasi);
                minVertex = lokasi;
            }
        }
        return minVertex;
    }

    private void printShortestPath(String start, String end,
                                   HashMap<String, Integer> distance,
                                   HashMap<String, String> previous) {

        if (distance.get(end) == Integer.MAX_VALUE) {
            System.out.println("Tidak ada jalur dari " + start + " ke " + end);
            return;
        }

        System.out.println("\nJarak terpendek dari " + start + " ke " + end +
                " = " + distance.get(end) + " meter");

        System.out.print("Rute: ");
        printPath(end, previous);
        System.out.println();
    }

    private void printPath(String lokasi, HashMap<String, String> previous) {
        if (previous.get(lokasi) != null) {
            printPath(previous.get(lokasi), previous);
            System.out.print(" -> ");
        }
        System.out.print(lokasi);
    }
}

public class GraphKampus {
    public static void main(String[] args) {

        WeightedGraph kampus = new WeightedGraph();

        kampus.addVertex("Asrama");
        kampus.addVertex("Masjid");
        kampus.addVertex("Parkiran Motor SBS");
        kampus.addVertex("Gedung SBS");
        kampus.addVertex("Lapangan Tenis");
        kampus.addVertex("WICO");
        kampus.addVertex("Parkiran Motor Utama");
        kampus.addVertex("WAR Stadium");
        kampus.addVertex("Gedung Utama");
        kampus.addVertex("Parkiran Mobil");
        kampus.addVertex("Lapangan Hybrid");
        kampus.addVertex("SWK");

        kampus.addEdge("Asrama", "Masjid", 95);
        kampus.addEdge("Asrama", "Parkiran Motor SBS", 50);
        kampus.addEdge("Masjid", "Lapangan Tenis", 30);
        kampus.addEdge("Parkiran Motor Utama", "Gedung Utama", 15);
        kampus.addEdge("Gedung Utama", "SWK", 100);

        kampus.dfs("Asrama");
        kampus.bfs("Asrama");
        kampus.dijkstra("Asrama", "SWK");
    }
}

//update
