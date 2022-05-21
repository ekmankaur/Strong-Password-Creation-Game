/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phase3;

/**
 *
 * @author Satender
 */
public class BasicGameComponents {
    
    private int playerScore; 
    private int hackerScore;
    
    public BasicGameComponents()
    {
        playerScore = 0;
        hackerScore = 0;
    }
    
//    setters
    public void setPlayerScore(int p)
    {
        playerScore = p;
    }
    
    public void setHackerScore(int h)
    {
        hackerScore = h;
    }
    
//    getters
    public int getPlayerScore()
    {
        return playerScore;
    }
    
    public int getHackerScore()
    {
        return hackerScore;
    }
}
    
