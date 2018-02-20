import java.util.LinkedList;
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
        int[] dx = {-1,  0, 0, 1};
        int[] dy = { 0, -1, 1, 0};

        //обход массива в ширину
        char [][] map1 = new char[rows][columns];
        for(int i = 0; i < rows; i++) {
            map1[i] = map[i].clone();
        }

        Node beginNode = new Node(beginX, beginY);
        beginNode.setPreviousNode(null);
        Node endNode = new Node(endX, endY);

        Queue<Node> wayQueue = new LinkedList<>();
        wayQueue.add(beginNode);

        while(endNode.getPreviousNode() == null) {
            Node currentNode = wayQueue.poll();
            if (currentNode == null) {
                return null; // если очередь пуста, а выход не найден
            }
            for (int i = 0; i < 4; i++) {
                    int newX = currentNode.getX() + dx[i];
                    int newY = currentNode.getY() + dy[i];
                    boolean outOfMap = newX < 0 || newY < 0 || newX > rows - 1 || newY > columns - 1;
                    if (outOfMap) continue;

                    if (map1[newX][newY] == '.') {
                        Node newNode = new Node(newX, newY);
                        map1[newX][newY] = 'N';
                        newNode.setPreviousNode(currentNode);
                        wayQueue.add(newNode);
                    }
                    if (map1[newX][newY] == 'N') {
                        continue;
                    }
                    if (map1[newX][newY] == 'X') {
                        endNode.setPreviousNode(currentNode);
                        i = 4;
                    }
            }
        }

        //заполнение плюсами
        char [][] mapOfPluses = new char[rows][columns];
        for(int i = 0; i < rows; i++) {
            mapOfPluses[i] = map[i].clone();
        }

        Node plusNode = endNode.getPreviousNode();
        while (plusNode != beginNode) {
            mapOfPluses[plusNode.getX()][plusNode.getY()] = '+';
            plusNode = plusNode.getPreviousNode();
        }
        return mapOfPluses;
    }


//
//    private void printArr(char[][] arr) {
//        int rows = arr.length;
//        int columns = arr[0].length;
//        for(int i = 0; i < rows; i++) {
//            for(int j = 0; j < columns; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

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
