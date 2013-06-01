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
package tuwien.big.formel0.picasa;

import com.google.gdata.client.Query;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import tuwien.big.formel0.entities.dao.PlayerDaoJPA;
import tuwien.big.formel0.entities.dao.RaceDriverDaoJPA;

/**
 *
 * @author Johannski
 */
public class IRaceDriverServiceImpl implements IRaceDriverService {

    private static IRaceDriverServiceImpl instance;
    private static RaceDriverDaoJPA raceDriverDao;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(IRaceDriverServiceImpl.class);

    private IRaceDriverServiceImpl() {
        raceDriverDao = RaceDriverDaoJPA.getRaceDriverDaoJPAInstance();
        getRaceDriversFromPicasa();
    }

    public static IRaceDriverServiceImpl getInstance() {
        if (instance == null) {
            instance = new IRaceDriverServiceImpl();
        }
        return instance;
    }

    @Override
    public List<RaceDriver> getRaceDrivers() {
        return raceDriverDao.findAll();
    }

    @Override
    public RaceDriver getById(int id) {
         return raceDriverDao.findById(id);
    }

    private void getRaceDriversFromPicasa() {

        try {
            System.out.println("Searching for all Drivers in Picasa Webalbum");
            PicasawebService service = new PicasawebService("Formel0-Racer");
            URL feedURL = new URL("https://picasaweb.google.com/data/feed/api/user/107302466601293793664");
            //Filtering needed Elements
            Query driverQuery = new Query(feedURL);
            driverQuery.setStringCustomParameter("kind", "photo");
            driverQuery.setStringCustomParameter("tag", "Driver");

            //Retrieving Information
            AlbumFeed albumFeed = service.query(driverQuery, AlbumFeed.class);
            for (PhotoEntry photo : albumFeed.getPhotoEntries()) {
                System.out.println(photo.getDescription().getPlainText());
                RaceDriver driver = new RaceDriver();
                driver.setName(photo.getDescription().getPlainText());
                driver.setUrl(photo.getMediaThumbnails().get(1).getUrl());
                MediaKeywords mediaKeywords = photo.getMediaKeywords();
                if (mediaKeywords != null) {
                    for (String tag : mediaKeywords.getKeywords()) {
                        if (tag.startsWith("wiki:")) {
                            driver.setWikiUrl(tag.substring(5));
                        }
                    }
                }
                raceDriverDao.create(driver);
            }
        } catch (IOException ex) {
            log.debug("Error in parsing information from Picasa "
                    + "to Webapp: " + ex.getMessage());
        } catch (ServiceException ex) {
            log.debug("Error in parsing information from Picasa "
                    + "to Webapp: " + ex.getMessage());
        }
    }
}
