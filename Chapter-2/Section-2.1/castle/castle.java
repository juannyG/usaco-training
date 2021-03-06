/*
ID: jmg20482
LANG: JAVA
TASK: castle
*/

import java.io.*;
import java.lang.*;
import java.util.*;
import java.awt.Point;

public class castle {
  
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new FileReader("castle.in"));
    PrintWriter out = new PrintWriter(new FileWriter("castle.out"));
    
    StringTokenizer dimensions = new StringTokenizer(in.readLine());
    final int m = Integer.parseInt(dimensions.nextToken());
    final int n = Integer.parseInt(dimensions.nextToken());
    
    int[][] castleModules = new int[n][m];
    for (int i=0; i < n; ++i) {
      StringTokenizer inputCastleRow = new StringTokenizer(in.readLine());
      for (int j=0; j < m; ++j) {
        castleModules[i][j] = Integer.parseInt(inputCastleRow.nextToken());
      }
    }
    
    CastleManager castleLayout = new CastleManager(castleModules, n, m);
    castleLayout.findRooms();
    castleLayout.findWallToRemove();
    int numRooms = castleLayout.getNumberOfRooms();
    int maxRoomSize = castleLayout.getMaxRoomSize();
    int newMaxRoomSize = castleLayout.getNewMaxSize();
    Point wallModule = castleLayout.getModuleToRemove();
    String wallDirection = castleLayout.getWallRemovalDirection();
    out.println(numRooms);
    out.println(maxRoomSize);
    out.println(newMaxRoomSize);
    out.println(((int)wallModule.getX()+1) + " " + ((int)wallModule.getY()+1) + " " + wallDirection);
    
    in.close();
    out.close();
    System.exit(0);
  }
}

class CastleManager {
  private static final int west  = 0x01;
  private static final int north = 0x02;
  private static final int east  = 0x04;
  private static final int south = 0x08;
  
  private int[][] castleModules;
  private List<List<Point>> rooms;
  private List<Point> walls;
  private int newMaxSize;
  private Point moduleBorderingWallToRemove;
  private int wallToRemoveDirection;
  private int n;
  private int m;
  
  public CastleManager(int[][] castleModules, int n, int m) {
    this.castleModules = castleModules;
    this.rooms = new ArrayList();
    this.walls = new ArrayList();
    this.newMaxSize = 0;
    this.moduleBorderingWallToRemove = new Point();
    this.wallToRemoveDirection = 0;
    this.n = n;
    this.m = m;
  }
  
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // Find attributes about rooms and their sizes
  public int getNewMaxSize() {
    return this.newMaxSize;
  }
  
  public Point getModuleToRemove() {
    return this.moduleBorderingWallToRemove;
  }
  
  public String getWallRemovalDirection() {
    if ((this.wallToRemoveDirection & this.north) == this.north)
      return "N";
    else if ((this.wallToRemoveDirection & this.east) == this.east)
      return "E";
    else if ((this.wallToRemoveDirection & this.south) == this.south)
      return "S";
    else if ((this.wallToRemoveDirection & this.west) == this.west)
      return "W";
    
    return ""; // This should never happen!
  }
  
  public int getNumberOfRooms() {
    return this.rooms.size();
  }
  
  public int getMaxRoomSize() {
    int max = 0;
    for (List room : this.rooms)
      max = (room.size() > max) ? room.size() : max;
    return max;
  }
  
  public int getRoomOfTile(Point tile) {
    int roomIdx=0;
    for (List<Point> room : this.rooms) {
      for (Point roomTile : room) {
        if (tile.equals(roomTile))
          return roomIdx;
      }
      ++roomIdx;
    }
//    System.out.println("No room found");
    return 0; // This should never happen!
  }
  
  public int getSizeOfRoomByTile(Point tile) {
    for (List<Point> room : this.rooms) {
      for (Point roomTile : room) {
        if (tile.equals(roomTile))
          return room.size();
      }
    }
//    System.out.println("No room size by tile found");
    return 0; // This should never happen!
  }
  
  public boolean[] defineWalls(int castleTile, int i, int j) {
    boolean[] wallDirections = new boolean[2];
    wallDirections[0] = (i!=0 && (castleTile & this.north)==this.north);
    wallDirections[1] = (j!=(this.m-1) && (castleTile & this.east)==this.east);
    return wallDirections;
  }
  
  // If you start at the southern most point, search south to north for each column from west to east,
  // You only need to check the north & east of each castle module for walls.
  public void findWallToRemove() {
//    System.out.println(this.n + " " + this.m);
    int minRoomSizeToFind = (this.n * this.m) / this.rooms.size();
    HashMap<Integer, List<Integer>> roomsBorderingRoom = new HashMap();
    for (int j=0; j < this.m; ++j) {
      for (int i=(this.n-1); i >= 0; --i) {
        // Where are we now?
        Point currentTile = new Point(i,j);
        int currentRoom = this.getRoomOfTile(currentTile);
        int currentModule = this.castleModules[i][j];
        
        if (this.rooms.get(currentRoom).size() <= minRoomSizeToFind && this.newMaxSize > 0)
          continue;
        
        // Who has a wall next to this tile?
        boolean[] wallDirections = new boolean[2];
        wallDirections[0] = (i!=0 && (currentModule & this.north)==this.north);
        wallDirections[1] = (j!=(this.m-1) && (currentModule & this.east)==this.east);
        
        // No need to check this module, it has no walls we're interested in...
        if (!wallDirections[0] && !wallDirections[1])
          continue;
        
        // Get the stats we need for the current room...
        int currentRoomSize = this.getSizeOfRoomByTile(currentTile);
        List<Integer> roomsBorderingCurrentRoom = roomsBorderingRoom.containsKey(currentRoom) ? roomsBorderingRoom.get(currentRoom) : new ArrayList();
        
        // Check the east wall
        if (wallDirections[1]) {
          Point borderingTile = new Point(i,j+1);
          int borderingRoom = this.getRoomOfTile(borderingTile);
          List<Integer> roomsBorderingBorderRoom = roomsBorderingRoom.containsKey(borderingRoom) ? roomsBorderingRoom.get(borderingRoom) : new ArrayList();
          
          boolean bHasComparedThisRoom = (roomsBorderingCurrentRoom.contains(borderingRoom) || roomsBorderingBorderRoom.contains(currentRoom));
          boolean bIsTheSameRoom = (borderingRoom == currentRoom);
          
          roomsBorderingCurrentRoom.add(borderingRoom);
          roomsBorderingRoom.put(currentRoom, roomsBorderingCurrentRoom);
          
          // Has the bordering room already been checked?
          if (!bHasComparedThisRoom && !bIsTheSameRoom) {
            int borderingRoomSize = this.getSizeOfRoomByTile(borderingTile);
            int newRoomSize = borderingRoomSize + currentRoomSize;
            if (this.newMaxSize < newRoomSize) {
              this.newMaxSize = newRoomSize;
              this.moduleBorderingWallToRemove = new Point(i,j);
              this.wallToRemoveDirection = this.east;
            }
          }
        }
        
        // Check the northern wall
        if (wallDirections[0]) {
          Point borderingTile = new Point(i-1,j);
          int borderingRoom = this.getRoomOfTile(borderingTile);
          List<Integer> roomsBorderingBorderRoom = roomsBorderingRoom.containsKey(borderingRoom) ? roomsBorderingRoom.get(borderingRoom) : new ArrayList();
          
          boolean bHasComparedThisRoom = (roomsBorderingCurrentRoom.contains(borderingRoom) || roomsBorderingBorderRoom.contains(currentRoom));
          boolean bIsTheSameRoom = (borderingRoom == currentRoom);
          
          roomsBorderingCurrentRoom.add(borderingRoom);
          roomsBorderingRoom.put(currentRoom, roomsBorderingCurrentRoom);
          
          // Has the bordering room already been checked?
          if (!bHasComparedThisRoom && !bIsTheSameRoom) {
            int borderingRoomSize = this.getSizeOfRoomByTile(borderingTile);
            int newRoomSize = borderingRoomSize + currentRoomSize;
            if (this.newMaxSize <= newRoomSize) {
              this.newMaxSize = newRoomSize;
              this.moduleBorderingWallToRemove = new Point(i,j);
              this.wallToRemoveDirection = this.north;
            }
          }
        }
      }
    }
  }
  
  /////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // Group together the tiles as a set of rooms
  public void findRooms() {
    for (int i=0; i < n; ++i) {
      for (int j=0; j < m; ++j) {
        Point tile = new Point(i, j);
        if(!this.hasSeenTile(tile))
        {
          List<Point> room = new ArrayList();
          findNeighbors(room, castleModules[i][j], i, j);
          TileComparator comp = new TileComparator();
          Collections.sort(room, comp);
          this.rooms.add(room);
        }
      }
    }
  }
  
  private boolean hasSeenTile(Point tile) {
    for (List<Point> room : this.rooms) {
      if (room.contains(tile))
        return true;
    }
    return false;
  }
  
  private void findNeighbors(List<Point> room, int castleTile, int i, int j) {
    // Add the current point to the room list or return if we've seen it before
    Point tile = new Point(i,j);
    if (room.contains(tile))
      return;
    room.add(tile);

    // Check for border/wall conditions
    if (i!=0 && (castleTile & this.north)!=this.north)
      findNeighbors(room, this.castleModules[i-1][j], i-1, j);
    if (i!=(this.n-1) && (castleTile & this.south)!=this.south)
      findNeighbors(room, this.castleModules[i+1][j], i+1, j);
    if (j!=0 && (castleTile & this.west)!=this.west)
      findNeighbors(room, this.castleModules[i][j-1], i, j-1);
    if (j!=(this.m-1) && (castleTile & this.east)!=this.east)
      findNeighbors(room, this.castleModules[i][j+1], i, j+1);
  }
}

class TileComparator implements Comparator<Point>{
  @Override
  public int compare(Point tile1, Point tile2) {
    int tile1X = (int)tile1.getX();
    int tile1Y = (int)tile1.getY();
    
    int tile2X = (int)tile2.getX();
    int tile2Y = (int)tile2.getY();
    
    if (tile1Y == tile2Y) {
      if (tile1X > tile2X)
        return -1;
      else
        return 1;
    }
    else if (tile1Y < tile2Y) {
      return -1;
    }
    else {
      return 1;
    }
  }
}
