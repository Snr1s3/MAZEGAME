public class BackTracking {
    public int[] startPosition(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int p = 0; p< maze[0].length; p++) {
                if (maze[i][p] == 'E' || maze[i][p] == 'L' || maze[i][p] == 'R' || maze[i][p] == 'D' ||maze[i][p] == 'U') {
                    return new int[]{i, p};
                }
            }
        }
        return null;
    }

     public boolean canFinish(char[][] maze, int x, int y) {
        if (maze[x][y] == 'G') {
            return true;
        }
        maze[x][y] = 'X'; // Mark the current position as visited
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
        for (int i = 0; i < directions.length; i++){
            int newX = x + directions[i][0], newY = y + directions[i][1];
            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && maze[newX][newY] != 'X' && canFinish(maze, newX, newY)) {
                return true;
            }
        }
        return false;
    }
}