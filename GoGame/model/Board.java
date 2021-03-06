package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a board in a Go game.
 *
 * @author Martijn Slot
 * @version 1.0
 */
public class Board {

	public int dim;
	Map<Position, Point> points = new HashMap<>();

	/**
	 * constructor of board of size dim * dim, containing only EMPTY fields.
	 * @param dim
	 */

	public Board(int dim) {
		this.dim = dim;
		for (int x = 1; x <= dim; x++) {
			for (int y = 1; y <= dim; y++) {
				this.points.put(new Position(x,y), new Point(Stone.EMPTY));
			}
		}
	}
	
	public int getDim() {
		return dim;
	}
	
	public Map<Position, Point> getPoints() {
		return points;
	}


	/**
	 * Returns true when the point at position exists.
	 * @param position
	 * @return boolean
	 */
	public boolean isPoint(Position pos) {
		return (this.points.containsKey(pos));
	}

	/**
	 * Returns the point at position.
	 * @param position
	 * @return point
	 */
	public Point getPoint(Position pos) {
		return points.get(pos);
	}

	/**
	 * Returns true when the point at position is empty.
	 * @param position
	 * @return boolean
	 */
	public boolean isEmptyPoint(Position pos) {
		return (this.getPoint(pos).getStone() == Stone.EMPTY);
	}

	/**
	 * Sets a point at position.
	 * @param position, s
	 */
	public void setPoint(Position pos, Stone s) {
		points.put(pos, new Point(s));
	}

	/**
	 * Empties a position / removes a stone.
	 * @param position
	 */
	public void removePoint(Position pos) {
		points.put(pos, new Point(Stone.EMPTY));
	}

	/**
	 * Returns a set of 2 (corner), 3 (edge) or 4 (center) neighbours of a specific position
	 * @param pos
	 * @return set
	 */
	public Set<Position> getNeighbours(Position pos){
		Set<Position> neighbours = new HashSet<>();

		for (int i = pos.x - 1; i <= pos.x + 1; i++) {
			Position a = new Position(i, pos.y);
			if (isPoint(a))	neighbours.add(a);
		}
		for (int i = pos.y - 1; i <= pos.y + 1; i++) {
			Position a = new Position(pos.x, i);
			if (isPoint(a))	neighbours.add(a);
		}
		return neighbours;
	} 


	/**
	 * Returns all empty positions surrounding argument position.
	 * @param position
	 * @return set
	 */
	public Set<Position> freePositions(Set<Position> cluster) {
		Set<Position> freePositions = new HashSet<>();

		for(Position clusterpos : cluster){
			for (Position p : getNeighbours(clusterpos)) {
				if (getPoint(p).getStone() == Stone.EMPTY) freePositions.add(p);
			}
		}
		return freePositions;
	} 

	/**
	 * Returns a cluster of defending stone positions in which position pos is situated.
	 * @param position
	 * @return set
	 */
	public Set<Position> defendingCluster(Position pos) {
		Stone defend = getPoint(pos).getStone();
		Set<Position> defendingCluster = new HashSet<>();
		Set<Position> temp = new HashSet<>();

		defendingCluster.add(pos);

		while (temp.size() != defendingCluster.size()){
			for (Position r : defendingCluster) temp.add(r);
			for (Position p : temp) {
				for (Position q : getNeighbours(p)) {
					if(isPoint(q) && getPoint(q).getStone() == defend){
						defendingCluster.add(q);
					}
				}
			}
		}
		return defendingCluster;
	}


	/**
	 * Returns all liberty positions surrounding argument position (even if arguments exists in cluster of samecolor stones).
	 * @param position
	 * @return set
	 */
	public Set<Position> libertyPositions(Position pos) {
		return freePositions(defendingCluster(pos));
	}

	/**
	 * Returns number of liberties of argument (even if arguments exists in cluster of samecolor stones).
	 * @param position
	 * @return int
	 */
	public int numberOfLiberties(Position pos) {
		return libertyPositions(pos).size();
	}


	/**
	 * checks if the placement of a stone on pos is legal
	 * stone is placed outside of the dimensions of the board
	 * stone is placed on an occupied spot (black, white)
	 * @param pos, s
	 * @return boolean
	 */
	public boolean isAllowed(int row, int col) {

		if (!isPoint(new Position(row, col))) {
			System.out.println("Move not allowed: position does not exist on this playing board.");
			return false;
		}
		if (!isEmptyPoint(new Position(row, col))) {
			System.out.println("Move not allowed: position occupied.");
			return false;
		}
		return true;
	}
	
	
	/**
	 * counts the endscore on the board. 
	 * int[0] = score Stone.BLACK 
	 * int[1] = score Stone.WHITE
	 * territory count has been removed because it caused errors.
	 * @return int[]
	 */
	public int[] countScore() {
		int blackScore = 0;
		int whiteScore = 0;
		int[] scores = new int[2];
		for (Position p : points.keySet()) {
			if (points.get(p).getStone() == Stone.BLACK) blackScore += 1;
			else if (points.get(p).getStone() == Stone.WHITE) whiteScore += 1;
			else {
//				Set<Position> a = freePositions(defendingCluster(p)); 
				//TODO territory count
			}
		}

		scores[0] = blackScore;
		scores[1] = whiteScore;
		return scores;
		
	}

	/**
	 * prints a string that used for creating board history
	 * @return String
	 */
	public String toSimpleString() {
		String boardString = "";
		for (int i = 1; i <= dim; i++) {
			for (int j = 1; j <= dim; j++) {
				boardString = boardString + getPoint(new Position(i, j)).getStone().toString();
			}
		}
		return boardString;
	}
	
	/**
	 * Prints a GTUI
	 * @return String
	 */
	public String toString() {
		String s = "  ";
		for (int i = 1; i <= dim; i++) {
			s = s + i + " ";
		}
		s = s + "\n";
		for (int i = 1; i <= dim; i++) {
			String row = "" + i;
			for (int j = 1; j <= dim; j++) {
				row = row + " " + getPoint(new Position(i, j)).getStone().toString();
			}
			s = s + row;
			if (i < dim) {
				s = s + "\n";
			}
		}
		return s;
	}

}
