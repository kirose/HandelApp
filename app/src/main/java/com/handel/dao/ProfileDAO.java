package com.handel.dao;

import com.handel.model.Profile;
import com.handel.vo.SearchParamsVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Antonio on 01/01/2018.
 */

public class ProfileDAO {
    private static List<Profile> profiles;
    static{
        profiles = new ArrayList<>();
        long idProfile = 0L;
        for (long i = 1; i <= 100; i++) {
            profiles.add(new Profile(idProfile++,"Martin", null, "martin@hotmal.com","5512345678","9.6"));
            profiles.add(new Profile(idProfile++,"Martin Hernandez", null, "martinhernandez@hotmail.com","5512345678","6.6"));
            profiles.add(new Profile(idProfile++,"Francisco Juarez", null, "franciscojuarez@hotmail.com","5512345678","9.6"));
            profiles.add(new Profile(idProfile++,"Hector Avila", null, "hectoravila@hotmail.com","5512345678","9.6"));
            profiles.add(new Profile(idProfile++,"David Sanchez", null, "davidsanchez@hotmail.com","5512345678","10.0"));
            profiles.add(new Profile(idProfile++,"Fernanda Lopez", null, "fernandalopez@hotmail.com","5512345678","9.9"));
            profiles.add(new Profile(idProfile++,"Jessica Fernandez", null, "jessicafernandez@hotmail.com","5512345678","9.6"));
            profiles.add(new Profile(idProfile++,"Victor Ortega", null, "victorortega@hotmail.com","5512345678","8.8"));
            profiles.add(new Profile(idProfile++,"Fabian Peralta", null, "fabianperalta@hotmail.com","5512345678","8.4"));
            profiles.add(new Profile(idProfile++,"Maria Torres", null, "mariatorres@hotmail.com","5512345678","7.6"));
        }

    }
    public static List<Profile> findProfiles(SearchParamsVO searchParamsVO){
        if (searchParamsVO == null){
            return profiles;
        }
        return profiles;
    }
    public static Profile find(Long idProfile){
        return profiles.get(idProfile.intValue());
    }
}
