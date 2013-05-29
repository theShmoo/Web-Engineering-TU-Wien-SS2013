package test.entities;

/*
 * Copyright 2013 david.
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
import javax.validation.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.AbstractTestInitializer;
import tuwien.big.formel0.entities.Player;
import tuwien.big.formel0.entities.Sex;
import tuwien.big.formel0.entities.dao.PlayerDaoJPA;
import tuwien.big.formel0.picasa.RaceDriver;

/**
 *
 * @author David Pfahler
 */
public class PlayerTest extends AbstractTestInitializer{

    private PlayerDaoJPA playerDao;
    private Player validPlayer;
    private RaceDriver avatar;

    public PlayerTest(){}

    @Before
    public void setUp() {
        playerDao = PlayerDaoJPA.getPlayDaoJPAInstance();
        
        avatar = new RaceDriver();
        avatar.setName("TestDriverName");
        avatar.setUrl("TestDriverURL");
        avatar.setWikiUrl("TestDriverWikiUrl");
        
        validPlayer = new Player();
        validPlayer.setAvatar(avatar); //TODO
        validPlayer.setFirstname("TestPlayerFirstname");
        validPlayer.setLastname("TestPlayerLastname");
        validPlayer.setName("TestPlayerName");
        validPlayer.setPassword("TestPlayerPassword1");
        validPlayer.setSex(Sex.MALE);
        validPlayer.setBirthday("09.08.1992");
        
    }

    @After
    public void tearDown() {
        playerDao = null;
        validPlayer = null;
    }

    @Test(expected = ConstraintViolationException.class)
    public void testToPersistInvalidPlayerEntity() {
        Player player = new Player();
        playerDao.create(player);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testToPersistNull() {
        Player player = null;
        playerDao.create(player);
    }
    
    //@Test
    public void testToPersistValidPlayerEntity() {
        
        playerDao.create(validPlayer);
        
        if (playerDao.findByNameAndPassword("TestPlayerName", "TestPlayerPassword1") == null){
            throw new AssertionError("Ned sehr korrekt");
        }
    }
}