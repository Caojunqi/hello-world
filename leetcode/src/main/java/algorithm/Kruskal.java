package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 最小生成树-Kruskal算法
 */
public class Kruskal {

    public static void main(String[] args) {
        // 用邻接矩阵表示图
        int[][] graph = {
                {0, 10, -1, -1, -1, 11, -1, -1, -1}, // a
                {10, 0, 18, -1, -1, -1, 12, 12, -1},//b
                {-1, 18, 0, 22, -1, -1, -1, 8, -1},//c
                {-1, -1, 22, 0, 20, -1, 24, 21, 16},//d
                {-1, -1, -1, 20, 0, 26, -1, -1, 7},//e
                {11, -1, -1, -1, 26, 0, 17, -1, -1},//f
                {-1, 12, -1, 24, -1, 17, 0, -1, 19},//g
                {-1, 12, 8, 21, -1, -1, -1, 0, -1},//h
                {-1, -1, -1, 16, 7, -1, 19, -1, 0},//i
        };

        // 检测图表示是否正确
        checkGraph(graph);

        // 构建边集
        int[][] eArr = buildEdgeArr(graph);

        // 构建最小生成树
        ArrayList<int[]> minTree = buildMinTree(eArr, graph.length);

        // 打印输出最小生成树
        printMinTree(minTree);
    }

    /**
     * 检查邻接矩阵表示的图是否有误
     *
     * @param graph 图表示
     */
    public static void checkGraph(int[][] graph) {
        if (graph == null) {
            System.out.println("图为null！！");
            return;
        }
        if (graph.length <= 0) {
            System.out.println("图为空！！");
            return;
        }

        if (graph.length != graph[0].length) {
            System.out.println("图表示长宽不一致！！");
            return;
        }

        int vNum = graph.length;
        for (int i = 0; i < graph.length; i++) {
            int[] e = graph[i];
            if (e.length != vNum) {
                System.out.println("顶点[" + i + "]的邻居表表示有误，数量不对！！");
                return;
            }
            for (int j = 0; j < vNum; j++) {
                if (i == j && graph[i][j] != 0) {
                    System.out.println("[" + i + "][" + j + "]的值应该为0！");
                    return;
                }
                if (graph[i][j] != graph[j][i]) {
                    System.out.println("[" + i + "][" + j + "]和[" + j + "][" + i + "]的值不一样！");
                    return;
                }
            }
        }
        System.out.println("此图正常无误！！");
    }

    /**
     * 构建边集
     *
     * @param graph 图表示
     * @return 边集
     */
    public static int[][] buildEdgeArr(int[][] graph) {
        ArrayList<int[]> eList = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i >= j) {
                    continue;
                }
                if (graph[i][j] <= 0) {
                    continue;
                }
                eList.add(new int[]{i, j, graph[i][j]});
            }
        }

        // 转换成数组
        int[][] eArr = new int[eList.size()][];
        int eNum = 0;
        for (int[] e : eList) {
            eArr[eNum++] = e;
        }

        // 对边集进行排序
        Arrays.sort(eArr, Comparator.comparingInt(o -> o[2]));
        return eArr;
    }

    /**
     * 构建最小生成树
     *
     * @param eArr 边集
     * @param vNum 顶点数量
     * @return 最小生成树
     */
    public static ArrayList<int[]> buildMinTree(int[][] eArr, int vNum) {
        // 构建顶点连通图，代表每个顶点挂在哪个根节点上
        int[] vArr = new int[vNum];
        ArrayList<int[]> minTreeList = new ArrayList<>();
        for (int[] e : eArr) {
            int xV = findV(vArr, e[0]);
            int yV = findV(vArr, e[1]);
            if (xV != yV) {
                // 此边的两个顶点不在一张连通图上
                minTreeList.add(e);
                vArr[xV] = yV;
            }
        }
        return minTreeList;
    }

    /**
     * 找出指定顶点所挂靠的连通图根节点
     * 注：每个连通图的根节点是此连通图中序号最大的顶点
     *
     * @param vArr 连通图集合
     * @param v    待判断的顶点序号
     * @return 所挂靠的连通图根节点序号
     */
    public static int findV(int[] vArr, int v) {
        while (vArr[v] > 0) {
            v = vArr[v];
        }
        return v;
    }

    /**
     * 打印输出最小生成树
     *
     * @param minTree 最小生成树
     */
    public static void printMinTree(ArrayList<int[]> minTree) {
        StringBuilder sb = new StringBuilder();
        for (int[] e : minTree) {
            sb.append("{" + e[0] + "," + e[1] + "," + e[2] + "} ");
        }
        System.out.println(sb.toString());
    }
}
