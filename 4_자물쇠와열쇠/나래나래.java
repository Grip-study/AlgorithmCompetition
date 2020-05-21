class Solution {
    int keyLength, lockLength;
    
    public boolean solution(int[][] key, int[][] lock) {
        this.keyLength = key.length;
        this.lockLength = lock.length;
        
        for (int r = 0; r < 4; r ++) {
            key = rotate(key);
            
            for (int y = 1 - keyLength; y < lockLength; y ++) {
                for (int x = 1 - keyLength; x < lockLength; x++) {
                    int[][] movedKey = moveKey(key, y, x);
                    if (unlock(movedKey, lock)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean unlock(int[][] key, int[][] lock) {
        for (int y = 0; y < lockLength; y ++) {
            for (int x = 0; x < lockLength; x ++) {
                if (key[y][x] + lock[y][x] != 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private int[][] moveKey(int[][] key, int moveY, int moveX) {
        int[][] result = new int[lockLength][lockLength];
        
        for (int y = 0; y < keyLength; y++) {
            for (int x = 0; x < keyLength; x++) {
                int newY = moveY + y;
                int newX = moveX + x;
                
                if (0 <= newY && newY < lockLength && 0 <= newX && newX < lockLength) {
                    result[newY][newX] = key[y][x];
                }
            }
        }
        
        return result;
    }
    
    private int[][] rotate(int[][] arr) {
        int length = arr.length;
        int[][] result = new int[length][length];
        
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                result[y][x] = arr[length - 1 - x][y];
            }
        }
        
        return result;
    }
}

