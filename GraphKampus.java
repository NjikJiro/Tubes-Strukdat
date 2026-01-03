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

    // TODO 1: Lengkapi method addVertex
    public void addVertex(String nama) {
        // Buat node baru
        // Jika head null → jadikan head
        // Jika tidak → tambahkan di akhir linked list
    }

    // TODO 2: Lengkapi method findVertex
    private VertexNode findVertex(String nama) {
        // Lakukan traversal linked list vertex
        // Jika nama sama → return vertex
        // Jika tidak ditemukan → return null
        return null;
    }

    // TODO 3: Lengkapi method addEdgeToList
    private void addEdgeToList(VertexNode vertex, String dest, int weight) {
        // Tambahkan edge ke adjacency list
    }

    // TODO 4: Lengkapi addEdge (graph tidak berarah)
    public void addEdge(String src, String dest, int weight) {
        // Cari src dan dest
        // Jika salah satu null → tampilkan pesan
        // Tambahkan edge dua arah
    }

    // ================= DFS =================
    public void dfs(String start) {
        HashSet<String> visited = new HashSet<>();
        ArrayList<String> hasil = new ArrayList<>();

        dfsRecursive(start, visited, hasil);

        System.out.println("DFS dari " + start + ":");
        System.out.println(String.join(" -> ", hasil));
    }

    // TODO 5: Lengkapi DFS rekursif
    private void dfsRecursive(String lokasi, HashSet<String> visited, ArrayList<String> hasil) {
        // Tandai visited
        // Masukkan ke hasil
        // Traversal semua edge
    }

    // ================= BFS =================
    // TODO 6: Lengkapi BFS
    public void bfs(String start) {
        // Gunakan Queue
        // Gunakan visited
        // Traversal graph secara BFS
    }

    // ================= DIJKSTRA =================
    public void dijkstra(String start, String end) {
        HashMap<String, Integer> distance = new HashMap<>();
        HashMap<String, String> previous = new HashMap<>();
        HashSet<String> visited = new HashSet<>();

        // TODO 7: Inisialisasi distance semua vertex = infinity
        // distance[start] = 0

        // TODO 8: Proses utama Dijkstra
        // Ambil vertex dengan jarak minimum
        // Update jarak tetangga
    }

    // TODO 9: Lengkapi getMinDistanceVertex
    private String getMinDistanceVertex(HashMap<String, Integer> distance,
                                        HashSet<String> visited) {
        return null;
    }

    // ================= OUTPUT =================
    private void printShortestPath(String start, String end,
                                   HashMap<String, Integer> distance,
                                   HashMap<String, String> previous) {
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

        // ===== NODE LOKASI =====
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

        // ===== EDGE =====
        kampus.addEdge("Asrama", "Masjid", 95);
        kampus.addEdge("Asrama", "Parkiran Motor SBS", 50);
        kampus.addEdge("Masjid", "Lapangan Tenis", 30);
        kampus.addEdge("Parkiran Motor Utama", "Gedung Utama", 15);
        kampus.addEdge("Gedung Utama", "SWK", 100);

        // ===== TEST =====
        kampus.dfs("Asrama");
        kampus.bfs("Asrama");
        kampus.dijkstra("Asrama", "SWK");
    }
}
