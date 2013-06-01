package tuwien.big.formel0.entities;

import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import tuwien.big.formel0.picasa.IRaceDriverService;
import tuwien.big.formel0.picasa.IRaceDriverServiceImpl;
import tuwien.big.formel0.picasa.RaceDriver;

/**
 * a builtin converter for enum from JSF
 *
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
@ManagedBean
@ApplicationScoped
public class Data {

    IRaceDriverService raceDriverService;

    /**
     * Returns all possible sexes
     *
     * @return sexes
     */
    public Sex[] getSexes() {
        return Sex.values();
    }

    public RaceDriver[] getRaceDrivers() {
        raceDriverService = IRaceDriverServiceImpl.getInstance();
        ArrayList<RaceDriver> drivers = null;
        drivers = (ArrayList<RaceDriver>) raceDriverService.getRaceDrivers();

        RaceDriver[] driverArray = drivers.toArray(new RaceDriver[drivers.size()]);

        return driverArray;

    }
}