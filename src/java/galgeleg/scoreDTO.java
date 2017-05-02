/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galgeleg;

/**
 *
 * @author Magnus
 */
public class scoreDTO  {
    String userID;
    int score;
    
    public scoreDTO(String userID, int score) {
        this.userID = userID;
        this.score = score;
    }
    
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
    
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    
    public String toString() { 
		return userID + " " + score; 
	}
    
}
