/**
 * This class represents the Controller of the Servlet
 * @author Lukas Kraenkl
 */

import Beans.RacingData;
import java.io.IOException;
import java.io.PrintWriter;
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
     * Creates a new race und resets all RacingData
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
        // Generate Random number and 
        int random = new Random().nextInt(3)+1;
        HttpSession session = request.getSession(true);
        RacingData bohne = (RacingData) session.getAttribute("raceData");
        bohne.setDice(random);
        
        // TODO 
        // Der Spieler fährt weiter.
        // Es muss überprüft werden, ob das "neue" Feld ein Ölfleck ist, wenn ja dann retour an Start
        // Es muss der Computer würfeln
        // Es muss überprüft werden, ob das "neue" Feld ein Ölfleck ist, wenn ja dann retour an Start
        // Es muss der Führende ermittelt und in der Bean aktualisiert werden
        
        bohne.setRound(bohne.getRound()+1);

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
        return "Short description";
    }// </editor-fold>
}
