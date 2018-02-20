public class RandomMap {

    public char[][] generateMap() {
        int sizeX = randomInt(5, 1000);
        int sizeY = randomInt(5, 1000);
        char[][] map = new char[sizeX][sizeY];
        for (int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = randomChar();
            }
        }
        int beginX = randomInt(0, sizeX);
        int beginY = randomInt(0, sizeY);
        int endX = randomInt(0, sizeX);
        int endY = randomInt(0, sizeY);
        map[beginX][beginY] = '@';
        map[endX][endY] = 'X';

        return map;
    }

    private int randomInt(int min, int max) {
        double i = Math.random();
        return min + (int)((max - min) * i);
    }

    private char randomChar() {
        int i = randomInt(1,5);
        switch (i) {
            case 1: return '.';
            case 2: return '.';
            case 3: return '.';
            case 4: return '#';
        }
        return '?';
    }

}
