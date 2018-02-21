import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AStarNavigatorTest {

    Navigator navigator;

    private void printArray(char[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Before
    public void setUp() {
        navigator = new AStarNavigator();
    }

    @Test
    public void noRoute() {
        char[][] no = {
            {'.', '.', '.', '.', '@'},
            {'#', '#', '#', '#', '#'},
            {'.', '.', '.', '.', '.'},
            {'#', '#', '#', '#', '.'},
            {'X', '.', '.', '.', '.'}
        };
        printArray(no);

        char[][] route = navigator.searchRoute(no);

        assertNull(route);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noEnd() {
        char[][] noEnd = {
            {'.', '.', '.', '.', '@'},
            {'#', '#', '#', '#', '#'},
            {'.', '.', '.', '.', '.'},
            {'#', '#', '#', '#', '.'},
            {'.', '.', '.', '.', '.'}
        };
        printArray(noEnd);

        char[][] route = navigator.searchRoute(noEnd);
    }

    @Test
    public void easyRoute() {
        char[][] easy = {
            {'.', '.', '.', '.', '@'},
            {'.', '#', '#', '#', '#'},
            {'.', '.', '.', '.', '.'},
            {'#', '#', '#', '#', '.'},
            {'X', '.', '.', '.', '.'}
        };
        printArray(easy);

        char[][] route = navigator.searchRoute(easy);

        printArray(route);
    }

    @Test
    public void manyWaysRoute() {
        char[][] manyWays = {
            {'.', '.', '.', '.', '@'},
            {'.', '#', '.', '#', '#'},
            {'.', '.', '.', '.', '.'},
            {'#', '.', '#', '#', '.'},
            {'X', '.', '.', '.', '.'}
        };
        printArray(manyWays);

        char[][] route = navigator.searchRoute(manyWays);

        printArray(route);
    }

    @Test
    public void clearFieldRoute() {
        char[][] clearField = {
            {'.', '.', '.', '.', '@'},
            {'.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', 'X', '.', '.', '.'},
            {'.', '#', '.', '.', '.'}
        };
        printArray(clearField);

        char[][] route = navigator.searchRoute(clearField);
        printArray(route);
    }

    @Test
    public void crossRoute() {
        char[][] cross = {
            {'.', '#', '.', '#', '.'},
            {'.', '#', '.', '#', '.'},
            {'#', '#', '.', '#', '#'},
            {'X', '.', '@', '.', '.'},
            {'#', '#', '.', '#', '#'},
            {'.', '#', '.', '#', '.'},
            {'.', '.', '.', '.', '.'}
        };
        printArray(cross);

        char[][] route = navigator.searchRoute(cross);
        printArray(route);
    }

    @Test
    public void snakeRoute() {
        char[][] snake = {
            {'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.'},
            {'.', '#', '.', '.', '.', '#', '.', '.', '.', '#', '.', '.', '.', '#', '.', '.', '.'},
            {'.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.'},
            {'.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.'},
            {'.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.'},
            {'.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.'},
            {'.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.'},
            {'.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.'},
            {'.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '@', '#', '.'}

        };
        printArray(snake);

        char[][] route = navigator.searchRoute(snake);
        printArray(route);
    }

    @Ignore
    @Test
    public void generateRandomMap() throws IOException {
        char[][] map = new RandomMap().generateMap();
        printArray(map);
        char[][] route = navigator.searchRoute(map);
        printArray(route);
        if (route != null) {
            Writer wr = new FileWriter("randomMap.txt");
            for (int i = 0; i < map.length; i++) {
                String str = new String(map[i]) + "\n";
                wr.write(str);
            }
            wr.flush();
            wr.close();
        }

    }

    @Test
    public void timeTest() throws IOException {
        Reader r = new FileReader("randomMap.txt");
        StringBuilder str = new StringBuilder();
        ArrayList<String> arList = new ArrayList<>();
        int columns = 1;
        int b;
        while ((b = r.read()) != -1) {
            if ((char)b != '\n') {
                str.append((char)b);
            } else {
                columns = str.length();
                arList.add(str.toString());
                str.delete(0, str.length() - 1);
            }
        }

        int rows = arList.size();
        char[][] map = new char[rows][columns];
        for (int i = 0; i < map.length; i++) {
            map[i] = arList.get(i).toCharArray();
        }

        long totalTime = 0;
        int n = 1000;
        for (int i = 0; i < n; i++) {
            long startTime = System.nanoTime();
            char[][] route = navigator.searchRoute(map);
            totalTime += System.nanoTime() - startTime;
        }
        double avgTime = (double) totalTime / (n * 1000000);
        System.out.println("Время выполнения " + avgTime + " мс");
    }
}