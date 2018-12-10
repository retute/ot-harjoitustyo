
package lomabudjetti.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private String username;
    private List<Holiday> holidays;
    
    public User(String username) {
        this.username = username;
        holidays = new ArrayList<>();
    }
    
    public void setUserName(String name) {
        this.username = name;
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    /**
     * Metodi lisää käyttäjälle listan
     *
     * 
     * @return lista käyttäjän lomista
     */
    public void setList(List<Holiday> list) {
    	this.holidays = list;
    }
    
    /**
     * Metodi palauttaa listan käyttäjän lomista.
     * 
     * @return lista käyttäjän lomista
     */
    public List<Holiday> getHolidays() {
    	return this.holidays;
    }
    
    /**
     * Metodi etsii käyttäjän lomalistasta tietyn loman loman id-tunnuksen avulla. Jos loma löytyy, niin metodi palauttaa lomakohtee. Muuten metodi palauttaa null. 
     *
     * @param id Loman tunnus
     * 
     * @return id-tunnusta vastaavan loman kohde tai null
     */
    public String getHoliday(int id) {
    	for(Holiday hol : holidays) {
    		if (hol.getId() == id) {
    			return hol.getDestination();
    		}
    	}
    	return null;
    }
    
    
    
}
