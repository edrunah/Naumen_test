import java.util.PriorityQueue;
import java.util.Queue;

public class MachuPikchuNavigator implements Navigator {

    public char[][] searchRoute(char[][] map) {

        //поиск начала и конца
        int[] beginCoordinates = searchForChar(map, '@');
        int beginX = beginCoordinates[0];
        int beginY = beginCoordinates[1];
        int[] endCoordinates = searchForChar(map, 'X');
        int endX = endCoordinates[0];
        int endY = endCoordinates[1];
        if(beginX < 0 || beginY < 0 || endX < 0 || endY < 0) {
            System.err.println("В заданной карте нет начала или конца");
            return null;
        }
        int rows = map.length;
        int columns = map[0].length;

        //обход массива методом A*
        char [][] map1 = new char[rows][columns];
        for(int i = 0; i < rows; i++) {
            map1[i] = map[i].clone();
        }
        Node beginNode = new Node(beginX, beginY, 0 , 0);
        beginNode.setParentNode(null);
        Node endNode = new Node(endX, endY, 0, 0);

        Queue<Node> queue = new PriorityQueue<>();
        queue.add(beginNode);

        int[] dx = {-1,  0, 0, 1};
        int[] dy = { 0, -1, 1, 0};
        while(endNode.getParentNode() == null) {
            if (queue.isEmpty()) {
                return null; // если открытый список пуст, а выход не найден
            }
            Node currentNode = queue.poll();
            map1[currentNode.getX()][currentNode.getY()] = 'c';
            for (int i = 0; i < 4; i++) {
                int newX = currentNode.getX() + dx[i];
                int newY = currentNode.getY() + dy[i];
                boolean outOfMap = newX < 0 || newY < 0 || newX > rows - 1 || newY > columns - 1;
                if (outOfMap) continue;

                if (map1[newX][newY] == '.') {
                    map1[newX][newY] = 'o';
                    int g = currentNode.getG() + 1;
                    int h = Math.abs(endX - newX) + Math.abs(endY - newY);
                    Node newOpenNode = new Node(newX, newY, g, h);
                    newOpenNode.setParentNode(currentNode);
                    queue.add(newOpenNode);
                }
                if (map1[newX][newY] == 'X') {
                    endNode.setParentNode(currentNode);
                    break;
                }
            }
        }

        //заполнение плюсами
        char [][] mapOfPluses = new char[rows][columns];
        for(int i = 0; i < rows; i++) {
            mapOfPluses[i] = map[i].clone();
        }
        Node plusNode = endNode.getParentNode();
        while (plusNode != beginNode) {
            mapOfPluses[plusNode.getX()][plusNode.getY()] = '+';
            plusNode = plusNode.getParentNode();
        }
        return mapOfPluses;
    }

    private void printArr(char[][] arr) {
        int rows = arr.length;
        int columns = arr[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[] searchForChar(char[][] arr, char symbol) {
        int rows = arr.length;
        int columns = arr[0].length;
        int[] coordinates = new int[2];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (arr[i][j] == symbol) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return coordinates;
                }
            }
        }
        coordinates[0] = -1;
        coordinates[1] = -1;
        return coordinates;
    }
}
