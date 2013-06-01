/*
 * Copyright 2013 Johannski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tuwien.big.formel0.converter;

import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.slf4j.LoggerFactory;
import tuwien.big.formel0.picasa.IRaceDriverService;
import tuwien.big.formel0.picasa.IRaceDriverServiceImpl;
import tuwien.big.formel0.picasa.RaceDriver;

/**
 *
 * @author Johannski
 */
@FacesConverter(forClass = tuwien.big.formel0.picasa.RaceDriver.class)
public class RaceDriverConverter implements Converter {

     private static final org.slf4j.Logger log = LoggerFactory.getLogger(RaceDriverConverter.class);
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) throws ConverterException {
        log.debug("Converter got called- Convert to Object: " + string);
        int input = Integer.parseInt(string);
        IRaceDriverService rds = IRaceDriverServiceImpl.getInstance();
        return rds.getById(input);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) throws ConverterException {
        log.debug("Converter got called- Convert to String");
        if (o != null) {
            if (o.getClass().equals(RaceDriver.class)) {
                RaceDriver driver = (RaceDriver) o;
                return String.valueOf(driver.getId());
            }
        }
        return "0";
    }
}
