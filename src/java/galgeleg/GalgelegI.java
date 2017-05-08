/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package galgeleg;

import brugerautorisation.data.Bruger;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Martin
 */
@WebService
public interface GalgelegI // extends java.rmi.Remote
{
    
//    @WebMethod public String synligtOrd() throws java.rmi.RemoteException;
//    @WebMethod public void gætBogstav(String ord) throws java.rmi.RemoteException;
//    @WebMethod public String log() throws java.rmi.RemoteException;
//    @WebMethod public String logWeb() throws java.rmi.RemoteException;
//    @WebMethod public boolean spilSlut() throws java.rmi.RemoteException;
//    @WebMethod public void nulstil() throws java.rmi.RemoteException;
//    @WebMethod public String ordet() throws java.rmi.RemoteException;
//    @WebMethod boolean hentBruger(String brugernavn, String password) throws java.rmi.RemoteException;
//
    
    @WebMethod public String synligtOrd(String brugerID);
    @WebMethod public void gætBogstav(String ord, String brugerID);
    @WebMethod public String log(String brugerID);
    @WebMethod public String logWeb(String brugerID);
    @WebMethod public boolean spilSlut();
    @WebMethod public void nulstil(String brugerID);
    @WebMethod public String ordet(String brugerID);
    @WebMethod boolean hentBruger(String brugernavn, String password);
    @WebMethod void playerCheck(String brugernavn);
    @WebMethod public Boolean isGameStarted(String brugerID);
    @WebMethod public void newMulti(String brugernavn);
    @WebMethod public ArrayList<String> getMultiListNames();
    @WebMethod public ArrayList joinMulti(String lobbyName, String brugerID);
    @WebMethod public void startGame(String brugerID);
    @WebMethod public String gætBogstavMultiOgLog(String ord, String brugernavn);
    @WebMethod public Boolean isContinueAvailable(String bruger);
    @WebMethod public void leaveLobby(String brugerID);
    @WebMethod public String multiLog(String brugerID);
    @WebMethod public String clearLobby(String brugerID);
    @WebMethod public boolean isMyMultiActive(String brugerID);
    @WebMethod public String isMyMultiOver(String brugerID);
    @WebMethod public Bruger login(String brugerID, String password);
    @WebMethod public ArrayList<scoreDTO> getScores();
    @WebMethod boolean enoughPlayers(String brugerID);
    @WebMethod public String multiLogWeb(String brugerID);
    @WebMethod public String isMyMultiOverWithoutHighscore(String brugerID);
    @WebMethod public ArrayList peopleInLobby(String brugerID);
    
    
}
