/**
 * This class represents the Controller of the Servlet
 * @author Lukas Kraenkl, Johannes Deml
 */

import Beans.RacingData;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

    /**
     * Creates a new race and resets all RacingData
     */
    protected void newRace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(true);
        session.setAttribute("raceData",new RacingData());
    }
   
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        RacingData bohne = null;         
        if(session.getAttribute("raceData") == null){
            newRace(request,response);
        }
        if(session.getAttribute("raceData") != null){
            bohne = (RacingData) session.getAttribute("raceData");
        }
        
    }
    
    protected void rollDice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int randomPlayer = createRandomRoll();
        HttpSession session = request.getSession(true);
        RacingData bohne = (RacingData) session.getAttribute("raceData");
        bohne.setDice(randomPlayer);
        
        int randomComputer = createRandomRoll();
        bohne.setDiceComputer(randomComputer);
        
        //calculating new positions
        int playerNewPosition = bohne.getPositionPlayerHuman() + randomPlayer;
        int computerNewPosition = bohne.getPositionPlayerComputer() + randomComputer;
        
        bohne.setExpectedFieldHuman(playerNewPosition);
        bohne.setExpectedFieldComputer(computerNewPosition);
        
        //Getting OilSpills
        List<Integer> oilSpillsList = bohne.getOilSpills();
        
        Integer[] oilSpills = new Integer[oilSpillsList.size()];
        oilSpills = oilSpillsList.toArray(oilSpills);
        
        //Checking for collisions with oil Spills
        for(int i = 0; i<oilSpills.length; i++) {
            if(oilSpills[i] == playerNewPosition) {
                playerNewPosition = 0;
            }
            if(oilSpills[i] == computerNewPosition) {
                computerNewPosition = 0;
            }
        }
        
        bohne.setFieldPlayerHuman(playerNewPosition);
        bohne.setFieldPlayerComputer(computerNewPosition);
        
        bohne.setLeadingPlayer();        
        //TODO setRound useless?
        //Ja doch brauchen wir... wir rufen ja auch immer getRound auf.
        // muss links im Kastel angezeigt werden :)
        bohne.setRound(bohne.getRound()+1);

    }
    /**
     * Creates a random dice roll
     * @return An Integer between 1 and 3
     */
    private int createRandomRoll() {
        return new Random().nextInt(3)+1;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if(action==null) {
            processRequest(request,response);
        }
        else if(action.equals("new")){
            newRace(request,response);
        }
        else if(action.equals("rolldice")){
            rollDice(request,response);          
        }
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/table.jsp");
        dispatcher.forward(request,response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "A Servlet for the Racing Logic";
    }// </editor-fold>
}
