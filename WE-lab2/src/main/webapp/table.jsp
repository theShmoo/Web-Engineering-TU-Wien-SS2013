<%-- 
    Document   : table jsp-File 
    Created on : 18.04.2013, 09:10:11
    Author     : Lukas Kraenkl
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<jsp:useBean id="raceData" scope="session" class="Beans.RacingData"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="de" xml:lang="de">
    <head>
        <title xml:lang="de">Formel 0 - Spielen</title>
        <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
        <link rel="stylesheet" type="text/css" href="styles/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="container">
            <div id="bordercontainer">
                <div id="header">
                    <div id="header_left"><h1 class="accessibility">Formel 0</h1></div>
                    <div id="header_right"></div>
                </div>
                <div id="navigation">
                    <h2 class="accessibility">Navigation</h2>
                    <ul title="Navigation">
                        <li><a id="startNewGame" href="ControllerServlet?action=new" tabindex="1">Neues Spiel</a></li>
                        <li><a href="#" tabindex="2">Ausloggen</a></li>
                    </ul>
                </div>
                <div id="main-area">
                    <div class="info">
                        <h2>Spielinformationen</h2>
                        <table summary="Diese Tabelle zeigt Informationen zum aktuellen Spiel">
                            <tr><th id="leaderLabel" class="label">F&uuml;hrender</th><td id="leader" class="data"><%= raceData.getLeadingPlayer() %></td></tr>
                            <tr><th id="roundLabel" class="label">Runde</th><td id="round" class="data"><%= raceData.getRound() %></td></tr>
                            <tr><th id="timeLabel" class="label">Zeit</th><td id="time" class="data"><%= new SimpleDateFormat("mm:ss").format(raceData.getTime().getTime())%></td></tr>
                            <tr><th id="computerScoreLabel" class="label">W&uuml;rfelergebnis <em><%= raceData.getComputerPlayer() %></em></th><td id="computerScore" class="data"><%= raceData.getDiceComputer()%></td></tr>
                        </table>  
                        <h2>Spieler</h2>
                        <table summary="Diese Tabelle listet die Namen der Spieler auf">
                            <tr><th id="player1NameLabel" class="label">Spieler 1</th><td id="player1Name" class="data"><%= raceData.getHumanPlayer() %></td></tr>
                            <tr><th id="player2NameLabel" class="label">Spieler 2</th><td id="player2Name" class="data"><%= raceData.getComputerPlayer() %></td></tr>
                        </table>    	  
                    </div>
                    <div class="field">
                        <h2 class="accessibility">Spielbereich</h2>
                        <ol id="road">
                            <li id="start_road">
                                <span class="accessibility">Startfeld</span>
                                <span id="player1">
                                    <span class="accessibility"><em>Spieler 1</em></span>
                                </span>
                                <span id="player2">
                                    <span class="accessibility"><em>Spieler 2</em></span>
                                </span>
                            </li>
                            <li class="empty_road" id="road_1">
                                <span class="accessibility">Feld 2</span>
                            </li>
                            <li class="oil_road" id="road_2">
                                <span class="accessibility">Feld 3</span>
                            </li>
                            <li class="empty_road" id="road_3">
                                <span class="accessibility">Feld 4</span>
                            </li>
                            <li class="empty_road" id="road_4">
                                <span class="accessibility">Feld 5</span>
                            </li>
                            <li class="oil_road" id="road_5">
                                <span class="accessibility">Feld 6</span>
                            </li>
                            <li id="finish_road">
                                <span class="accessibility">Zielfeld</span>
                            </li>
                        </ol>
                    </div>
                   
                    <div class="player">
                        <h2 class="accessibility">W&uuml;rfelbereich</h2>
                        <a id="dice" href="ControllerServlet?action=rolldice" tabindex="4">
                            <img id="diceImage" src="img/wuerfel<%= raceData.getDice() %>.png" alt="W&uuml;rfel mit der Augenzahl <%= raceData.getDice() %>" />	
                        </a>
                    </div>
                </div>
            </div>
            <div id="footer">
                &copy; 2013 Formel 0
            </div>
        </div>

        <script type="text/javascript">
            //<![CDATA[
            
            getDivId = function(num) {
                if(num <= 0) {
                    return "#start_road";
                }
                if(num >= 6) {
                    return "#finish_road";
                }
                return "#road_"+num;
            };
            // call this function once before starting the animations
            function prepareAnimation() {
                $("#player1").appendTo(getDivId("<%= raceData.getLastPositionPlayerHuman() %>"));
                $("#player2").appendTo(getDivId("<%= raceData.getLastPositionPlayerComputer()%>"));
                $("#animationDone").remove();
            }
            
            // call this function once after all animations have finished
            function completeAnimation() {
                var div = $(document.createElement('div'));
                div.attr('id', 'animationDone');
                div.addClass('hide');
                $("body").append(div);
            }
            
            $(document).ready(function() {
                prepareAnimation();
                
                var humanExpectedPosition = getDivId("<%= raceData.getExpectedPositionPlayerHuman() %>");
                var humanPosition = getDivId("<%= raceData.getPositionPlayerHuman() %>");
                var computerExpectedPosition = getDivId("<%= raceData.getExpectedPositionPlayerComputer()%>");
                var computerPosition = getDivId("<%= raceData.getPositionPlayerComputer()%>");
                
                driveHuman();
                
                
                function driveHuman() {
                    console.log("humanExpectedPosition: " + humanExpectedPosition + ", humanPosition: " + humanPosition);
                    $("#player1").fadeOut(700, function() {
                        $("#player1").appendTo(humanExpectedPosition);
                        $("#player1").fadeIn(700,checkHumanOnOilSpill);                    
                    });
                }
                
                function checkHumanOnOilSpill() {
                    if(humanExpectedPosition.toString() !== humanPosition.toString()) {
                        console.log("I'm in my nice little function!");
                            $("#player1").fadeOut(700, function() {
                            $("#player1").appendTo(humanPosition);
                            $("#player1").fadeIn(700, driveComputer());                    
                        });
                    } else {
                        driveComputer();
                    }
                }
                
                function driveComputer() {
                    $("#player2").fadeOut(700, function() {
                        $("#player2").appendTo(computerExpectedPosition);
                        $("#player2").fadeIn(700,checkComputerOnOilSpill);                    
                    });
                }
                
                function checkComputerOnOilSpill() {
                    if(computerExpectedPosition.toString() !== computerPosition.toString()) {
                        console.log("Computer on Oil Spill");
                            $("#player2").fadeOut(700, function() {
                            $("#player2").appendTo(computerPosition);
                            $("#player2").fadeIn(700, completeAnimation());                    
                        });
                    } else {
                        completeAnimation();
                    }
                }
                
            });
            
            //]]>
        </script>

    </body>
</html>
