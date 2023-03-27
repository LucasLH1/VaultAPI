/*
 * Copyright (c) 2002-2023, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *"
 * License 1.0
 */

package fr.paris.lutece.plugins.vaultapi.business;

import fr.paris.lutece.test.LuteceTestCase;

import java.util.Optional;

import java.sql.Date;

/**
 * This is the business class test for the object User
 */
public class UserBusinessTest extends LuteceTestCase
{
    private static final String FIRSTNAME1 = "Firstname1";
    private static final String FIRSTNAME2 = "Firstname2";
    private static final String LASTNAME1 = "Lastname1";
    private static final String LASTNAME2 = "Lastname2";
    private static final String PASSWORD1 = "Password1";
    private static final String PASSWORD2 = "Password2";
    private static final String EMAIL1 = "Email1";
    private static final String EMAIL2 = "Email2";
	private static final Date BIRTHDATE1 = new Date( 1000000l );
    private static final Date BIRTHDATE2 = new Date( 2000000l );
    private static final String FIRM1 = "Firm1";
    private static final String FIRM2 = "Firm2";

	/**
	* test User
	*/
    public void testBusiness(  )
    {
        // Initialize an object
        User user = new User();
        user.setFirstname( FIRSTNAME1 );
        user.setLastname( LASTNAME1 );
        user.setPassword( PASSWORD1 );
        user.setEmail( EMAIL1 );
        user.setBirthdate( BIRTHDATE1 );
        user.setFirm( FIRM1 );

        // Create test
        UserHome.create( user );
        Optional<User> optUserStored = UserHome.findByPrimaryKey( user.getId( ) );
        User userStored = optUserStored.orElse( new User ( ) );
        assertEquals( userStored.getFirstname( ) , user.getFirstname( ) );
        assertEquals( userStored.getLastname( ) , user.getLastname( ) );
        assertEquals( userStored.getPassword( ) , user.getPassword( ) );
        assertEquals( userStored.getEmail( ) , user.getEmail( ) );
        assertEquals( userStored.getBirthdate( ).toString() , user.getBirthdate( ).toString( ) );
        assertEquals( userStored.getFirm( ) , user.getFirm( ) );

        // Update test
        user.setFirstname( FIRSTNAME2 );
        user.setLastname( LASTNAME2 );
        user.setPassword( PASSWORD2 );
        user.setEmail( EMAIL2 );
        user.setBirthdate( BIRTHDATE2 );
        user.setFirm( FIRM2 );
        UserHome.update( user );
        optUserStored = UserHome.findByPrimaryKey( user.getId( ) );
        userStored = optUserStored.orElse( new User ( ) );
        
        assertEquals( userStored.getFirstname( ) , user.getFirstname( ) );
        assertEquals( userStored.getLastname( ) , user.getLastname( ) );
        assertEquals( userStored.getPassword( ) , user.getPassword( ) );
        assertEquals( userStored.getEmail( ) , user.getEmail( ) );
        assertEquals( userStored.getBirthdate( ).toString( ) , user.getBirthdate( ).toString( ) );
        assertEquals( userStored.getFirm( ) , user.getFirm( ) );

        // List test
        UserHome.getUsersList( );

        // Delete test
        UserHome.remove( user.getId( ) );
        optUserStored = UserHome.findByPrimaryKey( user.getId( ) );
        userStored = optUserStored.orElse( null );
        assertNull( userStored );
        
    }
    
    
     

}