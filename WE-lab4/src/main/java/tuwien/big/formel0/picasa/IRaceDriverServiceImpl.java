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
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johannski
 */
public class IRaceDriverServiceImpl implements IRaceDriverService{

    @Override
    public List<RaceDriver> getRaceDrivers() throws IOException, ServiceException {
        System.out.println("Searching for all Drivers in Picasa Webalbum");
        List<RaceDriver> drivers = new ArrayList<RaceDriver>();
        PicasawebService service = new PicasawebService("Formel0-Racer");

        URL feedURL = new URL("https://picasaweb.google.com/data/feed/api/user/107302466601293793664");
        Query driverQuery = new Query(feedURL);
        driverQuery.setStringCustomParameter("kind", "photo");
        driverQuery.setStringCustomParameter("tag", "Driver");
        
        AlbumFeed albumFeed = service.query(driverQuery, AlbumFeed.class);
        for(PhotoEntry photo: albumFeed.getPhotoEntries()) {
            System.out.println(photo.getDescription().getPlainText());
            RaceDriver driver = new RaceDriver();
            driver.setName(photo.getDescription().getPlainText());
            driver.setUrl(photo.getMediaThumbnails().get(0).getUrl());
            MediaKeywords mediaKeywords = photo.getMediaKeywords();
            if(mediaKeywords!= null) {
                for(String tag:mediaKeywords.getKeywords()) {
                    if(tag.startsWith("wiki:")) {
                        driver.setWikiUrl(tag.substring(5));
                    }
                }
            }
            drivers.add(driver);
        }
        
        return drivers;
    }
    
}
